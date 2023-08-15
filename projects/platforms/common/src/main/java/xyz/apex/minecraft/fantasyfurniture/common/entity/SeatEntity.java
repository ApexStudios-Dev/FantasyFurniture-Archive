package xyz.apex.minecraft.fantasyfurniture.common.entity;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.helper.EntityHelper;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Optional;

public final class SeatEntity extends Entity
{
    @Nullable private BlockPos pos = null;

    public SeatEntity(EntityType<?> entityType, Level level)
    {
        super(entityType, level);

        blocksBuilding = false;
    }

    @Override
    public void tick()
    {
        super.tick();

        if(getPassengers().isEmpty())
            discard();
        if(pos != null && level().getBlockState(pos).isAir())
            discard();

        for(var passenger : getPassengers())
        {
            if(!canSit(passenger))
                passenger.unRide();
        }
    }

    @Override
    protected void defineSynchedData()
    {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound)
    {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound)
    {
    }

    @Override
    protected boolean canAddPassenger(Entity passenger)
    {
        return super.canAddPassenger(passenger) && canSit(passenger);
    }

    @Override
    protected void addPassenger(Entity passenger)
    {
        super.addPassenger(passenger);
        onStartSitting(passenger);
    }

    @Override
    protected void removePassenger(Entity passenger)
    {
        onStopSitting(passenger);
        super.removePassenger(passenger);
    }

    @Override
    public PushReaction getPistonPushReaction()
    {
        return PushReaction.DESTROY;
    }

    @Override
    public boolean isIgnoringBlockTriggers()
    {
        return false;
    }

    @Nullable
    public static SeatEntity create(Level level, BlockPos pos, Entity entity)
    {
        var seater = determineSeater(entity);

        if(!(level instanceof ServerLevel sLevel))
            return null;
        if(!canSit(seater))
            return null;
        if(!level.noBlockCollision(seater, seater.getDimensions(seater.getPose()).makeBoundingBox(seater.position())))
            return null;

        var blockState = level.getBlockState(pos);

        if(!blockState.isFaceSturdy(level, pos, Direction.UP, SupportType.RIGID))
            return null;

        var seat = FantasyFurniture.SEAT_ENTITY.spawn(sLevel, pos, MobSpawnType.TRIGGERED);

        if(seat == null)
            return null;

        var center = pos.getCenter();
        seat.pos = pos;
        seat.moveTo(center.x, center.y + .25D, center.z, 0F, 0F);

        seater.unRide();
        seater.startRiding(seat);
        return seat;
    }

    public static boolean canSit(EntityType<?> entityType)
    {
        return !entityType.is(FantasyFurniture.SEAT_BLACKLIST);
    }

    public static boolean canSit(Entity entity)
    {
        if(entity.isRemoved() || !entity.getType().isEnabled(entity.level().enabledFeatures()) || entity.isSpectator())
            return false;
        if(EntityHelper.isFakePlayer(entity))
            return false;

        return canSit(entity.getType());
    }

    public static boolean isSittable(BlockState blockState)
    {
        return !blockState.isAir();
    }

    public static InteractionResult onBlockInteract(Level level, Entity seater, InteractionHand hand, BlockPos pos)
    {
        if(hand == InteractionHand.OFF_HAND)
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(!canSit(seater))
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(seater instanceof Player player && player.isSecondaryUseActive())
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(seater instanceof LivingEntity living && (!living.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() || !living.getItemInHand(InteractionHand.OFF_HAND).isEmpty()))
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(!isSittable(level.getBlockState(pos)))
            return InteractionResultHelper.BlockUse.noActionTaken();
        if(findAssociatedSeat(level, seater) != null)
            return InteractionResultHelper.BlockUse.noActionTaken();

        var seat = create(level, pos, seater);

        if(seat == null)
            return InteractionResultHelper.BlockUse.noActionTaken();

        return InteractionResultHelper.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
    }

    public static InteractionResult onEntityInteract(Level level, Player player, InteractionHand hand, Entity entity)
    {
        var seat = findAssociatedSeat(level, entity);

        if(seat != null)
        {
            entity.unRide();
            return InteractionResultHelper.EntityInteract.succeedAndSwingArmBothSides(level.isClientSide);
        }

        return InteractionResultHelper.EntityInteract.noActionTaken();
    }

    @Nullable
    private static SeatEntity findAssociatedSeat(Level level, Entity entity)
    {
        var seats = level.getEntities(FantasyFurniture.SEAT_ENTITY, entity.getBoundingBox().inflate(2D), Predicates.alwaysTrue());
        return seats.isEmpty() ? null : seats.get(0);
    }

    private static Entity determineSeater(Entity seater)
    {
        return getLeashedEntity(seater).orElse(seater);
    }

    private static Optional<Entity> getLeashedEntity(Entity seater)
    {
        for(var entity : seater.level().getEntities(null, seater.getBoundingBox().inflate(10D)))
        {
            if(entity instanceof Mob mob)
            {
                var leashHolder = mob.getLeashHolder();

                if(leashHolder != null && leashHolder.is(seater))
                    return Optional.of(mob);
            }
        }

        return Optional.empty();
    }

    private static void onStartSitting(Entity entity)
    {
        if(entity instanceof Camel camel)
        {
            camel.standUpInstantly();
            camel.sitDown();
        }
        else if(entity instanceof AbstractHorse horse)
            horse.setStanding(false);
        else if(entity instanceof Panda panda)
            panda.sit(true);
        else if(entity instanceof TamableAnimal tamable)
            tamable.setInSittingPose(true);
    }

    private static void onStopSitting(Entity entity)
    {
        if(entity instanceof Camel camel)
            camel.standUp();
        else if(entity instanceof AbstractHorse horse)
            horse.setStanding(true);
        else if(entity instanceof Panda panda)
            panda.sit(false);
        else if(entity instanceof TamableAnimal tamable)
            tamable.setInSittingPose(false);
    }
}

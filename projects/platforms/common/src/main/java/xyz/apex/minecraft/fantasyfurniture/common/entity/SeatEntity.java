package xyz.apex.minecraft.fantasyfurniture.common.entity;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.helper.EntityHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Optional;

public final class SeatEntity extends Entity
{
    private static final String NBT_POS = "Pos";

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
        if(pos == null || !isSittable(level().getBlockState(pos)))
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
        if(compound.contains(NBT_POS, Tag.TAG_COMPOUND))
            pos = NbtUtils.readBlockPos(compound.getCompound(NBT_POS));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound)
    {
        if(pos != null)
            compound.put(NBT_POS, NbtUtils.writeBlockPos(pos));
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
    private static SeatEntity create(Level level, BlockPos pos, Entity entity)
    {
        var seater = determineSeater(entity);

        if(!(level instanceof ServerLevel sLevel))
            return null;

        var seat = FantasyFurniture.SEAT_ENTITY.spawn(sLevel, pos, MobSpawnType.TRIGGERED);

        if(seat == null)
            return null;

        seat.pos = pos;
        seater.unRide();
        seater.startRiding(seat, true);

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
        return blockState.is(FantasyFurniture.SITTABLE);
    }

    public static InteractionResult tryStandUp(Level level, Entity entity)
    {
        if(entity instanceof Player)
            return InteractionResult.PASS;

        var seat = findAssociatedSeat(level, entity);

        if(seat != null)
        {
            entity.unRide();
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    public static InteractionResult trySitDown(Level level, BlockPos pos, Entity entity)
    {
        if(entity instanceof Player player && player.isSecondaryUseActive())
            return InteractionResult.PASS;

        var seater = determineSeater(entity);

        if(!canSit(seater) || !isSittable(level.getBlockState(pos)))
            return InteractionResult.PASS;
        if(findAssociatedSeat(level, seater) != null)
            return InteractionResult.PASS;
        if(!level.noBlockCollision(seater, seater.getDimensions(seater.getPose()).makeBoundingBox(pos.getCenter())))
            return InteractionResult.PASS;

        var seat = create(level, pos, seater);
        return seat != null ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
    }

    @Nullable
    private static SeatEntity findAssociatedSeat(Level level, Entity entity)
    {
        var seats = level.getEntities(FantasyFurniture.SEAT_ENTITY, entity.getBoundingBox().inflate(1D), Predicates.alwaysTrue());
        return seats.isEmpty() ? null : seats.get(0);
    }

    private static Entity determineSeater(Entity seater)
    {
        return getLeashedEntity(seater).filter(SeatEntity::canSit).orElse(seater);
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
        else if(entity instanceof Fox fox)
            fox.setSitting(true);
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
        else if(entity instanceof Fox fox)
            fox.setSitting(false);
        else if(entity instanceof TamableAnimal tamable)
            tamable.setInSittingPose(false);
    }
}

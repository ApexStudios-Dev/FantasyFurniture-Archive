package xyz.apex.minecraft.fantasyfurniture.common.entity;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import org.joml.Vector3f;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.helper.EntityHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.SeatComponent;

import java.util.Optional;

public final class SeatEntity extends Entity
{
    private static final EntityDataAccessor<Optional<BlockPos>> DATA_POS = SynchedEntityData.defineId(SeatEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);

    public SeatEntity(EntityType<?> entityType, Level level)
    {
        super(entityType, level);

        blocksBuilding = false;
        noPhysics = true;
    }

    public void setSittingPos(@Nullable BlockPos pos)
    {
        entityData.set(DATA_POS, Optional.ofNullable(pos));
    }

    public Optional<BlockPos> getSittingPos()
    {
        return entityData.get(DATA_POS).or(() -> Optional.of(blockPosition()));
    }

    public Optional<BlockState> getSittingBlockState()
    {
        return getSittingPos().map(level()::getBlockState);
    }

    public Optional<SeatComponent> getSittingBlockComponent()
    {
        return getSittingBlockState().map(BlockState::getBlock).map(BlockComponentHolder.class::cast).map(componentHolder -> componentHolder.getRequiredComponent(SeatComponent.COMPONENT_TYPE));
    }

    @Override
    public void tick()
    {
        super.tick();

        if(getPassengers().isEmpty())
            discard();
        if(!getSittingBlockState().map(SeatEntity::isSittable).orElse(false))
            discard();

        getSittingPos().ifPresent(pos -> {
            for(var passenger : getPassengers())
            {
                if(!canSit(passenger))
                    passenger.unRide();
            }
        });
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
    protected void defineSynchedData()
    {
        entityData.define(DATA_POS, Optional.empty());
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

    @Override
    protected Vector3f getPassengerAttachmentPoint(Entity entity, EntityDimensions entityDimensions, float f)
    {
        var defaultOffset = entityDimensions.height + (entity instanceof LivingEntity living && living.isBaby() ? -.19F : -.15625F) * f;
        var actualOffset = getSittingBlockComponent().map(component -> component.getSittingOffset(entity, entityDimensions, f, defaultOffset)).orElse(defaultOffset);
        return new Vector3f(0F, actualOffset, 0F);
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
        return blockState.getBlock() instanceof BlockComponentHolder componentHolder && componentHolder.hasComponent(SeatComponent.COMPONENT_TYPE);
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

    @Nullable
    public static SeatEntity findAssociatedSeat(Level level, Entity entity)
    {
        var seats = level.getEntities(FantasyFurniture.SEAT_ENTITY, entity.getBoundingBox().inflate(1D), Predicates.alwaysTrue());
        return seats.isEmpty() ? null : seats.get(0);
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

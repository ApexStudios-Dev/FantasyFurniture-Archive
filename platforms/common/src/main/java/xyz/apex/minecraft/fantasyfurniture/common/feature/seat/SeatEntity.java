package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public final class SeatEntity extends Entity
{
    SeatEntity(EntityType<?> entityType, Level level)
    {
        super(entityType, level);
    }

    @Override
    public void tick()
    {
        super.tick();

        var passengers = getPassengers();
        var blockState = getFeetBlockState();

        if(passengers.isEmpty() || !SeatHelper.isSittable(blockState))
        {
            discard();
            return;
        }

        for(var passenger : passengers)
        {
            if(!(passenger instanceof LivingEntity living) || !canSitAt(living))
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
        return super.canAddPassenger(passenger) && passenger instanceof LivingEntity living && canSitAt(living);
    }

    @Override
    protected void addPassenger(Entity passenger)
    {
        super.addPassenger(passenger);

        if(passenger instanceof LivingEntity living)
            onEntitySitDown(living);
    }

    @Override
    protected void removePassenger(Entity passenger)
    {
        if(passenger instanceof LivingEntity living)
            onEntityStandUp(living);

        super.removePassenger(passenger);
    }

    @Override
    public PushReaction getPistonPushReaction()
    {
        return PushReaction.IGNORE;
    }

    @Override
    public boolean isIgnoringBlockTriggers()
    {
        return true;
    }

    public boolean canSitAt(LivingEntity sitter)
    {
        var level = level();
        var pos = blockPosition();
        var blockState = getFeetBlockState();

        return canSitAt(level, sitter, pos, blockState);
    }

    public boolean canStealSeat(LivingEntity sitter, LivingEntity stealer)
    {
        var level = level();
        var pos = blockPosition();
        var blockState = getFeetBlockState();

        return canStealSeat(level, sitter, stealer, pos, blockState, this);
    }

    private void onEntitySitDown(LivingEntity sitter)
    {
        var level = level();
        var pos = blockPosition();
        var blockState = getFeetBlockState();

        setEntitySittingState(sitter, true);
        SitEvents.SIT_DOWN.post().handle(level, sitter, pos, blockState, this);

        if(blockState.getBlock() instanceof SeatBlock block)
            block.onEntitySitDown(level, sitter, pos, blockState, this);
    }

    private void onEntityStandUp(LivingEntity sitter)
    {
        var level = level();
        var pos = blockPosition();
        var blockState = getFeetBlockState();

        setEntitySittingState(sitter, false);
        SitEvents.STAND_UP.post().handle(level, sitter, pos, blockState, this);

        if(blockState.getBlock() instanceof SeatBlock block)
            block.onEntityStandUp(level, sitter, pos, blockState, this);
    }

    public static boolean canSitAt(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState)
    {
        if(!SeatHelper.isSittable(blockState))
            return false;
        if(!SeatHelper.canSit(sitter))
            return false;

        if(!SitEvents.CAN_SIT_AT.post().handle(level, sitter, pos, blockState))
            return false;
        if(blockState.getBlock() instanceof SeatBlock block)
            return block.canSitAt(level, sitter, pos, blockState);
        return true;
    }

    public static boolean canStealSeat(Level level, LivingEntity sitter, LivingEntity stealer, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
        // you can not sit, dont steal
        if(!canSitAt(level, stealer, pos, blockState))
            return false;
        // current sitter should not be sitting, steal it
        if(!canSitAt(level, sitter, pos, blockState))
            return true;

        if(!SitEvents.CAN_STEAL_SEAT.post().handle(level, sitter, stealer, pos, blockState, seat))
            return false;
        if(blockState.getBlock() instanceof SeatBlock block)
            return block.canStealSeat(level, sitter, stealer, pos, blockState, seat);
        return true;
    }

    private static void setEntitySittingState(LivingEntity entity, boolean sitting)
    {
        // put entities into their "sitting" states
        if(entity instanceof Camel camel)
        {
            if(sitting)
            {
                // if was already sitting, force standing up
                // to replay the animation
                if(camel.isCamelSitting())
                    camel.standUpInstantly();

                camel.sitDown();
            }
            else
                camel.standUp();
        }
        else if(entity instanceof AbstractHorse horse)
        {
            if(sitting)
            {
                // horse was on 2 hind legs
                // "sit" them down
                if(horse.isStanding())
                    horse.setStanding(false);
            }
        }
        else if(entity instanceof Panda panda)
            panda.sit(sitting);
        else if(entity instanceof TamableAnimal tamable)
            tamable.setInSittingPose(sitting);
    }
}

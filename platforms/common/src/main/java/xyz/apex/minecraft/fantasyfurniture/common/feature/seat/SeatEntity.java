package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
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

        var level = level();
        var pos = blockPosition();

        for(var passenger : passengers)
        {
            if(passenger instanceof LivingEntity living)
            {
                if(!Seat.canSitAt(level, living, pos, blockState))
                    living.stopRiding();
            }
            else
                passenger.stopRiding();
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
        return super.canAddPassenger(passenger) && passenger instanceof LivingEntity living && SeatHelper.canSit(living);
    }

    @Override
    protected void removePassenger(Entity passenger)
    {
        if(passenger instanceof LivingEntity living)
            Seat.onEntityStandUp(level(), living, blockPosition(), getBlockStateOn(), this);

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
}

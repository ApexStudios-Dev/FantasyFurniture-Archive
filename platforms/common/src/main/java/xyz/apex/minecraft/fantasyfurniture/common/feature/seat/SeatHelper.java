package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.helper.EntityHelper;

@ApiStatus.NonExtendable
public interface SeatHelper
{
    /**
     * Returns true if given entity can be seated.
     *
     * @param entity Entity to be seated.
     * @return True if entity can be seated.
     */
    static boolean canSit(LivingEntity entity)
    {
        // blacklist for sitting
        if(entity.getType().is(Seat.BLACKLIST))
            return false;
        // hostile sitting is disabled
        if(!Seat.SEAT_HOSTILES && !entity.getType().getCategory().isFriendly())
            return false;
        // dont allow fake players
        if(EntityHelper.isFakePlayer(entity))
            return false;
        // spectators and removed entities are ignored
        if(entity.isSpectator() || entity.isRemoved())
            return false;

        return true;
    }

    /**
     * Returns true if given block state is sittable.
     *
     * @param blockState Block state to be validated.
     * @return True if block state is sittable.
     */
    static boolean isSittable(BlockState blockState)
    {
        return !blockState.isAir() && blockState.is(Seat.SITTABLE);
    }
}

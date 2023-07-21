package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Optional interface for sittable blocks.
 * <p>
 * Used to determine when and how entities sit in your blocks.
 * <p>
 * Implementing this interface on your block, does not directly mark it as being sittable.
 * You must tag your block with {@link Seat#SITTABLE}, in order for it to be a valid sittable block.
 */
public interface SeatBlock
{
    /**
     * Returns true to determine if entity can be seated at the given position.
     *
     * @param level Level entity and block resides within.
     * @param sitter Entity to be seated.
     * @param pos Position to seat entity at.
     * @param blockState The current block state.
     * @return True if entity can be seated.
     */
    default boolean canSitAt(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState)
    {
        return true;
    }

    /**
     * Returns true if entity can steal seat from the currently seated entity.
     *
     * @param level Level entity and block resides within.
     * @param sitter Entity currently sitting.
     * @param stealer Entity trying to steal the seat and to be seated.
     * @param pos Position to seat entity at.
     * @param blockState The current block state.
     * @param seat Seat to be stolen.
     * @return True if entity can steal the seat.
     */
    default boolean canStealSeat(Level level, LivingEntity sitter, LivingEntity stealer, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
        return true;
    }

    /**
     * Called when entity is sat down in this seat.
     *
     * @param level Level entity and block resides within.
     * @param sitter Entity currently sitting.
     * @param pos Seat position.
     * @param blockState The current block state.
     * @param seat Seat to sit in.
     */
    default void onEntitySitDown(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
    }

    /**
     * Called when entity is stood up out of this seat.
     *
     * @param level Level entity and block resides within.
     * @param sitter Entity currently sitting.
     * @param pos Seat position.
     * @param blockState The current block state.
     * @param seat Seat to sit in.
     */
    default void onEntityStandUp(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat)
    {
    }
}

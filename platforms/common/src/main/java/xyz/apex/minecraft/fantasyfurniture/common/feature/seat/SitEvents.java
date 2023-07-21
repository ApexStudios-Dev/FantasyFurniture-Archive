package xyz.apex.minecraft.fantasyfurniture.common.feature.seat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.event.Event;
import xyz.apex.minecraft.apexcore.common.lib.event.EventType;

/**
 * Global events for seats.
 * <p>
 * Similar to {@link SeatBlock} callbacks, but not tied to specific block.
 * <p>
 * See {@link SeatBlock} for more information on each callback.
 */
@ApiStatus.NonExtendable
public interface SitEvents
{
    /**
     * Return true if to determine if entity can be seated at the given position.
     */
    EventType<CanSitAt> CAN_SIT_AT = EventType.create(listeners -> (level, sitter, pos, blockState) -> listeners.isEmpty() || listeners.stream().anyMatch(listener -> listener.handle(level, sitter, pos, blockState)));
    /**
     * Return true if entity can steal seat from currently seated entity.
     */
    EventType<CanStealSeat> CAN_STEAL_SEAT = EventType.create(listeners -> (level, sitter, stealer, pos, blockState, seat) -> listeners.isEmpty() || listeners.stream().anyMatch(listener -> listener.handle(level, sitter, stealer, pos, blockState, seat)));
    /**
     * Called when entity is sat down in given seat.
     */
    EventType<SitDown> SIT_DOWN = EventType.create(listeners -> (level, sitter, pos, blockState, seat) -> listeners.forEach(listener -> listener.handle(level, sitter, pos, blockState, seat)));
    /**
     * Called when is stood up out of given seat.
     */
    EventType<StandUp> STAND_UP = EventType.create(listeners -> (level, sitter, pos, blockState, seat) -> listeners.forEach(listener -> listener.handle(level, sitter, pos, blockState, seat)));

    @ApiStatus.Internal
    static void bootstrap()
    {
    }

    @FunctionalInterface
    @ApiStatus.NonExtendable
    interface CanSitAt extends Event
    {
        boolean handle(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState);
    }

    @FunctionalInterface
    @ApiStatus.NonExtendable
    interface CanStealSeat extends Event
    {
        boolean handle(Level level, LivingEntity sitter, LivingEntity stealer, BlockPos pos, BlockState blockState, SeatEntity seat);
    }

    @FunctionalInterface
    @ApiStatus.NonExtendable
    interface SitDown extends Event
    {
        void handle(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat);
    }

    @FunctionalInterface
    @ApiStatus.NonExtendable
    interface StandUp extends Event
    {
        void handle(Level level, LivingEntity sitter, BlockPos pos, BlockState blockState, SeatEntity seat);
    }
}

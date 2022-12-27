package xyz.apex.minecraft.fantasyfurniture.shared.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.entity.Seat;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface SeatBlock
{
    private Block asBlock()
    {
        return (Block) this;
    }

    default void seatEntityAfterFallOn(BlockGetter level, Entity entity, BiConsumer<BlockGetter, Entity> superFallOn)
    {
        var pos = entity.blockPosition();

        if(entity instanceof Player || !(entity instanceof LivingEntity) || !FantasyFurniture.isEntityValidForSeat(entity) || isSeatOccupied(entity.level, pos))
        {
            if(entity.isSuppressingBounce())
            {
                superFallOn.accept(level, entity);
                return;
            }

            var vec = entity.getDeltaMovement();

            if(vec.y < 0D)
            {
                var d = entity instanceof LivingEntity ? 1D : .8D;
                entity.setDeltaMovement(vec.x, -vec.y * (double) .66F * d, vec.z);
            }
            return;
        }

        if(!level.getBlockState(pos).is(asBlock())) return;
        sitDown(entity.level, pos, entity);
    }

    default InteractionResult useSeat(Level level, BlockPos pos, Player player)
    {
        if(player.isShiftKeyDown()) return InteractionResult.PASS;

        var seats = level.getEntitiesOfClass(Seat.class, new AABB(pos));

        if(!seats.isEmpty())
        {
            var seat = seats.get(0);
            var passengers = seat.getPassengers();
            if(!passengers.isEmpty() && passengers.get(0) instanceof Player) return InteractionResult.PASS;

            if(!level.isClientSide)
            {
                seat.ejectPassengers();
                player.startRiding(seat);
            }

            return InteractionResult.SUCCESS;
        }

        var seatEntity = getLeashed(player).orElse(player);
        sitDown(level, pos, seatEntity);
        return InteractionResult.SUCCESS;
    }

    default boolean isSeatOccupied(Level level, BlockPos pos)
    {
        return !level.getEntitiesOfClass(Seat.class, new AABB(pos)).isEmpty();
    }

    static Optional<Entity> getLeashed(Player player)
    {
        var entities = player.level.getEntities((Entity) null, player.getBoundingBox().inflate(10D), e -> true);

        for(var entity : entities)
        {
            if(entity instanceof Mob mob)
            {
                var leashHolder = mob.getLeashHolder();
                if(leashHolder != null && leashHolder.is(player) && FantasyFurniture.isEntityValidForSeat(entity)) return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    static void sitDown(Level level, BlockPos pos, Entity entity)
    {
        if(level.isClientSide) return;
        var seat = new Seat(level, pos);
        seat.setPos(pos.getX() + .5F, pos.getY(), pos.getZ() + .5F);
        level.addFreshEntity(seat);
        entity.startRiding(seat, false);
        if(entity instanceof TamableAnimal tamable) tamable.setInSittingPose(true);
    }
}

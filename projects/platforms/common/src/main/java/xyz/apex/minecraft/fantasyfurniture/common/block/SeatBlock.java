package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public interface SeatBlock
{
    default BlockPos getSittingPos(BlockGetter level, BlockState blockState, BlockPos pos)
    {
        return pos;
    }

    default float getSittingOffset(Entity entity, EntityDimensions dimensions, float f, float defaultOffset)
    {
        return defaultOffset;
    }
}

package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

public final class RoyalOvenBlockEntity extends OvenBlockEntity
{
    public RoyalOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(RoyalFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

public final class DunmerOvenBlockEntity extends OvenBlockEntity
{
    public DunmerOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(DunmerFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

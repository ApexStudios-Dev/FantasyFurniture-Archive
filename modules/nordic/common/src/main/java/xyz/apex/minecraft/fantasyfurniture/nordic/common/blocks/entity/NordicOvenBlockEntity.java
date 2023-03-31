package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public final class NordicOvenBlockEntity extends OvenBlockEntity
{
    public NordicOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(NordicFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

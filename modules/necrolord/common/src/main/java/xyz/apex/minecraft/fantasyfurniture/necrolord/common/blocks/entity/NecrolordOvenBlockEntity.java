package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

public final class NecrolordOvenBlockEntity extends OvenBlockEntity
{
    public NecrolordOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(NecrolordFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

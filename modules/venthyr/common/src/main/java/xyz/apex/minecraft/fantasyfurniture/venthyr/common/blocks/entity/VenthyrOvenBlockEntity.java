package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

public final class VenthyrOvenBlockEntity extends OvenBlockEntity
{
    public VenthyrOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(VenthyrFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

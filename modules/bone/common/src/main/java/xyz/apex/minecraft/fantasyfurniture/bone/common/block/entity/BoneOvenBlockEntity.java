package xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;

public final class BoneOvenBlockEntity extends OvenBlockEntity
{
    public BoneOvenBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(BoneFurnitureSet.BlockEntityTypes.OVEN.get(), pos, blockState);
    }
}

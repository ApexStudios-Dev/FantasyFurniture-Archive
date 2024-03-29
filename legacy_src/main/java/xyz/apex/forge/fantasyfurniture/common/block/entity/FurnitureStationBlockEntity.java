package xyz.apex.forge.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.forge.apexcore.lib.block.entity.BaseBlockEntity;

public final class FurnitureStationBlockEntity extends BaseBlockEntity.WithCustomName
{
	public FurnitureStationBlockEntity(BlockEntityType<? extends FurnitureStationBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState);
	}
}

package xyz.apex.forge.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.forge.apexcore.lib.block.entity.BaseBlockEntity;

public final class SkullBlossomsBlockEntity extends BaseBlockEntity
{
	public SkullBlossomsBlockEntity(BlockEntityType<? extends SkullBlossomsBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState);
	}
}

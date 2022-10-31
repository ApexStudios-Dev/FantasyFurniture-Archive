package xyz.apex.forge.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.MediumInventoryMenu;

public final class CounterBlockEntity extends InventoryBlockEntity
{
	public CounterBlockEntity(BlockEntityType<? extends CounterBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, MediumInventoryMenu.SIZE);
	}
}

package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

public final class OvenBlockEntity extends InventoryBlockEntity
{
	public OvenBlockEntity(BlockEntityType<? extends OvenBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, OvenMenu.SLOT_COUNT);
	}

	public void serverTick(Level level, BlockPos pos, BlockState blockState)
	{
	}
}
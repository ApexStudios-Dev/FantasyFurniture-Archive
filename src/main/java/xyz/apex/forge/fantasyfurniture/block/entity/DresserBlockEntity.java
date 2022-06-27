package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.revamp.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.menu.MediumInventoryMenu;

public final class DresserBlockEntity extends InventoryBlockEntity
{
	public DresserBlockEntity(BlockEntityType<? extends DresserBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, MediumInventoryMenu.SIZE);
	}
}
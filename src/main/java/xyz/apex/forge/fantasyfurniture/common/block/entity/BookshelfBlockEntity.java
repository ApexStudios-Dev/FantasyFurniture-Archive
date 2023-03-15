package xyz.apex.forge.fantasyfurniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.forge.apexcore.lib.block.entity.InventoryBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.menu.BookshelfMenu;

public final class BookshelfBlockEntity extends InventoryBlockEntity
{
	public BookshelfBlockEntity(BlockEntityType<? extends BookshelfBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, BookshelfMenu.SIZE);
	}
}

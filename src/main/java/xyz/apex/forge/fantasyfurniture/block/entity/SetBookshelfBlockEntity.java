package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetBookshelfContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetBookshelfBlockEntity extends InventoryBlockEntity<SetBookshelfContainer>
{
	public SetBookshelfBlockEntity(BlockEntityType<? extends SetBookshelfBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
	{
		super(blockEntityType, pos, blockState, SetBookshelfContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetBookshelfContainer.SIZE);
	}

	@Override
	protected MenuType<SetBookshelfContainer> getContainerType()
	{
		return FFElements.BOOKSHELF_CONTAINER.asMenuType();
	}
}

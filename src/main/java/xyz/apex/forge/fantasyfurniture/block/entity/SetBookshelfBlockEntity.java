package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetBookshelfContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetBookshelfBlockEntity extends InventoryBlockEntity<SetBookshelfContainer>
{
	public SetBookshelfBlockEntity(TileEntityType<? extends SetBookshelfBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetBookshelfContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetBookshelfContainer.SIZE);
	}

	@Override
	protected ContainerType<SetBookshelfContainer> getContainerType()
	{
		return FFElements.BOOKSHELF_CONTAINER.asContainerType();
	}
}

package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetChestContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetChestBlockEntity extends InventoryBlockEntity<SetChestContainer>
{
	public SetChestBlockEntity(TileEntityType<? extends SetChestBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetChestContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetChestContainer.SIZE);
	}

	@Override
	protected ContainerType<SetChestContainer> getContainerType()
	{
		return FFElements.CHEST_CONTAINER.asContainerType();
	}
}

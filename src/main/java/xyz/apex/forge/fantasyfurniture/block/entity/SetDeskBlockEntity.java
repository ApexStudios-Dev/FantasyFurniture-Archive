package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDeskContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDeskBlockEntity extends InventoryBlockEntity<SetDeskContainer>
{
	public SetDeskBlockEntity(TileEntityType<? extends SetDeskBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetDeskContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDeskContainer.SIZE);
	}

	@Override
	protected ContainerType<SetDeskContainer> getContainerType()
	{
		return FFElements.DESK_CONTAINER.asContainerType();
	}
}

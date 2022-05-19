package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDrawerBlockEntity extends InventoryBlockEntity<SetDrawerContainer>
{
	public SetDrawerBlockEntity(TileEntityType<? extends SetDrawerBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetDrawerContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDrawerContainer.SIZE);
	}

	@Override
	protected ContainerType<SetDrawerContainer> getContainerType()
	{
		return FFElements.DRAWER_CONTAINER.asContainerType();
	}
}

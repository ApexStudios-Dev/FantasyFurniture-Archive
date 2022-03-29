package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.DrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFContainers;

public final class DrawerBlockEntity extends InventoryBlockEntity<DrawerContainer>
{
	public DrawerBlockEntity(TileEntityType<? extends DrawerBlockEntity> blockEntityType)
	{
		super(blockEntityType, DrawerContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(15);
	}

	@Override
	protected ContainerType<DrawerContainer> getContainerType()
	{
		return FFContainers.DRAWER_CONTAINER.asContainerType();
	}
}

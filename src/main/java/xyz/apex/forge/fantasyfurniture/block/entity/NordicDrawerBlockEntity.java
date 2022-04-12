package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.NordicDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicDrawerBlockEntity extends InventoryBlockEntity<NordicDrawerContainer>
{
	public NordicDrawerBlockEntity(TileEntityType<? extends NordicDrawerBlockEntity> blockEntityType)
	{
		super(blockEntityType, NordicDrawerContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(NordicDrawerContainer.SIZE);
	}

	@Override
	protected ContainerType<NordicDrawerContainer> getContainerType()
	{
		return Nordic.DRAWER_CONTAINER.asContainerType();
	}
}

package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.NordicChestContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicChestBlockEntity extends InventoryBlockEntity<NordicChestContainer>
{
	public NordicChestBlockEntity(TileEntityType<? extends NordicChestBlockEntity> blockEntityType)
	{
		super(blockEntityType, NordicChestContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(NordicChestContainer.SIZE);
	}

	@Override
	protected ContainerType<NordicChestContainer> getContainerType()
	{
		return Nordic.CHEST_CONTAINER.asContainerType();
	}
}

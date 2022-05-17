package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.NordicWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicWardrobeBlockEntity extends InventoryBlockEntity<NordicWardrobeContainer>
{
	public NordicWardrobeBlockEntity(TileEntityType<? extends NordicWardrobeBlockEntity> blockEntityType)
	{
		super(blockEntityType, NordicWardrobeContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(NordicWardrobeContainer.SIZE);
	}

	@Override
	protected ContainerType<NordicWardrobeContainer> getContainerType()
	{
		return Nordic.WARDROBE_CONTAINER.asContainerType();
	}
}

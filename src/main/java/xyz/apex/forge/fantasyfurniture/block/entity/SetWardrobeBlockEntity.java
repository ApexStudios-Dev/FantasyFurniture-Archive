package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetWardrobeBlockEntity extends InventoryBlockEntity<SetWardrobeContainer>
{
	public SetWardrobeBlockEntity(TileEntityType<? extends SetWardrobeBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetWardrobeContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetWardrobeContainer.SIZE);
	}

	@Override
	protected ContainerType<SetWardrobeContainer> getContainerType()
	{
		return FFElements.WARDROBE_CONTAINER.asContainerType();
	}
}

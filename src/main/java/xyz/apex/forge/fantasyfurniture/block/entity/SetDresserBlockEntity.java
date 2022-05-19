package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.SetDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDresserBlockEntity extends InventoryBlockEntity<SetDresserContainer>
{
	public SetDresserBlockEntity(TileEntityType<? extends SetDresserBlockEntity> blockEntityType)
	{
		super(blockEntityType, SetDresserContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(SetDresserContainer.SIZE);
	}

	@Override
	protected ContainerType<SetDresserContainer> getContainerType()
	{
		return FFElements.DRESSER_CONTAINER.asContainerType();
	}
}

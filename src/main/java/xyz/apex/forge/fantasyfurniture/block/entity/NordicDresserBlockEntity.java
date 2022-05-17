package xyz.apex.forge.fantasyfurniture.block.entity;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.container.NordicDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicDresserBlockEntity extends InventoryBlockEntity<NordicDresserContainer>
{
	public NordicDresserBlockEntity(TileEntityType<? extends NordicDresserBlockEntity> blockEntityType)
	{
		super(blockEntityType, NordicDresserContainer::new);
	}

	@Override
	protected ItemStackHandler createInventoryHandler()
	{
		return new ItemStackHandler(NordicDresserContainer.SIZE);
	}

	@Override
	protected ContainerType<NordicDresserContainer> getContainerType()
	{
		return Nordic.DRESSER_CONTAINER.asContainerType();
	}
}

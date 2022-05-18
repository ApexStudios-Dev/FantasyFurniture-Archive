package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.NordicChestContainer;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

public final class NordicChestContainerScreen extends InventoryContainerScreen<NordicChestContainer>
{
	public NordicChestContainerScreen(NordicChestContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
	{
		super(menu, playerInventory, titleComponent, Registrations.LARGE_STORGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 222;

		super.init();

		inventoryLabelY = imageHeight - 94;
	}
}
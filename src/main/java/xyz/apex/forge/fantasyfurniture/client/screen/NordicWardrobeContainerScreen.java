package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.NordicWardrobeContainer;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

public final class NordicWardrobeContainerScreen extends InventoryContainerScreen<NordicWardrobeContainer>
{
	public NordicWardrobeContainerScreen(NordicWardrobeContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
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

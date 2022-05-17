package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.NordicDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

public final class NordicDresserContainerScreen extends InventoryContainerScreen<NordicDresserContainer>
{
	public NordicDresserContainerScreen(NordicDresserContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
	{
		super(menu, playerInventory, titleComponent, Registrations.MEDIUM_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();

		inventoryLabelY = imageHeight - 94;
	}
}

package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.NordicDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.Registrations;

public final class NordicDrawerContainerScreen extends InventoryContainerScreen<NordicDrawerContainer>
{
	public NordicDrawerContainerScreen(NordicDrawerContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
	{
		super(menu, playerInventory, titleComponent, Registrations.SMALL_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();
	}
}

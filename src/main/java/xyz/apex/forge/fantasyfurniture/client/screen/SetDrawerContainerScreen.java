package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.fantasyfurniture.container.SetDrawerContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDrawerContainerScreen extends InventoryContainerScreen<SetDrawerContainer>
{
	public SetDrawerContainerScreen(SetDrawerContainer menu, Inventory playerInventory, Component titleComponent)
	{
		super(menu, playerInventory, titleComponent, FFElements.SMALL_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();
	}
}

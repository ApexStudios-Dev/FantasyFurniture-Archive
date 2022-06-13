package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.revamp.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.container.SetBookshelfContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetBookshelfContainerScreen extends BaseMenuScreen<SetBookshelfContainer>
{
	public SetBookshelfContainerScreen(SetBookshelfContainer menu, Inventory playerInventory, Component titleComponent)
	{
		super(menu, playerInventory, titleComponent, FFElements.LARGE_STORAGE_TEXTURE);
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

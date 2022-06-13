package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.revamp.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.container.SetDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDresserContainerScreen extends BaseMenuScreen<SetDresserContainer>
{
	public SetDresserContainerScreen(SetDresserContainer menu, Inventory playerInventory, Component titleComponent)
	{
		super(menu, playerInventory, titleComponent, FFElements.MEDIUM_STORAGE_TEXTURE);
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

package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import xyz.apex.forge.fantasyfurniture.container.SetDresserContainer;
import xyz.apex.forge.fantasyfurniture.init.FFElements;

public final class SetDresserContainerScreen extends InventoryContainerScreen<SetDresserContainer>
{
	public SetDresserContainerScreen(SetDresserContainer menu, PlayerInventory playerInventory, ITextComponent titleComponent)
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

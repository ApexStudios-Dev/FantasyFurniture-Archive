package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.BookshelfMenu;

public final class BookshelfMenuScreen extends BaseMenuScreen<BookshelfMenu>
{
	public BookshelfMenuScreen(BookshelfMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, ModElements.LARGE_STORAGE_TEXTURE);
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
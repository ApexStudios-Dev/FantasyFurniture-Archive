package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.CookieJarMenu;

@SideOnly(SideOnly.Side.CLIENT)
public final class CookieJarMenuScreen extends BaseMenuScreen<CookieJarMenu>
{
	public CookieJarMenuScreen(CookieJarMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, ModElements.COOKIE_JAR_STORAGE_TEXTURE);
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
package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.common.menu.CookieJarMenu;

@SideOnly(SideOnly.Side.CLIENT)
public final class CookieJarMenuScreen extends BaseMenuScreen<CookieJarMenu>
{
	public static final ResourceLocation COOKIE_JAR_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/cookie_jar.png");

	public CookieJarMenuScreen(CookieJarMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, COOKIE_JAR_STORAGE_TEXTURE);
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

package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

public final class OvenMenuScreen extends BaseMenuScreen<OvenMenu>
{
	public OvenMenuScreen(OvenMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, ModElements.OVEN_TEXTURE);
	}

	@Override
	protected void init()
	{
		super.init();
		titleLabelX = (imageWidth - font.width(title)) / 2;
	}
}
package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.common.menu.StockingMenu;

@SideOnly(SideOnly.Side.CLIENT)
public final class StockingMenuScreen extends BaseMenuScreen<StockingMenu>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/stocking.png");

	public StockingMenuScreen(StockingMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();
	}
}

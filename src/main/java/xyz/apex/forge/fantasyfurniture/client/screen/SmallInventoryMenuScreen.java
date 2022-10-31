package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.common.menu.SmallInventoryMenu;

public final class SmallInventoryMenuScreen extends BaseMenuScreen<SmallInventoryMenu>
{
	public static final ResourceLocation SMALL_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/small_storage.png");

	public SmallInventoryMenuScreen(SmallInventoryMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, SMALL_STORAGE_TEXTURE);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 166;

		super.init();
	}
}

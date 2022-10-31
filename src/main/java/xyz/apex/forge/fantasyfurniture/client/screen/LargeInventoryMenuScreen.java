package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.common.menu.LargeInventoryMenu;

@SideOnly(SideOnly.Side.CLIENT)
public final class LargeInventoryMenuScreen extends BaseMenuScreen<LargeInventoryMenu>
{
	public static final ResourceLocation LARGE_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/large_storage.png");

	public LargeInventoryMenuScreen(LargeInventoryMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, LARGE_STORAGE_TEXTURE);
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

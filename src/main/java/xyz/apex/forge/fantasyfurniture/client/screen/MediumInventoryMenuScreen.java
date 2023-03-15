package xyz.apex.forge.fantasyfurniture.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.common.menu.MediumInventoryMenu;

@SideOnly(SideOnly.Side.CLIENT)
public final class MediumInventoryMenuScreen extends BaseMenuScreen<MediumInventoryMenu>
{
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/medium_storage.png");

	public MediumInventoryMenuScreen(MediumInventoryMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, MEDIUM_STORAGE_TEXTURE);
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

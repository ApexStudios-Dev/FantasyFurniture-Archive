package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LockboxMenu;

public final class LockboxMenuScreen extends InventoryMenuScreen<LockboxMenu>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FantasyFurniture.ID, "textures/gui/container/lockbox.png");

    public LockboxMenuScreen(LockboxMenu menu, Inventory inventory, Component displayName)
    {
        super(menu, inventory, displayName, BACKGROUND_TEXTURE);
    }

    @Override
    protected void init()
    {
        imageWidth = 176;
        imageHeight = 166;

        super.init();
    }
}

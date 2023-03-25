package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.WardrobeMenu;

public final class WardrobeMenuScreen extends SimpleContainerMenuScreen<WardrobeMenu>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FantasyFurniture.ID, "textures/gui/container/wardrobe.png");

    public WardrobeMenuScreen(WardrobeMenu menu, Inventory inventory, Component displayName)
    {
        super(menu, inventory, displayName, BACKGROUND_TEXTURE);
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

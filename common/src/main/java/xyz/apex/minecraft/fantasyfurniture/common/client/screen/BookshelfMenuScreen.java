package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.BookshelfMenu;

public final class BookshelfMenuScreen extends SimpleContainerMenuScreen<BookshelfMenu>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FantasyFurniture.ID, "textures/gui/container/bookshelf.png");

    public BookshelfMenuScreen(BookshelfMenu menu, Inventory inventory, Component displayName)
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

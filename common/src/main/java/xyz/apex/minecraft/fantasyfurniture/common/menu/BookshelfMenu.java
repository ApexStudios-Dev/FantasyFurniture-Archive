/*
package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;

public final class BookshelfMenu extends InventoryMenu
{
    public BookshelfMenu(MenuType<? extends BookshelfMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, BookshelfBlockEntity.ROWS, BookshelfBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 140);
    }

    public static BookshelfMenu forClient(MenuType<? extends BookshelfMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new BookshelfMenu(menuType, containerId, player, new Inventory(BookshelfBlockEntity.SLOT_COUNT));
    }
}
*/

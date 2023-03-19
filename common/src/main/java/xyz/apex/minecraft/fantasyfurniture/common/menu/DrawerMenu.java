/*
package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;

public final class DrawerMenu extends InventoryMenu
{
    public DrawerMenu(MenuType<? extends DrawerMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, DrawerBlockEntity.ROWS, DrawerBlockEntity.COLS, 44, 18);
        bindPlayerInventory(this, player, 8, 84);
    }

    public static DrawerMenu forClient(MenuType<? extends DrawerMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new DrawerMenu(menuType, containerId, player, new Inventory(DrawerBlockEntity.SLOT_COUNT));
    }
}
*/

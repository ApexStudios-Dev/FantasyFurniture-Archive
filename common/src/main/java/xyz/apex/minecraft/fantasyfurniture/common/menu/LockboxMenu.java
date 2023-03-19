/*
package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;

public final class LockboxMenu extends InventoryMenu
{
    public LockboxMenu(MenuType<? extends LockboxMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, LockboxBlockEntity.ROWS, LockboxBlockEntity.COLS, 44, 18);
        bindPlayerInventory(this, player, 8, 84);
    }

    public static LockboxMenu forClient(MenuType<? extends LockboxMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new LockboxMenu(menuType, containerId, player, new Inventory(LockboxBlockEntity.SLOT_COUNT));
    }
}
*/

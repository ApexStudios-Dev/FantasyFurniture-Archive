/*
package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;

public final class DresserMenu extends InventoryMenu
{
    public DresserMenu(MenuType<? extends DresserMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, DresserBlockEntity.ROWS, DresserBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 84);
    }

    public static DresserMenu forClient(MenuType<? extends DresserMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new DresserMenu(menuType, containerId, player, new Inventory(DresserBlockEntity.SLOT_COUNT));
    }
}
*/

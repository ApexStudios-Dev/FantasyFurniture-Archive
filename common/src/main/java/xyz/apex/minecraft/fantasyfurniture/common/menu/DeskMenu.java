package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DeskBlockEntity;

public final class DeskMenu extends InventoryMenu
{
    public DeskMenu(MenuType<? extends DeskMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, DeskBlockEntity.ROWS, DeskBlockEntity.COLS, 44, 18);
        bindPlayerInventory(this, player, 8, 84);
    }

    public static DeskMenu forClient(MenuType<? extends DeskMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new DeskMenu(menuType, containerId, player, new Inventory(DeskBlockEntity.SLOT_COUNT));
    }
}

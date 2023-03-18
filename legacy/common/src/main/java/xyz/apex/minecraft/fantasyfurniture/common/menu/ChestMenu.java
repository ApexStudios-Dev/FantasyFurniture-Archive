package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.ChestBlockEntity;

public final class ChestMenu extends InventoryMenu
{
    public ChestMenu(MenuType<? extends ChestMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, ChestBlockEntity.ROWS, ChestBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 84);
    }

    public static ChestMenu forClient(MenuType<? extends ChestMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new ChestMenu(menuType, containerId, player, new Inventory(ChestBlockEntity.SLOT_COUNT));
    }
}

package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.Inventory;
import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.WardrobeBlockEntity;

public final class WardrobeMenu extends InventoryMenu
{
    public WardrobeMenu(MenuType<? extends WardrobeMenu> menuType, int containerId, Player player, Inventory inventory)
    {
        super(menuType, containerId, player, inventory);

        bindInventory(this, inventory, WardrobeBlockEntity.ROWS, WardrobeBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 140);
    }

    public static WardrobeMenu forClient(MenuType<? extends WardrobeMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        return new WardrobeMenu(menuType, containerId, player, new Inventory(WardrobeBlockEntity.SLOT_COUNT));
    }
}

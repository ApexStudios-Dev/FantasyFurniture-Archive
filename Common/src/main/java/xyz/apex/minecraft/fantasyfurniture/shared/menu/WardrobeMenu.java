package xyz.apex.minecraft.fantasyfurniture.shared.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.WardrobeBlockEntity;

public final class WardrobeMenu extends InventoryMenu
{
    public WardrobeMenu(MenuType<? extends WardrobeMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, containerId, player, data);

        bindInventory(this, inventory, WardrobeBlockEntity.ROWS, WardrobeBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 140);
    }
}

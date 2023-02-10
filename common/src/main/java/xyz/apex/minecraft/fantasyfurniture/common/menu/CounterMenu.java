package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.common.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;

public final class CounterMenu extends InventoryMenu
{
    public CounterMenu(MenuType<? extends CounterMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, containerId, player, data);

        bindInventory(this, inventory, CounterBlockEntity.ROWS, CounterBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 84);
    }
}

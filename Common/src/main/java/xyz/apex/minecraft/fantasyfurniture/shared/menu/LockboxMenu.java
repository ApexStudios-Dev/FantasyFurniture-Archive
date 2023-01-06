package xyz.apex.minecraft.fantasyfurniture.shared.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.LockboxBlockEntity;

public final class LockboxMenu extends InventoryMenu
{
    public LockboxMenu(MenuType<? extends LockboxMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, containerId, player, data);

        bindInventory(this, inventory, LockboxBlockEntity.ROWS, LockboxBlockEntity.COLS, 44, 18);
        bindPlayerInventory(this, player, 8, 84);
    }
}

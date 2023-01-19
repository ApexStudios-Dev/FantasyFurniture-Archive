package xyz.apex.minecraft.fantasyfurniture.shared.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import xyz.apex.minecraft.apexcore.shared.inventory.InventoryMenu;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.DresserBlockEntity;

public final class DresserMenu extends InventoryMenu
{
    public DresserMenu(MenuType<? extends DresserMenu> menuType, int containerId, Player player, FriendlyByteBuf data)
    {
        super(menuType, containerId, player, data);

        bindInventory(this, inventory, DresserBlockEntity.ROWS, DresserBlockEntity.COLS, 8, 18);
        bindPlayerInventory(this, player, 8, 84);
    }
}
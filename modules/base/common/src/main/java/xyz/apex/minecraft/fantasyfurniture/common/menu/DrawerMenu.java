package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class DrawerMenu extends SimpleContainerMenu
{
    private DrawerMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.DRAWER.get(), containerId, playerInventory, container, DrawerBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, DrawerBlockEntity.ROWS, DrawerBlockEntity.COLS, 44, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static DrawerMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new DrawerMenu(containerId, playerInventory, container);
    }

    public static DrawerMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new DrawerMenu(containerId, playerInventory, new SimpleContainer(DrawerBlockEntity.SLOT_COUNT));
    }
}

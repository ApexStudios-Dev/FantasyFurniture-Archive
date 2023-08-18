package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;

public class SmallContainerMenu extends SimpleContainerMenu
{
    public static final int ROWS = 3;
    public static final int COLS = 5;
    public static final int SLOT_COUNT = ROWS * COLS;

    public SmallContainerMenu(MenuType<? extends SmallContainerMenu> menuType, int syncId, Inventory playerInventory, Container container)
    {
        super(menuType, syncId, playerInventory, container);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindContainer(container, ROWS, COLS, 44, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static SmallContainerMenu forNetwork(MenuType<? extends SmallContainerMenu> menuType, int syncId, Inventory inventory, FriendlyByteBuf buffer)
    {
        return new SmallContainerMenu(menuType, syncId, inventory, new SimpleContainer(SLOT_COUNT));
    }
}

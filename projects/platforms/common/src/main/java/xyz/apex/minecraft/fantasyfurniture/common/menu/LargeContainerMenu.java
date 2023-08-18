package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;

import java.util.stream.IntStream;

public class LargeContainerMenu extends SimpleContainerMenu
{
    public static final int ROWS = 6;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;
    public static final int[] SLOTS = IntStream.range(0, SLOT_COUNT).toArray();

    public LargeContainerMenu(MenuType<? extends LargeContainerMenu> menuType, int syncId, Inventory playerInventory, Container container)
    {
        super(menuType, syncId, playerInventory, container);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindContainer(container, ROWS, COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 140, this::addSlot);
    }

    public static LargeContainerMenu forNetwork(MenuType<? extends LargeContainerMenu> menuType, int syncId, Inventory inventory, FriendlyByteBuf buffer)
    {
        return new LargeContainerMenu(menuType, syncId, inventory, new SimpleContainer(SLOT_COUNT));
    }
}

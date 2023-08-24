package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;

public class MediumContainerMenu extends SimpleContainerMenu
{
    public static final int ROWS = 3;
    public static final int COLS = 9;
    public static final int SLOT_COUNT = ROWS * COLS;

    public MediumContainerMenu(MenuType<? extends MediumContainerMenu> menuType, int syncId, Inventory playerInventory, Container container)
    {
        super(menuType, syncId, playerInventory, container);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindContainer(container, ROWS, COLS, SLOT_BORDER_OFFSET, SLOT_SIZE, this::addSlot);
        bindPlayerInventory(playerInventory, SLOT_BORDER_OFFSET, 87, this::addSlot);
    }

    public static MediumContainerMenu forNetwork(MenuType<? extends MediumContainerMenu> menuType, int syncId, Inventory inventory, FriendlyByteBuf buffer)
    {
        return new MediumContainerMenu(menuType, syncId, inventory, new SimpleContainer(SLOT_COUNT));
    }
}

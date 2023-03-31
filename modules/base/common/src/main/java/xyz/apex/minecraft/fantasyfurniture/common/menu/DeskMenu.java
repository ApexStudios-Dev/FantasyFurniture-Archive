package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DeskBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class DeskMenu extends SimpleContainerMenu
{
    private DeskMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.DESK.get(), containerId, playerInventory, container, DeskBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, DeskBlockEntity.ROWS, DeskBlockEntity.COLS, 44, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static DeskMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new DeskMenu(containerId, playerInventory, container);
    }

    public static DeskMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new DeskMenu(containerId, playerInventory, new SimpleContainer(DeskBlockEntity.SLOT_COUNT));
    }
}

package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.ChestBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class ChestMenu extends SimpleContainerMenu
{
    private ChestMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.CHEST.get(), containerId, playerInventory, container, ChestBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, ChestBlockEntity.ROWS, ChestBlockEntity.COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static ChestMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new ChestMenu(containerId, playerInventory, container);
    }

    public static ChestMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new ChestMenu(containerId, playerInventory, new SimpleContainer(ChestBlockEntity.SLOT_COUNT));
    }
}

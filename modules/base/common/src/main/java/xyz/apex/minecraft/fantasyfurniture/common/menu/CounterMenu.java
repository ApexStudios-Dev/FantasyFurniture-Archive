package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class CounterMenu extends SimpleContainerMenu
{
    private CounterMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.COUNTER.get(), containerId, playerInventory, container, CounterBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, CounterBlockEntity.ROWS, CounterBlockEntity.COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static CounterMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new CounterMenu(containerId, playerInventory, container);
    }

    public static CounterMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new CounterMenu(containerId, playerInventory, new SimpleContainer(CounterBlockEntity.SLOT_COUNT));
    }
}

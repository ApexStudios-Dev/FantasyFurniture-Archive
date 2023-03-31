package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class LockboxMenu extends SimpleContainerMenu
{
    private LockboxMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.LOCKBOX.get(), containerId, playerInventory, container, LockboxBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, LockboxBlockEntity.ROWS, LockboxBlockEntity.COLS, 44, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static LockboxMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new LockboxMenu(containerId, playerInventory, container);
    }

    public static LockboxMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new LockboxMenu(containerId, playerInventory, new SimpleContainer(LockboxBlockEntity.SLOT_COUNT));
    }
}

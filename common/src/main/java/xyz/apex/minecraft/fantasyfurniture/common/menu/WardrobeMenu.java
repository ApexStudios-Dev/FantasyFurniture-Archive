package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.WardrobeBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class WardrobeMenu extends SimpleContainerMenu
{
    private WardrobeMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.WARDROBE.get(), containerId, playerInventory, container, WardrobeBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, WardrobeBlockEntity.ROWS, WardrobeBlockEntity.COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 140, this::addSlot);
    }

    public static WardrobeMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new WardrobeMenu(containerId, playerInventory, container);
    }

    public static WardrobeMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new WardrobeMenu(containerId, playerInventory, new SimpleContainer(WardrobeBlockEntity.SLOT_COUNT));
    }
}

package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class DresserMenu extends SimpleContainerMenu
{
    private DresserMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.DRESSER.get(), containerId, playerInventory, container, DresserBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, DresserBlockEntity.ROWS, DresserBlockEntity.COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 84, this::addSlot);
    }

    public static DresserMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new DresserMenu(containerId, playerInventory, container);
    }

    public static DresserMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new DresserMenu(containerId, playerInventory, new SimpleContainer(DresserBlockEntity.SLOT_COUNT));
    }
}

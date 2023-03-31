package xyz.apex.minecraft.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import xyz.apex.minecraft.apexcore.common.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;

public final class BookshelfMenu extends SimpleContainerMenu
{
    private BookshelfMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(AllMenuTypes.BOOKSHELF.get(), containerId, playerInventory, container, BookshelfBlockEntity.SLOT_COUNT);
    }

    @Override
    protected void bindSlots(Inventory playerInventory)
    {
        bindInventory(container, BookshelfBlockEntity.ROWS, BookshelfBlockEntity.COLS, 8, 18, this::addSlot);
        bindPlayerInventory(playerInventory, 8, 140, this::addSlot);
    }

    public static BookshelfMenu forServer(int containerId, Inventory playerInventory, Container container)
    {
        return new BookshelfMenu(containerId, playerInventory, container);
    }

    public static BookshelfMenu forClient(int containerId, Inventory playerInventory, Player player, FriendlyByteBuf extraData)
    {
        return new BookshelfMenu(containerId, playerInventory, new SimpleContainer(BookshelfBlockEntity.SLOT_COUNT));
    }
}

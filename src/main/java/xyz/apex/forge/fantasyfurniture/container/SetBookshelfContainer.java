package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;

import xyz.apex.forge.fantasyfurniture.container.slot.BookSlotItemHandler;

public final class SetBookshelfContainer extends InventoryContainer
{
	public static final int ROWS = 6;
	public static final int COLS = 9;
	public static final int SIZE = ROWS * COLS;

	public SetBookshelfContainer(ContainerType<?> menuType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler)
	{
		super(menuType, windowId, playerInventory, itemHandler, ROWS, COLS);

		for(int j = 0; j < ROWS; j++)
		{
			for(int k = 0; k < COLS; k++)
			{
				addSlot(new BookSlotItemHandler(itemHandler, k + j * COLS, 8 + k * 18, 18 + j * 18));
			}
		}

		// addInventorySlots(this::addSlot, itemHandler, ROWS, COLS, 8, 18);
		addPlayerInventorySlots(this::addSlot, playerInventory, 8, 140);
	}
}

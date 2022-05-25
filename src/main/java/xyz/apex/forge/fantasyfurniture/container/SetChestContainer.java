package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;

public final class SetChestContainer extends InventoryContainer
{
	public static final int ROWS = 6;
	public static final int COLS = 9;
	public static final int SIZE = ROWS * COLS;

	public SetChestContainer(ContainerType<?> menuType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler)
	{
		super(menuType, windowId, playerInventory, itemHandler, ROWS, COLS);

		addInventorySlots(this::addSlot, itemHandler, ROWS, COLS, 8, 18);
		addPlayerInventorySlots(this::addSlot, playerInventory, 8, 140);
	}
}

package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.IItemHandler;

public final class SetDresserContainer extends InventoryContainer
{
	public static final int ROWS = 3;
	public static final int COLS = 9;
	public static final int SIZE = ROWS * COLS;

	public SetDresserContainer(MenuType<?> menuType, int windowId, Inventory playerInventory, IItemHandler itemHandler)
	{
		super(menuType, windowId, playerInventory, itemHandler, ROWS, COLS);

		addInventorySlots(this::addSlot, itemHandler, ROWS, COLS, 8, 18);
		addPlayerInventorySlots(this::addSlot, playerInventory, 8, 84);
	}
}

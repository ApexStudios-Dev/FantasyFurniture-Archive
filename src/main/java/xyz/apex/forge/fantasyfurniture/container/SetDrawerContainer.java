package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.items.IItemHandler;

public final class SetDrawerContainer extends InventoryContainer
{
	public static final int ROWS = 3;
	public static final int COLS = 5;
	public static final int SIZE = ROWS * COLS;

	public SetDrawerContainer(MenuType<?> menuType, int windowId, Inventory playerInventory, IItemHandler itemHandler)
	{
		super(menuType, windowId, playerInventory, itemHandler, ROWS, COLS);

		addInventorySlots(this::addSlot, itemHandler, ROWS, COLS, 44, 18);
		addPlayerInventorySlots(this::addSlot, playerInventory, 8, 84);
	}
}

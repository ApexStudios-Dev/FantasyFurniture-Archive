package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;

public class DrawerContainer extends InventoryContainer
{
	public static final int ROWS = 3;
	public static final int COLS = 5;
	public static final int SIZE = ROWS * COLS;

	public DrawerContainer(ContainerType<?> menuType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler)
	{
		super(menuType, windowId, playerInventory, itemHandler, ROWS, COLS);
	}
}

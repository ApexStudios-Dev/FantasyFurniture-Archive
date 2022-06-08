package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.java.utility.nullness.NonnullConsumer;

public class InventoryContainer extends AbstractContainerMenu
{
	protected final Player player;
	protected final IItemHandler itemHandler;

	public InventoryContainer(MenuType<?> menuType, int windowId, Inventory playerInventory, IItemHandler itemHandler, int rows, int cols)
	{
		super(menuType, windowId);

		this.itemHandler = itemHandler;

		player = playerInventory.player;
	}

	@Override
	public boolean stillValid(Player player)
	{
		return player.isAlive() && player.containerMenu == this;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex)
	{
		var stack = ItemStack.EMPTY;
		var slot = slots.get(slotIndex);

		if(slot.hasItem())
		{
			var stack1 = slot.getItem();
			stack = stack1.copy();
			var maxIndex = itemHandler.getSlots();

			if(slotIndex < maxIndex)
			{
				if(!moveItemStackTo(stack1, maxIndex, this.slots.size(), true))
					return ItemStack.EMPTY;
			}
			else if(!moveItemStackTo(stack1, 0, maxIndex, false))
				return ItemStack.EMPTY;

			if(stack1.isEmpty())
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
		}

		return stack;
	}

	protected static void addInventorySlots(NonnullConsumer<Slot> addSlot, IItemHandler itemHandler, int rows, int cols, int xStart, int yStart)
	{
		for(var j = 0; j < rows; j++)
		{
			for(var k = 0; k < cols; k++)
			{
				addSlot.accept(new SlotItemHandler(itemHandler, k + j * cols, xStart + k * 18, yStart + j * 18));
			}
		}
	}

	protected static void addPlayerInventorySlots(NonnullConsumer<Slot> addSlot, Inventory playerInventory, int xStart, int yStart)
	{
		for(var i = 0; i < 3; i++)
		{
			for(var j = 0; j < 9; j++)
			{
				addSlot.accept(new Slot(playerInventory, j + i * 9 + 9, xStart + j * 18, yStart + i * 18));
			}
		}

		for(var i = 0; i < 9; ++i)
		{
			addSlot.accept(new Slot(playerInventory, i, xStart + i * 18, yStart + 58));
		}
	}
}

package xyz.apex.forge.fantasyfurniture.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.java.utility.nullness.NonnullConsumer;

public class InventoryContainer extends Container
{
	protected final PlayerEntity player;
	protected final IItemHandler itemHandler;

	public InventoryContainer(ContainerType<?> menuType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler, int rows, int cols)
	{
		super(menuType, windowId);

		this.itemHandler = itemHandler;

		player = playerInventory.player;
	}

	@Override
	public boolean stillValid(PlayerEntity player)
	{
		return player.isAlive() && player.containerMenu == this;
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int slotIndex)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if(slot != null && slot.hasItem())
		{
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			int maxIndex = itemHandler.getSlots();

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
		for(int j = 0; j < rows; j++)
		{
			for(int k = 0; k < cols; k++)
			{
				addSlot.accept(new SlotItemHandler(itemHandler, k + j * cols, xStart + k * 18, yStart + j * 18));
			}
		}
	}

	protected static void addPlayerInventorySlots(NonnullConsumer<Slot> addSlot, PlayerInventory playerInventory, int xStart, int yStart)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlot.accept(new Slot(playerInventory, j + i * 9 + 9, xStart + j * 18, yStart + i * 18));
			}
		}

		for(int i = 0; i < 9; ++i)
		{
			addSlot.accept(new Slot(playerInventory, i, xStart + i * 18, yStart + 58));
		}
	}
}

package xyz.apex.forge.fantasyfurniture.container;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;
import xyz.apex.java.utility.nullness.NonnullPredicate;

import javax.annotation.Nullable;
import java.util.List;

public final class FurnitureStationContainer extends Container
{
	private final IWorldPosCallable access;
	private final IInventory inputInventory;
	private final CraftResultInventory resultInventory;

	private final Slot claySlot;
	private final Slot redDyeSlot;
	private final Slot yellowDyeSlot;
	private final Slot blueDyeSlot;
	private final Slot resultSlot;

	private List<ItemStack> results = Lists.newArrayList();
	private long lastSoundTime = 0L;

	public FurnitureStationContainer(@Nullable ContainerType<?> menuType, int windowId, PlayerInventory playerInventory, IWorldPosCallable access)
	{
		super(menuType, windowId);

		this.access = access;

		inputInventory = new Inventory(4) {
			@Override
			public void setChanged()
			{
				super.setChanged();
				slotsChanged(this);
			}
		};

		resultInventory = new CraftResultInventory();

		claySlot = addSlot(new InputSlot(FurnitureStation.CLAY_SLOT, 35, 21, stack -> stack.getItem() == Items.CLAY_BALL));
		redDyeSlot = addSlot(new InputSlot(FurnitureStation.RED_DYE_SLOT, 71, 21, stack -> stack.getItem().is(Tags.Items.DYES_RED)));
		yellowDyeSlot = addSlot(new InputSlot(FurnitureStation.YELLOW_DYE_SLOT, 89, 21, stack -> stack.getItem().is(Tags.Items.DYES_YELLOW)));
		blueDyeSlot = addSlot(new InputSlot(FurnitureStation.BLUE_DYE_SLOT, 107, 21, stack -> stack.getItem().is(Tags.Items.DYES_BLUE)));
		resultSlot = addSlot(new ResultSlot());

		// player inventory slots
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++)
		{
			addSlot(new Slot(playerInventory, i, 8 + i * 18, 198));
		}
	}

	public Slot getClaySlot()
	{
		return claySlot;
	}

	public Slot getRedDyeSlot()
	{
		return redDyeSlot;
	}

	public Slot getBlueDyeSlot()
	{
		return blueDyeSlot;
	}

	public Slot getYellowDyeSlot()
	{
		return yellowDyeSlot;
	}

	public Slot getResultSlot()
	{
		return resultSlot;
	}

	public List<ItemStack> getResults()
	{
		return results;
	}

	@Override
	public boolean stillValid(PlayerEntity player)
	{
		return stillValid(access, player, FurnitureStation.BLOCK.asBlock());
	}

	@Override
	public void slotsChanged(IInventory inventory)
	{
		super.slotsChanged(inventory);

		results.clear();

		if(claySlot.hasItem() && redDyeSlot.hasItem() && yellowDyeSlot.hasItem() && blueDyeSlot.hasItem())
			FurnitureStation.CRAFTABLE.getValues().stream().map(Item::getDefaultInstance).forEach(results::add);

		setupResultSlot(-1);
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
	{
		return slot.container != resultInventory && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public boolean clickMenuButton(PlayerEntity player, int button)
	{
		setupResultSlot(button);
		return super.clickMenuButton(player, button);
	}

	@Override
	public void removed(PlayerEntity player)
	{
		super.removed(player);
		resultInventory.removeItemNoUpdate(1);
		access.execute((world, pos) -> clearContainer(player, world, inputInventory));
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

			int inputStart = 0;
			int inputEnd = 3;

			int resultSlot = 4;

			int playerStart = 5;
			int playerEnd = 31;

			int hotStart = 32;
			int hotEnd = 40;

			if(slotIndex >= inputStart && slotIndex <= inputEnd)
			{
				if(!moveItemStackTo(stack1, playerStart, hotEnd, false))
					return ItemStack.EMPTY;
			}
			else if(slotIndex == resultSlot)
			{
				stack1.onCraftedBy(player.level, player, stack1.getCount());

				if(!moveItemStackTo(stack1, playerStart, hotEnd, false))
					return ItemStack.EMPTY;

				slot.onQuickCraft(stack1, stack);
			}
			else if(slotIndex >= playerStart && slotIndex <= playerEnd)
			{
				if(!moveItemStackTo(stack1, inputStart, resultSlot, false))
					return ItemStack.EMPTY;
				if(!moveItemStackTo(stack1, hotStart, hotEnd, false))
					return ItemStack.EMPTY;
			}
			else if(slotIndex >= hotStart && slotIndex <= hotEnd)
			{
				if(!moveItemStackTo(stack1, inputStart, playerEnd, false))
					return ItemStack.EMPTY;
			}

			if(stack1.isEmpty())
				slot.set(ItemStack.EMPTY);

			slot.setChanged();

			if(stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, stack1);
			broadcastChanges();
		}

		return stack;
	}

	private void setupResultSlot(int button)
	{
		int size = results.size();

		if(size == 0 || button < 0 || button > size - 1)
		{
			resultSlot.set(ItemStack.EMPTY);
			broadcastChanges();
			return;
		}

		ItemStack stack = results.get(button).copy();
		stack.setCount(1);
		resultSlot.set(stack);
		broadcastChanges();
	}

	private void decrementInput()
	{
		claySlot.remove(1);
		redDyeSlot.remove(1);
		blueDyeSlot.remove(1);
		yellowDyeSlot.remove(1);
	}

	public final class InputSlot extends Slot
	{
		private final NonnullPredicate<ItemStack> predicate;

		private InputSlot(int index, int x, int y, NonnullPredicate<ItemStack> predicate)
		{
			super(inputInventory, index, x, y);

			this.predicate = predicate;
		}

		@Override
		public boolean mayPlace(ItemStack stack)
		{
			return predicate.test(stack);
		}
	}

	public final class ResultSlot extends Slot
	{
		private ResultSlot()
		{
			super(resultInventory, FurnitureStation.RESULT_SLOT, 134, 72);
		}

		@Override
		public boolean mayPlace(ItemStack stack)
		{
			return false;
		}

		@Override
		public ItemStack onTake(PlayerEntity player, ItemStack stack)
		{
			stack.getItem().onCraftedBy(stack, player.level, player);
			resultInventory.awardUsedRecipes(player);

			decrementInput();
			setupResultSlot(-1);

			access.execute((world, pos) -> {
				long gameTime = world.getGameTime();

				if(lastSoundTime != gameTime)
				{
					world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1F, 1F);
					lastSoundTime = gameTime;
				}
			});

			return super.onTake(player, stack);
		}
	}
}

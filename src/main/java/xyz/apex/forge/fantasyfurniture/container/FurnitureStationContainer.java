package xyz.apex.forge.fantasyfurniture.container;

import com.google.common.collect.Lists;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;
import xyz.apex.java.utility.nullness.NonnullPredicate;

import javax.annotation.Nullable;
import java.util.List;

public final class FurnitureStationContainer extends AbstractContainerMenu
{
	private final ContainerLevelAccess access;
	private final Container inputInventory;
	private final ResultContainer resultInventory;

	private final Slot claySlot;
	private final Slot woodSlot;
	private final Slot stoneSlot;
	private final Slot resultSlot;

	private final List<ItemStack> results = Lists.newArrayList();
	private long lastSoundTime = 0L;

	public FurnitureStationContainer(@Nullable MenuType<?> menuType, int windowId, Inventory playerInventory, ContainerLevelAccess access)
	{
		super(menuType, windowId);

		this.access = access;

		inputInventory = new SimpleContainer(4) {
			@Override
			public void setChanged()
			{
				super.setChanged();
				slotsChanged(this);
			}
		};

		resultInventory = new ResultContainer();

		claySlot = addSlot(new InputSlot(FurnitureStation.CLAY_SLOT, 16, 21, FurnitureStation::isValidClay));
		woodSlot = addSlot(new InputSlot(FurnitureStation.WOOD_SLOT, 34, 21, FurnitureStation::isValidWood));
		stoneSlot = addSlot(new InputSlot(FurnitureStation.STONE_SLOT, 52, 21, FurnitureStation::isValidStone));
		resultSlot = addSlot(new ResultSlot());

		// player inventory slots
		for(var i = 0; i < 3; i++)
		{
			for(var j = 0; j < 9; j++)
			{
				addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
			}
		}

		for(var i = 0; i < 9; i++)
		{
			addSlot(new Slot(playerInventory, i, 8 + i * 18, 198));
		}
	}

	public Slot getClaySlot()
	{
		return claySlot;
	}

	public Slot getWoodSlot()
	{
		return woodSlot;
	}

	public Slot getStoneSlot()
	{
		return stoneSlot;
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
	public boolean stillValid(Player player)
	{
		return stillValid(access, player, FurnitureStation.BLOCK.asBlock());
	}

	@Override
	public void slotsChanged(Container inventory)
	{
		super.slotsChanged(inventory);

		results.clear();

		if(claySlot.hasItem() && woodSlot.hasItem() && stoneSlot.hasItem())
			results.addAll(FurnitureStation.getCraftingResults());

		setupResultSlot(-1);
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
	{
		return slot.container != resultInventory && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public boolean clickMenuButton(Player player, int button)
	{
		setupResultSlot(button);
		return super.clickMenuButton(player, button);
	}

	@Override
	public void removed(Player player)
	{
		super.removed(player);
		resultInventory.removeItemNoUpdate(1);
		access.execute((world, pos) -> clearContainer(player, inputInventory));
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

			var inputStart = Math.min(FurnitureStation.CLAY_SLOT, Math.min(FurnitureStation.WOOD_SLOT, FurnitureStation.STONE_SLOT));
			var inputEnd = Math.max(FurnitureStation.CLAY_SLOT, Math.max(FurnitureStation.WOOD_SLOT, FurnitureStation.STONE_SLOT));

			var resultSlot = FurnitureStation.RESULT_SLOT;

			var playerStart = 4;
			var playerEnd = 31;

			var hotStart = 32;
			var hotEnd = 40;

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
		var size = results.size();

		if(size == 0 || button < 0 || button > size - 1)
		{
			resultSlot.set(ItemStack.EMPTY);
			broadcastChanges();
			return;
		}

		var stack = results.get(button).copy();
		stack.setCount(1);
		resultSlot.set(stack);
		broadcastChanges();
	}

	private void decrementInput()
	{
		claySlot.remove(1);
		woodSlot.remove(1);
		stoneSlot.remove(1);
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
			super(resultInventory, FurnitureStation.RESULT_SLOT, 148, 72);
		}

		@Override
		public boolean mayPlace(ItemStack stack)
		{
			return false;
		}

		@Override
		public void onTake(Player player, ItemStack stack)
		{
			stack.getItem().onCraftedBy(stack, player.level, player);
			resultInventory.awardUsedRecipes(player);

			decrementInput();
			setupResultSlot(-1);

			access.execute((world, pos) -> {
				var gameTime = world.getGameTime();

				if(lastSoundTime != gameTime)
				{
					world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1F, 1F);
					lastSoundTime = gameTime;
				}
			});
		}
	}
}

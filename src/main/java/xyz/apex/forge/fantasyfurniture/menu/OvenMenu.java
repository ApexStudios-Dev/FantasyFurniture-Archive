package xyz.apex.forge.fantasyfurniture.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.forge.apexcore.lib.container.BaseMenu;
import xyz.apex.forge.fantasyfurniture.block.entity.OvenBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

public final class OvenMenu extends BaseMenu
{
	public static final int SLOT_COUNT = 3;
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_FUEL = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int DATA_SLOT_COUNT = 4;
	public static final int DATA_SLOT_LIT_TIME = 0;
	public static final int DATA_SLOT_LIT_DURATION = 1;
	public static final int DATA_SLOT_LIT_COOKING_PROGRESS = 2;
	public static final int DATA_SLOT_LIT_COOKING_TOTAL_TIME = 3;

	public final Player player;
	public final IItemHandler itemHandler;
	private final OvenBlockEntity blockEntity;
	public final Slot inputSlot;
	public final Slot outputSlot;
	public final Slot fuelSlot;
	private final DataSlot litTime;
	private final DataSlot litDuration;
	private final DataSlot cookingProgress;
	private final DataSlot cookingTotalTime;
	public final RecipeBookMenu<Container> recipeBookMenu;

	public OvenMenu(@Nullable MenuType<? extends OvenMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		player = playerInventory.player;
		this.itemHandler = Objects.requireNonNull(getItemHandler());

		inputSlot = addSlot(new InputSlot());
		outputSlot = addSlot(new OutputSlot());
		fuelSlot = addSlot(new FuelSlot());

		bindPlayerInventory(this, 8, 84);

		blockEntity = ModElements.OVEN_BLOCK_ENTITY.get(player.level, pos).orElseThrow();

		litTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_LIT_TIME));
		litDuration = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_LIT_DURATION));
		cookingProgress = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_LIT_COOKING_PROGRESS));
		cookingTotalTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_LIT_COOKING_TOTAL_TIME));

		recipeBookMenu = new RecipeBookMenuWrapper(windowId);
	}

	public int getLitTime()
	{
		return litTime.get();
	}

	public int getLitDuration()
	{
		return litDuration.get();
	}

	public int getCookingProgress()
	{
		return cookingProgress.get();
	}

	public int getCookingTotalTime()
	{
		return cookingTotalTime.get();
	}

	public int getBurnProgress()
	{
		var progress = getCookingProgress();
		var totalTime = getCookingTotalTime();
		return totalTime != 0 && progress != 0 ? progress * 24 / totalTime : 0;
	}

	public int getLitProgress()
	{
		var duration = getLitDuration();

		if(duration == 0)
			duration = OvenBlockEntity.DEFAULT_COOK_TIME;

		return getLitTime() * 13 / duration;
	}

	public boolean isLit()
	{
		return getLitTime() > 0;
	}

	@Nullable
	@Override
	public IItemHandler getItemHandler()
	{
		var blockEntity = Objects.requireNonNull(player.level.getBlockEntity(pos));
		return getItemHandlerFromBlockEntity(blockEntity).resolve().orElse(null);
	}

	@Override
	protected void onInventoryChanges()
	{
		super.onInventoryChanges();
		setBlockEntityChanged();
	}

	public final class InputSlot extends SlotItemHandler
	{
		public InputSlot()
		{
			super(itemHandler, SLOT_INPUT, 56, 17);
		}
	}

	public final class OutputSlot extends SlotItemHandler
	{
		private int removeCount;

		public OutputSlot()
		{
			super(itemHandler, SLOT_OUTPUT, 116, 35);
		}

		@Override
		public @NotNull ItemStack remove(int amount)
		{
			if(hasItem())
				removeCount += Math.min(removeCount, getItem().getCount());

			return super.remove(amount);
		}

		@Override
		public boolean mayPlace(@NotNull ItemStack stack)
		{
			return true;
		}

		@Override
		public void onTake(Player player, ItemStack stack)
		{
			checkTakeAchievements(stack);
			super.onTake(player, stack);
		}

		@Override
		protected void onQuickCraft(ItemStack stack, int amount)
		{
			super.onQuickCraft(stack, amount);
			checkTakeAchievements(stack);
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack)
		{
			stack.onCraftedBy(player.level, player, removeCount);

			if(player instanceof ServerPlayer serverPlayer && container instanceof OvenBlockEntity.RecipeWrapperContainer wrapper)
				wrapper.awardUsedRecipesAndPopExperience(serverPlayer);

			removeCount = 0;
			ForgeEventFactory.firePlayerSmeltedEvent(player, stack);
		}
	}

	public final class FuelSlot extends SlotItemHandler
	{
		public FuelSlot()
		{
			super(itemHandler, SLOT_FUEL, 56, 53);
		}

		@Override
		public boolean mayPlace(@NotNull ItemStack stack)
		{
			return true;
			// return OvenBlockEntity.isValidFuel(stack);
		}

		@Override
		public int getMaxStackSize(@NotNull ItemStack stack)
		{
			return stack.is(Items.BUCKET) ? 1 : getMaxStackSize();
		}
	}

	public final class RecipeBookMenuWrapper extends RecipeBookMenu<Container>
	{
		private RecipeBookMenuWrapper(int windowId)
		{
			super(OvenMenu.this.getType(), windowId);
		}

		@Override
		public ItemStack quickMoveStack(Player player, int slot)
		{
			return OvenMenu.this.quickMoveStack(player, slot);
		}

		@Override
		public boolean stillValid(Player player)
		{
			return OvenMenu.this.stillValid(player);
		}

		@Override
		public void fillCraftSlotsStackedContents(StackedContents contents)
		{
			if(contents instanceof StackedContentsCompatible stackedContentsCompatible)
				stackedContentsCompatible.fillStackedContents(contents);
		}

		@Override
		public void clearCraftingContent()
		{
			inputSlot.set(ItemStack.EMPTY);
			outputSlot.set(ItemStack.EMPTY);
		}

		@Override
		public boolean recipeMatches(Recipe<? super Container> recipe)
		{
			return recipe.matches(blockEntity.getRecipeContainerWrapper(), player.level);
		}

		@Override
		public int getResultSlotIndex()
		{
			return SLOT_OUTPUT;
		}

		@Override
		public int getGridWidth()
		{
			return 1;
		}

		@Override
		public int getGridHeight()
		{
			return 1;
		}

		@Override
		public int getSize()
		{
			return SLOT_COUNT;
		}

		@Override
		public RecipeBookType getRecipeBookType()
		{
			return OvenBlockEntity.RECIPE_BOOK_TYPE.get();
		}

		@Override
		public boolean shouldMoveToInventory(int slot)
		{
			return slot != SLOT_FUEL;
		}

		@Override
		public MenuType<?> getType()
		{
			return OvenMenu.this.getType();
		}

		@Override
		public boolean isValidSlotIndex(int slotIndex)
		{
			return OvenMenu.this.isValidSlotIndex(slotIndex);
		}

		@Override
		protected Slot addSlot(Slot slot)
		{
			return OvenMenu.this.addSlot(slot);
		}

		@Override
		protected DataSlot addDataSlot(DataSlot slot)
		{
			return OvenMenu.this.addDataSlot(slot);
		}

		@Override
		protected void addDataSlots(ContainerData containerData)
		{
			OvenMenu.this.addDataSlots(containerData);
		}

		@Override
		public void addSlotListener(ContainerListener listener)
		{
			OvenMenu.this.addSlotListener(listener);
		}

		@Override
		public void setSynchronizer(ContainerSynchronizer synchronizer)
		{
			OvenMenu.this.setSynchronizer(synchronizer);
		}

		@Override
		public void sendAllDataToRemote()
		{
			OvenMenu.this.sendAllDataToRemote();
		}

		@Override
		public void removeSlotListener(ContainerListener listener)
		{
			OvenMenu.this.removeSlotListener(listener);
		}

		@Override
		public NonNullList<ItemStack> getItems()
		{
			return OvenMenu.this.getItems();
		}

		@Override
		public void broadcastChanges()
		{
			OvenMenu.this.broadcastChanges();
		}

		@Override
		public void broadcastFullState()
		{
			OvenMenu.this.broadcastFullState();
		}

		@Override
		public void setRemoteSlot(int slotIndex, ItemStack stack)
		{
			OvenMenu.this.setRemoteSlot(slotIndex, stack);
		}

		@Override
		public void setRemoteSlotNoCopy(int slotIndex, ItemStack stack)
		{
			OvenMenu.this.setRemoteSlotNoCopy(slotIndex, stack);
		}

		@Override
		public void setRemoteCarried(ItemStack stack)
		{
			OvenMenu.this.setRemoteCarried(stack);
		}

		@Override
		public boolean clickMenuButton(Player player, int button)
		{
			return OvenMenu.this.clickMenuButton(player, button);
		}

		@Override
		public Slot getSlot(int slotIndex)
		{
			return OvenMenu.this.getSlot(slotIndex);
		}

		@Override
		public void clicked(int slot, int button, ClickType clickType, Player player)
		{
			OvenMenu.this.clicked(slot, button, clickType, player);
		}

		@Override
		public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
		{
			return OvenMenu.this.canTakeItemForPickAll(stack, slot);
		}

		@Override
		public void removed(Player player)
		{
			OvenMenu.this.removed(player);
		}

		@Override
		protected void clearContainer(Player player, Container container)
		{
			OvenMenu.this.clearContainer(player, container);
		}

		@Override
		public void slotsChanged(Container container)
		{
			OvenMenu.this.slotsChanged(container);
		}

		@Override
		public void setItem(int slot, int button, ItemStack stack)
		{
			OvenMenu.this.setItem(slot, button, stack);
		}

		@Override
		public void initializeContents(int i, List<ItemStack> items, ItemStack stack)
		{
			OvenMenu.this.initializeContents(i, items, stack);
		}

		@Override
		public void setData(int slot, int value)
		{
			OvenMenu.this.setData(slot, value);
		}

		@Override
		protected boolean moveItemStackTo(ItemStack stack, int i, int j, boolean b)
		{
			return OvenMenu.this.moveItemStackTo(stack, i, j, b);
		}

		@Override
		protected void resetQuickCraft()
		{
			OvenMenu.this.resetQuickCraft();
		}

		@Override
		public boolean canDragTo(Slot slot)
		{
			return OvenMenu.this.canDragTo(slot);
		}

		@Override
		public void setCarried(ItemStack stack)
		{
			OvenMenu.this.setCarried(stack);
		}

		@Override
		public ItemStack getCarried()
		{
			return OvenMenu.this.getCarried();
		}

		@Override
		public void suppressRemoteUpdates()
		{
			OvenMenu.this.suppressRemoteUpdates();
		}

		@Override
		public void resumeRemoteUpdates()
		{
			OvenMenu.this.resumeRemoteUpdates();
		}

		@Override
		public void transferState(AbstractContainerMenu menu)
		{
			OvenMenu.this.transferState(menu);
		}

		@Override
		public OptionalInt findSlot(Container container, int slot)
		{
			return OvenMenu.this.findSlot(container, slot);
		}

		@Override
		public int getStateId()
		{
			return OvenMenu.this.getStateId();
		}

		@Override
		public int incrementStateId()
		{
			return OvenMenu.this.incrementStateId();
		}
	}
}
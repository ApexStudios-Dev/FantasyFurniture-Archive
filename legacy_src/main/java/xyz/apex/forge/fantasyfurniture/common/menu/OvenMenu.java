package xyz.apex.forge.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apex.forge.apexcore.lib.container.BaseMenu;
import xyz.apex.forge.fantasyfurniture.AllBlockEntities;
import xyz.apex.forge.fantasyfurniture.common.block.entity.OvenBlockEntity;

import java.util.Objects;

public final class OvenMenu extends BaseMenu
{
	public static final int SLOT_COUNT = 3;
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_FUEL = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int DATA_SLOT_COUNT = 4;
	public static final int DATA_SLOT_BURN_TIME = 0;
	public static final int DATA_SLOT_TOTAL_BURN_TIME = 1;
	public static final int DATA_SLOT_SMELT_TIME = 2;
	public static final int DATA_SLOT_TOTAL_SMELT_TIME = 3;

	private final Player player;
	private final IItemHandler itemHandler;
	private final OvenBlockEntity blockEntity;
	private final Slot inputSlot;
	private final Slot outputSlot;
	private final Slot fuelSlot;
	private DataSlot burnTime;
	private DataSlot totalBurnTime;
	private DataSlot smeltTime;
	private DataSlot totalSmeltTime;

	public OvenMenu(@Nullable MenuType<? extends OvenMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		player = playerInventory.player;
		this.itemHandler = Objects.requireNonNull(getItemHandler());

		inputSlot = addSlot(new InputSlot());
		outputSlot = addSlot(new OutputSlot());
		fuelSlot = addSlot(new FuelSlot());

		bindPlayerInventory(this, 8, 84);

		blockEntity = AllBlockEntities.OVEN_BLOCK_ENTITY.get(player.level, pos).orElseThrow();

		burnTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_BURN_TIME));
		totalBurnTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_TOTAL_BURN_TIME));
		smeltTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_SMELT_TIME));
		totalSmeltTime = addDataSlot(DataSlot.forContainer(blockEntity, DATA_SLOT_TOTAL_SMELT_TIME));
	}

	public int getSmeltProgress()
	{
		var totalSmeltTime = this.totalSmeltTime.get();

		if(totalSmeltTime <= 0)
			return -1;

		var smeltTime = this.smeltTime.get();
		return smeltTime * 24 / totalSmeltTime;
	}

	public int getBurnProgress()
	{
		var burnTime = this.burnTime.get();

		if(burnTime <= 0)
			return -1;

		var totalBurnTime = this.totalBurnTime.get();

		if(totalBurnTime == 0)
			totalBurnTime = AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;

		return burnTime * 100 / totalBurnTime;
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
		public boolean mayPlace(@NotNull ItemStack stack)
		{
			return false;
		}

		@Override
		public @NotNull ItemStack remove(int amount)
		{
			if(hasItem())
				removeCount += Math.min(amount, getItem().getCount());

			return super.remove(amount);
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
			removeCount += amount;
			super.onQuickCraft(stack, amount);
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack)
		{
			stack.onCraftedBy(player.level, player, removeCount);

			if(player instanceof ServerPlayer serverPlayer)
				blockEntity.awardRecipesAndExperience(serverPlayer.getLevel(), serverPlayer, Vec3.atCenterOf(pos));

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
			return blockEntity.canBurn(stack);
		}
	}
}

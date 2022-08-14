package xyz.apex.forge.fantasyfurniture.menu;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.forge.apexcore.lib.container.BaseMenu;
import xyz.apex.forge.fantasyfurniture.block.entity.OvenBlockEntity;
import xyz.apex.forge.fantasyfurniture.init.ModElements;

import java.util.Objects;

public final class OvenMenu extends BaseMenu
{
	public static final int SLOT_COUNT = 3;
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_FUEL = 1;
	public static final int SLOT_OUTPUT = 2;
	public static final int DATA_SLOT_COUNT = 0;

	public final Player player;
	public final IItemHandler itemHandler;
	private final OvenBlockEntity blockEntity;
	public final Slot inputSlot;
	public final Slot outputSlot;
	public final Slot fuelSlot;

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
		public OutputSlot()
		{
			super(itemHandler, SLOT_OUTPUT, 116, 35);
		}
	}

	public final class FuelSlot extends SlotItemHandler
	{
		public FuelSlot()
		{
			super(itemHandler, SLOT_FUEL, 56, 53);
		}
	}
}
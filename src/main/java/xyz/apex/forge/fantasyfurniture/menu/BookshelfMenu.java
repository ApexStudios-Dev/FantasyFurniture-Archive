package xyz.apex.forge.fantasyfurniture.menu;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.forge.apexcore.revamp.container.BaseMenu;
import xyz.apex.forge.commonality.tags.ItemTags;

public final class BookshelfMenu extends BaseMenu
{
	public static final int ROWS = 6;
	public static final int COLS = 9;
	public static final int SIZE = ROWS * COLS;

	public BookshelfMenu(@Nullable MenuType<? extends BookshelfMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		Validate.notNull(blockEntity);
		var itemHandler = blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).resolve().orElseThrow();

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 8, 18, BookSlotItemHandler::new);
		bindPlayerInventory(this, 8, 140);
	}

	public static final class BookSlotItemHandler extends SlotItemHandler
	{
		public BookSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition)
		{
			super(itemHandler, index, xPosition, yPosition);
		}

		@Override
		public boolean mayPlace(@NotNull ItemStack stack)
		{
			if(stack.is(ItemTags.Vanilla.LECTERN_BOOKS))
				return true;
			if(stack.is(Items.BOOK))
				return true;
			return stack.is(Items.ENCHANTED_BOOK);
		}
	}
}
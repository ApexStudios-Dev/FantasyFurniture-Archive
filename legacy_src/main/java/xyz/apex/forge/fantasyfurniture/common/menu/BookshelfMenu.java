package xyz.apex.forge.fantasyfurniture.common.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apex.forge.apexcore.lib.container.BaseMenu;
import xyz.apex.forge.commonality.tags.ItemTags;

import java.util.Objects;

public final class BookshelfMenu extends BaseMenu
{
	public static final int ROWS = 6;
	public static final int COLS = 9;
	public static final int SIZE = ROWS * COLS;

	public BookshelfMenu(@Nullable MenuType<? extends BookshelfMenu> menuType, int windowId, Inventory playerInventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, playerInventory, buffer);

		var itemHandler = Objects.requireNonNull(getItemHandler());

		bindItemHandlerSlots(this, itemHandler, ROWS, COLS, 8, 18, BookSlotItemHandler::new);
		bindPlayerInventory(this, 8, 140);
	}

	@Nullable
	@Override
	protected IItemHandler getItemHandler()
	{
		var blockEntity = Objects.requireNonNull(player.level.getBlockEntity(pos));
		return getItemHandlerFromBlockEntity(blockEntity).resolve().orElse(null);
	}

	@Override
	protected void onInventoryChanges()
	{
		setBlockEntityChanged();
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

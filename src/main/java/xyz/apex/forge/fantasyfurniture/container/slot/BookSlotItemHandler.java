package xyz.apex.forge.fantasyfurniture.container.slot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public final class BookSlotItemHandler extends SlotItemHandler
{
	public BookSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(@Nonnull ItemStack stack)
	{
		Item item = stack.getItem();

		if(item.is(ItemTags.LECTERN_BOOKS))
			return true;
		if(item == Items.BOOK)
			return true;
		return item == Items.ENCHANTED_BOOK;
	}
}

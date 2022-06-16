package xyz.apex.forge.fantasyfurniture.container.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import xyz.apex.forge.commonality.init.ItemTags;

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
		if(stack.is(ItemTags.Vanilla.LECTERN_BOOKS))
			return true;
		if(stack.is(Items.BOOK))
			return true;
		return stack.is(Items.ENCHANTED_BOOK);
	}
}

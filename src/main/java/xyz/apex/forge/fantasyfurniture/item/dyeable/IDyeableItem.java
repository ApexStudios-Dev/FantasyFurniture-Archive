/*
package xyz.apex.forge.fantasyfurniture.item.dyeable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import net.minecraft.nbt.Tag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import xyz.apex.forge.fantasyfurniture.block.dyeable.IDyeableBlock;

public interface IDyeableItem
{
	String NBT_DYE_COLOR = IDyeableBlock.DYE_COLOR_PROPERTY.getName();
	DyeColor DEFAULT_DYE_COLOR = IDyeableBlock.DYE_COLOR_PROPERTY_DEFAULT_VALUE;

	default DyeColor getDefaultDyeColor()
	{
		return this instanceof BlockItem item && item.getBlock() instanceof IDyeableBlock block ? block.getDefaultDyeColor() : DEFAULT_DYE_COLOR;
	}

	static DyeColor getDyeColor(ItemStack stack)
	{
		var stackTag = stack.getTag();

		if(stackTag != null)
		{
			if(stackTag.contains("BlockStateTag", Tag.TAG_COMPOUND))
			{
				var blockStateTag = stackTag.getCompound("BlockStateTag");
				var name = IDyeableBlock.DYE_COLOR_PROPERTY.getName();

				if(blockStateTag.contains(name, Tag.TAG_STRING))
				{
					var dyeColorName = blockStateTag.getString(name);
					var opt = IDyeableBlock.DYE_COLOR_PROPERTY.getValue(dyeColorName);

					if(opt.isPresent())
						return opt.get();
				}
			}

			if(stackTag.contains(NBT_DYE_COLOR, Tag.TAG_STRING))
			{
				var dyeColorName = stackTag.getString(NBT_DYE_COLOR);
				var opt = IDyeableBlock.DYE_COLOR_PROPERTY.getValue(dyeColorName);

				if(opt.isPresent())
					return opt.get();
			}
		}

		return getDefaultDyeColor(stack);
	}

	static DyeColor getDefaultDyeColor(ItemStack stack)
	{
		return stack.getItem() instanceof IDyeableItem item ? item.getDefaultDyeColor() : DEFAULT_DYE_COLOR;
	}

	@CanIgnoreReturnValue
	static ItemStack withDyeColor(ItemStack stack, DyeColor color)
	{
		var stackTag = stack.getOrCreateTag();
		stackTag.putString(NBT_DYE_COLOR, IDyeableBlock.DYE_COLOR_PROPERTY.getName(color));
		stack.setTag(stackTag);
		return stack;
	}

	@CanIgnoreReturnValue
	static ItemStack withDefaultDyeColor(ItemStack stack)
	{
		return withDyeColor(stack, getDefaultDyeColor(stack));
	}
}*/
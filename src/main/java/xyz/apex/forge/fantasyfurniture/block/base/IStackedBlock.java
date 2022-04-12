package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraftforge.common.extensions.IForgeBlock;

public interface IStackedBlock extends IForgeBlock
{
	IntegerProperty getStackSizeProperty();

	default boolean isForStack(ItemStack stack)
	{
		return stack.getItem() == getBlock().asItem();
	}

	static int getMinValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i <= 0).min().orElse(0);
	}

	static int getMaxValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i >= 0).max().orElse(0);
	}
}

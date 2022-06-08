package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.extensions.IForgeBlock;

public interface IStackedBlock extends IForgeBlock
{
	IntegerProperty getStackSizeProperty();

	String getStackableTranslationKey();

	default MutableComponent getStackableTranslation()
	{
		return new TranslatableComponent(getStackableTranslationKey());
	}

	boolean isForStack(ItemStack stack);

	static int getMinValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i <= 0).min().orElse(0);
	}

	static int getMaxValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i >= 0).max().orElse(0);
	}
}

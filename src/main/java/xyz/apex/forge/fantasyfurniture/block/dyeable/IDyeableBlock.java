/*
package xyz.apex.forge.fantasyfurniture.block.dyeable;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public interface IDyeableBlock
{
	EnumProperty<DyeColor> DYE_COLOR_PROPERTY = EnumProperty.create("dye_color", DyeColor.class);
	DyeColor DYE_COLOR_PROPERTY_DEFAULT_VALUE = DyeColor.WHITE;

	default DyeColor getDefaultDyeColor()
	{
		return DYE_COLOR_PROPERTY_DEFAULT_VALUE;
	}

	static DyeColor getDyeColor(BlockState blockState)
	{
		return blockState.getOptionalValue(DYE_COLOR_PROPERTY).orElseGet(() -> getDefaultDyeColor(blockState));
	}

	static DyeColor getDefaultDyeColor(BlockState blockState)
	{
		return blockState.getBlock() instanceof IDyeableBlock block ? block.getDefaultDyeColor() : DYE_COLOR_PROPERTY_DEFAULT_VALUE;
	}

	static BlockState withDyeColor(BlockState blockState, DyeColor color)
	{
		return blockState.hasProperty(DYE_COLOR_PROPERTY) ? blockState.setValue(DYE_COLOR_PROPERTY, color) : blockState;
	}

	static BlockState withDefaultDyeColor(BlockState blockState)
	{
		return withDyeColor(blockState, getDefaultDyeColor(blockState));
	}
}*/
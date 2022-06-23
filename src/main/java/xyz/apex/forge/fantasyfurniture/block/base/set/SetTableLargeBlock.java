package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;

import java.util.function.Consumer;

public class SetTableLargeBlock extends BaseMultiBlock
{
	public SetTableLargeBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_2x2x1;
	}
}

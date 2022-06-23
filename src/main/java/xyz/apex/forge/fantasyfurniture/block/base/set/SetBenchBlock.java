package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.revamp.block.SeatMultiBlock;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;

import java.util.function.Consumer;

public class SetBenchBlock extends SeatMultiBlock
{
	public SetBenchBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_1x2x1;
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}
}

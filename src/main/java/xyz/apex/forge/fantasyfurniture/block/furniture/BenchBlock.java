package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.revamp.block.SeatMultiBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public class BenchBlock extends SeatMultiBlock
{
	public BenchBlock(Properties properties)
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
		return ModPatterns.PATTERN_1x2x1;
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_BENCH.has(blockState))
			return HitBoxes.NORDIC.bench(this, blockState);
		else if(ModBlocks.DUNMER_BENCH.has(blockState))
			return HitBoxes.DUNMER.bench(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
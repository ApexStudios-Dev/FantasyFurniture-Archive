package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public class TableLargeBlock extends BaseMultiBlock
{
	public TableLargeBlock(Properties properties)
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
		return ModPatterns.PATTERN_2x2x1;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_TABLE_LARGE.has(blockState))
			return HitBoxes.NORDIC.tableLarge(this, blockState);
		else if(ModBlocks.DUNMER_TABLE_LARGE.has(blockState))
			return HitBoxes.DUNMER.tableLarge(this, blockState);
		else if(ModBlocks.VENTHYR_TABLE_LARGE.has(blockState) || ModBlocks.VENTHYR_TABLE_LARGE_FANCY.has(blockState))
			return HitBoxes.VENTHYR.tableLarge(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
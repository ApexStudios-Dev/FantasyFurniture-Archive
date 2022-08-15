package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.apexcore.lib.block.SeatMultiBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public class ChairBlock extends SeatMultiBlock
{
	public ChairBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .3D;
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x1x2;
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
		if(ModBlocks.NORDIC_CHAIR.isIn(blockState))
			return HitBoxes.NORDIC.chair(this, blockState);
		else if(ModBlocks.DUNMER_CHAIR.isIn(blockState))
			return HitBoxes.DUNMER.chair(this, blockState);
		else if(ModBlocks.VENTHYR_CHAIR.isIn(blockState))
			return HitBoxes.VENTHYR.chair(this, blockState);
		else if(ModBlocks.BONE_SKELETON_CHAIR.isIn(blockState) || ModBlocks.BONE_WITHER_CHAIR.isIn(blockState))
			return HitBoxes.BONE.chair(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class OriginOnly extends ChairBlock
	{
		public OriginOnly(Properties properties)
		{
			super(properties);
		}

		@Override
		public boolean sitAtOriginOnly()
		{
			return true;
		}
	}
}
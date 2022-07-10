package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

public class PaintingWideBlock extends BaseMultiBlock
{
	public PaintingWideBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(FACING_4_WAY);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_PAINTING_WIDE.has(blockState))
			return HitBoxes.NORDIC.paintingWide(this, blockState);
		else if(ModBlocks.DUNMER_PAINTING_WIDE.has(blockState))
			return HitBoxes.DUNMER.paintingWide(this, blockState);
		else if(ModBlocks.VENTHYR_PAINTING_WIDE.has(blockState))
			return HitBoxes.VENTHYR.paintingWide(this, blockState);
		else if(ModBlocks.BONE_SKELETON_PAINTING_WIDE.has(blockState) || ModBlocks.BONE_WITHER_PAINTING_WIDE.has(blockState))
			return HitBoxes.BONE.paintingWide(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x2x1;
	}
}
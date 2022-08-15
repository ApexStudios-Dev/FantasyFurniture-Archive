package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class PaintingSmallBlock extends BaseBlock
{
	public PaintingSmallBlock(Properties properties)
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
		if(ModBlocks.NORDIC_PAINTING_SMALL.isIn(blockState))
			return HitBoxes.NORDIC.paintingSmall(this, blockState);
		else if(ModBlocks.DUNMER_PAINTING_SMALL.isIn(blockState))
			return HitBoxes.DUNMER.paintingSmall(this, blockState);
		else if(ModBlocks.VENTHYR_PAINTING_SMALL.isIn(blockState))
			return HitBoxes.VENTHYR.paintingSmall(this, blockState);
		else if(ModBlocks.BONE_SKELETON_PAINTING_SMALL.isIn(blockState) || ModBlocks.BONE_WITHER_PAINTING_SMALL.isIn(blockState))
			return HitBoxes.BONE.paintingSmall(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.SeatBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class StoolBlock extends SeatBlock
{
	public StoolBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
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
		if(ModBlocks.NORDIC_STOOL.has(blockState))
			return HitBoxes.NORDIC.stool(this, blockState);
		else if(ModBlocks.DUNMER_STOOL.has(blockState))
			return HitBoxes.DUNMER.stool(this, blockState);
		else if(ModBlocks.VENTHYR_STOOL.has(blockState))
			return HitBoxes.VENTHYR.stool(this, blockState);
		else if(ModBlocks.BONE_SKELETON_STOOL.has(blockState) || ModBlocks.BONE_WITHER_STOOL.has(blockState))
			return HitBoxes.BONE.stool(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
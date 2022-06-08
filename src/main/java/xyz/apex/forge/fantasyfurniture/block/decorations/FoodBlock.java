package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedBlock;

public final class FoodBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final VoxelShape SHAPE = box(2D, 0D, 2D, 14D, 5D, 14D);

	public FoodBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}
}

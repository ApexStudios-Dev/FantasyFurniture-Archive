package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BaseCarpetBlock extends Block
{
	public static final VoxelShape SHAPE = box(0D, 0D, 0D, 16D, 1D, 16D);

	public BaseCarpetBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos currentPos, BlockPos facingPos)
	{
		return !blockState.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, facing, facingBlockState, level, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		return !level.isEmptyBlock(pos.below());
	}
}

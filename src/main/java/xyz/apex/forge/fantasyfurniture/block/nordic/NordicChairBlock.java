package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseSeatDoubleBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicChairBlock extends BaseSeatDoubleBlock
{
	public static final VoxelShape SHAPE_UPPER_NORTH = BlockHelper.makeShape(
			box(12D, -16D, 12D, 14D, -9D, 14D),
			box(2D, -16D, 12D, 4D, -9D, 14D),
			box(2D, -16D, 2D, 4D, -9D, 4D),
			box(12D, -16D, 2D, 14D, -9D, 4D),
			box(2D, -9D, 2D, 14D, -7D, 14D),
			box(2D, -7D, 2D, 14D, 9D, 3D)
	);

	public static final VoxelShape SHAPE_UPPER_EAST = BlockHelper.makeShape(
			box(2D, -16D, 12D, 4D, -9D, 14D),
			box(2D, -16D, 2D, 4D, -9D, 4D),
			box(12D, -16D, 2D, 14D, -9D, 4D),
			box(12D, -16D, 12D, 14D, -9D, 14D),
			box(2D, -9D, 2D, 14D, -7D, 14D),
			box(13D, -7D, 2D, 14D, 9D, 14D)
	);

	public static final VoxelShape SHAPE_UPPER_SOUTH = BlockHelper.makeShape(
			box(2D, -16D, 2D, 4D, -9D, 4D),
			box(12D, -16D, 2D, 14D, -9D, 4D),
			box(12D, -16D, 12D, 14D, -9D, 14D),
			box(2D, -16D, 12D, 4D, -9D, 14D),
			box(2D, -9D, 2D, 14D, -7D, 14D),
			box(2D, -7D, 13D, 14D, 9D, 14D)
	);

	public static final VoxelShape SHAPE_UPPER_WEST = BlockHelper.makeShape(
			box(12D, -16D, 2D, 14D, -9D, 4D),
			box(12D, -16D, 12D, 14D, -9D, 14D),
			box(2D, -16D, 12D, 4D, -9D, 14D),
			box(2D, -16D, 2D, 4D, -9D, 4D),
			box(2D, -9D, 2D, 14D, -7D, 14D),
			box(2D, -7D, 2D, 3D, 9D, 14D)
	);

	public static final VoxelShape SHAPE_LOWER_NORTH = BlockHelper.makeShape(
			box(12D, 0D, 12D, 14D, 7D, 14D),
			box(2D, 0D, 12D, 4D, 7D, 14D),
			box(2D, 0D, 2D, 4D, 7D, 4D),
			box(12D, 0D, 2D, 14D, 7D, 4D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(2D, 9D, 2D, 14D, 25D, 3D)
	);

	public static final VoxelShape SHAPE_LOWER_EAST = BlockHelper.makeShape(
			box(2D, 0D, 12D, 4D, 7D, 14D),
			box(2D, 0D, 2D, 4D, 7D, 4D),
			box(12D, 0D, 2D, 14D, 7D, 4D),
			box(12D, 0D, 12D, 14D, 7D, 14D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(13D, 9D, 2D, 14D, 25D, 14D)
	);

	public static final VoxelShape SHAPE_LOWER_SOUTH = BlockHelper.makeShape(
			box(2D, 0D, 2D, 4D, 7D, 4D),
			box(12D, 0D, 2D, 14D, 7D, 4D),
			box(2D, 0D, 12D, 4D, 7D, 14D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(2D, 9D, 13D, 14D, 25D, 14D)
	);

	public static final VoxelShape SHAPE_LOWER_WEST = BlockHelper.makeShape(
			box(12D, 0D, 2D, 14D, 7D, 4D),
			box(12D, 0D, 12D, 14D, 7D, 14D),
			box(2D, 0D, 12D, 4D, 7D, 14D),
			box(2D, 0D, 2D, 4D, 7D, 4D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(2D, 9D, 2D, 3D, 25D, 14D)
	);

	public NordicChairBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	protected double getSeatYOffset(BlockState blockState)
	{
		return .3D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		DoubleBlockHalf half = blockState.getValue(HALF);
		Direction facing = blockState.getValue(FACING);

		if(half == DoubleBlockHalf.UPPER)
		{
			if(facing == Direction.SOUTH)
				return SHAPE_UPPER_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_UPPER_WEST;
			else if(facing == Direction.NORTH)
				return SHAPE_UPPER_NORTH;
			else if(facing == Direction.EAST)
				return SHAPE_UPPER_EAST;
		}
		else if(half == DoubleBlockHalf.LOWER)
		{
			if(facing == Direction.SOUTH)
				return SHAPE_LOWER_SOUTH;
			else if(facing == Direction.EAST)
				return SHAPE_LOWER_EAST;
			else if(facing == Direction.NORTH)
				return SHAPE_LOWER_NORTH;
			else if(facing == Direction.WEST)
				return SHAPE_LOWER_WEST;
		}

		return VoxelShapes.empty();
	}
}

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicShelfBlock extends BaseShelfBlock
{
	public static final VoxelShape SHAPE = box(0D, 14D, 0D, 16D, 16D, 16D);

	public static final VoxelShape SHAPE_LEFT_SOUTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(14.5D, 9D, 2D, 15.5D, 14D, 13D),
			box(13D, 6D, 13D, 16D, 14D, 16D)
	);

	public static final VoxelShape SHAPE_LEFT_WEST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(3D, 9D, 14.5D, 14D, 14D, 15.5D),
			box(0D, 6D, 13D, 3D, 14D, 16D)
	);

	public static final VoxelShape SHAPE_LEFT_NORTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(.5D, 9D, 3D, 1.5D, 14D, 14D),
			box(0D, 6D, 0D, 3D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_LEFT_EAST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(2D, 9D, .5D, 13D, 14D, 1.5D),
			box(13D, 6D, 0D, 16D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_RIGHT_SOUTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(1.5D, 9D, 2D, 2.5D, 14D, 13D),
			box(0D, 6D, 13D, 3D, 14D, 16D)
	);

	public static final VoxelShape SHAPE_RIGHT_WEST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(3D, 9D, 1.5D, 14D, 14D, 2.5D),
			box(0D, 6D, 0D, 3D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_RIGHT_NORTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(13.5D, 9D, 3D, 14.5D, 14D, 14D),
			box(13D, 6D, 0D, 16D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_RIGHT_EAST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(2D, 9D, 13.5D, 13D, 14D, 14.5D),
			box(13D, 6D, 13D, 16D, 14D, 16D)
	);

	public static final VoxelShape SHAPE_NONE_SOUTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(14.5D, 9D, 2D, 15.5D, 14D, 13D),
			box(13D, 6D, 13D, 16D, 14D, 16D),
			box(1.5D, 9D, 2D, 2.5D, 14D, 13D),
			box(0D, 6D, 13D, 3D, 14D, 16D)
	);

	public static final VoxelShape SHAPE_NONE_WEST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(3D, 9D, 14.5D, 14D, 14D, 15.5D),
			box(0D, 6D, 13D, 3D, 14D, 16D),
			box(3D, 9D, 1.5D, 14D, 14D, 2.5D),
			box(0D, 6D, 0D, 3D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_NONE_NORTH = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(.5D, 9D, 3D, 1.5D, 14D, 14D),
			box(0D, 6D, 0D, 3D, 14D, 3D),
			box(13.5D, 9D, 3D, 14.5D, 14D, 14D),
			box(13D, 6D, 0D, 16D, 14D, 3D)
	);

	public static final VoxelShape SHAPE_NONE_EAST = BlockHelper.makeShape(
			box(0D, 14D, 0D, 16D, 16D, 16D),
			box(2D, 9D, .5D, 13D, 14D, 1.5D),
			box(13D, 6D, 0D, 16D, 14D, 3D),
			box(2D, 9D, 13.5D, 13D, 14D, 14.5D),
			box(13D, 6D, 13D, 16D, 14D, 16D)
	);

	public NordicShelfBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CONNECTION_TYPE, ConnectionType.NONE));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		ConnectionType connectionType = blockState.getValue(CONNECTION_TYPE);

		if(connectionType == ConnectionType.BOTH)
			return SHAPE;
		else if(connectionType == ConnectionType.LEFT)
		{
			if(facing == Direction.SOUTH)
				return SHAPE_LEFT_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_LEFT_WEST;
			else if(facing == Direction.NORTH)
				return SHAPE_LEFT_NORTH;
			else if(facing == Direction.EAST)
				return SHAPE_LEFT_EAST;
		}
		else if(connectionType == ConnectionType.RIGHT)
		{
			if(facing == Direction.SOUTH)
				return SHAPE_RIGHT_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_RIGHT_WEST;
			else if(facing == Direction.NORTH)
				return SHAPE_RIGHT_NORTH;
			else if(facing == Direction.EAST)
				return SHAPE_RIGHT_EAST;
		}
		else if(connectionType == ConnectionType.NONE)
		{
			if(facing == Direction.SOUTH)
				return SHAPE_NONE_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_NONE_WEST;
			else if(facing == Direction.NORTH)
				return SHAPE_NONE_NORTH;
			else if(facing == Direction.EAST)
				return SHAPE_NONE_EAST;
		}

		return VoxelShapes.empty();
	}
}

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseSofaBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicSofaBlock extends BaseSofaBlock
{
	public static final VoxelShape SHAPE_NORTH_NONE = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(14D, 10D, 0D, 16D, 12D, 14D),
			box(0D, 10D, 0D, 2D, 12D, 14D),
			box(0D, 6D, 0D, 2D, 10D, 2D),
			box(14D, 6D, 0D, 16D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_NORTH_LEFT = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(14D, 10D, 0D, 16D, 12D, 13D),
			box(14D, 6D, 0D, 16D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_NORTH_RIGHT = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(0D, 10D, 0D, 2D, 12D, 13D),
			box(0D, 6D, 0D, 2D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_NORTH_CORNER = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 13D)
	);

	public static final VoxelShape SHAPE_NORTH_CENTER = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_EAST_NONE = BlockHelper.makeShape(
			box(13D, 0D, 1D, 15D, 3D, 3D    ),
			box(1D, 0D, 1D, 3D, 3D, 3D  ),
			box(1D, 0D, 13D, 3D, 3D, 15D    ),
			box(13D, 0D, 13D, 15D, 3D, 15D  ),
			box(0D, 3D, 0D, 16D, 6D, 16D    ),
			box(0D, 6D, 0D, 3D, 16D, 16D    ),
			box(2D, 10D, 14D, 16D, 12D, 16D ),
			box(2D, 10D, 0D, 16D, 12D, 2D   ),
			box(14D, 6D, 0D, 16D, 10D, 2D   ),
			box(14D, 6D, 14D, 16D, 10D, 16D )
	);

	public static final VoxelShape SHAPE_EAST_LEFT = BlockHelper.makeShape(
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 3D, 16D, 16D),
			box(3D, 10D, 14D, 16D, 12D, 16D),
			box(14D, 6D, 14D, 16D, 10D, 16D)
	);

	public static final VoxelShape SHAPE_EAST_RIGHT = BlockHelper.makeShape(
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 3D, 16D, 16D),
			box(3D, 10D, 0D, 16D, 12D, 2D),
			box(14D, 6D, 0D, 16D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_EAST_CORNER = BlockHelper.makeShape(
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 3D, 16D, 16D),
			box(3D, 6D, 13D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_EAST_CENTER = BlockHelper.makeShape(
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 3D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH_NONE = BlockHelper.makeShape(
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 16D, 16D, 3D),
			box(0D, 10D, 2D, 2D, 12D, 16D),
			box(14D, 10D, 2D, 16D, 12D, 16D),
			box(14D, 6D, 14D, 16D, 10D, 16D),
			box(0D, 6D, 14D, 2D, 10D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH_LEFT = BlockHelper.makeShape(
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 16D, 16D, 3D),
			box(0D, 10D, 3D, 2D, 12D, 16D),
			box(0D, 6D, 14D, 2D, 10D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH_RIGHT = BlockHelper.makeShape(
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 16D, 16D, 3D),
			box(14D, 10D, 3D, 16D, 12D, 16D),
			box(14D, 6D, 14D, 16D, 10D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH_CORNER = BlockHelper.makeShape(
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 16D, 16D, 3D),
			box(0D, 6D, 3D, 3D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH_CENTER = BlockHelper.makeShape(
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 0D, 16D, 16D, 3D)
	);

	public static final VoxelShape SHAPE_WEST_NONE = BlockHelper.makeShape(
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 16D),
			box(0D, 10D, 0D, 14D, 12D, 2D),
			box(0D, 10D, 14D, 14D, 12D, 16D),
			box(0D, 6D, 14D, 2D, 10D, 16D),
			box(0D, 6D, 0D, 2D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_WEST_LEFT = BlockHelper.makeShape(
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 16D),
			box(0D, 10D, 0D, 13D, 12D, 2D),
			box(0D, 6D, 0D, 2D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_WEST_RIGHT = BlockHelper.makeShape(
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 16D),
			box(0D, 10D, 14D, 13D, 12D, 16D),
			box(0D, 6D, 14D, 2D, 10D, 16D)
	);

	public static final VoxelShape SHAPE_WEST_CORNER = BlockHelper.makeShape(
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 16D),
			box(0D, 6D, 0D, 13D, 16D, 3D)
	);

	public static final VoxelShape SHAPE_WEST_CENTER = BlockHelper.makeShape(
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 16D)
	);

	public NordicSofaBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false).setValue(CONNECTION_TYPE, ConnectionType.NONE));
	}

	@Override
	protected double getSeatYOffset(BlockState blockState)
	{
		return .175D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		ConnectionType connectionType = blockState.getValue(CONNECTION_TYPE);

		if(facing == Direction.NORTH)
		{
			if(connectionType == ConnectionType.NONE)
				return SHAPE_NORTH_NONE;
			else if(connectionType == ConnectionType.LEFT)
				return SHAPE_NORTH_LEFT;
			else if(connectionType == ConnectionType.RIGHT)
				return SHAPE_NORTH_RIGHT;
			else if(connectionType == ConnectionType.CORNER)
				return SHAPE_NORTH_CORNER;
			else if(connectionType == ConnectionType.CENTER)
				return SHAPE_NORTH_CENTER;
		}
		else if(facing == Direction.EAST)
		{
			if(connectionType == ConnectionType.NONE)
				return SHAPE_EAST_NONE;
			else if(connectionType == ConnectionType.LEFT)
				return SHAPE_EAST_LEFT;
			else if(connectionType == ConnectionType.RIGHT)
				return SHAPE_EAST_RIGHT;
			else if(connectionType == ConnectionType.CORNER)
				return SHAPE_EAST_CORNER;
			else if(connectionType == ConnectionType.CENTER)
				return SHAPE_EAST_CENTER;
		}
		else if(facing == Direction.SOUTH)
		{
			if(connectionType == ConnectionType.NONE)
				return SHAPE_SOUTH_NONE;
			else if(connectionType == ConnectionType.LEFT)
				return SHAPE_SOUTH_LEFT;
			else if(connectionType == ConnectionType.RIGHT)
				return SHAPE_SOUTH_RIGHT;
			else if(connectionType == ConnectionType.CORNER)
				return SHAPE_SOUTH_CORNER;
			else if(connectionType == ConnectionType.CENTER)
				return SHAPE_SOUTH_CENTER;
		}
		else if(facing == Direction.WEST)
		{
			if(connectionType == ConnectionType.NONE)
				return SHAPE_WEST_NONE;
			else if(connectionType == ConnectionType.LEFT)
				return SHAPE_WEST_LEFT;
			else if(connectionType == ConnectionType.RIGHT)
				return SHAPE_WEST_RIGHT;
			else if(connectionType == ConnectionType.CORNER)
				return SHAPE_WEST_CORNER;
			else if(connectionType == ConnectionType.CENTER)
				return SHAPE_WEST_CENTER;
		}

		return VoxelShapes.empty();
	}
}

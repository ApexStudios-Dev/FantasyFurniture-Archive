package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BasePaintingDoubleBlock;

public final class NordicPaintingWideBlock extends BasePaintingDoubleBlock
{
	public static final VoxelShape SHAPE_MAIN_NORTH = box(-16D, 0D, 14D, 16D, 16D, 16D);
	public static final VoxelShape SHAPE_MAIN_EAST = box(0D, 0D, -16D, 2D, 16D, 16D);
	public static final VoxelShape SHAPE_MAIN_SOUTH = box(0D, 0D, 0D, 32D, 16D, 2D);
	public static final VoxelShape SHAPE_MAIN_WEST = box(14D, 0D, 0D, 16D, 16D, 32D);
	public static final VoxelShape SHAPE_OTHER_NORTH = box(0D, 0D, 14D, 32D, 16D, 16D);
	public static final VoxelShape SHAPE_OTHER_EAST = box(0D, 0D, 0D, 2D, 16D, 32D);
	public static final VoxelShape SHAPE_OTHER_SOUTH = box(-16D, 0D, 0D, 16D, 16D, 2D);
	public static final VoxelShape SHAPE_OTHER_WEST = box(14D, 0D, -16D, 16D, 16D, 16D);

	public NordicPaintingWideBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, Type.MAIN));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);

		if(blockState.getValue(TYPE) == Type.MAIN)
		{
			if(facing == Direction.EAST)
				return SHAPE_MAIN_EAST;
			else if(facing == Direction.SOUTH)
				return SHAPE_MAIN_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_MAIN_WEST;

			return SHAPE_MAIN_NORTH;
		}
		else
		{
			if(facing == Direction.EAST)
				return SHAPE_OTHER_EAST;
			else if(facing == Direction.SOUTH)
				return SHAPE_OTHER_SOUTH;
			else if(facing == Direction.WEST)
				return SHAPE_OTHER_WEST;

			return SHAPE_OTHER_NORTH;
		}
	}
}

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BasePaintingBlock;

public final class NordicPaintingSmallBlock extends BasePaintingBlock
{
	public static final VoxelShape SHAPE_NORTH = box(0D, 0D, 14D, 16D, 16D, 16D);
	public static final VoxelShape SHAPE_EAST = box(0D, 0D, 0D, 2D, 16D, 16D);
	public static final VoxelShape SHAPE_SOUTH = box(0D, 0D, 0D, 16D, 16D, 2D);
	public static final VoxelShape SHAPE_WEST = box(14D, 0D, 0D, 16D, 16D, 16D);

	public NordicPaintingSmallBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		switch(blockState.getValue(FACING))
		{
			default:
			case NORTH: return SHAPE_NORTH;

			case EAST: return SHAPE_EAST;
			case SOUTH: return SHAPE_SOUTH;
			case WEST: return SHAPE_WEST;
		}
	}
}

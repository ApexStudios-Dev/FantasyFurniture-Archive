package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.util.math.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.BaseTableWideBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicTableWideBlock extends BaseTableWideBlock
{
	public static final VoxelShape SHAPE_NORTH = BlockHelper.makeShape(
			box(14D, 0D, 13D, 16D, 9D, 15D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(1D, 7D, 13D, 3D, 13D, 15D),
			box(1D, 7D, -15D, 3D, 13D, -13D),
			box(13D, 7D, -15D, 15D, 13D, -13D),
			box(14D, 0D, -15D, 16D, 9D, -13D),
			box(0D, 0D, -15D, 2D, 9D, -13D),
			box(0D, 0D, 13D, 2D, 9D, 15D),
			box(0D, 13D, -16D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_EAST = BlockHelper.makeShape(
			box(29D, 0D, 0D, 31D, 9D, 2D),
			box(29D, 7D, 1D, 31D, 13D, 3D),
			box(29D, 7D, 13D, 31D, 13D, 15D),
			box(1D, 7D, 13D, 3D, 13D, 15D),
			box(1D, 7D, 1D, 3D, 13D, 3D),
			box(1D, 0D, 0D, 3D, 9D, 2D),
			box(1D, 0D, 14D, 3D, 9D, 16D),
			box(29D, 0D, 14D, 31D, 9D, 16D),
			box(0D, 13D, 0D, 32D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_SOUTH = BlockHelper.makeShape(
			box(14D, 0D, 29D, 16D, 9D, 31D),
			box(13D, 7D, 29D, 15D, 13D, 31D),
			box(1D, 7D, 29D, 3D, 13D, 31D),
			box(1D, 7D, 1D, 3D, 13D, 3D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(14D, 0D, 1D, 16D, 9D, 3D),
			box(0D, 0D, 1D, 2D, 9D, 3D),
			box(0D, 0D, 29D, 2D, 9D, 31D),
			box(0D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShape SHAPE_WEST = BlockHelper.makeShape(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D)
	);

	public NordicTableWideBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		// registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	/*@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction connectedDirection = getConnectedDirection(blockState);

		switch(connectedDirection)
		{
			case NORTH: return SHAPE_NORTH;
			case EAST: return SHAPE_EAST;
			case SOUTH: return SHAPE_SOUTH;
			case WEST: return SHAPE_WEST;
		}

		return VoxelShapes.empty();
	}*/
}

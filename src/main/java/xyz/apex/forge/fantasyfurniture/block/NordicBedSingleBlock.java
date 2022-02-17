package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public final class NordicBedSingleBlock extends BaseBedBlock
{
	private static final VoxelShape NORTH_SHAPE = BlockHelper.makeShape(
			box(0D, 0D, 0D, 16D, 14D, 2D),
			box(0D, 0D, 30D, 16D, 14D, 32D),
			box(0D, 3D, 2D, 16D, 5D, 30D),
			box(1D, 5D, 2D, 15D, 8D, 30D)
	);

	private static final VoxelShape SOUTH_SHAPE = BlockHelper.makeShape(
			box(0D, 0D, -16D, 16D, 14D, -14D),
			box(0D, 0D, 14D, 16D, 14D, 16D),
			box(0D, 3D, -14D, 16D, 5D, 14D),
			box(1D, 5D, -14D, 15D, 8D, 14D)
	);

	private static final VoxelShape EAST_SHAPE = BlockHelper.makeShape(
			box(14D, 0D, 0D, 16D, 14D, 16D),
			box(-16D, 0D, 0D, -14D, 14D, 16D),
			box(-14D, 3D, 0D, 14D, 5D, 16D),
			box(-14D, 5D, 1D, 14D, 8D, 15D)
	);

	private static final VoxelShape WEST_SHAPE = BlockHelper.makeShape(
			box(30D, 0D, 0D, 32D, 14D, 16D),
			box(0D, 0D, 0D, 2D, 14D, 16D),
			box(2D, 3D, 0D, 30D, 5D, 16D),
			box(2D, 5D, 1D, 30D, 8D, 15D)
	);

	public NordicBedSingleBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction direction = getConnectedDirection(blockState).getOpposite();

		switch(direction)
		{
			case NORTH: return NORTH_SHAPE;
			default:
			case EAST: return EAST_SHAPE;
			case SOUTH: return SOUTH_SHAPE;
			case WEST: return WEST_SHAPE;
		}
	}
}

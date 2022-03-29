package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.SimpleFourWayBlock;

public final class BerryBasketBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE_NS = BlockHelper.makeShape(
			box(2D, 0D, 3.5D, 14D, 5D, 12.5D),
			box(1.5D, 5D, 3D, 14.5D, 6D, 13D),
			box(7D, 6D, 3.25D, 9D, 11.75D, 12.75D)
	);

	public static final VoxelShape SHAPE_EW = BlockHelper.makeShape(
			box(3.5D, 0D, 2D, 12.5D, 5D, 14D),
			box(3D, 5D, 1.5D, 13D, 6D, 14.5D),
			box(3.25D, 6D, 7D, 12.75D, 11.75D, 9D)
	);

	public BerryBasketBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);

		switch(facing)
		{
			case NORTH:
			case SOUTH:
				return SHAPE_NS;

			case EAST:
			case WEST:
				return SHAPE_EW;
		}

		return VoxelShapes.empty();
	}
}

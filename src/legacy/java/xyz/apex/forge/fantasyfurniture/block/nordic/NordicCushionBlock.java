package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseSeatBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicCushionBlock extends BaseSeatBlock
{
	private static final VoxelShape SHAPE = BlockHelper.makeShape(
			box(2D, 0D, 2D, 4D, 4D, 4D),
			box(2D, 0D, 12D, 4D, 4D, 14D),
			box(12D, 0D, 12D, 14D, 4D, 14D),
			box(12D, 0D, 2D, 14D, 4D, 4D),
			box(2D, 4D, 2D, 14D, 7D, 14D)
	);

	public NordicCushionBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false));
	}

	@Override
	protected double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}
}

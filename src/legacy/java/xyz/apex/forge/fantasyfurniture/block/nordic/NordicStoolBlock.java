package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BaseSeatBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicStoolBlock extends BaseSeatBlock
{
	public static final VoxelShape NORTH = BlockHelper.makeShape(
			Block.box(2, 0, 2, 4, 3, 4),
			Block.box(12, 0, 12, 14, 3, 14),
			Block.box(12, 0, 2, 14, 3, 4),
			Block.box(2, 0, 12, 4, 3, 14),
			Block.box(2, 3, 11.5, 4, 5, 13.5),
			Block.box(12, 3, 11.5, 14, 5, 13.5),
			Block.box(12, 3, 2.5, 14, 5, 4.5),
			Block.box(2, 3, 2.5, 4, 5, 4.5),
			Block.box(1.5, 5, 1.75, 14.5, 7, 14.25)
	);

	public static final VoxelShape EAST = BlockHelper.makeShape(
			Block.box(2, 0, 12, 4, 3, 14),
			Block.box(12, 0, 2, 14, 3, 4),
			Block.box(2, 0, 2, 4, 3, 4),
			Block.box(12, 0, 12, 14, 3, 14),
			Block.box(11.5, 3, 12, 13.5, 5, 14),
			Block.box(11.5, 3, 2, 13.5, 5, 4),
			Block.box(2.5, 3, 2, 4.5, 5, 4),
			Block.box(2.5, 3, 12, 4.5, 5, 14),
			Block.box(1.75, 5, 1.5, 14.25, 7, 14.5)
	);

	public NordicStoolBlock(AbstractBlock.Properties properties)
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
		Direction facing = blockState.getValue(FACING);
		return facing == Direction.NORTH || facing.getOpposite() == Direction.NORTH ? NORTH : EAST;
	}
}

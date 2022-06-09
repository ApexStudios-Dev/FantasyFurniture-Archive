package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;

public final class DunmerTableLargeBlock extends SetTableLargeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 13D, 4D),
			box(-14D, 0D, 2D, -12D, 13D, 4D),
			box(-14D, 0D, 28D, -12D, 13D, 30D),
			box(12D, 0D, 28D, 14D, 13D, 30D),
			box(-16D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerTableLargeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShape shape = SHAPER.get(facing);
		int index = pattern.getIndex(blockState);

		if(index == 1 || index == 3)
		{
			Direction other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}
}

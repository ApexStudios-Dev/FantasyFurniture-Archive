package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedDoubleBlock;

public final class NordicBedDoubleBlock extends SetBedDoubleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-16D, 3D, 2D, 16D, 5D, 30D),
			box(-14D, 5D, 2D, 14D, 8D, 30D),
			box(-16D, 3D, 0D, 16D, 5D, 2D),
			box(-16D, 0D, 0D, -14D, 8D, 2D),
			box(14D, 0D, 0D, 16D, 8D, 2D),
			box(-16D, 12D, 0D, -8D, 14D, 2D),
			box(8D, 12D, 0D, 16D, 14D, 2D),
			box(-10D, 12D, 0D, 10D, 16D, 2D),
			box(-15D, 5D, 0D, 15D, 12D, 2D),
			box(-15D, 5D, 30D, 15D, 12D, 32D),
			box(-16D, 3D, 30D, 16D, 5D, 32D),
			box(-16D, 0D, 30D, -14D, 8D, 32D),
			box(14D, 0D, 30D, 16D, 8D, 32D),
			box(-16D, 12D, 30D, -8D, 14D, 32D),
			box(8D, 12D, 30D, 16D, 14D, 32D),
			box(-10D, 12D, 30D, 10D, 16D, 32D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicBedDoubleBlock(Properties properties, MultiBlockPattern pattern)
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

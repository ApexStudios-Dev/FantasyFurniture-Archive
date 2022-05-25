package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBenchBlock;

public final class NordicBenchBlock extends SetBenchBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 3D, 4D),
			box(-14D, 0D, 2D, -12D, 3D, 4D),
			box(-14D, 0D, 12D, -12D, 3D, 14D),
			box(12D, 0D, 12D, 14D, 3D, 14D),
			box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
			box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
			box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
			box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
			box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
			box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
			box(-15D, 5D, 2D, 15D, 7D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicBenchBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShape shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
		{
			Direction other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

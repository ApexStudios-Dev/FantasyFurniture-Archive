package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBenchBlock;

public final class DunmerBenchBlock extends SetBenchBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(12, 0, 2, 14, 5, 4),
			Block.box(-14, 0, 2, -12, 5, 4),
			Block.box(-14, 0, 12, -12, 5, 14),
			Block.box(12, 0, 12, 14, 5, 14),
			Block.box(-15, 5, 1, 15, 7, 15),
			Block.box(12.5, 2.5, 4, 13.5, 3.5, 12),
			Block.box(-13.5, 2.5, 4, -12.5, 3.5, 12)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerBenchBlock(Properties properties, MultiBlockPattern pattern)
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

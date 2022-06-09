package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWardrobeBlock;

public final class NordicWardrobeBlock extends SetWardrobeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
			box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
			box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
			box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
			box(-14D, 2D, 1D, 14D, 31D, 15D),
			box(-15D, 31D, 0D, 15D, 32D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicWardrobeBlock(Properties properties, MultiBlockPattern pattern)
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
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}
}

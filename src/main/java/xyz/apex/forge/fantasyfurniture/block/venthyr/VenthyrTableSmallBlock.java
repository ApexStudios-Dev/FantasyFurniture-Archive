package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableSmallBlock;

public final class VenthyrTableSmallBlock extends SetTableSmallBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 4D, 2D, 4D),
			box(1D, 0D, 12D, 4D, 2D, 15D),
			box(12D, 0D, 12D, 15D, 2D, 15D),
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
			box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
			box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrTableSmallBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}
}

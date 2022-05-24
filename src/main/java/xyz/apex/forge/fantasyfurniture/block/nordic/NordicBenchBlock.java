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
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
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

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(28D, 0D, 2D, 30D, 3D, 4D),
			box(2D, 0D, 2D, 4D, 3D, 4D),
			box(2D, 0D, 12D, 4D, 3D, 14D),
			box(28D, 0D, 12D, 30D, 3D, 14D),
			box(28D, 3D, 11.5D, 30D, 5D, 13.5D),
			box(28D, 3D, 2.5D, 30D, 5D, 4.5D),
			box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
			box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
			box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
			box(28.5D, 3.5D, 4.5D, 29.5D, 4.5D, 11.5D),
			box(1D, 5D, 2D, 31D, 7D, 14D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicBenchBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = pattern.isOrigin(blockState) ? SHAPER_A : SHAPER_B;
		return shaper.get(facing);
	}
}

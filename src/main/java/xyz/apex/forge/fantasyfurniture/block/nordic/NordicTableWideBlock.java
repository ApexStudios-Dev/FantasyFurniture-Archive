package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;

public final class NordicTableWideBlock extends SetTableWideBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-15D, 7D, 1D, -13D, 13D, 3D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(1D, 0D, 0D, 3D, 9D, 2D),
			box(1D, 7D, 1D, 3D, 13D, 3D),
			box(1D, 7D, 13D, 3D, 13D, 15D),
			box(29D, 7D, 13D, 31D, 13D, 15D),
			box(29D, 0D, 0D, 31D, 9D, 2D),
			box(29D, 0D, 14D, 31D, 9D, 16D),
			box(1D, 0D, 14D, 3D, 9D, 16D),
			box(0D, 13D, 0D, 32D, 16D, 16D),
			box(29D, 7D, 1D, 31D, 13D, 3D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicTableWideBlock(Properties properties, MultiBlockPattern pattern)
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

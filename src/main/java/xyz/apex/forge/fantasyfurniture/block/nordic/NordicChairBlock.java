package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.SeatMultiBlock;

public final class NordicChairBlock extends SeatMultiBlock
{
	public static final VoxelShape SHAPE_UPPER = VoxelShaper.or(
			box(2D, 0D, 2D, 4D, 4D, 4D),
			box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
			box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
			box(12D, 0D, 2D, 14D, 4D, 4D),
			box(2D, 0D, 12D, 4D, 4D, 14D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(2D, 9D, 13D, 14D, 25D, 14D),
			box(12D, 0D, 12D, 14D, 4D, 14D),
			box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
			box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
			box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
			box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
	);

	public static final VoxelShape SHAPE_LOWER = VoxelShaper.or(
			box(2D, -16D, 2D, 4D, -12D, 4D),
			box(2.5D, -11.5D, 4.5D, 3.5D, -10.5D, 11.5D),
			box(12.5D, -11.5D, 4.5D, 13.5D, -10.5, 11.5),
			box(12D, -16D, 2D, 14D, -12D, 4D),
			box(2D, -16D, 12D, 4D, -12D, 14D),
			box(2D, -9D, 2D, 14D, -7D, 14D),
			box(2D, -7D, 13D, 14D, 9D, 14D),
			box(12D, -16D, 12D, 14D, -12D, 14D),
			box(2D, -12D, 11.5D, 4D, -9D, 13.5D),
			box(12D, -12D, 11.5D, 14D, -9D, 13.5D),
			box(2D, -12D, 2.5D, 4D, -9D, 4.5D),
			box(12D, -12D, 2.5D, 14D, -9D, 4.5D)
	);

	public static final VoxelShaper SHAPER_UPPER = VoxelShaper.forHorizontal(SHAPE_UPPER, Direction.NORTH);
	public static final VoxelShaper SHAPER_LOWER = VoxelShaper.forHorizontal(SHAPE_LOWER, Direction.NORTH);

	public NordicChairBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .3D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = pattern.isOrigin(blockState) ? SHAPER_UPPER : SHAPER_LOWER;
		return shaper.get(facing);
	}
}

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;

public final class NordicTableLargeBlock extends SetTableLargeBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 13D, 4D),
			box(-14D, 0D, 2D, -12D, 13D, 4D),
			box(-14D, 0D, 28D, -12D, 13D, 30D),
			box(12D, 0D, 28D, 14D, 13D, 30D),
			box(-16D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(12D, 0D, -14D, 14D, 13D, -12D),
			box(-14D, 0D, -14D, -12D, 13D, -12D),
			box(-14D, 0D, 12D, -12D, 13D, 14D),
			box(12D, 0D, 12D, 14D, 13D, 14D),
			box(-16D, 13D, -16D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_C = VoxelShaper.or(
			box(28D, 0D, 2D, 30D, 13D, 4D),
			box(2D, 0D, 2D, 4D, 13D, 4D),
			box(2D, 0D, 28D, 4D, 13D, 30D),
			box(28D, 0D, 28D, 30D, 13D, 30D),
			box(0D, 13D, 0D, 32D, 16D, 32D)
	);

	public static final VoxelShape SHAPE_D = VoxelShaper.or(
			box(28D, 0D, -14D, 30D, 13D, -12D),
			box(2D, 0D, -14D, 4D, 13D, -12D),
			box(2D, 0D, 12D, 4D, 13D, 14D),
			box(28D, 0D, 12D, 30D, 13D, 14D),
			box(0D, 13D, -16D, 32D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_C = VoxelShaper.forHorizontal(SHAPE_C, Direction.NORTH);
	public static final VoxelShaper SHAPER_D = VoxelShaper.forHorizontal(SHAPE_D, Direction.NORTH);

	public NordicTableLargeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int index = pattern.getIndex(blockState);
		VoxelShaper shaper;

		switch(index)
		{
			default:
			case 0:
				shaper = SHAPER_A;
				break;
			case 1:
				shaper = SHAPER_C;
				break;
			case 2:
				shaper = SHAPER_B;
				break;
			case 3:
				shaper = SHAPER_D;
				break;
		}

		return shaper.get(facing);
	}
}

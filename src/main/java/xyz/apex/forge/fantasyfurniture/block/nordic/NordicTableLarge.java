package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.SimpleFourWayWaterLoggedMultiBlock;

public final class NordicTableLarge extends SimpleFourWayWaterLoggedMultiBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 13D, 4D),
			box(-14D, 0D, 2D, -12D, 13D, 4D),
			box(-14D, 0D, 28D, -12D, 13D, 30D),
			box(12D, 0D, 28D, 14D, 13D, 30D),
			box(-16D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			Block.box(12, 0, -14, 14, 13, -12),
			Block.box(-14, 0, -14, -12, 13, -12),
			Block.box(-14, 0, 12, -12, 13, 14),
			Block.box(12, 0, 12, 14, 13, 14),
			Block.box(-16, 13, -16, 16, 16, 16)
	);

	public static final VoxelShape SHAPE_C = VoxelShaper.or(
			Block.box(28, 0, 2, 30, 13, 4),
			Block.box(2, 0, 2, 4, 13, 4),
			Block.box(2, 0, 28, 4, 13, 30),
			Block.box(28, 0, 28, 30, 13, 30),
			Block.box(0, 13, 0, 32, 16, 32)
	);

	public static final VoxelShape SHAPE_D = VoxelShaper.or(
			Block.box(28, 0, -14, 30, 13, -12),
			Block.box(2, 0, -14, 4, 13, -12),
			Block.box(2, 0, 12, 4, 13, 14),
			Block.box(28, 0, 12, 30, 13, 14),
			Block.box(0, 13, -16, 32, 16, 16)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_C = VoxelShaper.forHorizontal(SHAPE_C, Direction.NORTH);
	public static final VoxelShaper SHAPER_D = VoxelShaper.forHorizontal(SHAPE_D, Direction.NORTH);

	public NordicTableLarge(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
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

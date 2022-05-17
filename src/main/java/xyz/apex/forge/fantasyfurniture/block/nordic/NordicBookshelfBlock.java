package xyz.apex.forge.fantasyfurniture.block.nordic;

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

public final class NordicBookshelfBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(-15D, 0D, 1D, 15D, 30D, 15D),
			box(-16D, 30D, 0D, 16D, 32D, 16D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(1D, 0D, 1D, 31D, 30D, 15D),
			box(0D, 30D, 0D, 32D, 32D, 16D)
	);

	public static final VoxelShape SHAPE_C = VoxelShaper.or(
			box(-15D, -16D, 1D, 15D, 14D, 15D),
			box(-16D, 14D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_D = VoxelShaper.or(
			box(1D, -16D, 1D, 31D, 14D, 15D),
			box(0D, 14D, 0D, 32D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_C = VoxelShaper.forHorizontal(SHAPE_C, Direction.NORTH);
	public static final VoxelShaper SHAPER_D = VoxelShaper.forHorizontal(SHAPE_D, Direction.NORTH);

	public NordicBookshelfBlock(Properties properties, MultiBlockPattern pattern)
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
				shaper = SHAPER_B;
				break;
			case 2:
				shaper = SHAPER_C;
				break;
			case 3:
				shaper = SHAPER_D;
				break;
		}

		return shaper.get(facing);
	}
}

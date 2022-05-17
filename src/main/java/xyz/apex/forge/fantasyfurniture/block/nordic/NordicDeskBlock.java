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
import xyz.apex.forge.fantasyfurniture.init.Nordic;

public final class NordicDeskBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public static final VoxelShape SHAPE_LEFT_A = VoxelShaper.or(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-15D, 7D, 1D, -13D, 13D, 3D),
			box(5D, 9D, 2D, 12D, 13D, 11D)
	);

	public static final VoxelShape SHAPE_LEFT_B = VoxelShaper.or(
			box(29D, 0D, 0D, 31D, 9D, 2D),
			box(29D, 7D, 1D, 31D, 13D, 3D),
			box(29D, 7D, 13D, 31D, 13D, 15D),
			box(1D, 7D, 13D, 3D, 13D, 15D),
			box(1D, 0D, 0D, 3D, 9D, 2D),
			box(1D, 0D, 14D, 3D, 9D, 16D),
			box(29D, 0D, 14D, 31D, 9D, 16D),
			box(0D, 13D, 0D, 32D, 16D, 16D),
			box(1D, 7D, 1D, 3D, 13D, 3D),
			box(21D, 9D, 2D, 28D, 13D, 11D)
	);

	public static final VoxelShape SHAPE_RIGHT_A = VoxelShaper.or(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-15D, 7D, 1D, -13D, 13D, 3D),
			box(-12D, 9D, 2D, -5D, 13D, 11D)
	);

	public static final VoxelShape SHAPE_RIGHT_B = VoxelShaper.or(
		box(29D, 0D, 0D, 31D, 9D, 2D),
		box(29D, 7D, 1D, 31D, 13D, 3D),
		box(29D, 7D, 13D, 31D, 13D, 15D),
		box(1D, 7D, 13D, 3D, 13D, 15D),
		box(1D, 0D, 0D, 3D, 9D, 2D),
		box(1D, 0D, 14D, 3D, 9D, 16D),
		box(29D, 0D, 14D, 31D, 9D, 16D),
		box(0D, 13D, 0D, 32D, 16D, 16D),
		box(1D, 7D, 1D, 3D, 13D, 3D),
		box(4D, 9D, 2D, 11D, 13D, 11D)
	);

	public static final VoxelShaper SHAPER_LEFT_A = VoxelShaper.forHorizontal(SHAPE_LEFT_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT_B = VoxelShaper.forHorizontal(SHAPE_LEFT_B, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT_A = VoxelShaper.forHorizontal(SHAPE_RIGHT_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT_B = VoxelShaper.forHorizontal(SHAPE_RIGHT_B, Direction.NORTH);

	public NordicDeskBlock(Properties properties, MultiBlockPattern pattern)
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
		VoxelShaper shaper = Nordic.DESK_LEFT_BLOCK.is(this) ? pattern.isOrigin(blockState) ? SHAPER_LEFT_A : SHAPER_LEFT_B : pattern.isOrigin(blockState) ? SHAPER_RIGHT_A : SHAPER_RIGHT_B;
		return shaper.get(facing);
	}
}

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.SofaBlock;

public final class NordicSofaBlock extends SofaBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(14D, 10D, 0D, 16D, 12D, 14D),
			box(0D, 10D, 0D, 2D, 12D, 14D),
			box(0D, 6D, 0D, 2D, 10D, 2D),
			box(14D, 6D, 0D, 16D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(14D, 10D, 0D, 16D, 12D, 13D),
			box(14D, 6D, 0D, 16D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_CENTER = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D)
	);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(0D, 10D, 0D, 2D, 12D, 13D),
			box(0D, 6D, 0D, 2D, 10D, 2D)
	);

	public static final VoxelShape SHAPE_CORNER = VoxelShaper.or(
			box(1D, 0D, 1D, 3D, 3D, 3D),
			box(1D, 0D, 13D, 3D, 3D, 15D),
			box(13D, 0D, 13D, 15D, 3D, 15D),
			box(13D, 0D, 1D, 15D, 3D, 3D),
			box(0D, 3D, 0D, 16D, 6D, 16D),
			box(0D, 6D, 13D, 16D, 16D, 16D),
			box(13D, 6D, 0D, 16D, 16D, 13D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CENTER = VoxelShaper.forHorizontal(SHAPE_CENTER, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CORNER = VoxelShaper.forHorizontal(SHAPE_CORNER, Direction.NORTH);

	public NordicSofaBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .1D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		ConnectionType connectionType = blockState.getValue(CONNECTION_TYPE);

		switch(connectionType)
		{
			default:
			case NONE: return SHAPER.get(facing);

			case LEFT: return SHAPER_LEFT.get(facing);
			case CENTER: return SHAPER_CENTER.get(facing);
			case RIGHT: return SHAPER_RIGHT.get(facing);
			case CORNER: return SHAPER_CORNER.get(facing);
		}
	}
}

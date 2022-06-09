package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetShelfBlock;

public final class DunmerShelfBlock extends SetShelfBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(13, 8, 14, 15, 14, 16),
			box(1, 8, 14, 3, 14, 16),
			box(0, 14, 0, 16, 16, 16)
	);

	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(13, 8, 14, 15, 14, 16),
			box(0, 14, 0, 16, 16, 16)
	);

	public static final VoxelShape SHAPE_CENTER = box(0, 14, 0, 16, 16, 16);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(1, 8, 14, 3, 14, 16),
			box(0, 14, 0, 16, 16, 16)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CENTER = VoxelShaper.forHorizontal(SHAPE_CENTER, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);

	public DunmerShelfBlock(Properties properties)
	{
		super(properties);
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
			case BOTH: return SHAPER_CENTER.get(facing);
			case RIGHT: return SHAPER_RIGHT.get(facing);
		}
	}
}

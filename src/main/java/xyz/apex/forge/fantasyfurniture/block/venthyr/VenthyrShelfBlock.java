package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetShelfBlock;

public final class VenthyrShelfBlock extends SetShelfBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0D, 13D, 0D, 16D, 16D, 16D),
			box(13D, 9D, 10D, 16D, 13D, 16D),
			box(13D, 11D, 3D, 16D, 13D, 10D),
			box(13D, 10D, 0D, 16D, 13D, 3D),
			box(13D, 6D, 13D, 16D, 9D, 16D),
			box(13D, 3D, 12D, 16D, 6D, 16D),
			box(0D, 3D, 12D, 3D, 6D, 16D),
			box(0D, 9D, 10D, 3D, 13D, 16D),
			box(0D, 11D, 3D, 3D, 13D, 10D),
			box(0D, 10D, 0D, 3D, 13D, 3D),
			box(0D, 6D, 13D, 3D, 9D, 16D)
	);

	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(0D, 13D, 0D, 16D, 16D, 16D),
			box(13D, 3D, 12D, 16D, 6D, 16D),
			box(13D, 9D, 10D, 16D, 13D, 16D),
			box(13D, 11D, 3D, 16D, 13D, 10D),
			box(13D, 10D, 0D, 16D, 13D, 3D),
			box(13D, 6D, 13D, 16D, 9D, 16D)
	);

	public static final VoxelShape SHAPE_CENTER = box(0D, 13D, 0D, 16D, 16D, 16D);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(0D, 3D, 12D, 3D, 6D, 16D),
			box(0D, 6D, 13D, 3D, 9D, 16D),
			box(0D, 9D, 10D, 3D, 13D, 16D),
			box(0D, 11D, 3D, 3D, 13D, 10D),
			box(0D, 10D, 0D, 3D, 13D, 3D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CENTER = VoxelShaper.forHorizontal(SHAPE_CENTER, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);

	public VenthyrShelfBlock(Properties properties)
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

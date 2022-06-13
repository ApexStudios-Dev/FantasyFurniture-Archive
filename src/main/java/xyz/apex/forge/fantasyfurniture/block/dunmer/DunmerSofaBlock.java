package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetSofaBlock;

public final class DunmerSofaBlock extends SetSofaBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(0, 4, 1, 16, 6, 15),
			Block.box(2, 0, 12, 4, 4, 14),
			Block.box(2, 0, 2, 4, 4, 4),
			Block.box(12, 0, 2, 14, 4, 4),
			Block.box(12, 0, 12, 14, 4, 14),
			Block.box(3, 6, 13, 13, 14, 13),
			Block.box(13, 6, 12, 15, 16, 14),
			Block.box(3, 14, 12, 13, 16, 14),
			Block.box(1, 6, 12, 3, 16, 14),
			Block.box(13, 10, 2, 15, 12, 12),
			Block.box(13, 6, 3, 15, 10, 5),
			Block.box(1, 6, 3, 3, 10, 5),
			Block.box(1, 10, 2, 3, 12, 12),
			Block.box(3, 6, 2, 13, 7, 13)
	);

	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			Block.box(2, 0, 12, 4, 4, 14),
			Block.box(2, 0, 2, 4, 4, 4),
			Block.box(12, 0, 2, 14, 4, 4),
			Block.box(12, 0, 12, 14, 4, 14),
			Block.box(0, 4, 1, 15, 6, 15),
			Block.box(0, 6, 13, 13, 14, 13),
			Block.box(13, 6, 12, 15, 16, 14),
			Block.box(0, 14, 12, 13, 16, 14),
			Block.box(13, 10, 2, 15, 12, 12),
			Block.box(13, 6, 3, 15, 10, 5),
			Block.box(0, 6, 2, 13, 7, 13)
	);

	public static final VoxelShape SHAPE_CENTER = VoxelShaper.or(
			Block.box(2, 0, 2, 4, 4, 4),
			Block.box(12, 0, 2, 14, 4, 4),
			Block.box(12, 0, 12, 14, 4, 14),
			Block.box(2, 0, 12, 4, 4, 14),
			Block.box(0, 4, 1, 16, 6, 15),
			Block.box(0, 6, 13, 16, 14, 13),
			Block.box(0, 14, 12, 16, 16, 14),
			Block.box(0, 6, 2, 16, 7, 13)
	);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			Block.box(12, 0, 12, 14, 4, 14),
			Block.box(12, 0, 2, 14, 4, 4),
			Block.box(2, 0, 2, 4, 4, 4),
			Block.box(2, 0, 12, 4, 4, 14),
			Block.box(1, 4, 1, 16, 6, 15),
			Block.box(3, 6, 13, 16, 14, 13),
			Block.box(1, 6, 12, 3, 16, 14),
			Block.box(3, 14, 12, 16, 16, 14),
			Block.box(1, 10, 2, 3, 12, 12),
			Block.box(1, 6, 3, 3, 10, 5),
			Block.box(3, 6, 2, 16, 7, 13)
	);

	public static final VoxelShape SHAPE_CORNER = VoxelShaper.or(
			Block.box(2, 0, 12, 4, 4, 14),
			Block.box(2, 0, 2, 4, 4, 4),
			Block.box(12, 0, 2, 14, 4, 4),
			Block.box(12, 0, 12, 14, 4, 14),
			Block.box(12, 6, 12, 14, 16, 14),
			Block.box(12, 14, 0, 14, 16, 12),
			Block.box(0, 14, 12, 12, 16, 14),
			Block.box(13, 6, 0, 13, 14, 12),
			Block.box(0, 6, 13, 12, 14, 13),
			Block.box(1, 4, 0, 15, 6, 15),
			Block.box(0, 4, 1, 1, 6, 15),
			Block.box(0, 6, 2, 13, 7, 13),
			Block.box(2, 6, 0, 13, 7, 2)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CENTER = VoxelShaper.forHorizontal(SHAPE_CENTER, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);
	public static final VoxelShaper SHAPER_CORNER = VoxelShaper.forHorizontal(SHAPE_CORNER, Direction.NORTH);

	public DunmerSofaBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var connectionType = blockState.getValue(CONNECTION_TYPE);

		return switch(connectionType)
				{
					case NONE -> SHAPER.get(facing);
					case LEFT -> SHAPER_LEFT.get(facing);
					case CENTER -> SHAPER_CENTER.get(facing);
					case RIGHT -> SHAPER_RIGHT.get(facing);
					case CORNER -> SHAPER_CORNER.get(facing);
				};
	}
}

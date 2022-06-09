package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetStoolBlock;

public final class DunmerStoolBlock extends SetStoolBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12, 0, 2, 14, 5, 4),
			box(2, 0, 2, 4, 5, 4),
			box(2, 0, 12, 4, 5, 14),
			box(12, 0, 12, 14, 5, 14),
			box(1, 5, 1, 15, 7, 15),
			box(12.5, 2.5, 4, 13.5, 3.5, 12),
			box(2.5, 2.5, 4, 3.5, 3.5, 12)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerStoolBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}
}

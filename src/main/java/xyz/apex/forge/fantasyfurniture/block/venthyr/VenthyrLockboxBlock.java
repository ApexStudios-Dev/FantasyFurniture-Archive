package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetLockboxBlock;

public final class VenthyrLockboxBlock extends SetLockboxBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2, 1, 4, 14, 10, 12),
			box(1.5, 6, 3.5, 14.5, 7, 12.5),
			box(1.5, 1, 3.5, 14.5, 2, 12.5),
			box(7, 4, 3.5, 9, 6, 4.25),
			box(12, 0, 4, 14, 1, 6),
			box(2, 0, 4, 4, 1, 6),
			box(2, 0, 10, 4, 1, 12),
			box(12, 0, 10, 14, 1, 12)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrLockboxBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		return SHAPER.get(facing);
	}
}

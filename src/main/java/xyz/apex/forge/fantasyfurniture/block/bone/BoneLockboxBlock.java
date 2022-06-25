package xyz.apex.forge.fantasyfurniture.block.bone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetLockboxBlock;

public final class BoneLockboxBlock extends SetLockboxBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2, 0, 4, 5, 2, 7),
			box(2, 0, 9, 5, 2, 12),
			box(11, 0, 9, 14, 2, 12),
			box(11, 0, 4, 14, 2, 7),
			box(1, 2, 3, 15, 4, 13),
			box(1.5, 4, 3.5, 14.5, 12.25, 12.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BoneLockboxBlock(Properties properties)
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

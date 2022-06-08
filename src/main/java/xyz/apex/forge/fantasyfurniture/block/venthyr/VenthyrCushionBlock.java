package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetCushionBlock;

public final class VenthyrCushionBlock extends SetCushionBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 2D, 5D, 3D, 5D),
			box(2D, 0D, 11D, 5D, 3D, 14D),
			box(11D, 0D, 11D, 14D, 3D, 14D),
			box(11D, 0D, 2D, 14D, 3D, 5D),
			box(1D, 3D, 1D, 15D, 4D, 15D),
			box(2D, 4D, 2D, 14D, 7D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrCushionBlock(Properties properties)
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

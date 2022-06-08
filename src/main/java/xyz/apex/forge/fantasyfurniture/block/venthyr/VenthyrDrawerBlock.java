package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetDrawerBlock;

public final class VenthyrDrawerBlock extends SetDrawerBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(.5D, 0D, .5D, 3.5D, 2D, 3.5D),
			box(.5D, 0D, 12.5D, 3.5D, 2D, 15.5D),
			box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
			box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
			box(13D, 2D, 1D, 15D, 13D, 3D),
			box(1D, 2D, 1D, 3D, 13D, 3D),
			box(1D, 2D, 13D, 3D, 13D, 15D),
			box(13D, 2D, 13D, 15D, 13D, 15D),
			box(1D, 5D, 1D, 15D, 13D, 15D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrDrawerBlock(Properties properties)
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

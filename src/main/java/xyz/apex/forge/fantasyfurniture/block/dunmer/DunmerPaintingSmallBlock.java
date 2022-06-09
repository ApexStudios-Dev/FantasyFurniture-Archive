package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetPaintingSmallBlock;

public final class DunmerPaintingSmallBlock extends SetPaintingSmallBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0, 1, 14, 16, 3, 16),
			box(0, 13, 14, 16, 15, 16),
			box(1, 3, 14, 15, 13, 16)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerPaintingSmallBlock(Properties properties)
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

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetCushionBlock;

public final class DunmerCushionBlock extends SetCushionBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(3, 0, 3, 5, 4, 5),
			box(3, 0, 11, 5, 4, 13),
			box(11, 0, 11, 13, 4, 13),
			box(11, 0, 3, 13, 4, 5),
			box(2, 4, 2, 14, 5, 14),
			box(2.5, 5, 2.5, 13.5, 7, 13.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerCushionBlock(Properties properties)
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

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWardrobeBlock;

public final class DunmerWardrobeBlock extends SetWardrobeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-15, 0, 1, -13, 30, 3),
			box(-15, 0, 13, -13, 30, 15),
			box(13, 0, 13, 15, 30, 15),
			box(13, 0, 1, 15, 30, 3),
			box(-16, 30, 0, 16, 32, 16),
			box(-16, 20, 0, 16, 22, 16),
			box(-16, 2, 0, 16, 4, 16),
			box(-13, 4, 2, 13, 30, 14)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerWardrobeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var shape = SHAPER.get(facing);
		var index = pattern.getIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}
}

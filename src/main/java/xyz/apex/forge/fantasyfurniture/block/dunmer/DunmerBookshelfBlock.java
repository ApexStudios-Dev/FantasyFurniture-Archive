package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBookshelfBlock;

public final class DunmerBookshelfBlock extends SetBookshelfBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-14, 0, 2, -12, 30, 4),
			box(-14, 0, 12, -12, 30, 14),
			box(12, 0, 12, 14, 30, 14),
			box(12, 0, 2, 14, 30, 4),
			box(-12, 9, 4, 12, 32, 12),
			box(-16, 9, 1, 15, 11, 15),
			box(-16, 19, 1, 15, 21, 15),
			box(-16, 30, 1, 15, 32, 15)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerBookshelfBlock(Properties properties, MultiBlockPattern pattern)
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

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;

public final class DunmerTableLargeBlock extends SetTableLargeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12, 0, 2, 14, 14, 4),
			box(-14, 0, 2, -12, 14, 4),
			box(-14, 0, 28, -12, 14, 30),
			box(12, 0, 28, 14, 14, 30),
			box(-16, 14, 0, 16, 16, 32)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerTableLargeBlock(Properties properties, MultiBlockPattern pattern)
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
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}
}

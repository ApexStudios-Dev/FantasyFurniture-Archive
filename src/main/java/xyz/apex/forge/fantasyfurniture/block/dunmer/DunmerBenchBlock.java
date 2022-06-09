package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBenchBlock;

public final class DunmerBenchBlock extends SetBenchBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 5D, 4D),
			box(-14D, 0D, 2D, -12D, 5D, 4D),
			box(-14D, 0D, 12D, -12D, 5D, 14D),
			box(12D, 0D, 12D, 14D, 5D, 14D),
			box(-16D, 5D, 1D, 15D, 7D, 15D),
			box(12.5D, 2.5D, 4D, 13.5D, 3.5D, 12D),
			box(-13.5D, 2.5D, 4D, -12.5D, 3.5D, 12D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerBenchBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

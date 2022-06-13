package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBenchBlock;

public final class NordicBenchBlock extends SetBenchBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(12D, 0D, 2D, 14D, 3D, 4D),
			box(-14D, 0D, 2D, -12D, 3D, 4D),
			box(-14D, 0D, 12D, -12D, 3D, 14D),
			box(12D, 0D, 12D, 14D, 3D, 14D),
			box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
			box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
			box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
			box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
			box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
			box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
			box(-15D, 5D, 2D, 15D, 7D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicBenchBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);

		if(!isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

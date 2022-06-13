package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedDoubleBlock;

public final class NordicBedDoubleBlock extends SetBedDoubleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-16D, 3D, 2D, 16D, 5D, 30D),
			box(-14D, 5D, 2D, 14D, 8D, 30D),
			box(-16D, 3D, 0D, 16D, 5D, 2D),
			box(-16D, 0D, 0D, -14D, 8D, 2D),
			box(14D, 0D, 0D, 16D, 8D, 2D),
			box(-16D, 12D, 0D, -8D, 14D, 2D),
			box(8D, 12D, 0D, 16D, 14D, 2D),
			box(-10D, 12D, 0D, 10D, 16D, 2D),
			box(-15D, 5D, 0D, 15D, 12D, 2D),
			box(-15D, 5D, 30D, 15D, 12D, 32D),
			box(-16D, 3D, 30D, 16D, 5D, 32D),
			box(-16D, 0D, 30D, -14D, 8D, 32D),
			box(14D, 0D, 30D, 16D, 8D, 32D),
			box(-16D, 12D, 30D, -8D, 14D, 32D),
			box(8D, 12D, 30D, 16D, 14D, 32D),
			box(-10D, 12D, 30D, 10D, 16D, 32D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicBedDoubleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);
		var index = getMultiBlockIndex(blockState);

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

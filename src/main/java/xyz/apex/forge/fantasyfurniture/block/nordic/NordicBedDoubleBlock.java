package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedDoubleBlock;

public final class NordicBedDoubleBlock extends SetBedDoubleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-16D, 0D, 0D, 16D, 16D, 2D),
			box(-16D, 0D, 30D, 16D, 16D, 32D),
			box(-16D, 3D, 2D, 16D, 9D, 30D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicBedDoubleBlock(Properties properties, MultiBlockPattern pattern)
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

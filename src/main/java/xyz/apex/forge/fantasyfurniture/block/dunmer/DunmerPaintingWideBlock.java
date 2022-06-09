package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetPaintingWideBlock;

public final class DunmerPaintingWideBlock extends SetPaintingWideBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-16, 0, 14, 16, 2, 16),
			box(-16, 14, 14, 16, 16, 16),
			box(-15, 2, 14, 15, 14, 16)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerPaintingWideBlock(Properties properties, MultiBlockPattern pattern)
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

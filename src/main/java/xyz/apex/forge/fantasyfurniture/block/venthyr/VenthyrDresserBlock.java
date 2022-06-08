package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetDresserBlock;

public final class VenthyrDresserBlock extends SetDresserBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-15.5D, 0D, .5D, -12.5D, 2D, 3.5D),
			box(-15.5D, 0D, 12.5D, -12.5D, 2D, 15.5D),
			box(12.5D, 0D, 12.5D, 15.5D, 2D, 15.5D),
			box(12.5D, 0D, .5D, 15.5D, 2D, 3.5D),
			box(13D, 2D, 1D, 15D, 13D, 3D),
			box(-15D, 2D, 1D, -13D, 13D, 3D),
			box(-15D, 2D, 13D, -13D, 13D, 15D),
			box(13D, 2D, 13D, 15D, 13D, 15D),
			box(-15D, 5D, 1D, 15D, 13D, 15D),
			box(-16D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrDresserBlock(Properties properties, MultiBlockPattern pattern)
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

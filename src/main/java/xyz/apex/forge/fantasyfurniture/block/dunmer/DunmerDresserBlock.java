package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetDresserBlock;

public final class DunmerDresserBlock extends SetDresserBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(1, 0, 1, 3, 14, 3),
			Block.box(1, 0, 13, 3, 14, 15),
			Block.box(13, 0, 13, 15, 14, 15),
			Block.box(13, 0, 1, 15, 14, 3),
			Block.box(0, 14, 0, 16, 16, 16),
			Block.box(1, 6, 1, 15, 8, 15),
			Block.box(2, 6, 2, 14, 14, 14)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerDresserBlock(Properties properties, MultiBlockPattern pattern)
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

package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;

public final class NordicBedSingleBlock extends SetBedSingleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0D, 0D, 0D, 16D, 14D, 2D),
			box(0D, 0D, 30D, 16D, 14D, 32D),
			box(0D, 3D, 2D, 16D, 5D, 30D),
			box(1D, 5D, 2D, 15D, 8D, 30D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public NordicBedSingleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}
}

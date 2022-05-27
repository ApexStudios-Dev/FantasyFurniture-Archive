package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairBlock;

public final class VenthyrChairBlock extends SetChairBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 4D, 5D, 4D),
			box(12D, 0D, 1D, 15D, 5D, 4D),
			box(12D, 0D, 12D, 15D, 5D, 15D),
			box(1D, 0D, 12D, 4D, 5D, 15D),
			box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
			box(1D, 9D, 12D, 15D, 31D, 15D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrChairBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShape shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	@Override
	protected boolean sitAtOriginOnly()
	{
		return true;
	}
}

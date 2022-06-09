package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairBlock;

public final class DunmerChairBlock extends SetChairBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2, 0, 2, 4, 4, 4),
			box(2, 0, 12, 4, 4, 14),
			box(12, 0, 12, 14, 4, 14),
			box(12, 0, 2, 14, 4, 4),
			box(11.5, 4, 2.5, 13.5, 7, 4.5),
			box(2.5, 4, 2.5, 4.5, 7, 4.5),
			box(2.5, 4, 11.5, 4.5, 7, 13.5),
			box(11.5, 4, 11.5, 13.5, 7, 13.5),
			box(2, 7, 1, 14, 9, 15),
			box(2.5, 9, 11.5, 13.5, 31.5, 13.5)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerChairBlock(Properties properties, MultiBlockPattern pattern)
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
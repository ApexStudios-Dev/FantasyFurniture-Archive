package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairBlock;

public final class NordicChairBlock extends SetChairBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 2D, 4D, 4D, 4D),
			box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
			box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
			box(12D, 0D, 2D, 14D, 4D, 4D),
			box(2D, 0D, 12D, 4D, 4D, 14D),
			box(2D, 7D, 2D, 14D, 9D, 14D),
			box(2D, 9D, 13D, 14D, 25D, 14D),
			box(12D, 0D, 12D, 14D, 4D, 14D),
			box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
			box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
			box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
			box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicChairBlock(Properties properties, MultiBlockPattern pattern)
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
}

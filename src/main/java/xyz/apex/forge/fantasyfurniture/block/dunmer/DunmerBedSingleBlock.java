package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;

public final class DunmerBedSingleBlock extends SetBedSingleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0D, 0D, 0D, 16D, 14D, 2D),
			box(0D, 0D, 30D, 16D, 14D, 32D),
			box(0D, 3D, 2D, 16D, 5D, 30D),
			box(1D, 5D, 2D, 15D, 8D, 30D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public DunmerBedSingleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShape shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}
}

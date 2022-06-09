package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;

public final class DunmerTableWideBlock extends SetTableWideBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-15D, 7D, 1D, -13D, 13D, 3D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);

	public DunmerTableWideBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShape shape = SHAPER.get(facing);

		if(!pattern.isOrigin(blockState))
		{
			Direction opposite = facing.getClockWise();
			shape = shape.move(opposite.getStepX(), 0D, opposite.getStepZ());
		}

		return shape;
	}
}

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetStoolBlock;

public final class DunmerStoolBlock extends SetStoolBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 2D, 4D, 3D, 4D),
			box(12D, 0D, 12D, 14D, 3D, 14D),
			box(12D, 0D, 2D, 14D, 3D, 4D),
			box(2D, 0D, 12D, 4D, 3D, 14D),
			box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
			box(12D, 3D, 11.5D, 14D, 5, 13.5D),
			box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
			box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
			box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
			box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
			box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerStoolBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}
}

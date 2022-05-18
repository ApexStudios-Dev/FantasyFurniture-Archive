package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.SimpleFourWayWaterLoggedBlock;

public final class CoinStackBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(5D, 0D, 3D, 8D, 3D, 6D),
			box(9D, 0D, 5D, 12D, 2D, 8D),
			box(12D, 0D, 7D, 14D, 4D, 9D),
			box(3D, 0D, 5D, 5D, 4D, 7D),
			box(4D, 0D, 9D, 7D, 7D, 12D),
			box(8.26536686473018D, 0D, 9.347759065022572D, 12.26536686473018D, 5D, 13.347759065022572D)
	);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public CoinStackBlock(Properties properties)
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

package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedBlock;

public final class BerryBasketBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 3.5D, 14D, 5D, 12.5D),
			box(1.5D, 5D, 3D, 14.5D, 6D, 13D),
			box(7D, 6D, 3.25D, 9D, 11.75D, 12.75D)
	);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BerryBasketBlock(Properties properties)
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

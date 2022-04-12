package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.SimpleFourWayWaterLoggedBlock;

public final class BowlBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final VoxelShape SHAPE = box(3.5D, 0D, 3.5D, 12.5D, 3.5D, 12.5D);
	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BowlBlock(Properties properties)
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

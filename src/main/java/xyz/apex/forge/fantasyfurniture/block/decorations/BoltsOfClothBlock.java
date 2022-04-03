package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.SimpleFourWayBlock;

public final class BoltsOfClothBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 2.5D, 15D, 5D, 7.5D),
			box(1D, 0D, 8.5D, 15D, 5D, 13.5D),
			box(1D, 5D, 5.5D, 15D, 10D, 10.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public BoltsOfClothBlock(Properties properties)
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

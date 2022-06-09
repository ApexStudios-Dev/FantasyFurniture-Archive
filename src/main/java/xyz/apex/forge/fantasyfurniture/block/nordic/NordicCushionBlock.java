package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetCushionBlock;

public final class NordicCushionBlock extends SetCushionBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(2D, 0D, 2D, 4D, 2D, 4D),
			box(2D, 0D, 12D, 4D, 2D, 14D),
			box(12D, 0D, 12D, 14D, 2D, 14D),
			box(12D, 0D, 2D, 14D, 2D, 4D),
			box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
			box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
			box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
			box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
			box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
			box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
			box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
			box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicCushionBlock(Properties properties)
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

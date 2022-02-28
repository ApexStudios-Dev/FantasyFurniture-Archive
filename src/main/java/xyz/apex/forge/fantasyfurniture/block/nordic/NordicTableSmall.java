package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

public final class NordicTableSmall extends Block
{
	public static VoxelShape SHAPE = BlockHelper.makeShape(
			box(1D, 0D, 1D, 3D, 13D, 3D),
			box(1D, 0D, 13D, 3D, 13D, 15D),
			box(13D, 0D, 13D, 15D, 13D, 15D),
			box(13D, 0D, 1D, 15D, 13D, 3D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public NordicTableSmall(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}
}

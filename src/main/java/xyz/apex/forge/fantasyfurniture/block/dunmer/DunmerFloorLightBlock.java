package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetFloorLightBlock;

public final class DunmerFloorLightBlock extends SetFloorLightBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6, 0, 6, 10, 2, 10),
			box(7, 2, 7, 9, 22, 9),
			box(6, 22, 6, 10, 27, 10),
			box(7, 27, 7, 9, 28, 9)
	);

	public DunmerFloorLightBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		if(pattern.isOrigin(blockState))
			return SHAPE;
		return SHAPE.move(0D, -1D, 0D);
	}
}

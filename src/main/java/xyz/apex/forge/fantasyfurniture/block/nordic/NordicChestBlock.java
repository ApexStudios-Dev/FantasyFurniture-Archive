package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChestBlock;

public final class NordicChestBlock extends SetChestBlock
{
	public static final VoxelShape SHAPE_A = box(-15D, 0D, 2D, 15D, 14D, 16D);

	public static final VoxelShape SHAPE_B = box(1D, 0D, 2D, 31D, 14D, 16D);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicChestBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = pattern.isOrigin(blockState) ? SHAPER_A : SHAPER_B;
		return shaper.get(facing);
	}
}

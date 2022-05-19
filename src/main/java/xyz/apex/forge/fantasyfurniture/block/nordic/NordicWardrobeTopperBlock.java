package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWardrobeTopperBlock;

public final class NordicWardrobeTopperBlock extends SetWardrobeTopperBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(-15D, 3D, 0D, 15D, 14D, 16D),
			box(-16D, 0D, 0D, 16D, 3D, 16D)
	);

	public static final VoxelShape SHAPE_B = VoxelShaper.or(
			box(1D, 3D, 0D, 31D, 14D, 16D),
			box(0D, 0D, 0D, 32D, 3D, 16D)
	);

	public static final VoxelShaper SHAPER_A = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);
	public static final VoxelShaper SHAPER_B = VoxelShaper.forHorizontal(SHAPE_B, Direction.NORTH);

	public NordicWardrobeTopperBlock(Properties properties, MultiBlockPattern pattern)
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

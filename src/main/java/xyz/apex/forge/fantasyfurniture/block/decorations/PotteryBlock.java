package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedBlock;
import xyz.apex.forge.fantasyfurniture.init.Decorations;

public final class PotteryBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final VoxelShape SHAPE_0 = VoxelShaper.or(
			box(2, 0, 2, 7, 3, 7),
			box(3.5, 3, 3.5, 5.5, 4, 5.5),
			box(3, 4, 3, 6, 5, 6),
			box(7, 0, 6, 15, 6, 14),
			box(9.5, 6, 8.5, 12.5, 8, 11.5),
			box(9, 8, 8, 13, 9, 12)
	);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(5.5, 0, 1.5, 9.5, 3, 5.75),
			box(6.75, 3, 2.75, 8.25, 4, 4.25),
			box(6.15, 4, 2.15, 8.85, 5, 4.85),
			box(3.1500000000000004, 7, 7.450000000000001, 5.85, 8, 10.15),
			box(2.55, 0, 7.1, 6.549999999999999, 5, 11.1),
			box(3.75, 5, 8.05, 5.25, 7, 9.55),
			box(9.5, 5, 8.5, 12.5, 6, 11.5),
			box(10, 4, 9, 12, 5, 11),
			box(8, 0, 7, 14, 4, 13)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);

	public PotteryBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return (Decorations.DUNMER_POTTERY_0_BLOCK.is(this) ? SHAPER_0 : SHAPER_1).get(facing);
	}
}

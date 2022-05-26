package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedStackedBlock;

public final class TeaCupsBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10.5D, 5D, 10D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 4D, 13.5D, 5D, 8D),
			box(2.5D, 0D, 7D, 8.25D, 5D, 12.75D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(10D, 0D, 6D, 14.5D, 5D, 10D),
			box(3.5D, 0D, 9D, 9.25D, 5D, 14.75D),
			box(1.75D, 0D, 1.5D, 7.25D, 5D, 6.75D)
	);

	public static final IntegerProperty TEA_CUPS = IntegerProperty.create("tea_cups", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public TeaCupsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return TEA_CUPS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(TEA_CUPS);
		return (count == 2 ? SHAPER_2 : count == 1 ? SHAPER_1 : SHAPER_0).get(facing);
	}
}

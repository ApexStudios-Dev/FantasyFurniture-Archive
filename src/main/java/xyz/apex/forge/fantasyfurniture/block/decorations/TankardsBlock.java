package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.SimpleFourWayWaterLoggedStackedBlock;

public final class TankardsBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(5.995320150246556D, -5.6098489409350805e-8D, 5.9809408927783565D, 11.495320150246556D, 5.499999943901511D, 9.980940892778356D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(8.386913458880244D, -2.6490953430879927e-8D, 7.543530193916975D, 14.486913458880244D, 5.499999973509047D, 12.843530193916976D),
			box(1.2869134588802438D, -2.6490953430879927e-8D, 3.943530193916976D, 7.386913458880244D, 5.499999973509047D, 9.243530193916975D)
	);

	public static final IntegerProperty TANKARDS = IntegerProperty.create("tankards", 0, 1);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);

	public TankardsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return TANKARDS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(TANKARDS);
		return (count == 0 ? SHAPER_0 : SHAPER_1).get(facing);
	}
}

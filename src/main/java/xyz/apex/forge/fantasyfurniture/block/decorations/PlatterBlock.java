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

public final class PlatterBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(2D, 0D, 2D, 14D, 1D, 14D);
	public static final VoxelShape SHAPE_1 = box(2D, 0D, 2D, 14D, 2D, 14D);
	public static final VoxelShape SHAPE_2 = box(2D, 0D, 2D, 14D, 3D, 14D);

	public static final IntegerProperty PLATTER = IntegerProperty.create("platter", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public PlatterBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return PLATTER;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(PLATTER);
		return (count == 2 ? SHAPER_2 : count == 1 ? SHAPER_1 : SHAPER_0).get(facing);
	}
}

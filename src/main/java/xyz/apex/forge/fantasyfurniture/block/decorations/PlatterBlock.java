package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedStackedBlock;

public final class PlatterBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(2D, 0D, 2D, 14D, 1D, 14D);
	public static final VoxelShape SHAPE_1 = box(2D, 0D, 2D, 14D, 2D, 14D);
	public static final VoxelShape SHAPE_2 = box(2D, 0D, 2D, 14D, 3D, 14D);
	public static final VoxelShape SHAPE_3 = box(2, 0, 2, 14, 4, 14);
	public static final VoxelShape SHAPE_4 = box(2, 0, 2, 14, 5, 14);
	public static final VoxelShape SHAPE_5 = box(2D, 0D, 2D, 14D, 6D, 14D);
	public static final VoxelShape SHAPE_6 = box(2D, 0D, 2D, 14D, 7D, 14D);
	public static final VoxelShape SHAPE_7 = box(2D, 0D, 2D, 14D, 8D, 14D);
	public static final VoxelShape SHAPE_8 = box(2D, 0D, 2D, 14D, 9D, 14D);
	public static final VoxelShape SHAPE_9 = box(2D, 0D, 2D, 14D, 10D, 14D);
	public static final VoxelShape SHAPE_10 = box(2D, 0D, 2D, 14D, 11D, 14D);
	public static final VoxelShape SHAPE_11 = box(2D, 0D, 2D, 14D, 12D, 14D);
	public static final VoxelShape SHAPE_12 = box(2D, 0D, 2D, 14D, 13D, 14D);
	public static final VoxelShape SHAPE_13 = box(2D, 0D, 2D, 14D, 14D, 14D);
	public static final VoxelShape SHAPE_14 = box(2D, 0D, 2D, 14D, 15D, 14D);
	public static final VoxelShape SHAPE_15 = box(2D, 0D, 2D, 14D, 16D, 14D);

	public static final IntegerProperty PLATTER = IntegerProperty.create("platter", 0, 15);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);
	public static final VoxelShaper SHAPER_3 = VoxelShaper.forHorizontal(SHAPE_3, Direction.NORTH);
	public static final VoxelShaper SHAPER_4 = VoxelShaper.forHorizontal(SHAPE_4, Direction.NORTH);
	public static final VoxelShaper SHAPER_5 = VoxelShaper.forHorizontal(SHAPE_5, Direction.NORTH);
	public static final VoxelShaper SHAPER_6 = VoxelShaper.forHorizontal(SHAPE_6, Direction.NORTH);
	public static final VoxelShaper SHAPER_7 = VoxelShaper.forHorizontal(SHAPE_7, Direction.NORTH);
	public static final VoxelShaper SHAPER_8 = VoxelShaper.forHorizontal(SHAPE_8, Direction.NORTH);
	public static final VoxelShaper SHAPER_9 = VoxelShaper.forHorizontal(SHAPE_9, Direction.NORTH);
	public static final VoxelShaper SHAPER_10 = VoxelShaper.forHorizontal(SHAPE_10, Direction.NORTH);
	public static final VoxelShaper SHAPER_11 = VoxelShaper.forHorizontal(SHAPE_11, Direction.NORTH);
	public static final VoxelShaper SHAPER_12 = VoxelShaper.forHorizontal(SHAPE_12, Direction.NORTH);
	public static final VoxelShaper SHAPER_13 = VoxelShaper.forHorizontal(SHAPE_13, Direction.NORTH);
	public static final VoxelShaper SHAPER_14 = VoxelShaper.forHorizontal(SHAPE_14, Direction.NORTH);
	public static final VoxelShaper SHAPER_15 = VoxelShaper.forHorizontal(SHAPE_15, Direction.NORTH);
	public static final VoxelShaper[] SHAPERS = new VoxelShaper[] {
			SHAPER_0, SHAPER_1, SHAPER_2,
			SHAPER_3, SHAPER_4, SHAPER_5,
			SHAPER_6, SHAPER_7, SHAPER_8,
			SHAPER_9, SHAPER_10, SHAPER_11,
			SHAPER_12, SHAPER_13, SHAPER_14,
			SHAPER_15,
	};

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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var count = blockState.getValue(PLATTER);
		// mod to never go out of bounds
		return SHAPERS[count % SHAPERS.length].get(facing);
	}
}

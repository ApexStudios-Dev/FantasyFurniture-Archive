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

public final class MuffinsBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(5.5D, 0D, 5.5D, 10.5D, 5D, 10.5D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(.75D, 0D, 3.75D, 7.5D, 5D, 10.25D),
			box(9.5D, 0D, 7.5D, 14.5D, 5D, 12.5D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(.75D, 0D, 5.75D, 7.5D, 5D, 12.25D),
			box(6.65D, 0D, .75D, 13.4D, 5D, 7.25D),
			box(9.5D, 0D, 9.5D, 14.5D, 5D, 14.5D)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public static final IntegerProperty MUFFINS = IntegerProperty.create("muffins", 0, 2);

	public MuffinsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return MUFFINS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(MUFFINS);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		return shaper.get(facing);
	}
}

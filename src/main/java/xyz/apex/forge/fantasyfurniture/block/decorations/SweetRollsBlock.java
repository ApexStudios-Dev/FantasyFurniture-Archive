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

public final class SweetRollsBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 4D, 10D);
	public static final VoxelShape SHAPE_1 = box(1.75D, 0D, 4D, 14D, 4D, 11D);
	public static final VoxelShape SHAPE_2 = box(1.75D, 0D, 1D, 14D, 4D, 13D);

	public static final IntegerProperty ROLLS = IntegerProperty.create("rolls", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public SweetRollsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return ROLLS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(ROLLS);
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

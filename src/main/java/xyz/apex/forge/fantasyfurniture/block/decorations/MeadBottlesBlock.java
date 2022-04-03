package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.SimpleFourWayStackedBlock;

public final class MeadBottlesBlock extends SimpleFourWayStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 11D, 9.5D);
	public static final VoxelShape SHAPE_1 = box(3.75D, 0D, 4D, 12D, 11D, 12D);
	public static final VoxelShape SHAPE_2 = box(1.75D, 0D, 2D, 14.5D, 11D, 13D);

	public static final IntegerProperty BOTTLES = IntegerProperty.create("bottles", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public MeadBottlesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected IntegerProperty getStackSizeProperty()
	{
		return BOTTLES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(BOTTLES);
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

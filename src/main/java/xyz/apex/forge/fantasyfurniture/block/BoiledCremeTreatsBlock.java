package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

public final class BoiledCremeTreatsBlock extends SimpleFourWayStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 2D, 10D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 8D, 13D, 2D, 12D),
			box(3D, 0D, 4D, 7D, 2D, 8D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(8D, 0D, 10D, 12D, 2D, 14D),
			box(2D, 0D, 6D, 6D, 2D, 10D),
			box(10D, 0D, 3D, 14D, 2D, 7D)
	);

	public static final IntegerProperty TREATS = IntegerProperty.create("treats", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public BoiledCremeTreatsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected IntegerProperty getStackSizeProperty()
	{
		return TREATS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(TREATS);
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

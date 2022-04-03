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

public final class BookStackBlock extends SimpleFourWayStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(4D, 0D, 3D, 11D, 3D, 13D);
	public static final VoxelShape SHAPE_1 = box(2D, 0D, 2D, 13D, 6D, 14.25D);
	public static final VoxelShape SHAPE_2 = box(2D, 0D, 1.75D, 13D, 9D, 14.25D);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public static final IntegerProperty BOOKS = IntegerProperty.create("books", 0, 2);

	public BookStackBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected IntegerProperty getStackSizeProperty()
	{
		return BOOKS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(BOOKS);
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

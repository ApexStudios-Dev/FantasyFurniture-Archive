package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedStackedBlock;

public class MushroomsRedBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(5D, 0D, 5D, 11D, 7D, 11D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 7D, 15D, 7D, 13D),
			box(2D, 0D, 3D, 6D, 5D, 7D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(9D, 0D, 9D, 15D, 7D, 15D),
			box(2D, 0D, 5D, 6D, 5D, 9D),
			box(8D, 0D, 2D, 11D, 3D, 5D)
	);

	public static final IntegerProperty MUSHROOMS = IntegerProperty.create("mushrooms", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public MushroomsRedBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return MUSHROOMS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(MUSHROOMS);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		Vector3d offset = blockState.getOffset(level, pos);
		VoxelShape shape = shaper.get(facing);
		return shape.move(offset.x, offset.y, offset.z);
	}

	@Override
	public OffsetType getOffsetType()
	{
		return OffsetType.XZ;
	}
}

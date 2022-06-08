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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var count = blockState.getValue(MUSHROOMS);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		var offset = blockState.getOffset(level, pos);
		var shape = shaper.get(facing);
		return shape.move(offset.x, offset.y, offset.z);
	}

	@Override
	public OffsetType getOffsetType()
	{
		return OffsetType.XZ;
	}
}

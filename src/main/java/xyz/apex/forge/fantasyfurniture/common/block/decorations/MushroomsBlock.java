package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllBlocks;

public final class MushroomsBlock extends StackedBlock
{
	public static final VoxelShape RED_SHAPE_0 = box(5D, 0D, 5D, 11D, 7D, 11D);

	public static final VoxelShape RED_SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 7D, 15D, 7D, 13D),
			box(2D, 0D, 3D, 6D, 5D, 7D)
	);

	public static final VoxelShape RED_SHAPE_2 = VoxelShaper.or(
			box(9D, 0D, 9D, 15D, 7D, 15D),
			box(2D, 0D, 5D, 6D, 5D, 9D),
			box(8D, 0D, 2D, 11D, 3D, 5D)
	);

	public static final VoxelShape BROWN_SHAPE_0 = VoxelShaper.or(
			Block.box(7, 0, 7, 9, 1, 9),
			Block.box(6, 1, 6, 10, 3, 10)
	);

	public static final VoxelShape BROWN_SHAPE_1 = VoxelShaper.or(
			Block.box(10, 0, 5, 12, 1, 7),
			Block.box(9, 1, 4, 13, 3, 8),
			Block.box(3, 2, 9, 7, 4, 13),
			Block.box(4, 0, 10, 6, 2, 12)
	);

	public static final VoxelShape BROWN_SHAPE_2 = VoxelShaper.or(
			Block.box(9, 0, 3, 11, 1, 5),
			Block.box(8, 1, 2, 12, 3, 6),
			Block.box(2, 2, 7, 6, 4, 11),
			Block.box(3, 0, 8, 5, 2, 10),
			Block.box(11, 0, 11, 13, 4, 13),
			Block.box(9, 4, 9, 15, 6, 15)
	);

	public static final IntegerProperty MUSHROOMS = IntegerProperty.create("mushrooms", 0, 2);
	public static final VoxelShaper RED_SHAPER_0 = VoxelShaper.forHorizontal(RED_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper RED_SHAPER_1 = VoxelShaper.forHorizontal(RED_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper RED_SHAPER_2 = VoxelShaper.forHorizontal(RED_SHAPE_2, Direction.NORTH);
	public static final VoxelShaper BROWN_SHAPER_0 = VoxelShaper.forHorizontal(BROWN_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper BROWN_SHAPER_1 = VoxelShaper.forHorizontal(BROWN_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper BROWN_SHAPER_2 = VoxelShaper.forHorizontal(BROWN_SHAPE_2, Direction.NORTH);

	public MushroomsBlock(Properties properties)
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
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(MUSHROOMS);
		VoxelShaper shaper;

		if(AllBlocks.MUSHROOMS_RED.isIn(blockState))
		{
			if(count == 1)
				shaper = RED_SHAPER_1;
			else if(count < 1)
				shaper = RED_SHAPER_0;
			else
				shaper = RED_SHAPER_2;
		}
		else
		{
			if(count == 1)
				shaper = BROWN_SHAPER_1;
			else if(count < 1)
				shaper = BROWN_SHAPER_0;
			else
				shaper = BROWN_SHAPER_2;
		}

		var offset = blockState.getOffset(level, pos);
		var shape = shaper.get(facing);
		return shape.move(offset.x, offset.y, offset.z);
	}
}

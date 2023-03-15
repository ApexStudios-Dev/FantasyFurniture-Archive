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

public class SnowballsBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = Block.box(5.5, 0, 5.5, 10.5, 5, 10.5);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			Block.box(2.5, 0, 3.5, 7.5, 5, 8.5),
			Block.box(8.5, 0, 7.5, 13.5, 5, 12.5)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			Block.box(1.5, 0, 4.5, 6.5, 5, 9.5),
			Block.box(7.5, 0, 8.5, 12.5, 5, 13.5),
			Block.box(8.5, 0, 1.5, 13.5, 5, 6.5)
	);
	public static final VoxelShape SHAPE_3 = VoxelShaper.or(
			Block.box(1.5, 0, 4.5, 6.5, 5, 9.5),
			Block.box(7.5, 0, 8.5, 12.5, 5, 13.5),
			Block.box(8.5, 0, 1.5, 13.5, 5, 6.5),
			Block.box(5.5, 5, 5.5, 10.5, 10, 10.5)
	);

	public static final IntegerProperty SNOWBALLS = IntegerProperty.create("snowballs", 0, 3);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);
	public static final VoxelShaper SHAPER_3 = VoxelShaper.forHorizontal(SHAPE_3, Direction.NORTH);

	public SnowballsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return SNOWBALLS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(SNOWBALLS);
		VoxelShaper shaper;

		if(count == 0) shaper = SHAPER_0;
		else if(count == 1) shaper = SHAPER_1;
		else if(count == 2) shaper = SHAPER_2;
		else shaper = SHAPER_3;

		return shaper.get(facing);
	}
}

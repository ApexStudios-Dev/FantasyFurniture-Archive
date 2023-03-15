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

public final class StackablePumpkinsBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = Block.box(4, 0, 4, 12, 7, 12);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			Block.box(4, 0, 4, 12, 7, 12),
			Block.box(3.25, 7, 3.25, 12.75, 12, 12.75)
	);

	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			Block.box(4, 0, 4, 12, 7, 12),
			Block.box(3.25, 7, 3.25, 12.75, 12, 12.75),
			Block.box(5.5, 12, 5.5, 10.5, 16, 10.5)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public static final IntegerProperty PUMPKINS = IntegerProperty.create("pumpkins", 0, 2);

	public StackablePumpkinsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return PUMPKINS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(PUMPKINS);
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

package xyz.apex.forge.fantasyfurniture.block.decorations;

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

public final class PotionBottlesBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = Block.box(6, 0, 6, 10, 10, 10);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			Block.box(10, 0, 7, 14, 10, 11),
			Block.box(1.25, 0, 3.25, 6.75, 10, 8.75)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			Block.box(10, 0, 10, 14, 10, 14),
			Block.box(1.25, 0, 6.25, 6.75, 10, 11.75),
			Block.box(6.25, 0, 1.25, 11.75, 10, 6.75)
	);

	public static final IntegerProperty POTIONS = IntegerProperty.create("potions", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public PotionBottlesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return POTIONS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(POTIONS);
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
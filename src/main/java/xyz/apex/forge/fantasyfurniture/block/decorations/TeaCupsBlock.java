package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

public final class TeaCupsBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10.5D, 5D, 10D);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 4D, 13.5D, 5D, 8D),
			box(2.5D, 0D, 7D, 8.25D, 5D, 12.75D)
	);

	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(10D, 0D, 6D, 14.5D, 5D, 10D),
			box(3.5D, 0D, 9D, 9.25D, 5D, 14.75D),
			box(1.75D, 0D, 1.5D, 7.25D, 5D, 6.75D)
	);

	public static final IntegerProperty TEA_CUPS = IntegerProperty.create("tea_cups", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public TeaCupsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return TEA_CUPS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(TEA_CUPS);
		return (count == 2 ? SHAPER_2 : count == 1 ? SHAPER_1 : SHAPER_0).get(facing);
	}
}
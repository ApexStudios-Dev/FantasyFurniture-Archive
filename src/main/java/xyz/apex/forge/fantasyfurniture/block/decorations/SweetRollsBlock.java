package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedStackedBlock;

public final class SweetRollsBlock extends SimpleFourWayWaterLoggedStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 4D, 10D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9.75D, 0D, 7D, 14D, 4D, 11D),
			box(1.9071067811865463D, 1.0217939117040942e-7D, 4.492893218813454D, 7.507106781186546D, 4.000000102179391D, 10.092893218813455D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(10D, 0D, 9D, 14D, 4D, 13D),
			box(1.8071067811865476D, 1.0217939117040942e-7D, 6.3928932188134535D, 7.607106781186548D, 4.000000102179391D, 12.192893218813454D),
			box(7.3D, 0D, 1.2999999999999998D, 12.7D, 4D, 6.699999999999999D)
	);

	public static final IntegerProperty ROLLS = IntegerProperty.create("rolls", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public SweetRollsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return ROLLS;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		var count = blockState.getValue(ROLLS);
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

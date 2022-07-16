package xyz.apex.forge.fantasyfurniture.block.decorations;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

public final class MuffinsBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = box(5.5D, 0D, 5.5D, 10.5D, 5D, 10.5D);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(.75D, 0D, 3.75D, 7.5D, 5D, 10.25D),
			box(9.5D, 0D, 7.5D, 14.5D, 5D, 12.5D)
	);

	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(.75D, 0D, 5.75D, 7.5D, 5D, 12.25D),
			box(6.65D, 0D, .75D, 13.4D, 5D, 7.25D),
			box(9.5D, 0D, 9.5D, 14.5D, 5D, 14.5D)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public static final IntegerProperty MUFFINS = IntegerProperty.create("muffins", 0, 2);

	public MuffinsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return MUFFINS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(MUFFINS);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		return shaper.get(facing);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.NORMAL;
	}
}
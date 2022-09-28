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

public final class FloatingTomesBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = Block.box(2.5, 1.5, 4, 15.5, 5, 14);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			Block.box(2.5, 1.5, 4, 15.5, 5, 14),
			Block.box(3, 7.5, -0.5, 12, 11, 12.5)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);

	public static final IntegerProperty TOMES = IntegerProperty.create("tomes", 0, 1);

	public FloatingTomesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return TOMES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(TOMES);
		var shaper = count == 0 ? SHAPER_0 : SHAPER_1;
		return shaper.get(facing);
	}
}
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
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public final class ChalicesBlock extends StackedBlock
{
	public static final VoxelShape VENTHYR_SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 8D, 9.5D);

	public static final VoxelShape VENTHYR_SHAPE_1 = VoxelShaper.or(
			box(9.5D, 0D, 5.5D, 12.5D, 8D, 8.5D),
			box(2D, 0D, 8D, 6D, 8D, 12D)
	);

	public static final VoxelShape VENTHYR_SHAPE_2 = VoxelShaper.or(
			box(10.5D, 0D, 6.5D, 13.5D, 8D, 9.5D),
			box(2D, 0D, 9D, 6D, 8D, 13D),
			box(5D, 0D, 2D, 9D, 8D, 6D)
	);

	public static final VoxelShape BONE_SHAPE_0 = VoxelShaper.or(
			box(6.5, 0, 6.5, 9.5, 1, 9.5),
			box(7.25, 1, 7.25, 8.75, 4, 8.75),
			box(6, 4, 6, 10, 8, 10)
	);

	public static final VoxelShape BONE_SHAPE_1 = VoxelShaper.or(
			box(3.5, 0, 8.5, 6.5, 1, 11.5),
			box(4.25, 1, 9.25, 5.75, 4, 10.75),
			box(3, 4, 8, 7, 8, 12),
			box(8.25, 4, 4.25, 13.75, 8, 9.75),
			box(9, 0, 5, 13, 1, 9),
			box(10, 1, 6, 12, 4, 8)
	);

	public static final VoxelShape BONE_SHAPE_2 = VoxelShaper.or(
			box(4.5, 0, 10.5, 7.5, 1, 13.5),
			box(5.25, 1, 11.25, 6.75, 4, 12.75),
			box(4, 4, 10, 8, 8, 14),
			box(9.25, 4, 5.25, 14.75, 8, 10.75),
			box(10, 0, 6, 14, 1, 10),
			box(11, 1, 7, 13, 4, 9),
			box(1.25, 4, 2.25, 6.75, 8, 7.75),
			box(2, 0, 3, 6, 1, 7),
			box(3, 1, 4, 5, 4, 6)
	);

	public static final IntegerProperty CHALICES = IntegerProperty.create("chalices", 0, 2);
	public static final VoxelShaper VENTHYR_SHAPER_0 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper VENTHYR_SHAPER_1 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper VENTHYR_SHAPER_2 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_2, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_0 = VoxelShaper.forHorizontal(BONE_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_1 = VoxelShaper.forHorizontal(BONE_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_2 = VoxelShaper.forHorizontal(BONE_SHAPE_2, Direction.NORTH);

	public ChalicesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return CHALICES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(CHALICES);

		if(ModBlocks.VENTHYR_CHALICES.has(blockState))
			return (count == 2 ? VENTHYR_SHAPER_2 : count == 1 ? VENTHYR_SHAPER_1 : VENTHYR_SHAPER_0).get(facing);
		else
			return (count == 2 ? BONE_SHAPER_2 : count == 1 ? BONE_SHAPER_1 : BONE_SHAPER_0).get(facing);
	}
}
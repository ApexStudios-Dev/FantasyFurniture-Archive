package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;

public final class TankardsBlock extends SimpleFourWayStackedBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 12D, 6D, 10D);
	public static final VoxelShape SHAPE_1 = box(1.1879195559200255D, -2.6490953430879927e-8D, 3.3239826453649517D, 14.687919555920026D, 5.999999973509047D, 13.323982645364952D);

	public static final IntegerProperty TANKARDS = IntegerProperty.create("tankards", 0, 1);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);

	public TankardsBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected IntegerProperty getStackSizeProperty()
	{
		return TANKARDS;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(TANKARDS);
		return (count == 0 ? SHAPER_0 : SHAPER_1).get(facing);
	}
}

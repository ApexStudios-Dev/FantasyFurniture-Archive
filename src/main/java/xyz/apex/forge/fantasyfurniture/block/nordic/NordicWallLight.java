package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.block.BaseWallLight;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;

import java.util.Random;

public final class NordicWallLight extends BaseWallLight
{
	public static final VoxelShape SHAPE_NORTH = BlockHelper.makeShape(
			box(6D, 5D, 15D, 10D, 11D, 16D),
			box(6D, 2D, 8D, 10D, 15D, 15D)
	);

	public static final VoxelShape SHAPE_EAST = BlockHelper.makeShape(
			box(0D, 5D, 6D, 1D, 11D, 10D),
			box(1D, 2D, 6D, 8D, 15D, 10D)
	);

	public static final VoxelShape SHAPE_SOUTH = BlockHelper.makeShape(
			box(6D, 5D, 0D, 10D, 11D, 1D),
			box(6D, 2D, 1D, 10D, 15D, 8D)
	);

	public static final VoxelShape SHAPE_WEST = BlockHelper.makeShape(
			box(15D, 5D, 6D, 16D, 11D, 10D),
			box(8D, 2D, 6D, 15D, 15D, 10D)
	);

	public NordicWallLight(Properties properties)
	{
		super(properties, ParticleTypes.FLAME);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		switch(blockState.getValue(FACING))
		{
			case NORTH: return SHAPE_NORTH;
			case EAST: return SHAPE_EAST;
			case SOUTH: return SHAPE_SOUTH;
			case WEST: return SHAPE_WEST;
		}

		return super.getShape(blockState, level, pos, ctx);
	}

	@Override
	protected void playParticleAt(World level, double x, double y, double z, BlockState blockState, Random rng)
	{
		Direction facing = blockState.getValue(FACING).getOpposite();

		double hOffset = .145D;
		double vOffset = .24D;

		double xPos = x + .5D + (hOffset * facing.getStepX());
		double yPos = y + .7D + vOffset;
		double zPos = z + .5D + (hOffset * facing.getStepZ());

		super.playParticleAt(level, xPos, yPos, zPos, blockState, rng);
	}
}

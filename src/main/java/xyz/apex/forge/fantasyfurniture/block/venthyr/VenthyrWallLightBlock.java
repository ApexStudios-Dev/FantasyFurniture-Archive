package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;

import java.util.Random;

public final class VenthyrWallLightBlock extends SetWallLightBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6D, 1D, 15D, 10D, 3D, 16D),
			box(5D, 3D, 15D, 11D, 12D, 16D),
			box(6D, 12D, 15D, 10D, 14D, 16D),
			box(7D, 3.5D, 14D, 9D, 5.5D, 15D),
			box(4.25D, 2.5D, 10.5D, 11.75D, 11.5D, 14D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}

	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			Direction facing = blockState.getValue(FACING).getOpposite();

			double x = pos.getX() + .5D + (.25D * facing.getStepX());
			double y = pos.getY() + .85D;
			double z = pos.getZ() + .5D + (.25D * facing.getStepZ());

			Direction face = facing.getClockWise();
			double xOffset = .15D * face.getStepX();
			double zOffset = .15D * face.getStepZ();

			onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
			onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
		}
	}
}

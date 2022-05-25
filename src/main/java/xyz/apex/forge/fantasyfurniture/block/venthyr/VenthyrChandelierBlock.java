package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetChandelierBlock;

import java.util.Random;

public final class VenthyrChandelierBlock extends SetChandelierBlock
{
	public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);

	public VenthyrChandelierBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			double x = pos.getX() + .2D;
			double y = pos.getY() + .55D;
			double z = pos.getZ() + .2D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
			onLightParticle(level, pos, blockState, x + .6D, y, z, rng);
			onLightParticle(level, pos, blockState, x + .6D, y, z + .6D, rng);
			onLightParticle(level, pos, blockState, x, y, z + .6D, rng);

			onLightParticle(level, pos, blockState, x + .1D, y + .2D, z + .1D, rng);
			onLightParticle(level, pos, blockState, x + .1D + .4D, y + .2D, z + .1D, rng);
			onLightParticle(level, pos, blockState, x + .1D + .4D, y + .2D, z + .1D + .4D, rng);
			onLightParticle(level, pos, blockState, x + .1D, y + .2D, z + .1D + .4D, rng);
		}
	}
}

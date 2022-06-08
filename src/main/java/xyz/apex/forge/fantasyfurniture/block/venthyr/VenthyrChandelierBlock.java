package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			var x = pos.getX() + .2D;
			var y = pos.getY() + .55D;
			var z = pos.getZ() + .2D;

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

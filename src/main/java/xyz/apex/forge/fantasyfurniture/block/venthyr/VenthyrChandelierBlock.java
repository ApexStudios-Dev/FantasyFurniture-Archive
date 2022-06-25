package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetChandelierBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class VenthyrChandelierBlock extends SetChandelierBlock
{
	public VenthyrChandelierBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!isWaterLogged(blockState))
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

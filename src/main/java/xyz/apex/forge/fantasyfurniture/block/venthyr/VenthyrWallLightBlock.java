package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class VenthyrWallLightBlock extends SetWallLightBlock
{
	public VenthyrWallLightBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	protected void spawnLightParticles(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		var x = pX;
		var y = pY + .35D;
		var z = pZ;

		var xOffset = 0D;
		var zOffset = 0D;

		if(supportsFacing(blockState))
		{
			var facing =getFacing(blockState).getOpposite();
			var face = facing.getClockWise();

			xOffset = .15D * face.getStepX();
			zOffset = .15D * face.getStepZ();

			x += .25D * facing.getStepX();
			z += .25D * facing.getStepZ();
		}

		onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
		onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
	}
}

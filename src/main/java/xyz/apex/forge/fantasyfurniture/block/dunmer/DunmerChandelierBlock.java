package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetChandelierBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class DunmerChandelierBlock extends SetChandelierBlock
{
	public DunmerChandelierBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!isWaterLogged(blockState))
		{
			double x = pos.getX() + .5D;
			double y = pos.getY() + .875D;
			double z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x + .275D, y, z - .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z - .275D, rng);
		}
	}
}

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetWallLightBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class DunmerWallLightBlock extends SetWallLightBlock
{
	public DunmerWallLightBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		// NOOP
	}
}

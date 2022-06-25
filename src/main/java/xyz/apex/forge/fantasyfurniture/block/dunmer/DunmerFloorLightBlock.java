package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetFloorLightBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class DunmerFloorLightBlock extends SetFloorLightBlock
{
	public DunmerFloorLightBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED) && blockState.getValue(SIDE) == Side.TOP)
		{
			double x = pos.getX() + .5D;
			double y = pos.getY() + .5D + .5D;
			double z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
	}
}

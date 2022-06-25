package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetFloorLightBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;

public final class VenthyrFloorLightBlock extends SetFloorLightBlock
{
	public VenthyrFloorLightBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED) && blockState.getValue(SIDE) == Side.TOP)
		{
			var x = pos.getX() + .3D;
			var y = pos.getY() + .9D;
			var z = pos.getZ() + .3D;

			onLightParticle(level, pos, blockState, x, y, z, rng);

			onLightParticle(level, pos, blockState, x + .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .4D, rng);
			onLightParticle(level, pos, blockState, x + .4D, y, z + .4D, rng);
		}
	}
}

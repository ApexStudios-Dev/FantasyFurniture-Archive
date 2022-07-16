package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.Random;
import java.util.function.Consumer;

public class ChandelierBlock extends BaseBlock
{
	public ChandelierBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!isWaterLogged(blockState))
			spawnLightParticles(blockState, level, pos, rng);
	}

	protected void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(ModBlocks.NORDIC_CHANDELIER.has(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .65D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x + .25D, y, z - .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z - .25D, rng);
		}
		else if(ModBlocks.DUNMER_CHANDELIER.has(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .875D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x + .275D, y, z - .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z - .275D, rng);
		}
		else if(ModBlocks.VENTHYR_CHANDELIER.has(blockState))
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
		else if(ModBlocks.BONE_SKELETON_CHANDELIER.has(blockState) || ModBlocks.BONE_WITHER_CHANDELIER.has(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .8D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .4D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .4D, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		var flame = ParticleTypes.FLAME;

		if(ModBlocks.BONE_WITHER_CHANDELIER.has(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_CHANDELIER.has(blockState))
			return HitBoxes.NORDIC.chandelier(this, blockState);
		else if(ModBlocks.DUNMER_CHANDELIER.has(blockState))
			return HitBoxes.DUNMER.chandelier(this, blockState);
		else if(ModBlocks.VENTHYR_CHANDELIER.has(blockState))
			return HitBoxes.VENTHYR.chandelier(this, blockState);
		else if(ModBlocks.BONE_SKELETON_CHANDELIER.has(blockState) || ModBlocks.BONE_WITHER_CHANDELIER.has(blockState))
			return HitBoxes.BONE.chandelier(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
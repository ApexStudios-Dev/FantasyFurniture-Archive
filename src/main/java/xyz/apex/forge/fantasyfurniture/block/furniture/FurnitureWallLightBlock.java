package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.WallLightBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class FurnitureWallLightBlock extends WallLightBlock
{
	public FurnitureWallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	protected void spawnLightParticles(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		if(ModBlocks.NORDIC_WALL_LIGHT.has(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY, pZ, rng);
		else if(ModBlocks.VENTHYR_WALL_LIGHT.has(blockState))
		{
			var x = pX;
			var y = pY + .35D;
			var z = pZ;

			var xOffset = 0D;
			var zOffset = 0D;

			if(supportsFacing(blockState))
			{
				var facing = getFacing(blockState).getOpposite();
				var face = facing.getClockWise();

				xOffset = .15D * face.getStepX();
				zOffset = .15D * face.getStepZ();

				x += .25D * facing.getStepX();
				z += .25D * facing.getStepZ();
			}

			onLightParticle(level, pos, blockState, x + xOffset, y, z + zOffset, rng);
			onLightParticle(level, pos, blockState, x - xOffset, y, z - zOffset, rng);
		}
		else if(ModBlocks.BONE_SKELETON_WALL_LIGHT.has(blockState) || ModBlocks.BONE_WITHER_WALL_LIGHT.has(blockState))
			super.spawnLightParticles(level, pos, blockState, pX, pY + .05D, pZ, rng);
	}

	@Override
	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, RandomSource rng)
	{
		var flame = ParticleTypes.FLAME;

		if(ModBlocks.BONE_WITHER_WALL_LIGHT.has(blockState))
			flame = ParticleTypes.SOUL_FIRE_FLAME;

		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(flame, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_WALL_LIGHT.has(blockState))
			return HitBoxes.NORDIC.wallLight(this, blockState);
		else if(ModBlocks.DUNMER_WALL_LIGHT.has(blockState))
			return HitBoxes.DUNMER.wallLight(this, blockState);
		else if(ModBlocks.VENTHYR_WALL_LIGHT.has(blockState))
			return HitBoxes.VENTHYR.wallLight(this, blockState);
		else if(ModBlocks.BONE_SKELETON_WALL_LIGHT.has(blockState) || ModBlocks.BONE_WITHER_WALL_LIGHT.has(blockState))
			return HitBoxes.BONE.wallLight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
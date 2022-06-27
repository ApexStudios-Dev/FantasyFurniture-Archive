package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.Random;
import java.util.function.Consumer;

public class FloorLightBlock extends BaseMultiBlock
{
	public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);

	public FloorLightBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(PART, Part.BOTTOM));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(WATERLOGGED);
		consumer.accept(PART);
	}

	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!isWaterLogged(blockState) && blockState.getOptionalValue(PART).orElse(Part.BOTTOM).isTop())
			spawnLightParticles(blockState, level, pos, rng);
	}

	protected void spawnLightParticles(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(ModBlocks.NORDIC_FLOOR_LIGHT.has(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .34D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .27D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .27D, rng);
		}
		else if(ModBlocks.DUNMER_FLOOR_LIGHT.has(blockState))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .5D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.FLAME, pX, pY, pZ, 0D, 0D, 0D);
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return ModPatterns.PATTERN_1x1x2_PAINTING;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_FLOOR_LIGHT.has(blockState))
			return HitBoxes.NORDIC.floorLight(this, blockState);
		else if(ModBlocks.DUNMER_FLOOR_LIGHT.has(blockState))
			return HitBoxes.DUNMER.floorLight(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public enum Part implements StringRepresentable
	{
		TOP("top"),
		BOTTOM("bottom");

		public final String serializedName;

		Part(String serializedName)
		{
			this.serializedName = serializedName;
		}

		public boolean isTop()
		{
			return this == TOP;
		}

		public boolean isBottom()
		{
			return this == BOTTOM;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}
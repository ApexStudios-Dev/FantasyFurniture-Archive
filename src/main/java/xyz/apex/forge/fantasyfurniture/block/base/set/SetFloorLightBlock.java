package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;
import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.java.utility.nullness.NonnullConsumer;

import java.util.Random;

public class SetFloorLightBlock extends BaseMultiBlock
{
	public static final EnumProperty<Side> SIDE = EnumProperty.create("side", Side.class);

	public SetFloorLightBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(SIDE, Side.BOTTOM));
	}

	@Override
	protected void registerProperties(NonnullConsumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(SIDE);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED) && blockState.getValue(SIDE) == Side.TOP)
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .5D + .34D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .27D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .27D, rng);
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
		return FFPatterns.PATTERN_1x1x2;
	}

	public enum Side implements StringRepresentable
	{
		TOP("top"),
		BOTTOM("bottom");

		private final String serializedName;

		Side(String serializedName)
		{
			this.serializedName = serializedName;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}

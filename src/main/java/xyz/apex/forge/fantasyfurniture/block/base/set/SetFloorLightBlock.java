package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.WaterLoggedMultiBlock;

import java.util.Random;

public class SetFloorLightBlock extends WaterLoggedMultiBlock
{
	public static final EnumProperty<Side> SIDE = EnumProperty.create("side", Side.class);

	public SetFloorLightBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(SIDE, Side.BOTTOM));
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		return pattern.isOrigin(blockState) ? RenderShape.MODEL : RenderShape.INVISIBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(SIDE);
		super.createBlockStateDefinition(builder);
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

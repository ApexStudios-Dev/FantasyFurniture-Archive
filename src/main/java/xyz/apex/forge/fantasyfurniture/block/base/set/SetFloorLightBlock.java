package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		return pattern.isOrigin(blockState) ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(SIDE);
		super.createBlockStateDefinition(builder);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(blockState.getValue(SIDE) == Side.TOP)
		{
			double x = pos.getX() + .5D;
			double y = pos.getY() + .5D + .34D;
			double z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .27D, rng);
			onLightParticle(level, pos, blockState, x, y, z - .27D, rng);
		}
	}

	protected void onLightParticle(World level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.FLAME, pX, pY, pZ, 0D, 0D, 0D);
	}

	public enum Side implements IStringSerializable
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

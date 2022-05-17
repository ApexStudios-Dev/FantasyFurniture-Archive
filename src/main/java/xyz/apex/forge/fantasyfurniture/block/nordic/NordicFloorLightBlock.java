package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.WaterLoggedMultiBlock;

import java.util.Random;

public final class NordicFloorLightBlock extends WaterLoggedMultiBlock
{
	public static final VoxelShape SHAPE_LOWER = VoxelShaper.or(
			box(6D, 0D, 6D, 10D, 2D, 10D),
			box(7D, 2D, 7D, 9D, 20D, 9D),
			box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
			box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
			box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
			box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
			box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
			box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
			box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
			box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
			box(3D, 16.75D, 7D, 7D, 20.75, 9D),
			box(9D, 16.75D, 7D, 13D, 20.75, 9D),
			box(7D, 16.75D, 3D, 9D, 20.75, 7D),
			box(7D, 16.75D, 9D, 9D, 20.75, 13D)
	);

	public static final VoxelShape SHAPE_UPPER = VoxelShaper.or(
			box(6, -16, 6, 10, -14, 10),
			box(7, -14, 7, 9, 4, 9),
			box(6.5, 4.75, 2.5, 9.5, 6.75, 5.5),
			box(2.5, 4.75, 6.5, 5.5, 6.75, 9.5),
			box(7.25, 6.75, 3.25, 8.75, 10.75, 4.75),
			box(3.25, 6.75, 7.25, 4.75, 10.75, 8.75),
			box(7.25, 6.75, 11.25, 8.75, 10.75, 12.75),
			box(11.25, 6.75, 7.25, 12.75, 10.75, 8.75),
			box(10.5, 4.75, 6.5, 13.5, 6.75, 9.5),
			box(6.5, 4.75, 10.5, 9.5, 6.75, 13.5),
			box(3, 0.75, 7, 7, 4.75, 9),
			box(9, 0.75, 7, 13, 4.75, 9),
			box(7, 0.75, 3, 9, 4.75, 7),
			box(7, 0.75, 9, 9, 4.75, 13)
	);

	public static final EnumProperty<Side> SIDE = EnumProperty.create("side", Side.class);

	public NordicFloorLightBlock(Properties properties, MultiBlockPattern pattern)
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
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return pattern.isOrigin(blockState) ? SHAPE_LOWER : SHAPE_UPPER;
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

	private void onLightParticle(World level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
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

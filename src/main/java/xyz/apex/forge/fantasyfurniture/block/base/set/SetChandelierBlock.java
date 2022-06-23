package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.commonality.tags.FluidTags;

import javax.annotation.Nullable;
import java.util.Random;

public class SetChandelierBlock extends Block implements SimpleWaterloggedBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SetChandelierBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState blockState, BlockGetter level, BlockPos pos)
	{
		return !blockState.getValue(WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState blockState)
	{
		return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
	{
		if(blockState.getValue(WATERLOGGED))
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

		return super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var stateForPlacement = super.getStateForPlacement(ctx);

		if(stateForPlacement != null)
		{
			var fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
			var waterLogged = fluidState.is(FluidTags.Vanilla.WATER) && fluidState.isSource();;
			return stateForPlacement.setValue(WATERLOGGED, waterLogged);
		}

		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(WATERLOGGED);
		super.createBlockStateDefinition(builder);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			var x = pos.getX() + .5D;
			var y = pos.getY() + .65D;
			var z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x + .25D, y, z - .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z - .25D, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.FLAME, pX, pY, pZ, 0D, 0D, 0D);
	}
}

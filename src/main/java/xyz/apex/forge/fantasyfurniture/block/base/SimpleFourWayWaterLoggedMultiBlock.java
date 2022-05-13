package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockFourWay;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

public class SimpleFourWayWaterLoggedMultiBlock extends MultiBlockFourWay
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public SimpleFourWayWaterLoggedMultiBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		return pattern.isOrigin(blockState) ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState blockState, IBlockReader level, BlockPos pos)
	{
		return !blockState.getValue(WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState blockState)
	{
		return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos pos, BlockPos facingPos)
	{
		if(blockState.getValue(WATERLOGGED))
			level.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

		return super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
	}

	@Override
	protected BlockState getPlacementState(BlockItemUseContext ctx, BlockState defaultBlockState)
	{
		FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
		boolean waterLogged = fluidState.is(FluidTags.WATER);
		return super.getPlacementState(ctx, defaultBlockState).setValue(WATERLOGGED, waterLogged);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(WATERLOGGED);
		super.createBlockStateDefinition(builder);
	}
}

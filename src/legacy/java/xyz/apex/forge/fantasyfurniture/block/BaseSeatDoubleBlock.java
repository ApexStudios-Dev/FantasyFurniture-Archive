package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

import javax.annotation.Nullable;

public class BaseSeatDoubleBlock extends BaseSeatBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public BaseSeatDoubleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected ActionResultType trySitInSeat(World level, BlockPos pos, PlayerEntity player)
	{
		BlockState blockState = level.getBlockState(pos);

		DoubleBlockHalf half = blockState.getValue(HALF);

		if(half == DoubleBlockHalf.LOWER)
			return super.trySitInSeat(level, pos, player);

		double seatYOffset = getSeatYOffset(blockState);
		BlockPos seatPos = pos.below();

		if(SeatEntity.create(level, seatPos, seatYOffset, player))
		{
			setSeatOccupied(level, seatPos, true);
			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingState, IWorld level, BlockPos currentPos, BlockPos facingPos)
	{
		DoubleBlockHalf half = blockState.getValue(HALF);

		if(facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(HALF) != half)
			return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !blockState.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, facing, facingState, level, currentPos, facingPos);
		else
			return Blocks.AIR.defaultBlockState();
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return super.getStateForPlacement(ctx).setValue(HALF, DoubleBlockHalf.LOWER);
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		level.setBlock(pos.above(), defaultBlockState().setValue(FACING, blockState.getValue(FACING)).setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState pState)
	{
		return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HALF);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		if(blockState.getValue(HALF) != DoubleBlockHalf.UPPER)
			return super.canSurvive(blockState, level, pos);
		else
		{
			BlockState lowerBlockState = level.getBlockState(pos.below());

			if(lowerBlockState.getBlock() != this)
				return super.canSurvive(blockState, level, pos);

			return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide)
		{
			if(player.isCreative())
				preventCreativeDropFromBottomPart(level, pos, blockState, player);
		}

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Override
	public long getSeed(BlockState blockState, BlockPos pos)
	{
		return MathHelper.getSeed(pos.getX(), pos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	protected static void preventCreativeDropFromBottomPart(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		DoubleBlockHalf half = blockState.getValue(HALF);

		if(half == DoubleBlockHalf.UPPER)
		{
			BlockPos belowPos = pos.below();
			BlockState belowBlockState = level.getBlockState(belowPos);

			if(belowBlockState.getBlock() == blockState.getBlock() && belowBlockState.getValue(HALF) == DoubleBlockHalf.LOWER)
			{
				level.setBlock(belowPos, Blocks.AIR.defaultBlockState(), 35);
				level.levelEvent(player, 2001, belowPos, Block.getId(belowBlockState));
			}
		}
	}
}

package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

import javax.annotation.Nullable;

public class BaseSeatBlock extends HorizontalBlock
{
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public BaseSeatBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		if(level.isClientSide)
			return ActionResultType.CONSUME;

		Boolean occupied = blockState.getValue(OCCUPIED);

		if(occupied)
		{
			player.displayClientMessage(new TranslationTextComponent(getDescriptionId() + ".occupied"), true);
			return ActionResultType.SUCCESS;
		}

		DoubleBlockHalf half = blockState.getValue(HALF);
		double seatYOffset = getSeatYOffset(blockState);

		if(half == DoubleBlockHalf.UPPER)
		{
			BlockPos seatPos = pos.below();

			if(SeatEntity.create(level, seatPos, seatYOffset, player))
			{
				setSeatOccupied(level, seatPos, true);
				return ActionResultType.SUCCESS;
			}
		}
		else
		{
			if(SeatEntity.create(level, pos, seatYOffset, player))
			{
				setSeatOccupied(level, pos, true);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	protected double getSeatYOffset(BlockState blockState)
	{
		return 0D;
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

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER).setValue(OCCUPIED, false);
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
		builder.add(FACING, OCCUPIED, HALF);
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
			else
				dropResources(blockState, level, pos, null, player, player.getMainHandItem());
		}

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Override
	public void playerDestroy(World level, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable TileEntity tileEntity, ItemStack stack)
	{
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), tileEntity, stack);
	}

	@Override
	public long getSeed(BlockState blockState, BlockPos pos)
	{
		return MathHelper.getSeed(pos.getX(), pos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
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

	public static void setSeatOccupied(IWorld level, BlockPos pos, boolean occupied)
	{
		BlockState seatBlockState = level.getBlockState(pos);

		if(seatBlockState.hasProperty(OCCUPIED))
			level.setBlock(pos, seatBlockState.setValue(OCCUPIED, occupied), 3);

		if(seatBlockState.hasProperty(HALF))
		{
			BlockPos otherSetBlockPos = seatBlockState.getValue(HALF) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
			BlockState otherSeatBlockState = level.getBlockState(otherSetBlockPos);


			if(otherSeatBlockState.hasProperty(OCCUPIED))
				level.setBlock(otherSetBlockPos, otherSeatBlockState.setValue(OCCUPIED, occupied), 3);
		}
	}
}

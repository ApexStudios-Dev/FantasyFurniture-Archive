package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

import javax.annotation.Nullable;

public class BaseSeatBlock extends SimpleFourWayBlock
{
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

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

		return trySitInSeat(level, pos, player);
	}

	protected ActionResultType trySitInSeat(World level, BlockPos pos, PlayerEntity player)
	{
		BlockState blockState = level.getBlockState(pos);
		double seatYOffset = getSeatYOffset(blockState);

		if(SeatEntity.create(level, pos, seatYOffset, player))
		{
			setSeatOccupied(level, pos, true);
			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	protected double getSeatYOffset(BlockState blockState)
	{
		return 0D;
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return super.getStateForPlacement(ctx).setValue(OCCUPIED, false);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(OCCUPIED);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide && !player.isCreative())
			dropResources(blockState, level, pos, null, player, player.getMainHandItem());

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
		return MathHelper.getSeed(pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	public static void setSeatOccupied(IWorld level, BlockPos pos, boolean occupied)
	{
		BlockState seatBlockState = level.getBlockState(pos);

		if(seatBlockState.hasProperty(OCCUPIED))
			level.setBlock(pos, seatBlockState.setValue(OCCUPIED, occupied), 3);

		if(seatBlockState.hasProperty(BaseSeatDoubleBlock.HALF))
		{
			BlockPos otherSetBlockPos = seatBlockState.getValue(BaseSeatDoubleBlock.HALF) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
			BlockState otherSeatBlockState = level.getBlockState(otherSetBlockPos);


			if(otherSeatBlockState.hasProperty(OCCUPIED))
				level.setBlock(otherSetBlockPos, otherSeatBlockState.setValue(OCCUPIED, occupied), 3);
		}
	}
}

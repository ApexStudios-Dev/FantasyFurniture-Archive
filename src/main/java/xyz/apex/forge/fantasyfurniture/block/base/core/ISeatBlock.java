package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

public interface ISeatBlock
{
	BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

	String getOccupiedTranslationKey();

	default MutableComponent getOccupiedTranslation()
	{
		return new TranslatableComponent(getOccupiedTranslationKey());
	}

	default InteractionResult useSeatBlock(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult)
	{
		if(level.isClientSide)
			return InteractionResult.CONSUME;

		var sitPos = getSeatSitPos(blockState, pos);

		if(isSeatOccupied(level, sitPos, player, this))
		{
			player.displayClientMessage(getOccupiedTranslation(), true);
			return InteractionResult.SUCCESS;
		}

		return trySitInSeat(level, pos, player);
	}

	default double getSeatYOffset(BlockState blockState)
	{
		return 0D;
	}

	default BlockPos getSeatSitPos(BlockState blockState, BlockPos pos)
	{
		return pos;
	}

	default AABB getSeatOccupiedArea(BlockState blockState, BlockPos pos)
	{
		return new AABB(pos).inflate(0D, 1D, 0D);
	}

	default InteractionResult trySitInSeat(Level level, BlockPos pos, Player player)
	{
		var blockState = level.getBlockState(pos);
		var sitPos = getSeatSitPos(blockState, pos);

		if(SeatEntity.create(level, sitPos, this, player))
		{
			setSeatOccupied(level, pos, true);
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	default void setSeatOccupied(Level level, BlockPos pos, BlockState blockState, boolean occupied)
	{
		level.setBlock(pos, blockState.setValue(OCCUPIED, occupied), 3);
	}

	static void setSeatOccupied(Level level, BlockPos pos, boolean occupied)
	{
		var blockState = level.getBlockState(pos);
		var block = blockState.getBlock();

		if(block instanceof ISeatBlock seat)
			seat.setSeatOccupied(level, pos, blockState, occupied);
	}

	static boolean isSeatOccupied(Level level, BlockPos pos, Player sitter, ISeatBlock seatBlock)
	{
		var blockState = level.getBlockState(pos);
		var seatPos = seatBlock.getSeatSitPos(blockState, pos);
		return level.getBlockState(seatPos).getOptionalValue(OCCUPIED).orElse(false);
		/*BlockState blockState = level.getBlockState(pos);
		AxisAlignedBB sitArea = seatBlock.getSeatOccupiedArea(blockState, pos);
		List<PlayerEntity> nearbyPlayers = level.getNearbyPlayers(EntityPredicate.DEFAULT, sitter, sitArea);
		return !nearbyPlayers.isEmpty();*/
	}
}

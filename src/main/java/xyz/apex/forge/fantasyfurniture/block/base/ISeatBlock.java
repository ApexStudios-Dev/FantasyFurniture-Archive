package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

public interface ISeatBlock
{
	BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

	String getOccupiedTranslationKey();

	default ActionResultType useSeatBlock(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		if(level.isClientSide)
			return ActionResultType.CONSUME;

		BlockPos sitPos = getSeatSitPos(blockState, pos);

		if(isSeatOccupied(level, sitPos, player, this))
		{
			player.displayClientMessage(new TranslationTextComponent(getOccupiedTranslationKey()), true);
			return ActionResultType.SUCCESS;
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

	default AxisAlignedBB getSeatOccupiedArea(BlockState blockState, BlockPos pos)
	{
		return new AxisAlignedBB(pos).inflate(0D, 1D, 0D);
	}

	default ActionResultType trySitInSeat(World level, BlockPos pos, PlayerEntity player)
	{
		BlockState blockState = level.getBlockState(pos);
		BlockPos sitPos = getSeatSitPos(blockState, pos);

		if(SeatEntity.create(level, sitPos, this, player))
		{
			setSeatOccupied(level, pos, true);
			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}

	default void setSeatOccupied(World level, BlockPos pos, BlockState blockState, boolean occupied)
	{
		level.setBlock(pos, blockState.setValue(OCCUPIED, occupied), 3);
	}

	static void setSeatOccupied(World level, BlockPos pos, boolean occupied)
	{
		BlockState blockState = level.getBlockState(pos);
		Block block = blockState.getBlock();

		if(block instanceof ISeatBlock)
			((ISeatBlock) block).setSeatOccupied(level, pos, blockState, occupied);
	}

	static boolean isSeatOccupied(World level, BlockPos pos, PlayerEntity sitter, ISeatBlock seatBlock)
	{
		BlockState blockState = level.getBlockState(pos);
		BlockPos seatPos = seatBlock.getSeatSitPos(blockState, pos);
		return level.getBlockState(seatPos).getOptionalValue(OCCUPIED).orElse(false);
		/*BlockState blockState = level.getBlockState(pos);
		AxisAlignedBB sitArea = seatBlock.getSeatOccupiedArea(blockState, pos);
		List<PlayerEntity> nearbyPlayers = level.getNearbyPlayers(EntityPredicate.DEFAULT, sitter, sitArea);
		return !nearbyPlayers.isEmpty();*/
	}
}

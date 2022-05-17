package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlock;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

import java.util.List;

public interface ISeatBlock
{
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
		if(this instanceof MultiBlock)
		{
			MultiBlockPattern pattern = ((MultiBlock) this).getMultiBlockPattern();

			if(!pattern.isOrigin(blockState))
			{
				int index = pattern.getIndex(blockState);
				BlockPos localSpace = pattern.getLocalPositions().get(index);
				return pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
			}
		}

		return pos;
	}

	default AxisAlignedBB getSeatOccupiedArea(BlockState blockState, BlockPos pos)
	{
		BlockPos testPos = pos;

		double xOffset = 0D;
		double yOffset = 0D;
		double zOffset = 0D;

		if(this instanceof MultiBlock)
		{
			MultiBlockPattern pattern = ((MultiBlock) this).getMultiBlockPattern();

			if(!pattern.isOrigin(blockState))
			{
				int index = pattern.getIndex(blockState);
				testPos = pattern.getOriginFromWorldSpace(blockState, pos, pattern.getLocalPositions().get(index));
			}

			for(BlockPos localPos : pattern.getLocalPositions())
			{
				xOffset = Math.max(xOffset, localPos.getX());
				yOffset = Math.max(yOffset, localPos.getY());
				zOffset = Math.max(zOffset, localPos.getZ());
			}
		}
		else
			yOffset = 1D;

		return new AxisAlignedBB(testPos).inflate(xOffset, yOffset, zOffset);
	}

	default ActionResultType trySitInSeat(World level, BlockPos pos, PlayerEntity player)
	{
		BlockState blockState = level.getBlockState(pos);
		BlockPos sitPos = getSeatSitPos(blockState, pos);

		if(SeatEntity.create(level, sitPos, this, player))
			return ActionResultType.SUCCESS;

		return ActionResultType.PASS;
	}

	static boolean isSeatOccupied(World level, BlockPos pos, PlayerEntity sitter, ISeatBlock seatBlock)
	{
		BlockState blockState = level.getBlockState(pos);
		AxisAlignedBB sitArea = seatBlock.getSeatOccupiedArea(blockState, pos);
		List<PlayerEntity> nearbyPlayers = level.getNearbyPlayers(EntityPredicate.DEFAULT, sitter, sitArea);
		return !nearbyPlayers.isEmpty();
	}
}

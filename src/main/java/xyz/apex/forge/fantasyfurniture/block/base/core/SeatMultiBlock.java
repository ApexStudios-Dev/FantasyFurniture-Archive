package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import java.util.List;

public class SeatMultiBlock extends SimpleFourWayWaterLoggedMultiBlock implements ISeatBlock
{
	public SeatMultiBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(OCCUPIED, false));
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		return useSeatBlock(blockState, level, pos, player, hand, rayTraceResult);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public String getOccupiedTranslationKey()
	{
		return getDescriptionId() + ".occupied";
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(OCCUPIED);
		super.createBlockStateDefinition(builder);
	}

	protected boolean sitAtOriginOnly()
	{
		return false;
	}

	@Override
	public BlockPos getSeatSitPos(BlockState blockState, BlockPos pos)
	{
		if(sitAtOriginOnly())
		{
			if(pattern.isOrigin(blockState))
				return pos;

			int index = pattern.getIndex(blockState);
			return pattern.getOriginFromWorldSpace(blockState, pos, pattern.getLocalPositions().get(index));
		}

		return ISeatBlock.super.getSeatSitPos(blockState, pos);
	}

	@Override
	public void setSeatOccupied(World level, BlockPos pos, BlockState blockState, boolean occupied)
	{
		if(sitAtOriginOnly())
		{
			BlockPos origin = pos;
			List<BlockPos> localPositions = pattern.getLocalPositions();

			if(!pattern.isOrigin(blockState))
			{
				int index = pattern.getIndex(blockState);
				origin = pattern.getOriginFromWorldSpace(blockState, pos, localPositions.get(index));
			}

			for(BlockPos localSpace : localPositions)
			{
				BlockPos worldSpace = pattern.getWorldSpaceFromLocalSpace(blockState, origin, localSpace);
				BlockState seatBlockState = level.getBlockState(worldSpace);

				ISeatBlock.super.setSeatOccupied(level, worldSpace, seatBlockState, occupied);
			}
		}
		else
			ISeatBlock.super.setSeatOccupied(level, pos, blockState, occupied);
	}
}

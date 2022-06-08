package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

public class SeatMultiBlock extends SimpleFourWayWaterLoggedMultiBlock implements ISeatBlock
{
	public SeatMultiBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(OCCUPIED, false));
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult)
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
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

			var index = pattern.getIndex(blockState);
			return pattern.getOriginFromWorldSpace(blockState, pos, pattern.getLocalPositions().get(index));
		}

		return ISeatBlock.super.getSeatSitPos(blockState, pos);
	}

	@Override
	public void setSeatOccupied(Level level, BlockPos pos, BlockState blockState, boolean occupied)
	{
		if(sitAtOriginOnly())
		{
			var origin = pos;
			var localPositions = pattern.getLocalPositions();

			if(!pattern.isOrigin(blockState))
			{
				var index = pattern.getIndex(blockState);
				origin = pattern.getOriginFromWorldSpace(blockState, pos, localPositions.get(index));
			}

			for(var localSpace : localPositions)
			{
				var worldSpace = pattern.getWorldSpaceFromLocalSpace(blockState, origin, localSpace);
				var seatBlockState = level.getBlockState(worldSpace);

				ISeatBlock.super.setSeatOccupied(level, worldSpace, seatBlockState, occupied);
			}
		}
		else
			ISeatBlock.super.setSeatOccupied(level, pos, blockState, occupied);
	}
}

package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SeatBlock extends SimpleFourWayWaterLoggedBlock implements ISeatBlock
{
	public SeatBlock(Properties properties)
	{
		super(properties);
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
}

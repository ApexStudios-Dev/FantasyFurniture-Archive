package xyz.apex.forge.fantasyfurniture.block.base;

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

public class SeatMultiBlock extends SimpleFourWayWaterLoggedMultiBlock implements ISeatMultiBlock
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
}

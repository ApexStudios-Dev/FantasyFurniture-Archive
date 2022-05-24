package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedMultiBlock;

import javax.annotation.Nullable;

public class SetWardrobeTopperBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public SetWardrobeTopperBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result)
	{
		// move to origin of topper block
		BlockPos startPos = pos;

		if(!pattern.isOrigin(blockState))
		{
			int index = pattern.getIndex(blockState);
			BlockPos localSpace = pattern.getLocalPositions().get(index);
			startPos = pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
		}

		// validate block below topper origin is a wardrobe block
		BlockPos belowPos = startPos.below();
		BlockState belowBlockState = level.getBlockState(belowPos);
		Block belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof SetWardrobeBlock)
		{
			// move to wardrobe origin block
			SetWardrobeBlock wardrobeBlock = (SetWardrobeBlock) belowBlock;
			MultiBlockPattern multiBlockPattern = wardrobeBlock.getMultiBlockPattern();
			int index = multiBlockPattern.getIndex(belowBlockState);
			BlockPos localSpace = multiBlockPattern.getLocalPositions().get(index);
			BlockPos origin = multiBlockPattern.getOriginFromWorldSpace(belowBlockState, belowPos, localSpace);

			// build new raytrace result
			BlockRayTraceResult newResult = result.withPosition(origin).withDirection(player.getDirection());

			// try again from wardrobe origin pov
			BlockState originBlockState = level.getBlockState(origin);
			return originBlockState.use(level, player, hand, newResult);
		}

		// default logic, no wardrobe below topper
		return super.use(blockState, level, pos, player, hand, result);
	}

	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState blockState, World level, BlockPos pos)
	{
		// move to origin of topper block
		BlockPos startPos = pos;

		if(!pattern.isOrigin(blockState))
		{
			int index = pattern.getIndex(blockState);
			BlockPos localSpace = pattern.getLocalPositions().get(index);
			startPos = pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
		}

		// validate block below topper origin is a wardrobe block
		BlockPos belowPos = startPos.below();
		BlockState belowBlockState = level.getBlockState(belowPos);
		Block belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof SetWardrobeBlock)
		{
			SetWardrobeBlock wardrobeBlock = (SetWardrobeBlock) belowBlock;
			MultiBlockPattern multiBlockPattern = wardrobeBlock.getMultiBlockPattern();
			int index = multiBlockPattern.getIndex(belowBlockState);
			BlockPos localSpace = multiBlockPattern.getLocalPositions().get(index);
			BlockPos origin = multiBlockPattern.getOriginFromWorldSpace(belowBlockState, belowPos, localSpace);

			// try again from wardrobe origin pov
			BlockState originBlockState = level.getBlockState(origin);
			return originBlockState.getMenuProvider(level, origin);
		}

		// default logic, no wardrobe below topper
		return super.getMenuProvider(blockState, level, pos);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}

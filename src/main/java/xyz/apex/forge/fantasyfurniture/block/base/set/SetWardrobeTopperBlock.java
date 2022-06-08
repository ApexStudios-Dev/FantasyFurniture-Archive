package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

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
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		// move to origin of topper block
		var startPos = pos;

		if(!pattern.isOrigin(blockState))
		{
			var index = pattern.getIndex(blockState);
			var localSpace = pattern.getLocalPositions().get(index);
			startPos = pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
		}

		// validate block below topper origin is a wardrobe block
		var belowPos = startPos.below();
		var belowBlockState = level.getBlockState(belowPos);
		var belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof SetWardrobeBlock wardrobeBlock)
		{
			// move to wardrobe origin block
			var multiBlockPattern = wardrobeBlock.getMultiBlockPattern();
			var index = multiBlockPattern.getIndex(belowBlockState);
			var localSpace = multiBlockPattern.getLocalPositions().get(index);
			var origin = multiBlockPattern.getOriginFromWorldSpace(belowBlockState, belowPos, localSpace);

			// build new raytrace result
			var newResult = result.withPosition(origin).withDirection(player.getDirection());

			// try again from wardrobe origin pov
			var originBlockState = level.getBlockState(origin);
			return originBlockState.use(level, player, hand, newResult);
		}

		// default logic, no wardrobe below topper
		return super.use(blockState, level, pos, player, hand, result);
	}

	@Nullable
	@Override
	public MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos pos)
	{
		// move to origin of topper block
		var startPos = pos;

		if(!pattern.isOrigin(blockState))
		{
			var index = pattern.getIndex(blockState);
			var localSpace = pattern.getLocalPositions().get(index);
			startPos = pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
		}

		// validate block below topper origin is a wardrobe block
		var belowPos = startPos.below();
		var belowBlockState = level.getBlockState(belowPos);
		var belowBlock = belowBlockState.getBlock();

		if(belowBlock instanceof SetWardrobeBlock wardrobeBlock)
		{
			var multiBlockPattern = wardrobeBlock.getMultiBlockPattern();
			var index = multiBlockPattern.getIndex(belowBlockState);
			var localSpace = multiBlockPattern.getLocalPositions().get(index);
			var origin = multiBlockPattern.getOriginFromWorldSpace(belowBlockState, belowPos, localSpace);

			// try again from wardrobe origin pov
			var originBlockState = level.getBlockState(origin);
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

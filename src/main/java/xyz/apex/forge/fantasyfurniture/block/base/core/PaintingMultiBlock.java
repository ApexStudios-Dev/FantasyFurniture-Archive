package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import javax.annotation.Nullable;

public class PaintingMultiBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public PaintingMultiBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Nullable
	@Override
	protected BlockState getPlacementState(BlockPlaceContext ctx, BlockState defaultBlockState)
	{
		var blockState = super.getPlacementState(ctx, defaultBlockState);

		if(blockState != null)
		{
			var level = ctx.getLevel();
			var pos = ctx.getClickedPos();

			for(var facing : ctx.getNearestLookingDirections())
			{
				var opposite = facing.getOpposite();

				if(canSupportPainting(level, pos, opposite))
					return blockState.setValue(FACING, opposite);
			}
		}

		return null;
	}

	@Override
	public boolean canSurviveAdditional(LevelReader level, BlockPos pos, BlockState blockState)
	{
		return canSupportPainting(level, pos);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	public static boolean canSupportPainting(LevelReader level, BlockPos pos)
	{
		for(var facing : Direction.Plane.HORIZONTAL)
		{
			if(canSupportPainting(level, pos, facing))
				return true;
		}

		return false;
	}

	public static boolean canSupportPainting(LevelReader level, BlockPos pos, Direction facing)
	{
		return facing.getAxis().isHorizontal();
	}
}

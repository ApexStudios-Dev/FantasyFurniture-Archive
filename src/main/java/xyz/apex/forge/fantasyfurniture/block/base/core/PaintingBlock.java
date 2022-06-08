package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

public class PaintingBlock extends SimpleFourWayWaterLoggedBlock
{
	public PaintingBlock(Properties properties)
	{
		super(properties);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var level = ctx.getLevel();
		var pos = ctx.getClickedPos();

		for(var facing : ctx.getNearestLookingDirections())
		{
			var opposite = facing.getOpposite();

			if(canSupportPainting(level, pos, facing))
				return defaultBlockState().setValue(FACING, opposite);
		}

		return null;
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
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
		if(!facing.getAxis().isHorizontal())
			return false;

		return true;
		/*BlockPos facingPos = pos.relative(facing);
		return level.getBlockState(facingPos).isFaceSturdy(level, facingPos, facing.getOpposite(), BlockVoxelShape.RIGID);*/
	}
}

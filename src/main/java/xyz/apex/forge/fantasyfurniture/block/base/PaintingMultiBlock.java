package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

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
	protected BlockState getPlacementState(BlockItemUseContext ctx, BlockState defaultBlockState)
	{
		BlockState blockState = super.getPlacementState(ctx, defaultBlockState);

		if(blockState != null)
		{
			World level = ctx.getLevel();
			BlockPos pos = ctx.getClickedPos();

			for(Direction facing : ctx.getNearestLookingDirections())
			{
				Direction opposite = facing.getOpposite();

				if(canSupportPainting(level, pos, facing))
					return blockState.setValue(FACING, opposite);
			}
		}

		return null;
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		return canSupportPainting(level, pos);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	public static boolean canSupportPainting(IBlockReader level, BlockPos pos)
	{
		return PaintingBlock.canSupportPainting(level, pos);
	}

	public static boolean canSupportPainting(IBlockReader level, BlockPos pos, Direction facing)
	{
		return PaintingBlock.canSupportPainting(level, pos, facing);
	}
}

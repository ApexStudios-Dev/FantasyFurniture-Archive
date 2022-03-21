package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockFourWay;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

public class BaseWardrobeTopperBlock extends MultiBlockFourWay
{
	public BaseWardrobeTopperBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState)
	{
		Direction facing = blockState.getValue(FACING);
		int index = pattern.getIndex(blockState);

		if(facing == Direction.EAST || facing == Direction.SOUTH)
			return index == 1 ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;

		return index == MultiBlockPattern.INDEX_ORIGIN ? BlockRenderType.MODEL : BlockRenderType.INVISIBLE;
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}
}

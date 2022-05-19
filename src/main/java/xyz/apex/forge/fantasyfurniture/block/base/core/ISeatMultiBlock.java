package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlock;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import java.util.List;

public interface ISeatMultiBlock extends ISeatBlock
{
	@Override
	default BlockPos getSeatSitPos(BlockState blockState, BlockPos pos)
	{
		if(this instanceof MultiBlock)
		{
			MultiBlockPattern pattern = ((MultiBlock) this).getMultiBlockPattern();

			if(!pattern.isOrigin(blockState))
			{
				int index = pattern.getIndex(blockState);
				BlockPos localSpace = pattern.getLocalPositions().get(index);
				return pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
			}
		}

		return ISeatBlock.super.getSeatSitPos(blockState, pos);
	}

	@Override
	default AxisAlignedBB getSeatOccupiedArea(BlockState blockState, BlockPos pos)
	{
		if(this instanceof MultiBlock)
		{
			BlockPos testPos = pos;

			double xOffset = 0D;
			double yOffset = 0D;
			double zOffset = 0D;

			MultiBlockPattern pattern = ((MultiBlock) this).getMultiBlockPattern();

			if(!pattern.isOrigin(blockState))
			{
				int index = pattern.getIndex(blockState);
				testPos = pattern.getOriginFromWorldSpace(blockState, pos, pattern.getLocalPositions().get(index));
			}

			for(BlockPos localPos : pattern.getLocalPositions())
			{
				xOffset = Math.max(xOffset, localPos.getX());
				yOffset = Math.max(yOffset, localPos.getY());
				zOffset = Math.max(zOffset, localPos.getZ());
			}

			return new AxisAlignedBB(testPos).inflate(xOffset, yOffset, zOffset);
		}

		return ISeatBlock.super.getSeatOccupiedArea(blockState, pos);
	}

	@Override
	default void setSeatOccupied(World level, BlockPos pos, BlockState blockState, boolean occupied)
	{
		if(this instanceof MultiBlock)
		{
			MultiBlockPattern pattern = ((MultiBlock) this).getMultiBlockPattern();
			int index = pattern.getIndex(blockState);
			List<BlockPos> localPositions = pattern.getLocalPositions();
			BlockPos origin = pattern.getOriginFromWorldSpace(blockState, pos, localPositions.get(index));

			for(BlockPos localPos : localPositions)
			{
				BlockPos worldSpace = pattern.getWorldSpaceFromLocalSpace(blockState, origin, localPos);
				BlockState otherState = level.getBlockState(worldSpace);

				ISeatBlock.super.setSeatOccupied(level, worldSpace, otherState, occupied);
			}
		}
		else
			ISeatBlock.super.setSeatOccupied(level, pos, blockState, occupied);
	}
}

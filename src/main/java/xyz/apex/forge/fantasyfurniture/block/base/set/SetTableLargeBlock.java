package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedMultiBlock;

public class SetTableLargeBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public SetTableLargeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}

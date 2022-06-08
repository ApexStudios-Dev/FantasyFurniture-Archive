package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import xyz.apex.forge.fantasyfurniture.block.base.core.SimpleFourWayWaterLoggedBlock;

public class SetTableSmallBlock extends SimpleFourWayWaterLoggedBlock
{
	public SetTableSmallBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}
}

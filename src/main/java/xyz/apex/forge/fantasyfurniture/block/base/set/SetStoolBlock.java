package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.core.SeatBlock;

public class SetStoolBlock extends SeatBlock
{
	public SetStoolBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}
}

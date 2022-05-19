package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.core.SofaBlock;

public class SetSofaBlock extends SofaBlock
{
	public SetSofaBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .1D;
	}
}

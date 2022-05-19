package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.block.BlockState;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SeatMultiBlock;

public class SetBenchBlock extends SeatMultiBlock
{
	public SetBenchBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}
}

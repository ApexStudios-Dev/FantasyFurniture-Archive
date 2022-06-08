package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.SeatMultiBlock;

public class SetChairBlock extends SeatMultiBlock
{
	public SetChairBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .3D;
	}
}

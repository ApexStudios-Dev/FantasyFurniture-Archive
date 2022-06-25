package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedDoubleBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public final class DunmerBedDoubleBlock extends SetBedDoubleBlock
{
	public DunmerBedDoubleBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		int index = getMultiBlockIndex(blockState);
		return index == 2 ? RenderShape.MODEL : super.getRenderShape(blockState);
	}
}

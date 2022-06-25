package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public final class DunmerBedSingleBlock extends SetBedSingleBlock
{
	public DunmerBedSingleBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		return RenderShape.MODEL;
	}
}

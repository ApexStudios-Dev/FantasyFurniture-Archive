package xyz.apex.forge.fantasyfurniture.block.base.set;

import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public class SetChairOriginOnly extends SetChairBlock
{
	public SetChairOriginOnly(ModBlocks furnitureSet, Properties properties)
	{
		super(furnitureSet, properties);
	}

	@Override
	public boolean sitAtOriginOnly()
	{
		return true;
	}
}

package xyz.apex.forge.fantasyfurniture.item.dyeable;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class DyeableBlockItem extends BlockItem implements IDyeableItem
{
	public DyeableBlockItem(Block block, Properties properties)
	{
		super(block, properties);
	}

	@Override
	public ItemStack getDefaultInstance()
	{
		return IDyeableItem.withDefaultDyeColor(super.getDefaultInstance());
	}
}
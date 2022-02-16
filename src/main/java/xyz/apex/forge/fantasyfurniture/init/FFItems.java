package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.item.BedItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

public final class FFItems
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final ItemEntry<BedItem> NORDIC_BED_SINGLE = fromBlock(FFBlocks.NORDIC_BED_SINGLE);

	static void bootstrap()
	{
	}

	private static <BLOCK extends Block, ITEM extends BlockItem> ItemEntry<ITEM> fromBlock(BlockEntry<BLOCK> block)
	{
		return ItemEntry.cast(block.getSibling(Item.class));
	}
}

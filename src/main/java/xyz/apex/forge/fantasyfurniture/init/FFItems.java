package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

public final class FFItems
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final ItemEntry<BlockItem> NORDIC_BED_DOUBLE = fromBlock(FFBlocks.NORDIC_BED_DOUBLE);
	public static final ItemEntry<BlockItem> NORDIC_BED_SINGLE = fromBlock(FFBlocks.NORDIC_BED_SINGLE);
	public static final ItemEntry<BlockItem> NORDIC_BENCH = fromBlock(FFBlocks.NORDIC_BENCH);
	public static final ItemEntry<BlockItem> NORDIC_CHAIR = fromBlock(FFBlocks.NORDIC_CHAIR);
	public static final ItemEntry<BlockItem> NORDIC_CHEST = fromBlock(FFBlocks.NORDIC_CHEST);
	public static final ItemEntry<BlockItem> NORDIC_CUSHION = fromBlock(FFBlocks.NORDIC_CUSHION);
	public static final ItemEntry<BlockItem> NORDIC_DRAWER = fromBlock(FFBlocks.NORDIC_DRAWER);
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_WIDE = fromBlock(FFBlocks.NORDIC_PAINTING_WIDE);
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_SMALL = fromBlock(FFBlocks.NORDIC_PAINTING_SMALL);
	public static final ItemEntry<BlockItem> NORDIC_SHELF = fromBlock(FFBlocks.NORDIC_SHELF);
	public static final ItemEntry<BlockItem> NORDIC_STOOL = fromBlock(FFBlocks.NORDIC_STOOL);
	public static final ItemEntry<BlockItem> NORDIC_SOFA = fromBlock(FFBlocks.NORDIC_SOFA);
	public static final ItemEntry<BlockItem> NORDIC_TABLE_LARGE = fromBlock(FFBlocks.NORDIC_TABLE_LARGE);
	public static final ItemEntry<BlockItem> NORDIC_TABLE_LONG = fromBlock(FFBlocks.NORDIC_TABLE_LONG);
	public static final ItemEntry<BlockItem> NORDIC_TABLE_SMALL = fromBlock(FFBlocks.NORDIC_TABLE_SMALL);
	public static final ItemEntry<BlockItem> NORDIC_TABLE_WIDE = fromBlock(FFBlocks.NORDIC_TABLE_WIDE);
	public static final ItemEntry<BlockItem> NORDIC_WALL_LIGHT = fromBlock(FFBlocks.NORDIC_WALL_LIGHT);
	public static final ItemEntry<BlockItem> NORDIC_WARDROBE = fromBlock(FFBlocks.NORDIC_WARDROBE);

	public static final ItemEntry<BlockItem> BERRY_BASKET = fromBlock(FFBlocks.BERRY_BASKET);

	static void bootstrap()
	{
	}

	private static <BLOCK extends Block, ITEM extends BlockItem> ItemEntry<ITEM> fromBlock(BlockEntry<BLOCK> block)
	{
		return ItemEntry.cast(block.getSibling(Item.class));
	}
}

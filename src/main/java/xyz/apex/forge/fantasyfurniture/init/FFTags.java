package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.apexcore.core.init.ACTags;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.BLOCK_ENTITY_TAGS_PROVIDER;
import static com.tterrag.registrate.providers.ProviderType.*;

@SuppressWarnings("unchecked")
public final class FFTags
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	static void bootstrap()
	{
		Blocks.bootstrap();
		Items.bootstrap();
		BlockEntities.bootstrap();
		Entities.bootstrap();
	}

	public static final class Blocks
	{
		public static final ITag.INamedTag<Block> NORDIC = REGISTRY.moddedBlockTag("nordic");
		public static final ITag.INamedTag<Block> DECORATIONS = REGISTRY.moddedBlockTag("decorations");

		public static final ITag.INamedTag<Block> BOOKSHELVES = REGISTRY.moddedBlockTag("bookshelves");
		public static final ITag.INamedTag<Block> DESKS = REGISTRY.moddedBlockTag("desks");
		public static final ITag.INamedTag<Block> DRAWERS = REGISTRY.moddedBlockTag("drawers");
		public static final ITag.INamedTag<Block> PAINTINGS = REGISTRY.moddedBlockTag("paintings");
		public static final ITag.INamedTag<Block> SHELVES = REGISTRY.moddedBlockTag("shelves");
		public static final ITag.INamedTag<Block> TORCHES = REGISTRY.moddedBlockTag("torches");
		public static final ITag.INamedTag<Block> WARDROBES = REGISTRY.moddedBlockTag("wardrobes");
		public static final ITag.INamedTag<Block> CARPETS = REGISTRY.moddedBlockTag("carpets");
		public static final ITag.INamedTag<Block> WOOLS = REGISTRY.moddedBlockTag("wools");

		public static final ITag.INamedTag<Block> BEDS = REGISTRY.moddedBlockTag("beds");
		public static final ITag.INamedTag<Block> BEDS_SINGLE = REGISTRY.moddedBlockTag("beds/single");
		public static final ITag.INamedTag<Block> BEDS_DOUBLE = REGISTRY.moddedBlockTag("beds/double");

		public static final ITag.INamedTag<Block> SEATS = REGISTRY.moddedBlockTag("seats");
		public static final ITag.INamedTag<Block> BENCHES = REGISTRY.moddedBlockTag("benches");
		public static final ITag.INamedTag<Block> CHAIRS = REGISTRY.moddedBlockTag("chairs");
		public static final ITag.INamedTag<Block> CUSHIONS = REGISTRY.moddedBlockTag("cushions");
		public static final ITag.INamedTag<Block> STOOLS = REGISTRY.moddedBlockTag("stools");
		public static final ITag.INamedTag<Block> SOFAS = REGISTRY.moddedBlockTag("sofas");

		public static final ITag.INamedTag<Block> CHESTS = REGISTRY.moddedBlockTag("chests");
		public static final ITag.INamedTag<Block> CHESTS_WOODEN = REGISTRY.moddedBlockTag("chests/wooden");

		public static final ITag.INamedTag<Block> TABLES_LARGE = REGISTRY.moddedBlockTag("tables/large");
		public static final ITag.INamedTag<Block> TABLES_LONG = REGISTRY.moddedBlockTag("tables/long");
		public static final ITag.INamedTag<Block> TABLES_SMALL = REGISTRY.moddedBlockTag("tables/small");
		public static final ITag.INamedTag<Block> TABLES_WIDE = REGISTRY.moddedBlockTag("tables/wide");
		public static final ITag.INamedTag<Block> TABLES = REGISTRY.moddedBlockTag("tables");

		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(BLOCK_TAGS, provider -> {
				provider.tag(NORDIC);
				provider.tag(DECORATIONS);

				provider.tag(BOOKSHELVES);
				provider.tag(DESKS);
				provider.tag(DRAWERS);
				provider.tag(PAINTINGS);
				provider.tag(SHELVES);
				provider.tag(TORCHES);
				provider.tag(WARDROBES);

				provider.tag(WOOLS);
				provider.tag(ACTags.Blocks.WOOLS).addTags(WOOLS);
				provider.tag(BlockTags.WOOL).addTags(WOOLS);

				provider.tag(CARPETS);
				provider.tag(BlockTags.CARPETS).addTags(CARPETS);

				provider.tag(BEDS_SINGLE);
				provider.tag(BEDS_DOUBLE);
				provider.tag(BEDS).addTags(BEDS_SINGLE, BEDS_DOUBLE);
				provider.tag(BlockTags.BEDS).addTag(BEDS);

				provider.tag(BENCHES);
				provider.tag(CHAIRS);
				provider.tag(CUSHIONS);
				provider.tag(STOOLS);
				provider.tag(SOFAS);
				provider.tag(SEATS).addTags(BENCHES, CHAIRS, CUSHIONS, STOOLS, SOFAS);

				provider.tag(CHESTS_WOODEN);
				provider.tag(CHESTS).addTag(CHESTS_WOODEN);
				provider.tag(Tags.Blocks.CHESTS_WOODEN).addTags(CHESTS_WOODEN);
				provider.tag(Tags.Blocks.CHESTS).addTags(CHESTS);

				provider.tag(TABLES_LARGE);
				provider.tag(TABLES_LONG);
				provider.tag(TABLES_SMALL);
				provider.tag(TABLES_WIDE);
				provider.tag(TABLES).addTags(TABLES_LARGE, TABLES_LONG, TABLES_SMALL, TABLES_WIDE);
			});
		}
	}

	public static final class Items
	{
		public static final ITag.INamedTag<Item> NORDIC = REGISTRY.moddedItemTag("nordic");
		public static final ITag.INamedTag<Item> DECORATIONS = REGISTRY.moddedItemTag("decorations");

		public static final ITag.INamedTag<Item> BOOKSHELVES = REGISTRY.moddedItemTag("bookshelves");
		public static final ITag.INamedTag<Item> DESKS = REGISTRY.moddedItemTag("desks");
		public static final ITag.INamedTag<Item> DRAWERS = REGISTRY.moddedItemTag("drawers");
		public static final ITag.INamedTag<Item> PAINTINGS = REGISTRY.moddedItemTag("paintings");
		public static final ITag.INamedTag<Item> SHELVES = REGISTRY.moddedItemTag("shelves");
		public static final ITag.INamedTag<Item> TORCHES = REGISTRY.moddedItemTag("torches");
		public static final ITag.INamedTag<Item> WARDROBES = REGISTRY.moddedItemTag("wardrobes");
		public static final ITag.INamedTag<Item> CARPETS = REGISTRY.moddedItemTag("carpets");
		public static final ITag.INamedTag<Item> WOOLS = REGISTRY.moddedItemTag("wools");

		public static final ITag.INamedTag<Item> BEDS = REGISTRY.moddedItemTag("beds");
		public static final ITag.INamedTag<Item> BEDS_SINGLE = REGISTRY.moddedItemTag("beds/single");
		public static final ITag.INamedTag<Item> BEDS_DOUBLE = REGISTRY.moddedItemTag("beds/double");

		public static final ITag.INamedTag<Item> SEATS = REGISTRY.moddedItemTag("seats");
		public static final ITag.INamedTag<Item> BENCHES = REGISTRY.moddedItemTag("benches");
		public static final ITag.INamedTag<Item> CHAIRS = REGISTRY.moddedItemTag("chairs");
		public static final ITag.INamedTag<Item> CUSHIONS = REGISTRY.moddedItemTag("cushions");
		public static final ITag.INamedTag<Item> STOOLS = REGISTRY.moddedItemTag("stools");
		public static final ITag.INamedTag<Item> SOFAS = REGISTRY.moddedItemTag("sofas");

		public static final ITag.INamedTag<Item> CHESTS = REGISTRY.moddedItemTag("chests");
		public static final ITag.INamedTag<Item> CHESTS_WOODEN = REGISTRY.moddedItemTag("chests/wooden");

		public static final ITag.INamedTag<Item> TABLES_LARGE = REGISTRY.moddedItemTag("tables/large");
		public static final ITag.INamedTag<Item> TABLES_LONG = REGISTRY.moddedItemTag("tables/long");
		public static final ITag.INamedTag<Item> TABLES_SMALL = REGISTRY.moddedItemTag("tables/small");
		public static final ITag.INamedTag<Item> TABLES_WIDE = REGISTRY.moddedItemTag("tables/wide");
		public static final ITag.INamedTag<Item> TABLES = REGISTRY.moddedItemTag("tables");

		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(ITEM_TAGS, provider -> {
				provider.tag(NORDIC);
				provider.tag(DECORATIONS);

				provider.tag(DESKS);
				provider.tag(DRAWERS);
				provider.tag(PAINTINGS);
				provider.tag(SHELVES);
				provider.tag(TORCHES);
				provider.tag(WARDROBES);

				provider.tag(CARPETS);
				provider.tag(ItemTags.CARPETS).addTags(CARPETS);

				provider.tag(WOOLS);
				provider.tag(ACTags.Items.WOOLS).addTags(WOOLS);
				provider.tag(ItemTags.WOOL).addTags(CARPETS);

				provider.tag(BOOKSHELVES);
				provider.tag(Tags.Items.BOOKSHELVES).addTags(BOOKSHELVES);

				provider.tag(BEDS_SINGLE);
				provider.tag(BEDS_DOUBLE);
				provider.tag(BEDS).addTags(BEDS_SINGLE, BEDS_DOUBLE);
				provider.tag(ItemTags.BEDS).addTag(BEDS);

				provider.tag(BENCHES);
				provider.tag(CHAIRS);
				provider.tag(CUSHIONS);
				provider.tag(STOOLS);
				provider.tag(SOFAS);
				provider.tag(SEATS).addTags(BENCHES, CHAIRS, CUSHIONS, STOOLS, SOFAS);

				provider.tag(CHESTS_WOODEN);
				provider.tag(CHESTS).addTag(CHESTS_WOODEN);
				provider.tag(Tags.Items.CHESTS_WOODEN).addTags(CHESTS_WOODEN);
				provider.tag(Tags.Items.CHESTS).addTags(CHESTS);

				provider.tag(TABLES_LARGE);
				provider.tag(TABLES_LONG);
				provider.tag(TABLES_SMALL);
				provider.tag(TABLES_WIDE);
				provider.tag(TABLES).addTags(TABLES_LARGE, TABLES_LONG, TABLES_SMALL, TABLES_WIDE);
			});
		}
	}

	public static final class BlockEntities
	{
		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(BLOCK_ENTITY_TAGS_PROVIDER, provider -> {

			});
		}
	}

	public static final class Entities
	{
		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(ENTITY_TAGS, provider -> {

			});
		}
	}
}

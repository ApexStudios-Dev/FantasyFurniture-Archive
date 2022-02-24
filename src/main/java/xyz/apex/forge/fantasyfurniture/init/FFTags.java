package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.BLOCK_ENTITY_TAGS_PROVIDER;
import static xyz.apex.repack.com.tterrag.registrate.providers.ProviderType.*;

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
		public static final ITag.INamedTag<Block> NORDIC = REGISTRY.blockTagModded("nordic");
		public static final ITag.INamedTag<Block> DECORATIONS = REGISTRY.blockTagModded("decorations");

		public static final ITag.INamedTag<Block> BOOKSHELVES = REGISTRY.blockTagModded("bookshelves");
		public static final ITag.INamedTag<Block> DESKS = REGISTRY.blockTagModded("desks");
		public static final ITag.INamedTag<Block> DRAWERS = REGISTRY.blockTagModded("drawers");
		public static final ITag.INamedTag<Block> PAINTINGS = REGISTRY.blockTagModded("paintings");
		public static final ITag.INamedTag<Block> SHELVES = REGISTRY.blockTagModded("shelves");
		public static final ITag.INamedTag<Block> TORCHES = REGISTRY.blockTagModded("torches");
		public static final ITag.INamedTag<Block> WARDROBES = REGISTRY.blockTagModded("wardrobes");

		public static final ITag.INamedTag<Block> BEDS = REGISTRY.blockTagModded("beds");
		public static final ITag.INamedTag<Block> BEDS_SINGLE = REGISTRY.blockTagModded("beds/single");
		public static final ITag.INamedTag<Block> BEDS_DOUBLE = REGISTRY.blockTagModded("beds/double");

		public static final ITag.INamedTag<Block> SEATS = REGISTRY.blockTagModded("seats");
		public static final ITag.INamedTag<Block> BENCHES = REGISTRY.blockTagModded("benches");
		public static final ITag.INamedTag<Block> CHAIRS = REGISTRY.blockTagModded("chairs");
		public static final ITag.INamedTag<Block> CUSHIONS = REGISTRY.blockTagModded("cushions");
		public static final ITag.INamedTag<Block> STOOLS = REGISTRY.blockTagModded("stools");

		public static final ITag.INamedTag<Block> CHESTS = REGISTRY.blockTagModded("chests");
		public static final ITag.INamedTag<Block> CHESTS_WOODEN = REGISTRY.blockTagModded("chests/wooden");

		public static final ITag.INamedTag<Block> TABLES_LARGE = REGISTRY.blockTagModded("tables/large");
		public static final ITag.INamedTag<Block> TABLES_LONG = REGISTRY.blockTagModded("tables/long");
		public static final ITag.INamedTag<Block> TABLES_SMALL = REGISTRY.blockTagModded("tables/small");
		public static final ITag.INamedTag<Block> TABLES_WIDE = REGISTRY.blockTagModded("tables/wide");
		public static final ITag.INamedTag<Block> TABLES = REGISTRY.blockTagModded("tables");

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

				provider.tag(BEDS_SINGLE);
				provider.tag(BEDS_DOUBLE);
				provider.tag(BEDS).addTags(BEDS_SINGLE, BEDS_DOUBLE);
				provider.tag(BlockTags.BEDS).addTag(BEDS);

				provider.tag(BENCHES);
				provider.tag(CHAIRS);
				provider.tag(CUSHIONS);
				provider.tag(STOOLS);
				provider.tag(SEATS).addTags(BENCHES, CHAIRS, CUSHIONS, STOOLS);

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
		public static final ITag.INamedTag<Item> NORDIC = REGISTRY.itemTagModded("nordic");
		public static final ITag.INamedTag<Item> DECORATIONS = REGISTRY.itemTagModded("decorations");

		public static final ITag.INamedTag<Item> BOOKSHELVES = REGISTRY.itemTagModded("bookshelves");
		public static final ITag.INamedTag<Item> DESKS = REGISTRY.itemTagModded("desks");
		public static final ITag.INamedTag<Item> DRAWERS = REGISTRY.itemTagModded("drawers");
		public static final ITag.INamedTag<Item> PAINTINGS = REGISTRY.itemTagModded("paintings");
		public static final ITag.INamedTag<Item> SHELVES = REGISTRY.itemTagModded("shelves");
		public static final ITag.INamedTag<Item> TORCHES = REGISTRY.itemTagModded("torches");
		public static final ITag.INamedTag<Item> WARDROBES = REGISTRY.itemTagModded("wardrobes");

		public static final ITag.INamedTag<Item> BEDS = REGISTRY.itemTagModded("beds");
		public static final ITag.INamedTag<Item> BEDS_SINGLE = REGISTRY.itemTagModded("beds/single");
		public static final ITag.INamedTag<Item> BEDS_DOUBLE = REGISTRY.itemTagModded("beds/double");

		public static final ITag.INamedTag<Item> SEATS = REGISTRY.itemTagModded("seats");
		public static final ITag.INamedTag<Item> BENCHES = REGISTRY.itemTagModded("benches");
		public static final ITag.INamedTag<Item> CHAIRS = REGISTRY.itemTagModded("chairs");
		public static final ITag.INamedTag<Item> CUSHIONS = REGISTRY.itemTagModded("cushions");
		public static final ITag.INamedTag<Item> STOOLS = REGISTRY.itemTagModded("stools");

		public static final ITag.INamedTag<Item> CHESTS = REGISTRY.itemTagModded("chests");
		public static final ITag.INamedTag<Item> CHESTS_WOODEN = REGISTRY.itemTagModded("chests/wooden");

		public static final ITag.INamedTag<Item> TABLES_LARGE = REGISTRY.itemTagModded("tables/large");
		public static final ITag.INamedTag<Item> TABLES_LONG = REGISTRY.itemTagModded("tables/long");
		public static final ITag.INamedTag<Item> TABLES_SMALL = REGISTRY.itemTagModded("tables/small");
		public static final ITag.INamedTag<Item> TABLES_WIDE = REGISTRY.itemTagModded("tables/wide");
		public static final ITag.INamedTag<Item> TABLES = REGISTRY.itemTagModded("tables");

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
				provider.tag(SEATS).addTags(BENCHES, CHAIRS, CUSHIONS, STOOLS);

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

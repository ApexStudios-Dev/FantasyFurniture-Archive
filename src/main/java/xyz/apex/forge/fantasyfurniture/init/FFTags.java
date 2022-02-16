package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.BLOCK_ENTITY_TAGS_PROVIDER;
import static xyz.apex.repack.com.tterrag.registrate.providers.ProviderType.*;

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

		public static final ITag.INamedTag<Block> BEDS = REGISTRY.blockTagModded("beds");
		public static final ITag.INamedTag<Block> CHAIRS = REGISTRY.blockTagModded("chairs");
		public static final ITag.INamedTag<Block> CUSHIONS = REGISTRY.blockTagModded("cushions");
		public static final ITag.INamedTag<Block> SHELVES = REGISTRY.blockTagModded("shelves");
		public static final ITag.INamedTag<Block> TABLES = REGISTRY.blockTagModded("tables");

		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(BLOCK_TAGS, provider -> {
				provider.tag(NORDIC);
				provider.tag(DECORATIONS);

				provider.tag(BEDS);
				provider.tag(CHAIRS);
				provider.tag(CUSHIONS);
				provider.tag(SHELVES);
				provider.tag(TABLES);

				provider.tag(BlockTags.BEDS).addTag(BEDS);
			});
		}
	}

	public static final class Items
	{
		public static final ITag.INamedTag<Item> NORDIC = REGISTRY.itemTagModded("nordic");
		public static final ITag.INamedTag<Item> DECORATIONS = REGISTRY.itemTagModded("decorations");

		public static final ITag.INamedTag<Item> BEDS = REGISTRY.itemTagModded("beds");
		public static final ITag.INamedTag<Item> CHAIRS = REGISTRY.itemTagModded("chairs");
		public static final ITag.INamedTag<Item> CUSHIONS = REGISTRY.itemTagModded("cushions");
		public static final ITag.INamedTag<Item> SHELVES = REGISTRY.itemTagModded("shelves");
		public static final ITag.INamedTag<Item> TABLES = REGISTRY.itemTagModded("tables");

		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(ITEM_TAGS, provider -> {
				provider.tag(NORDIC);
				provider.tag(DECORATIONS);

				provider.tag(BEDS);
				provider.tag(CHAIRS);
				provider.tag(CUSHIONS);
				provider.tag(SHELVES);
				provider.tag(TABLES);

				provider.tag(ItemTags.BEDS).addTag(BEDS);
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

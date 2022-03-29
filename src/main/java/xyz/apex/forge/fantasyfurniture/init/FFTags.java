package xyz.apex.forge.fantasyfurniture.init;

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
		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(BLOCK_TAGS, provider -> {
			});
		}
	}

	public static final class Items
	{
		private static void bootstrap()
		{
			REGISTRY.addDataGenerator(ITEM_TAGS, provider -> {
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

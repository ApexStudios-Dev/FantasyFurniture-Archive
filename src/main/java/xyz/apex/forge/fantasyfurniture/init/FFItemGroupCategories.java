package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.repack.com.tterrag.registrate.providers.RegistrateLangProvider;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static xyz.apex.repack.com.tterrag.registrate.providers.ProviderType.LANG;

public final class FFItemGroupCategories
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
				.tagged(FFTags.Items.NORDIC)
			.build();

	public static final ItemGroupCategory DECORATIONS = ItemGroupCategory
			.builder("decorations")
				.tagged(FFTags.Items.DECORATIONS)
			.build();

	static final ItemGroupCategory[] CATEGORIES = new ItemGroupCategory[] {
			NORDIC,
			DECORATIONS
	};

	static void bootstrap()
	{
		REGISTRY.addDataGenerator(LANG, provider -> {
			for(ItemGroupCategory category : CATEGORIES)
			{
				String categoryName = category.getCategoryNameKey().substring("itemGroup.category.".length());
				provider.add(category.getCategoryNameKey(), RegistrateLangProvider.toEnglishName(categoryName));
			}
		});

		REGISTRY.addDataGenerator(LANG_EXT_PROVIDER, provider -> {
			for(ItemGroupCategory category : CATEGORIES)
			{
				String categoryName = category.getCategoryNameKey().substring("itemGroup.category.".length());
				provider.add(EN_GB, category.getCategoryNameKey(), RegistrateLangProvider.toEnglishName(categoryName));
			}
		});
	}
}

package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;

public final class FFItemGroupCategories
{
	public static final ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
				.tagged(FFTags.Items.NORDIC)
			.build();

	public static final ItemGroupCategory DECORATIONS = ItemGroupCategory
			.builder("decorations")
				.tagged(FFTags.Items.DECORATIONS)
			.build();

	static void bootstrap()
	{
	}
}

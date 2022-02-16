package xyz.apex.forge.fantasyfurniture.init;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;

public final class FFItemGroupCategories
{
	public static final ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
				.tagged(FFTags.Items.NORDIC)
			.build();

	public static final ItemGroupCategory BEDS = ItemGroupCategory
			.builder("beds")
				.tagged(FFTags.Items.BEDS)
			.build();

	public static final ItemGroupCategory SHELVES = ItemGroupCategory
			.builder("shelves")
				.tagged(FFTags.Items.SHELVES)
			.build();

	public static final ItemGroupCategory CHAIRS = ItemGroupCategory
			.builder("chairs")
				.tagged(FFTags.Items.CHAIRS)
			.build();

	public static final ItemGroupCategory TABLES = ItemGroupCategory
			.builder("tables")
				.tagged(FFTags.Items.TABLES)
			.build();

	public static final ItemGroupCategory CUSHIONS = ItemGroupCategory
			.builder("cushions")
				.tagged(FFTags.Items.CUSHIONS)
			.build();

	public static final ItemGroupCategory DECORATIONS = ItemGroupCategory
			.builder("decorations")
				.tagged(FFTags.Items.DECORATIONS)
			.build();

	static void bootstrap()
	{
	}
}

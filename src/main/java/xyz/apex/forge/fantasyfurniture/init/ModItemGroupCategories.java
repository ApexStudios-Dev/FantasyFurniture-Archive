package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;

public final class ModItemGroupCategories
{
	public static final TagKey<Item> NORDIC_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/nordic");
	public static final TagKey<Item> DUNMER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/dunmer");

	public static final ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
			.tagged(NORDIC_TAG)
	.build();

	public static final ItemGroupCategory DUNMER = ItemGroupCategory
			.builder("dunmer")
			.tagged(DUNMER_TAG)
	.build();

	static void bootstrap()
	{
		NORDIC.addTranslationGenerator(REGISTRATE, "Nordic");
		DUNMER.addTranslationGenerator(REGISTRATE, "Dunmer");

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> ItemGroupCategoryManager
				.getInstance(ModRegistry.CREATIVE_MODE_TAB)
				.addCategories(NORDIC, DUNMER)
		);
	}
}
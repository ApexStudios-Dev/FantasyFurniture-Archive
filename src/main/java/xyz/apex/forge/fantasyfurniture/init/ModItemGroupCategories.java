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

// do not use method reference, causes NPE
@SuppressWarnings("Convert2MethodRef")
public final class ModItemGroupCategories
{
	public static final TagKey<Item> NORDIC_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/nordic");
	public static final TagKey<Item> DUNMER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/dunmer");
	public static final TagKey<Item> VENTHYR_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/venthyr");

	public static final ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
			.tagged(NORDIC_TAG)
			.defaultIcon(() -> ModItems.NORDIC_BED_SINGLE.asStack())
	.build();

	public static final ItemGroupCategory DUNMER = ItemGroupCategory
			.builder("dunmer")
			.tagged(DUNMER_TAG)
			.defaultIcon(() -> ModItems.DUNMER_BED_SINGLE.asStack())
	.build();

	public static final ItemGroupCategory VENTHYR = ItemGroupCategory
			.builder("venthyr")
			.tagged(VENTHYR_TAG)
			.defaultIcon(() -> ModItems.VENTHYR_BED_SINGLE.asStack())
	.build();

	static void bootstrap()
	{
		NORDIC.addTranslationGenerator(REGISTRATE, "Nordic");
		DUNMER.addTranslationGenerator(REGISTRATE, "Dunmer");
		VENTHYR.addTranslationGenerator(REGISTRATE, "Venthyr");

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> ItemGroupCategoryManager
				.getInstance(ModRegistry.CREATIVE_MODE_TAB)
				.addCategories(NORDIC, DUNMER, VENTHYR)
		);
	}
}
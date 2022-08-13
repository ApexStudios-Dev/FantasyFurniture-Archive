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
import static com.tterrag.registrate.providers.ProviderType.ITEM_TAGS;

// do not use method reference, causes NPE
@SuppressWarnings("Convert2MethodRef")
public final class ModItemGroupCategories
{
	public static final TagKey<Item> NORDIC_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/nordic");
	public static final TagKey<Item> DUNMER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/dunmer");
	public static final TagKey<Item> VENTHYR_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/venthyr");
	public static final TagKey<Item> BONE_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone");
	public static final TagKey<Item> BONE_SKELETON_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone/skeleton");
	public static final TagKey<Item> BONE_WITHER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone/wither");
	public static final TagKey<Item> DECORATIONS_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/decorations");
	public static final TagKey<Item> ROYAL_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/royal");

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

	public static final ItemGroupCategory BONE = ItemGroupCategory
			.builder("bone")
			.tagged(BONE_TAG)
			.defaultIcon(() -> ModItems.BONE_SKELETON_BED_SINGLE.asStack())
	.build();

	/*public static final ItemGroupCategory BONE_SKELETON = ItemGroupCategory
			.builder("bone/skeleton")
			.tagged(BONE_SKELETON_TAG)
			.defaultIcon(() -> ModItems.BONE_SKELETON_BED_SINGLE.asStack())
	.build();*/

	/*public static final ItemGroupCategory BONE_WITHER = ItemGroupCategory
			.builder("bone/wither")
			.tagged(BONE_WITHER_TAG)
			.defaultIcon(() -> ModItems.BONE_WITHER_BED_SINGLE.asStack())
	.build();*/

	public static final ItemGroupCategory DECORATIONS = ItemGroupCategory
			.builder("decorations")
			.tagged(DECORATIONS_TAG)
			.defaultIcon(() -> ModItems.NORDIC_SWEETROLLS.asStack())
	.build();

	public static final ItemGroupCategory ROYAL = ItemGroupCategory
			.builder("royal")
			.tagged(ROYAL_TAG)
			.defaultIcon(() -> ModItems.ROYAL_BED_SINGLE.asStack())
	.build();

	static void bootstrap()
	{
		NORDIC.addTranslationGenerator(REGISTRATE, "Nordic");
		DUNMER.addTranslationGenerator(REGISTRATE, "Dunmer");
		VENTHYR.addTranslationGenerator(REGISTRATE, "Venthyr");
		BONE.addTranslationGenerator(REGISTRATE, "Bone");
		// BONE_SKELETON.addTranslationGenerator(REGISTRATE, "Bone - Skeleton");
		// BONE_WITHER.addTranslationGenerator(REGISTRATE, "Bone - Wither");
		DECORATIONS.addTranslationGenerator(REGISTRATE, "Decorations");
		ROYAL.addTranslationGenerator(REGISTRATE, "Royal");

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> ItemGroupCategoryManager
				.getInstance(ModRegistry.CREATIVE_MODE_TAB.get())
				.addCategories(NORDIC, DUNMER, VENTHYR, BONE, /*BONE_SKELETON,*/ /*BONE_WITHER,*/ DECORATIONS, ROYAL)
		);

		REGISTRATE.addDataGenerator(ITEM_TAGS, provider -> provider.tag(BONE_TAG).addTags(BONE_SKELETON_TAG, BONE_WITHER_TAG));
	}
}
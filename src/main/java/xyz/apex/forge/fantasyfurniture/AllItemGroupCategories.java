package xyz.apex.forge.fantasyfurniture;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;

@SuppressWarnings("Convert2MethodRef")
public interface AllItemGroupCategories
{
	TagKey<Item> NORDIC_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/nordic");
	TagKey<Item> DUNMER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/dunmer");
	TagKey<Item> VENTHYR_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/venthyr");
	TagKey<Item> BONE_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone");
	TagKey<Item> BONE_SKELETON_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone/skeleton");
	TagKey<Item> BONE_WITHER_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/bone/wither");
	TagKey<Item> DECORATIONS_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/decorations");
	TagKey<Item> ROYAL_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/royal");
	TagKey<Item> NECROLORD_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_group_category/necrolord");

	ItemGroupCategory NORDIC = ItemGroupCategory
			.builder("nordic")
			.tagged(NORDIC_TAG)
			.defaultIcon(() -> AllItems.NORDIC_BED_SINGLE.asStack())
	.build();

	ItemGroupCategory DUNMER = ItemGroupCategory
			.builder("dunmer")
			.tagged(DUNMER_TAG)
			.defaultIcon(() -> AllItems.DUNMER_BED_SINGLE.asStack())
	.build();

	ItemGroupCategory VENTHYR = ItemGroupCategory
			.builder("venthyr")
			.tagged(VENTHYR_TAG)
			.defaultIcon(() -> AllItems.VENTHYR_BED_SINGLE.asStack())
	.build();

	ItemGroupCategory BONE = ItemGroupCategory
			.builder("bone")
			.tagged(BONE_TAG)
			.defaultIcon(() -> AllItems.BONE_SKELETON_BED_SINGLE.asStack())
	.build();

	/*ItemGroupCategory BONE_SKELETON = ItemGroupCategory
			.builder("bone/skeleton")
			.tagged(BONE_SKELETON_TAG)
			.defaultIcon(() -> AllItems.BONE_SKELETON_BED_SINGLE.asStack())
	.build();*/

	/*ItemGroupCategory BONE_WITHER = ItemGroupCategory
			.builder("bone/wither")
			.tagged(BONE_WITHER_TAG)
			.defaultIcon(() -> AllItems.BONE_WITHER_BED_SINGLE.asStack())
	.build();*/

	ItemGroupCategory DECORATIONS = ItemGroupCategory
			.builder("decorations")
			.tagged(DECORATIONS_TAG)
			.defaultIcon(() -> AllItems.NORDIC_SWEETROLLS.asStack())
	.build();

	ItemGroupCategory ROYAL = ItemGroupCategory
			.builder("royal")
			.tagged(ROYAL_TAG)
			.defaultIcon(() -> AllItems.ROYAL_BED_SINGLE.asStack())
	.build();

	ItemGroupCategory NECROLORD = ItemGroupCategory
			.builder("necrolord")
			.tagged(NECROLORD_TAG)
			.defaultIcon(() -> AllItems.NECROLORD_BED_SINGLE.asStack())
	.build();

	static void bootstrap()
	{
	}
}

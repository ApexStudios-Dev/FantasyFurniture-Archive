package xyz.apex.forge.fantasyfurniture;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;

import xyz.apex.forge.apexcore.lib.item.WearableBlockItem;
import xyz.apex.forge.apexcore.registrate.entry.ItemEntry;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.common.item.SkullBlossomsBlockItem;
import xyz.apex.forge.fantasyfurniture.common.item.WidowBloomBlockItem;
import xyz.apex.forge.fantasyfurniture.core.registrate.ItemBuilders;
import xyz.apex.forge.fantasyfurniture.core.registrate.ItemTransformers;

public interface AllItems
{
	// region: Decorations
	ItemEntry<BlockItem> BERRY_BASKET_EMPTY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BERRY_BASKET_EMPTY).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BERRY_BASKET_SWEETBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BERRY_BASKET_SWEETBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BERRY_BASKET_BLUEBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BERRY_BASKET_BLUEBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BERRY_BASKET_STRAWBERRYBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BERRY_BASKET_STRAWBERRYBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BOLTS_OF_CLOTH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BOLTS_OF_CLOTH).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BOOK_STACK = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BOOK_STACK).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, BookStackBlock.BOOKS)).register();
	ItemEntry<BlockItem> BOWL_EMPTY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BOWL_EMPTY).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BOWL_BERRTROOT_SOUP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BOWL_BERRTROOT_SOUP).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BOWL_MUSHROOM_STEW = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BOWL_MUSHROOM_STEW).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> TANKARD_EMPTY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.TANKARD_EMPTY).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	ItemEntry<BlockItem> TANKARD_HONEYMEAD = ItemBuilders.blockItem(BlockItem::new, AllBlocks.TANKARD_HONEYMEAD).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	ItemEntry<BlockItem> TANKARD_MILK = ItemBuilders.blockItem(BlockItem::new, AllBlocks.TANKARD_MILK).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	ItemEntry<BlockItem> TANKARD_SWEETBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.TANKARD_SWEETBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	ItemEntry<BlockItem> MUSHROOMS_RED = ItemBuilders.blockItem(BlockItem::new, AllBlocks.MUSHROOMS_RED).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MushroomsBlock.MUSHROOMS)).register();
	ItemEntry<BlockItem> COIN_STOCK_GOLD = ItemBuilders.blockItem(BlockItem::new, AllBlocks.COIN_STOCK_GOLD).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> COIN_STOCK_IRON = ItemBuilders.blockItem(BlockItem::new, AllBlocks.COIN_STOCK_IRON).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> MUFFINS_BLUEBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.MUFFINS_BLUEBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	ItemEntry<BlockItem> MUFFINS_CHOCOLATE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.MUFFINS_CHOCOLATE).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	ItemEntry<BlockItem> MUFFINS_SWEETBERRY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.MUFFINS_SWEETBERRY).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	ItemEntry<BlockItem> PAPER_STACK = ItemBuilders.blockItem(BlockItem::new, AllBlocks.PAPER_STACK).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> COOKIE_JAR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.COOKIE_JAR).transform(ItemTransformers::cookieJarModel).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> BREWING_CAULDRON = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BREWING_CAULDRON).tag(AllItemGroupCategories.DECORATIONS_TAG).transform(ItemTransformers::applyDyeable).register();
	ItemEntry<BlockItem> FLOATING_TOMES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.FLOATING_TOMES).tag(AllItemGroupCategories.DECORATIONS_TAG).transform(ItemTransformers::applyDyeable).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, FloatingTomesBlock.TOMES)).register();
	ItemEntry<BlockItem> GRAVESTONE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.GRAVESTONE).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> HANGING_HERBS = ItemBuilders.blockItem(BlockItem::new, AllBlocks.HANGING_HERBS).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> SPIDER_WEB_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.SPIDER_WEB_SMALL).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> SPIDER_WEB_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.SPIDER_WEB_WIDE).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> STACKABLE_PUMPKINS = ItemBuilders.blockItem(BlockItem::new, AllBlocks.STACKABLE_PUMPKINS).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, StackablePumpkinsBlock.PUMPKINS)).register();
	ItemEntry<BlockItem> BRONZE_CHAIN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BRONZE_CHAIN).tag(AllItemGroupCategories.DECORATIONS_TAG).register();
	ItemEntry<BlockItem> MUSHROOMS_BROWN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.MUSHROOMS_BROWN).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MushroomsBlock.MUSHROOMS)).register();
	ItemEntry<BlockItem> POTION_BOTTLES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.POTION_BOTTLES).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, PotionBottlesBlock.POTIONS)).register();
	ItemEntry<BlockItem> DYEABLE_POTION_BOTTLES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DYEABLE_POTION_BOTTLES).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, PotionBottlesBlock.POTIONS)).register();

	// region: Nordic
	ItemEntry<BlockItem> NORDIC_BOILED_CREME_TREATS = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_BOILED_CREME_TREATS).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, BoiledCremeTreatsBlock.TREATS)).register();
	ItemEntry<BlockItem> NORDIC_SWEETROLLS = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_SWEETROLLS).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, SweetRollsBlock.ROLLS)).register();
	ItemEntry<BlockItem> NORDIC_MEAD_BOTTLES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_MEAD_BOTTLES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, MeadBottlesBlock.BOTTLES)).register();
	ItemEntry<BlockItem> NORDIC_SOUL_GEMS_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_SOUL_GEMS_LIGHT).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_SOUL_GEMS_DARK = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_SOUL_GEMS_DARK).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NORDIC_TAG).register();
	// endregion

	// region: Venthyr
	ItemEntry<BlockItem> VENTHYR_FOOD_0 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_FOOD_0).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_FOOD_1 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_FOOD_1).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TEA_SET = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TEA_SET).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TEA_CUPS = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TEA_CUPS).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TeaCupsBlock.TEA_CUPS)).register();
	ItemEntry<BlockItem> VENTHYR_PLATTER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_PLATTER).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, PlatterBlock.PLATTER)).register();
	ItemEntry<WidowBloomBlockItem> VENTHYR_WIDOW_BLOOM = ItemBuilders.widowBloomItem();
	ItemEntry<BlockItem> VENTHYR_TOMES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TOMES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, TomesBlock.TOMES)).register();
	ItemEntry<BlockItem> VENTHYR_CHALICES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CHALICES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	ItemEntry<BlockItem> VENTHYR_CANDLES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CANDLES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG, ItemTags.Vanilla.CANDLES).register();
	ItemEntry<BlockItem> VENTHYR_BANNER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_BANNER).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.VENTHYR_TAG).register();
	// endregion

	// region: Dunmer
	ItemEntry<BlockItem> DUNMER_POTTERY_0 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_POTTERY_0).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_POTTERY_1 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_POTTERY_1).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.DUNMER_TAG).register();
	// endregion

	// region: Bone
	ItemEntry<BlockItem> BONE_CANDLES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_CANDLES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.BONE_SKELETON_TAG, ItemTags.Vanilla.CANDLES).register();

	// region: Skeleton
	ItemEntry<BlockItem> BONE_SKELETON_CHALICES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_CHALICES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.BONE_SKELETON_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	ItemEntry<BlockItem> BONE_SKELETON_PILE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_PILE).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<SkullBlossomsBlockItem> BONE_SKELETON_SKULL_BLOSSOMS = ItemBuilders.skullBlossoms(AllBlocks.BONE_SKELETON_SKULL_BLOSSOMS).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	ItemEntry<BlockItem> BONE_WITHER_CHALICES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_CHALICES).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.BONE_WITHER_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	ItemEntry<BlockItem> BONE_WITHER_PILE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_PILE).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<SkullBlossomsBlockItem> BONE_WITHER_SKULL_BLOSSOMS = ItemBuilders.skullBlossoms(AllBlocks.BONE_WITHER_SKULL_BLOSSOMS).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion
	// endregion

	// region: Royal
	ItemEntry<WearableBlockItem> ROYAL_CROWN = ItemBuilders.blockItem((block, properties) -> new WearableBlockItem(block, properties, EquipmentSlot.HEAD), AllBlocks.ROYAL_CROWN).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CANDELABRA = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CANDELABRA).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CHALICES = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CHALICES).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	ItemEntry<BlockItem> ROYAL_CUSHIONED_CROWN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CUSHIONED_CROWN).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_FOOD_0 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_FOOD_0).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_FOOD_1 = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_FOOD_1).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_PLATTER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_PLATTER).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).model((ctx, provider) -> ItemTransformers.stackedBlockItemModel(ctx, provider, PlatterBlock.PLATTER)).register();
	ItemEntry<BlockItem> ROYAL_FLOOR_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_FLOOR_CUSHION).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_WALL_MIRROR_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_WALL_MIRROR_LARGE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_WALL_MIRROR_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_WALL_MIRROR_SMALL).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.ROYAL_TAG).register();
	// endregion

	// region: Necrolord
	ItemEntry<BlockItem> NECROLORD_CANDELABRA = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_CANDELABRA).tag(AllItemGroupCategories.DECORATIONS_TAG, AllItemGroupCategories.NECROLORD_TAG).register();
	//endregion
	// endregion

	// region: Furniture Sets
	// region: Nordic
	ItemEntry<BlockItem> NORDIC_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.NORDIC_WOOL).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.NORDIC_CARPET).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_WALL_LIGHT).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_FLOOR_LIGHT).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_TABLE_SMALL).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_TABLE_WIDE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_TABLE_LARGE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_STOOL).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_CUSHION).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_PAINTING_SMALL).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_PAINTING_WIDE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_DRAWER).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.NORDIC_SHELF).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.NORDIC_SOFA).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_DESK_LEFT).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_DESK_RIGHT).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_CHAIR).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_BENCH).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_BOOKSHELF).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_CHEST).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_DRESSER).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_WARDROBE_BOTTOM).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_WARDROBE_TOP).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.NORDIC_BED_SINGLE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.NORDIC_BED_DOUBLE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_CHANDELIER).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.NORDIC_DOOR_SINGLE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.NORDIC_DOOR_DOUBLE).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_LOCKBOX).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.NORDIC_COUNTER).tag(AllItemGroupCategories.NORDIC_TAG).register();
	ItemEntry<BlockItem> NORDIC_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NORDIC_OVEN).tag(AllItemGroupCategories.NORDIC_TAG).register();
	// endregion

	// region: Dunmer
	ItemEntry<BlockItem> DUNMER_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.DUNMER_WOOL).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.DUNMER_CARPET).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_WALL_LIGHT).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_FLOOR_LIGHT).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_TABLE_SMALL).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_TABLE_WIDE).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_TABLE_LARGE).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_STOOL).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_CUSHION).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_PAINTING_SMALL).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_PAINTING_WIDE).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_DRAWER).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.DUNMER_SHELF).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.DUNMER_SOFA).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_DESK_LEFT).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_DESK_RIGHT).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_CHAIR).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_BENCH).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_BOOKSHELF).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_CHEST).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_DRESSER).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_WARDROBE_BOTTOM).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_WARDROBE_TOP).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.DUNMER_BED_SINGLE).transform(ItemTransformers::generatedModel).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.DUNMER_BED_DOUBLE).transform(ItemTransformers::generatedModel).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_CHANDELIER).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.DUNMER_DOOR_SINGLE).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.DUNMER_DOOR_DOUBLE).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_LOCKBOX).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.DUNMER_COUNTER).tag(AllItemGroupCategories.DUNMER_TAG).register();
	ItemEntry<BlockItem> DUNMER_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.DUNMER_OVEN).tag(AllItemGroupCategories.DUNMER_TAG).register();
	// endregion

	// region: Venthyr
	ItemEntry<BlockItem> VENTHYR_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.VENTHYR_WOOL).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.VENTHYR_CARPET).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_WALL_LIGHT).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_FLOOR_LIGHT).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_SMALL).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_SMALL_FANCY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_SMALL_FANCY).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_WIDE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_WIDE_FANCY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_WIDE_FANCY).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_LARGE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_TABLE_LARGE_FANCY = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_TABLE_LARGE_FANCY).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_STOOL).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CUSHION).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_PAINTING_SMALL).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_PAINTING_WIDE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_DRAWER).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.VENTHYR_SHELF).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.VENTHYR_SOFA).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_DESK_LEFT).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_DESK_RIGHT).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CHAIR).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_BENCH).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_BOOKSHELF).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CHEST).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_DRESSER).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_WARDROBE_BOTTOM).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_WARDROBE_TOP).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.VENTHYR_BED_SINGLE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.VENTHYR_BED_DOUBLE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_CHANDELIER).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.VENTHYR_DOOR_SINGLE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.VENTHYR_DOOR_DOUBLE).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_LOCKBOX).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.VENTHYR_COUNTER).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	ItemEntry<BlockItem> VENTHYR_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.VENTHYR_OVEN).tag(AllItemGroupCategories.VENTHYR_TAG).register();
	// endregion

	// region: Bone
	// region: Skeleton
	ItemEntry<BlockItem> BONE_SKELETON_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.BONE_SKELETON_WOOL).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.BONE_SKELETON_CARPET).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_WALL_LIGHT).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_FLOOR_LIGHT).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_TABLE_SMALL).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_TABLE_WIDE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_TABLE_LARGE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_STOOL).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_SKULL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_SKULL).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_PAINTING_SMALL).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_PAINTING_WIDE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_DRAWER).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.BONE_SKELETON_SHELF).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.BONE_SKELETON_SOFA).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_DESK_LEFT).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_DESK_RIGHT).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_CHAIR).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_BENCH).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_BOOKSHELF).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_CHEST).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_DRESSER).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_WARDROBE_BOTTOM).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_WARDROBE_TOP).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.BONE_SKELETON_BED_SINGLE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.BONE_SKELETON_BED_DOUBLE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_CHANDELIER).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.BONE_SKELETON_DOOR_SINGLE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.BONE_SKELETON_DOOR_DOUBLE).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_LOCKBOX).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.BONE_SKELETON_COUNTER).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	ItemEntry<BlockItem> BONE_SKELETON_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_SKELETON_OVEN).tag(AllItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	ItemEntry<BlockItem> BONE_WITHER_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.BONE_WITHER_WOOL).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.BONE_WITHER_CARPET).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_WALL_LIGHT).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_FLOOR_LIGHT).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_TABLE_SMALL).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_TABLE_WIDE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_TABLE_LARGE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_STOOL).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_SKULL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_SKULL).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_PAINTING_SMALL).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_PAINTING_WIDE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_DRAWER).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.BONE_WITHER_SHELF).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.BONE_WITHER_SOFA).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_DESK_LEFT).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_DESK_RIGHT).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_CHAIR).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_BENCH).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_BOOKSHELF).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_CHEST).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_DRESSER).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_WARDROBE_BOTTOM).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_WARDROBE_TOP).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.BONE_WITHER_BED_SINGLE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.BONE_WITHER_BED_DOUBLE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_CHANDELIER).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.BONE_WITHER_DOOR_SINGLE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.BONE_WITHER_DOOR_DOUBLE).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_LOCKBOX).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.BONE_WITHER_COUNTER).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	ItemEntry<BlockItem> BONE_WITHER_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.BONE_WITHER_OVEN).tag(AllItemGroupCategories.BONE_WITHER_TAG).register();
	// endregion
	// endregion

	// region: Royal
	ItemEntry<BlockItem> ROYAL_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.ROYAL_WOOL).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.ROYAL_CARPET).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_WALL_LIGHT).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_FLOOR_LIGHT).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_TABLE_SMALL).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_TABLE_WIDE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_TABLE_LARGE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_STOOL).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CUSHION).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_PAINTING_SMALL).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_PAINTING_WIDE).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_DRAWER).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.ROYAL_SHELF).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.ROYAL_SOFA).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_DESK_LEFT).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_DESK_RIGHT).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CHAIR).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_BENCH).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_BOOKSHELF).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CHEST).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_DRESSER).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_WARDROBE_BOTTOM).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_WARDROBE_TOP).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.ROYAL_BED_SINGLE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.ROYAL_BED_DOUBLE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_CHANDELIER).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.ROYAL_DOOR_SINGLE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.ROYAL_DOOR_DOUBLE).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_LOCKBOX).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.ROYAL_COUNTER).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	ItemEntry<BlockItem> ROYAL_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.ROYAL_OVEN).transform(ItemTransformers::applyDyeable).tag(AllItemGroupCategories.ROYAL_TAG).register();
	// endregion

	// region: Necrolord
	ItemEntry<BlockItem> NECROLORD_WOOL = ItemBuilders.wool(BlockItem::new, AllBlocks.NECROLORD_WOOL).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_CARPET = ItemBuilders.carpet(BlockItem::new, AllBlocks.NECROLORD_CARPET).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_WALL_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_WALL_LIGHT).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_FLOOR_LIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_FLOOR_LIGHT).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_TABLE_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_TABLE_SMALL).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_TABLE_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_TABLE_WIDE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_TABLE_LARGE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_TABLE_LARGE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_STOOL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_STOOL).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_CUSHION = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_CUSHION).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_PAINTING_SMALL = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_PAINTING_SMALL).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_PAINTING_WIDE = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_PAINTING_WIDE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DRAWER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_DRAWER).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_SHELF = ItemBuilders.shelf(BlockItem::new, AllBlocks.NECROLORD_SHELF).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_SOFA = ItemBuilders.sofa(BlockItem::new, AllBlocks.NECROLORD_SOFA).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DESK_LEFT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_DESK_LEFT).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DESK_RIGHT = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_DESK_RIGHT).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_CHAIR = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_CHAIR).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_BENCH = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_BENCH).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_BOOKSHELF = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_BOOKSHELF).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_CHEST = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_CHEST).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DRESSER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_DRESSER).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_WARDROBE_BOTTOM = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_WARDROBE_BOTTOM).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_WARDROBE_TOP = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_WARDROBE_TOP).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_BED_SINGLE = ItemBuilders.bed(BlockItem::new, AllBlocks.NECROLORD_BED_SINGLE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_BED_DOUBLE = ItemBuilders.bed(BlockItem::new, AllBlocks.NECROLORD_BED_DOUBLE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_CHANDELIER = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_CHANDELIER).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DOOR_SINGLE = ItemBuilders.door(BlockItem::new, AllBlocks.NECROLORD_DOOR_SINGLE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_DOOR_DOUBLE = ItemBuilders.door(BlockItem::new, AllBlocks.NECROLORD_DOOR_DOUBLE).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_LOCKBOX = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_LOCKBOX).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_COUNTER = ItemBuilders.counter(BlockItem::new, AllBlocks.NECROLORD_COUNTER).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	ItemEntry<BlockItem> NECROLORD_OVEN = ItemBuilders.blockItem(BlockItem::new, AllBlocks.NECROLORD_OVEN).tag(AllItemGroupCategories.NECROLORD_TAG).register();
	// endregion
	// endregion

	ItemEntry<BlockItem> FURNITURE_STATION = ItemEntry.cast(AllBlocks.FURNITURE_STATION.getSibling(Registry.ITEM_REGISTRY));

	static void bootstrap()
	{
	}
}

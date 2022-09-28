package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;

import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import xyz.apex.forge.apexcore.lib.item.WearableBlockItem;
import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.ItemBuilder;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntry;
import xyz.apex.forge.apexcore.registrate.entry.ItemEntry;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.block.furniture.*;
import xyz.apex.forge.fantasyfurniture.item.SkullBlossomsBlockItem;
import xyz.apex.forge.fantasyfurniture.item.WidowBloomBlockItem;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class ModItems
{
	// region: Decorations
	public static final ItemEntry<BlockItem> BERRY_BASKET_EMPTY = blockItem(BlockItem::new, ModBlocks.BERRY_BASKET_EMPTY).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BERRY_BASKET_SWEETBERRY = blockItem(BlockItem::new, ModBlocks.BERRY_BASKET_SWEETBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BERRY_BASKET_BLUEBERRY = blockItem(BlockItem::new, ModBlocks.BERRY_BASKET_BLUEBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BERRY_BASKET_STRAWBERRYBERRY = blockItem(BlockItem::new, ModBlocks.BERRY_BASKET_STRAWBERRYBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BOLTS_OF_CLOTH = blockItem(BlockItem::new, ModBlocks.BOLTS_OF_CLOTH).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BOOK_STACK = blockItem(BlockItem::new, ModBlocks.BOOK_STACK).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, BookStackBlock.BOOKS)).register();
	public static final ItemEntry<BlockItem> BOWL_EMPTY = blockItem(BlockItem::new, ModBlocks.BOWL_EMPTY).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BOWL_BERRTROOT_SOUP = blockItem(BlockItem::new, ModBlocks.BOWL_BERRTROOT_SOUP).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BOWL_MUSHROOM_STEW = blockItem(BlockItem::new, ModBlocks.BOWL_MUSHROOM_STEW).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> TANKARD_EMPTY = blockItem(BlockItem::new, ModBlocks.TANKARD_EMPTY).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	public static final ItemEntry<BlockItem> TANKARD_HONEYMEAD = blockItem(BlockItem::new, ModBlocks.TANKARD_HONEYMEAD).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	public static final ItemEntry<BlockItem> TANKARD_MILK = blockItem(BlockItem::new, ModBlocks.TANKARD_MILK).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	public static final ItemEntry<BlockItem> TANKARD_SWEETBERRY = blockItem(BlockItem::new, ModBlocks.TANKARD_SWEETBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TankardsBlock.TANKARDS)).register();
	public static final ItemEntry<BlockItem> MUSHROOMS_RED = blockItem(BlockItem::new, ModBlocks.MUSHROOMS_RED).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, MushroomsRedBlock.MUSHROOMS)).register();
	public static final ItemEntry<BlockItem> COIN_STOCK_GOLD = blockItem(BlockItem::new, ModBlocks.COIN_STOCK_GOLD).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> COIN_STOCK_IRON = blockItem(BlockItem::new, ModBlocks.COIN_STOCK_IRON).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> MUFFINS_BLUEBERRY = blockItem(BlockItem::new, ModBlocks.MUFFINS_BLUEBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	public static final ItemEntry<BlockItem> MUFFINS_CHOCOLATE = blockItem(BlockItem::new, ModBlocks.MUFFINS_CHOCOLATE).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	public static final ItemEntry<BlockItem> MUFFINS_SWEETBERRY = blockItem(BlockItem::new, ModBlocks.MUFFINS_SWEETBERRY).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, MuffinsBlock.MUFFINS)).register();
	public static final ItemEntry<BlockItem> PAPER_STACK = blockItem(BlockItem::new, ModBlocks.PAPER_STACK).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> COOKIE_JAR = blockItem(BlockItem::new, ModBlocks.COOKIE_JAR).transform(ModItems::cookieJarModel).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> BREWING_CAULDRON = blockItem(BlockItem::new, ModBlocks.BREWING_CAULDRON).tag(ModItemGroupCategories.DECORATIONS_TAG).transform(ModItems::applyDyeable).register();
	public static final ItemEntry<BlockItem> FLOATING_TOMES = blockItem(BlockItem::new, ModBlocks.FLOATING_TOMES).tag(ModItemGroupCategories.DECORATIONS_TAG).transform(ModItems::applyDyeable).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, FloatingTomesBlock.TOMES)).register();
	public static final ItemEntry<BlockItem> GRAVESTONE = blockItem(BlockItem::new, ModBlocks.GRAVESTONE).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> HANGING_HERBS = blockItem(BlockItem::new, ModBlocks.HANGING_HERBS).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> SPIDER_WEB_SMALL = blockItem(BlockItem::new, ModBlocks.SPIDER_WEB_SMALL).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> SPIDER_WEB_WIDE = blockItem(BlockItem::new, ModBlocks.SPIDER_WEB_WIDE).tag(ModItemGroupCategories.DECORATIONS_TAG).register();
	public static final ItemEntry<BlockItem> STACKABLE_PUMPKINS = blockItem(BlockItem::new, ModBlocks.STACKABLE_PUMPKINS).tag(ModItemGroupCategories.DECORATIONS_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, StackablePumpkinsBlock.PUMPKINS)).register(); // TODO
	public static final ItemEntry<BlockItem> BRONZE_CHAIN = blockItem(BlockItem::new, ModBlocks.BRONZE_CHAIN).tag(ModItemGroupCategories.DECORATIONS_TAG).register();

	// region: Nordic
	public static final ItemEntry<BlockItem> NORDIC_BOILED_CREME_TREATS = blockItem(BlockItem::new, ModBlocks.NORDIC_BOILED_CREME_TREATS).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, BoiledCremeTreatsBlock.TREATS)).register();
	public static final ItemEntry<BlockItem> NORDIC_SWEETROLLS = blockItem(BlockItem::new, ModBlocks.NORDIC_SWEETROLLS).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, SweetRollsBlock.ROLLS)).register();
	public static final ItemEntry<BlockItem> NORDIC_MEAD_BOTTLES = blockItem(BlockItem::new, ModBlocks.NORDIC_MEAD_BOTTLES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NORDIC_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, MeadBottlesBlock.BOTTLES)).register();
	public static final ItemEntry<BlockItem> NORDIC_SOUL_GEMS_LIGHT = blockItem(BlockItem::new, ModBlocks.NORDIC_SOUL_GEMS_LIGHT).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_SOUL_GEMS_DARK = blockItem(BlockItem::new, ModBlocks.NORDIC_SOUL_GEMS_DARK).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NORDIC_TAG).register();
	// endregion

	// region: Venthyr
	public static final ItemEntry<BlockItem> VENTHYR_FOOD_0 = blockItem(BlockItem::new, ModBlocks.VENTHYR_FOOD_0).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_FOOD_1 = blockItem(BlockItem::new, ModBlocks.VENTHYR_FOOD_1).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TEA_SET = blockItem(BlockItem::new, ModBlocks.VENTHYR_TEA_SET).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TEA_CUPS = blockItem(BlockItem::new, ModBlocks.VENTHYR_TEA_CUPS).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TeaCupsBlock.TEA_CUPS)).register();
	public static final ItemEntry<BlockItem> VENTHYR_PLATTER = blockItem(BlockItem::new, ModBlocks.VENTHYR_PLATTER).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, PlatterBlock.PLATTER)).register();
	public static final ItemEntry<WidowBloomBlockItem> VENTHYR_WIDOW_BLOOM = widowBloomItem();
	public static final ItemEntry<BlockItem> VENTHYR_TOMES = blockItem(BlockItem::new, ModBlocks.VENTHYR_TOMES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, TomesBlock.TOMES)).register();
	public static final ItemEntry<BlockItem> VENTHYR_CHALICES = blockItem(BlockItem::new, ModBlocks.VENTHYR_CHALICES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> VENTHYR_CANDLES = blockItem(BlockItem::new, ModBlocks.VENTHYR_CANDLES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG, ItemTags.Vanilla.CANDLES).register();
	public static final ItemEntry<BlockItem> VENTHYR_BANNER = blockItem(BlockItem::new, ModBlocks.VENTHYR_BANNER).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG).register();
	// endregion

	// region: Dunmer
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_0 = blockItem(BlockItem::new, ModBlocks.DUNMER_POTTERY_0).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_1 = blockItem(BlockItem::new, ModBlocks.DUNMER_POTTERY_1).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.DUNMER_TAG).register();
	// endregion

	// region: Bone
	public static final ItemEntry<BlockItem> BONE_CANDLES = blockItem(BlockItem::new, ModBlocks.BONE_CANDLES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_SKELETON_TAG, ItemTags.Vanilla.CANDLES).register();

	// region: Skeleton
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHALICES = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHALICES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PILE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PILE).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<SkullBlossomsBlockItem> BONE_SKELETON_SKULL_BLOSSOMS = skullBlossoms(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	public static final ItemEntry<BlockItem> BONE_WITHER_CHALICES = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHALICES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_WITHER_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PILE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PILE).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<SkullBlossomsBlockItem> BONE_WITHER_SKULL_BLOSSOMS = skullBlossoms(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion
	// endregion

	// region: Royal
	public static final ItemEntry<WearableBlockItem> ROYAL_CROWN = blockItem((block, properties) -> new WearableBlockItem(block, properties, EquipmentSlot.HEAD), ModBlocks.ROYAL_CROWN).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CANDELABRA = blockItem(BlockItem::new, ModBlocks.ROYAL_CANDELABRA).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CHALICES = blockItem(BlockItem::new, ModBlocks.ROYAL_CHALICES).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> ROYAL_CUSHIONED_CROWN = blockItem(BlockItem::new, ModBlocks.ROYAL_CUSHIONED_CROWN).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_FOOD_0 = blockItem(BlockItem::new, ModBlocks.ROYAL_FOOD_0).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_FOOD_1 = blockItem(BlockItem::new, ModBlocks.ROYAL_FOOD_1).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_PLATTER = blockItem(BlockItem::new, ModBlocks.ROYAL_PLATTER).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, PlatterBlock.PLATTER)).register();
	public static final ItemEntry<BlockItem> ROYAL_FLOOR_CUSHION = blockItem(BlockItem::new, ModBlocks.ROYAL_FLOOR_CUSHION).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_WALL_MIRROR_LARGE = blockItem(BlockItem::new, ModBlocks.ROYAL_WALL_MIRROR_LARGE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_WALL_MIRROR_SMALL = blockItem(BlockItem::new, ModBlocks.ROYAL_WALL_MIRROR_SMALL).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.ROYAL_TAG).register();
	// endregion

	// region: Necrolord
	public static final ItemEntry<BlockItem> NECROLORD_CANDELABRA = blockItem(BlockItem::new, ModBlocks.NECROLORD_CANDELABRA).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.NECROLORD_TAG).register();
	//endregion
	// endregion

	// region: Furniture Sets
	// region: Nordic
	public static final ItemEntry<BlockItem> NORDIC_WOOL = wool(BlockItem::new, ModBlocks.NORDIC_WOOL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CARPET = carpet(BlockItem::new, ModBlocks.NORDIC_CARPET).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.NORDIC_WALL_LIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.NORDIC_FLOOR_LIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.NORDIC_TABLE_SMALL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.NORDIC_TABLE_WIDE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.NORDIC_TABLE_LARGE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_STOOL = blockItem(BlockItem::new, ModBlocks.NORDIC_STOOL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CUSHION = blockItem(BlockItem::new, ModBlocks.NORDIC_CUSHION).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.NORDIC_PAINTING_SMALL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.NORDIC_PAINTING_WIDE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DRAWER = blockItem(BlockItem::new, ModBlocks.NORDIC_DRAWER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_SHELF = shelf(BlockItem::new, ModBlocks.NORDIC_SHELF).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_SOFA = sofa(BlockItem::new, ModBlocks.NORDIC_SOFA).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.NORDIC_DESK_LEFT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.NORDIC_DESK_RIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHAIR = blockItem(BlockItem::new, ModBlocks.NORDIC_CHAIR).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BENCH = blockItem(BlockItem::new, ModBlocks.NORDIC_BENCH).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.NORDIC_BOOKSHELF).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHEST = blockItem(BlockItem::new, ModBlocks.NORDIC_CHEST).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DRESSER = blockItem(BlockItem::new, ModBlocks.NORDIC_DRESSER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.NORDIC_WARDROBE_BOTTOM).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.NORDIC_WARDROBE_TOP).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BED_SINGLE = bed(BlockItem::new, ModBlocks.NORDIC_BED_SINGLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BED_DOUBLE = bed(BlockItem::new, ModBlocks.NORDIC_BED_DOUBLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHANDELIER = blockItem(BlockItem::new, ModBlocks.NORDIC_CHANDELIER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DOOR_SINGLE = door(BlockItem::new, ModBlocks.NORDIC_DOOR_SINGLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.NORDIC_DOOR_DOUBLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_LOCKBOX = blockItem(BlockItem::new, ModBlocks.NORDIC_LOCKBOX).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_COUNTER = counter(BlockItem::new, ModBlocks.NORDIC_COUNTER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_OVEN = blockItem(BlockItem::new, ModBlocks.NORDIC_OVEN).tag(ModItemGroupCategories.NORDIC_TAG).register();
	// endregion

	// region: Dunmer
	public static final ItemEntry<BlockItem> DUNMER_WOOL = wool(BlockItem::new, ModBlocks.DUNMER_WOOL).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CARPET = carpet(BlockItem::new, ModBlocks.DUNMER_CARPET).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.DUNMER_WALL_LIGHT).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.DUNMER_FLOOR_LIGHT).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.DUNMER_TABLE_SMALL).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.DUNMER_TABLE_WIDE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.DUNMER_TABLE_LARGE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_STOOL = blockItem(BlockItem::new, ModBlocks.DUNMER_STOOL).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CUSHION = blockItem(BlockItem::new, ModBlocks.DUNMER_CUSHION).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.DUNMER_PAINTING_SMALL).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.DUNMER_PAINTING_WIDE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DRAWER = blockItem(BlockItem::new, ModBlocks.DUNMER_DRAWER).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_SHELF = shelf(BlockItem::new, ModBlocks.DUNMER_SHELF).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_SOFA = sofa(BlockItem::new, ModBlocks.DUNMER_SOFA).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.DUNMER_DESK_LEFT).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.DUNMER_DESK_RIGHT).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CHAIR = blockItem(BlockItem::new, ModBlocks.DUNMER_CHAIR).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_BENCH = blockItem(BlockItem::new, ModBlocks.DUNMER_BENCH).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.DUNMER_BOOKSHELF).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CHEST = blockItem(BlockItem::new, ModBlocks.DUNMER_CHEST).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DRESSER = blockItem(BlockItem::new, ModBlocks.DUNMER_DRESSER).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.DUNMER_WARDROBE_BOTTOM).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.DUNMER_WARDROBE_TOP).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_BED_SINGLE = bed(BlockItem::new, ModBlocks.DUNMER_BED_SINGLE).transform(ModItems::generatedModel).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_BED_DOUBLE = bed(BlockItem::new, ModBlocks.DUNMER_BED_DOUBLE).transform(ModItems::generatedModel).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CHANDELIER = blockItem(BlockItem::new, ModBlocks.DUNMER_CHANDELIER).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DOOR_SINGLE = door(BlockItem::new, ModBlocks.DUNMER_DOOR_SINGLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.DUNMER_DOOR_DOUBLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_LOCKBOX = blockItem(BlockItem::new, ModBlocks.DUNMER_LOCKBOX).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_COUNTER = counter(BlockItem::new, ModBlocks.DUNMER_COUNTER).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_OVEN = blockItem(BlockItem::new, ModBlocks.DUNMER_OVEN).tag(ModItemGroupCategories.DUNMER_TAG).register();
	// endregion

	// region: Venthyr
	public static final ItemEntry<BlockItem> VENTHYR_WOOL = wool(BlockItem::new, ModBlocks.VENTHYR_WOOL).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_CARPET = carpet(BlockItem::new, ModBlocks.VENTHYR_CARPET).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.VENTHYR_WALL_LIGHT).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.VENTHYR_FLOOR_LIGHT).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_SMALL).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_SMALL_FANCY = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_SMALL_FANCY).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_WIDE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_WIDE_FANCY = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_WIDE_FANCY).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_LARGE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_TABLE_LARGE_FANCY = blockItem(BlockItem::new, ModBlocks.VENTHYR_TABLE_LARGE_FANCY).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_STOOL = blockItem(BlockItem::new, ModBlocks.VENTHYR_STOOL).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_CUSHION = blockItem(BlockItem::new, ModBlocks.VENTHYR_CUSHION).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.VENTHYR_PAINTING_SMALL).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.VENTHYR_PAINTING_WIDE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DRAWER = blockItem(BlockItem::new, ModBlocks.VENTHYR_DRAWER).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_SHELF = shelf(BlockItem::new, ModBlocks.VENTHYR_SHELF).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_SOFA = sofa(BlockItem::new, ModBlocks.VENTHYR_SOFA).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.VENTHYR_DESK_LEFT).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.VENTHYR_DESK_RIGHT).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_CHAIR = blockItem(BlockItem::new, ModBlocks.VENTHYR_CHAIR).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_BENCH = blockItem(BlockItem::new, ModBlocks.VENTHYR_BENCH).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.VENTHYR_BOOKSHELF).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_CHEST = blockItem(BlockItem::new, ModBlocks.VENTHYR_CHEST).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DRESSER = blockItem(BlockItem::new, ModBlocks.VENTHYR_DRESSER).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.VENTHYR_WARDROBE_BOTTOM).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.VENTHYR_WARDROBE_TOP).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_BED_SINGLE = bed(BlockItem::new, ModBlocks.VENTHYR_BED_SINGLE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_BED_DOUBLE = bed(BlockItem::new, ModBlocks.VENTHYR_BED_DOUBLE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_CHANDELIER = blockItem(BlockItem::new, ModBlocks.VENTHYR_CHANDELIER).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DOOR_SINGLE = door(BlockItem::new, ModBlocks.VENTHYR_DOOR_SINGLE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.VENTHYR_DOOR_DOUBLE).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_LOCKBOX = blockItem(BlockItem::new, ModBlocks.VENTHYR_LOCKBOX).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_COUNTER = counter(BlockItem::new, ModBlocks.VENTHYR_COUNTER).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	public static final ItemEntry<BlockItem> VENTHYR_OVEN = blockItem(BlockItem::new, ModBlocks.VENTHYR_OVEN).tag(ModItemGroupCategories.VENTHYR_TAG).register();
	// endregion

	// region: Bone
	// region: Skeleton
	public static final ItemEntry<BlockItem> BONE_SKELETON_WOOL = wool(BlockItem::new, ModBlocks.BONE_SKELETON_WOOL).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CARPET = carpet(BlockItem::new, ModBlocks.BONE_SKELETON_CARPET).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WALL_LIGHT).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_FLOOR_LIGHT).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_SMALL).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_WIDE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_LARGE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_STOOL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_STOOL).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SKULL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_SKULL).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PAINTING_SMALL).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PAINTING_WIDE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DRAWER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DRAWER).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SHELF = shelf(BlockItem::new, ModBlocks.BONE_SKELETON_SHELF).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SOFA = sofa(BlockItem::new, ModBlocks.BONE_SKELETON_SOFA).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DESK_LEFT).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DESK_RIGHT).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHAIR = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHAIR).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BENCH = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_BENCH).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_BOOKSHELF).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHEST = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHEST).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DRESSER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DRESSER).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WARDROBE_TOP).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BED_SINGLE = bed(BlockItem::new, ModBlocks.BONE_SKELETON_BED_SINGLE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BED_DOUBLE = bed(BlockItem::new, ModBlocks.BONE_SKELETON_BED_DOUBLE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHANDELIER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHANDELIER).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DOOR_SINGLE = door(BlockItem::new, ModBlocks.BONE_SKELETON_DOOR_SINGLE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.BONE_SKELETON_DOOR_DOUBLE).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_LOCKBOX = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_LOCKBOX).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_COUNTER = counter(BlockItem::new, ModBlocks.BONE_SKELETON_COUNTER).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_OVEN = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_OVEN).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	public static final ItemEntry<BlockItem> BONE_WITHER_WOOL = wool(BlockItem::new, ModBlocks.BONE_WITHER_WOOL).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CARPET = carpet(BlockItem::new, ModBlocks.BONE_WITHER_CARPET).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WALL_LIGHT).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_FLOOR_LIGHT).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_SMALL).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_WIDE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_LARGE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_STOOL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_STOOL).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SKULL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_SKULL).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PAINTING_SMALL).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PAINTING_WIDE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DRAWER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DRAWER).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SHELF = shelf(BlockItem::new, ModBlocks.BONE_WITHER_SHELF).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SOFA = sofa(BlockItem::new, ModBlocks.BONE_WITHER_SOFA).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DESK_LEFT).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DESK_RIGHT).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHAIR = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHAIR).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BENCH = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_BENCH).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_BOOKSHELF).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHEST = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHEST).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DRESSER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DRESSER).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WARDROBE_BOTTOM).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WARDROBE_TOP).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BED_SINGLE = bed(BlockItem::new, ModBlocks.BONE_WITHER_BED_SINGLE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BED_DOUBLE = bed(BlockItem::new, ModBlocks.BONE_WITHER_BED_DOUBLE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHANDELIER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHANDELIER).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DOOR_SINGLE = door(BlockItem::new, ModBlocks.BONE_WITHER_DOOR_SINGLE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.BONE_WITHER_DOOR_DOUBLE).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_LOCKBOX = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_LOCKBOX).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_COUNTER = counter(BlockItem::new, ModBlocks.BONE_WITHER_COUNTER).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_OVEN = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_OVEN).tag(ModItemGroupCategories.BONE_WITHER_TAG).register();
	// endregion
	// endregion

	// region: Royal
	public static final ItemEntry<BlockItem> ROYAL_WOOL = wool(BlockItem::new, ModBlocks.ROYAL_WOOL).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CARPET = carpet(BlockItem::new, ModBlocks.ROYAL_CARPET).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.ROYAL_WALL_LIGHT).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.ROYAL_FLOOR_LIGHT).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.ROYAL_TABLE_SMALL).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.ROYAL_TABLE_WIDE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.ROYAL_TABLE_LARGE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_STOOL = blockItem(BlockItem::new, ModBlocks.ROYAL_STOOL).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CUSHION = blockItem(BlockItem::new, ModBlocks.ROYAL_CUSHION).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.ROYAL_PAINTING_SMALL).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.ROYAL_PAINTING_WIDE).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DRAWER = blockItem(BlockItem::new, ModBlocks.ROYAL_DRAWER).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_SHELF = shelf(BlockItem::new, ModBlocks.ROYAL_SHELF).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_SOFA = sofa(BlockItem::new, ModBlocks.ROYAL_SOFA).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.ROYAL_DESK_LEFT).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.ROYAL_DESK_RIGHT).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CHAIR = blockItem(BlockItem::new, ModBlocks.ROYAL_CHAIR).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_BENCH = blockItem(BlockItem::new, ModBlocks.ROYAL_BENCH).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.ROYAL_BOOKSHELF).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CHEST = blockItem(BlockItem::new, ModBlocks.ROYAL_CHEST).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DRESSER = blockItem(BlockItem::new, ModBlocks.ROYAL_DRESSER).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.ROYAL_WARDROBE_BOTTOM).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.ROYAL_WARDROBE_TOP).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_BED_SINGLE = bed(BlockItem::new, ModBlocks.ROYAL_BED_SINGLE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_BED_DOUBLE = bed(BlockItem::new, ModBlocks.ROYAL_BED_DOUBLE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_CHANDELIER = blockItem(BlockItem::new, ModBlocks.ROYAL_CHANDELIER).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DOOR_SINGLE = door(BlockItem::new, ModBlocks.ROYAL_DOOR_SINGLE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.ROYAL_DOOR_DOUBLE).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_LOCKBOX = blockItem(BlockItem::new, ModBlocks.ROYAL_LOCKBOX).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_COUNTER = counter(BlockItem::new, ModBlocks.ROYAL_COUNTER).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	public static final ItemEntry<BlockItem> ROYAL_OVEN = blockItem(BlockItem::new, ModBlocks.ROYAL_OVEN).transform(ModItems::applyDyeable).tag(ModItemGroupCategories.ROYAL_TAG).register();
	// endregion

	// region: Necrolord
	public static final ItemEntry<BlockItem> NECROLORD_WOOL = wool(BlockItem::new, ModBlocks.NECROLORD_WOOL).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_CARPET = carpet(BlockItem::new, ModBlocks.NECROLORD_CARPET).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.NECROLORD_WALL_LIGHT).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.NECROLORD_FLOOR_LIGHT).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.NECROLORD_TABLE_SMALL).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.NECROLORD_TABLE_WIDE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.NECROLORD_TABLE_LARGE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_STOOL = blockItem(BlockItem::new, ModBlocks.NECROLORD_STOOL).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_CUSHION = blockItem(BlockItem::new, ModBlocks.NECROLORD_CUSHION).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.NECROLORD_PAINTING_SMALL).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.NECROLORD_PAINTING_WIDE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_DRAWER = blockItem(BlockItem::new, ModBlocks.NECROLORD_DRAWER).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_SHELF = shelf(BlockItem::new, ModBlocks.NECROLORD_SHELF).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_SOFA = sofa(BlockItem::new, ModBlocks.NECROLORD_SOFA).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.NECROLORD_DESK_LEFT).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.NECROLORD_DESK_RIGHT).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_CHAIR = blockItem(BlockItem::new, ModBlocks.NECROLORD_CHAIR).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_BENCH = blockItem(BlockItem::new, ModBlocks.NECROLORD_BENCH).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.NECROLORD_BOOKSHELF).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_CHEST = blockItem(BlockItem::new, ModBlocks.NECROLORD_CHEST).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_DRESSER = blockItem(BlockItem::new, ModBlocks.NECROLORD_DRESSER).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.NECROLORD_WARDROBE_BOTTOM).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.NECROLORD_WARDROBE_TOP).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_BED_SINGLE = bed(BlockItem::new, ModBlocks.NECROLORD_BED_SINGLE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_BED_DOUBLE = bed(BlockItem::new, ModBlocks.NECROLORD_BED_DOUBLE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	public static final ItemEntry<BlockItem> NECROLORD_CHANDELIER = blockItem(BlockItem::new, ModBlocks.NECROLORD_CHANDELIER).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	// public static final ItemEntry<BlockItem> NECROLORD_DOOR_SINGLE = door(BlockItem::new, ModBlocks.NECROLORD_DOOR_SINGLE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	// public static final ItemEntry<BlockItem> NECROLORD_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.NECROLORD_DOOR_DOUBLE).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	// public static final ItemEntry<BlockItem> NECROLORD_LOCKBOX = blockItem(BlockItem::new, ModBlocks.NECROLORD_LOCKBOX).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	// public static final ItemEntry<BlockItem> NECROLORD_COUNTER = counter(BlockItem::new, ModBlocks.NECROLORD_COUNTER).tag(ModItemGroupCategories.NECROLORD_TAG).register();
	// public static final ItemEntry<BlockItem> NECROLORD_OVEN = blockItem(BlockItem::new, ModBlocks.NECROLORD_OVEN).tag(ModItemGroupCategories.NECROLORD_TAG).transform(ModBlocks::mineablePickaxe).register();
	// endregion
	// endregion

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <BLOCK extends Block, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> wool(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.WOOL);
	}

	private static <BLOCK extends CarpetBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> carpet(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.WOOL_CARPETS);
	}

	private static <BLOCK extends ShelfBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> shelf(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends SofaBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> sofa(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends CounterBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> counter(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends BedBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> bed(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.BEDS);
	}

	private static <BLOCK extends FurnitureDoorBlock, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> door(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.tag(ItemTags.Vanilla.WOODEN_DOORS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_left".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends Block, ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> blockItem(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> itemFactory.apply(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
						)
				)
				.tag(FurnitureStation.CRAFTABLE)
		;
	}

	private static <ITEM extends Item> void stackedBlockItemModel(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider, IntegerProperty property)
	{
		var maxValue = StackedBlock.getMaxValue(property);

		provider.withExistingParent(
				"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
				new ResourceLocation(ctx.getId().getNamespace(), "block/%s_%d".formatted(ctx.getId().getPath(), maxValue))
		);
	}

	private static ItemEntry<WidowBloomBlockItem> widowBloomItem()
	{
		return REGISTRATE
				.object(ModBlocks.VENTHYR_WIDOW_BLOOM.getId().getPath())
				.item(properties -> new WidowBloomBlockItem(ModBlocks.VENTHYR_WIDOW_BLOOM.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.tag(FurnitureStation.CRAFTABLE, ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.VENTHYR_TAG)
				.model((ctx, provider) -> provider
						.getBuilder("%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
						.transforms()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ItemTransforms.TransformType.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 3F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				)
		.register();
	}

	private static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> generatedModel(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> builder)
	{
		return builder.model(ModItems::getModelFile);
	}

	private static <BLOCK extends SkullBlossomsBlock> ItemBuilder<BasicRegistrate, SkullBlossomsBlockItem, BasicRegistrate> skullBlossoms(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new SkullBlossomsBlockItem(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.tag(FurnitureStation.CRAFTABLE, ModItemGroupCategories.DECORATIONS_TAG)
				.model((ctx, provider) -> provider
						.getBuilder("%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
						.transforms()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ItemTransforms.TransformType.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ItemTransforms.TransformType.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ItemTransforms.TransformType.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 4F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				);
	}

	private static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> cookieJarModel(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> buider)
	{
		return buider
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_full".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <ITEM extends Item> ItemModelBuilder getModelFile(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider)
	{
		var suffix = "";

		return provider
				.withExistingParent(
						// <namespace>:generated/item/<path>[suffix] | Model we are generating
						"%s:generated/item/%s%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath(), suffix),
						// <namespace>:item/<path>[suffix] | Existing model, exported from BlockBench
						new ResourceLocation(ctx.getId().getNamespace(), "item/%s%s".formatted(ctx.getId().getPath(), suffix))
				)
				.renderType(new ResourceLocation(Mods.MINECRAFT, "cutout"))
				.texture(ModBlocks.getTextureKey(ctx.getId()), ModBlocks.getTexturePath(ctx.getId()))
		;
	}

	private static <ITEM extends Item> ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> applyDyeable(ItemBuilder<BasicRegistrate, ITEM, BasicRegistrate> builder)
	{
		// NOTE: ROYAL_WOOL & ROYAL_CARPET should be any DyeableBlock / DyeableCarpet blocks
		return builder.color(() -> () -> (stack, tintIndex) -> tintIndex == (ROYAL_WOOL.isIn(stack) || ROYAL_CARPET.isIn(stack) ? 0 : 1) ? IDyeable.getDyeColor(stack).map(IDyeable::tintFromDyeColor).orElse(-1) : -1);
	}
	// endregion
}
package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.block.furniture.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.FurnitureDoorBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.SofaBlock;
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
	// endregion

	// region: Dunmer
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_0 = blockItem(BlockItem::new, ModBlocks.DUNMER_POTTERY_0).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_1 = blockItem(BlockItem::new, ModBlocks.DUNMER_POTTERY_1).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.DUNMER_TAG).register();
	// endregion

	// region: Bone
	public static final ItemEntry<BlockItem> BONE_CANDLES = blockItem(BlockItem::new, ModBlocks.BONE_CANDLES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();

	// region: Skeleton
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHALICES = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHALICES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PILE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PILE).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<SkullBlossomsBlockItem> BONE_SKELETON_SKULL_BLOSSOMS = skullBlossoms(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	public static final ItemEntry<BlockItem> BONE_WITHER_CHALICES = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHALICES).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).model((ctx, provider) -> stackedBlockItemModel(ctx, provider, ChalicesBlock.CHALICES)).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PILE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PILE).tag(ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<SkullBlossomsBlockItem> BONE_WITHER_SKULL_BLOSSOMS = skullBlossoms(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS).tag(ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion
	// endregion
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
	public static final ItemEntry<BlockItem> DUNMER_BED_SINGLE = bed(BlockItem::new, ModBlocks.DUNMER_BED_SINGLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_BED_DOUBLE = bed(BlockItem::new, ModBlocks.DUNMER_BED_DOUBLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_CHANDELIER = blockItem(BlockItem::new, ModBlocks.DUNMER_CHANDELIER).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DOOR_SINGLE = door(BlockItem::new, ModBlocks.DUNMER_DOOR_SINGLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.DUNMER_DOOR_DOUBLE).tag(ModItemGroupCategories.DUNMER_TAG).register();
	public static final ItemEntry<BlockItem> DUNMER_LOCKBOX = blockItem(BlockItem::new, ModBlocks.DUNMER_LOCKBOX).tag(ModItemGroupCategories.DUNMER_TAG).register();
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
	// endregion

	// region: Bone
	// region: Skeleton
	public static final ItemEntry<BlockItem> BONE_SKELETON_WOOL = wool(BlockItem::new, ModBlocks.BONE_SKELETON_WOOL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CARPET = carpet(BlockItem::new, ModBlocks.BONE_SKELETON_CARPET).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WALL_LIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_FLOOR_LIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_SMALL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_WIDE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_TABLE_LARGE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_STOOL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_STOOL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SKULL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_SKULL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PAINTING_SMALL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_PAINTING_WIDE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DRAWER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DRAWER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SHELF = shelf(BlockItem::new, ModBlocks.BONE_SKELETON_SHELF).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_SOFA = sofa(BlockItem::new, ModBlocks.BONE_SKELETON_SOFA).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DESK_LEFT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DESK_RIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHAIR = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHAIR).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BENCH = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_BENCH).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_BOOKSHELF).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHEST = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHEST).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DRESSER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_DRESSER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_WARDROBE_TOP).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BED_SINGLE = bed(BlockItem::new, ModBlocks.BONE_SKELETON_BED_SINGLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_BED_DOUBLE = bed(BlockItem::new, ModBlocks.BONE_SKELETON_BED_DOUBLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_CHANDELIER = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_CHANDELIER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DOOR_SINGLE = door(BlockItem::new, ModBlocks.BONE_SKELETON_DOOR_SINGLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.BONE_SKELETON_DOOR_DOUBLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	public static final ItemEntry<BlockItem> BONE_SKELETON_LOCKBOX = blockItem(BlockItem::new, ModBlocks.BONE_SKELETON_LOCKBOX).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_SKELETON_TAG).register();
	// endregion

	// region: Wither
	public static final ItemEntry<BlockItem> BONE_WITHER_WOOL = wool(BlockItem::new, ModBlocks.BONE_WITHER_WOOL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CARPET = carpet(BlockItem::new, ModBlocks.BONE_WITHER_CARPET).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WALL_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WALL_LIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_FLOOR_LIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_FLOOR_LIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_SMALL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_WIDE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_TABLE_LARGE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_TABLE_LARGE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_STOOL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_STOOL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SKULL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_SKULL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PAINTING_SMALL = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PAINTING_SMALL).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_PAINTING_WIDE = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_PAINTING_WIDE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DRAWER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DRAWER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SHELF = shelf(BlockItem::new, ModBlocks.BONE_WITHER_SHELF).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_SOFA = sofa(BlockItem::new, ModBlocks.BONE_WITHER_SOFA).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DESK_LEFT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DESK_LEFT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DESK_RIGHT = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DESK_RIGHT).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHAIR = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHAIR).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BENCH = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_BENCH).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BOOKSHELF = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_BOOKSHELF).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHEST = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHEST).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DRESSER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_DRESSER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WARDROBE_BOTTOM = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WARDROBE_BOTTOM).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_WARDROBE_TOP = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_WARDROBE_TOP).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BED_SINGLE = bed(BlockItem::new, ModBlocks.BONE_WITHER_BED_SINGLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_BED_DOUBLE = bed(BlockItem::new, ModBlocks.BONE_WITHER_BED_DOUBLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_CHANDELIER = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_CHANDELIER).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DOOR_SINGLE = door(BlockItem::new, ModBlocks.BONE_WITHER_DOOR_SINGLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_DOOR_DOUBLE = door(BlockItem::new, ModBlocks.BONE_WITHER_DOOR_DOUBLE).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	public static final ItemEntry<BlockItem> BONE_WITHER_LOCKBOX = blockItem(BlockItem::new, ModBlocks.BONE_WITHER_LOCKBOX).tag(ModItemGroupCategories.BONE_TAG, ModItemGroupCategories.BONE_WITHER_TAG).register();
	// endregion
	// endregion
	// endregion

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <BLOCK extends Block, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> wool(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.WOOL);
	}

	private static <BLOCK extends CarpetBlock, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> carpet(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.CARPETS);
	}

	private static <BLOCK extends ShelfBlock, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> shelf(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.tag(ItemTags.Vanilla.BEDS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends SofaBlock, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> sofa(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block)
				.tag(ItemTags.Vanilla.BEDS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends BedBlock, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> bed(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
	{
		return blockItem(itemFactory, block).tag(ItemTags.Vanilla.BEDS);
	}

	private static <BLOCK extends FurnitureDoorBlock, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> door(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
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

	private static <BLOCK extends Block, ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> blockItem(NonNullBiFunction<BLOCK, Item.Properties, ITEM> itemFactory, BlockEntry<BLOCK> block)
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
							.transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ModelBuilder.Perspective.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ModelBuilder.Perspective.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ModelBuilder.Perspective.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ModelBuilder.Perspective.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 3F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				)
		.register();
	}

	private static <BLOCK extends SkullBlossomsBlock> ItemBuilder<Registrate, SkullBlossomsBlockItem, Registrate> skullBlossoms(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new SkullBlossomsBlockItem(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.tag(FurnitureStation.CRAFTABLE, ModItemGroupCategories.DECORATIONS_TAG, ModItemGroupCategories.BONE_TAG)
				.model((ctx, provider) -> provider
						.getBuilder("%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
						.parent(new ModelFile.UncheckedModelFile("minecraft:builtin/entity"))
						.transforms()
							.transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
								.end()
							.transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
								.rotation(75F, 45F, 0F)
								.translation(0F, 3F, 4F)
								.scale(.375F, .375F, .375F)
							.end()
							.transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
								.rotation(0F, 135F, 0F)
								.translation(0F, 7F, 0F)
								.scale(.4F, .4F, .4F)
							.end()
							.transform(ModelBuilder.Perspective.HEAD)
								.rotation(0F, 0F, 0F)
								.translation(0F, 30F, 0F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ModelBuilder.Perspective.GROUND)
								.rotation(0F, 0F, 0F)
								.translation(0F, 6F, 0F)
								.scale(.25F, .25F, .25F)
							.end()
							.transform(ModelBuilder.Perspective.FIXED)
								.rotation(-90F, 0F, 0F)
								.translation(0F, 0F, -23F)
								.scale(1F, 1F, 1F)
							.end()
							.transform(ModelBuilder.Perspective.GUI)
								.rotation(30F, -135F, 0F)
								.translation(0F, 4F, 0F)
								.scale(.5F, .5F, .5F)
							.end()
						.end()
				);
	}
	// endregion
}
package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.base.Predicates;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.core.init.ACRegistry;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.apexcore.lib.block.ISeatBlock;
import xyz.apex.forge.apexcore.registrate.BasicRegistrate;
import xyz.apex.forge.apexcore.registrate.builder.BlockBuilder;
import xyz.apex.forge.apexcore.registrate.builder.factory.BlockFactory;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntry;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.fantasyfurniture.block.decorations.BannerBlock;
import xyz.apex.forge.fantasyfurniture.block.decorations.CandleBlock;
import xyz.apex.forge.fantasyfurniture.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.block.furniture.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.ChestBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.*;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.*;

@SuppressWarnings({ "SameParameterValue", "Guava", "SuspiciousToArrayCall" })
public final class ModBlocks
{
	// region: Decorations
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY = berryBasket("empty").register();
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = berryBasket("sweetberry").register();
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_BLUEBERRY = berryBasket("blueberry").register();
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_STRAWBERRYBERRY = berryBasket("strawberry").register();
	public static final BlockEntry<BoltsOfClothBlock> BOLTS_OF_CLOTH = boltsOfCloth().register();
	public static final BlockEntry<BookStackBlock> BOOK_STACK = bookStack().register();
	public static final BlockEntry<BowlBlock> BOWL_EMPTY = bowl("empty").register();
	public static final BlockEntry<BowlBlock> BOWL_BERRTROOT_SOUP = bowl("beetroot_soup").register();
	public static final BlockEntry<BowlBlock> BOWL_MUSHROOM_STEW = bowl("mushroom_stew").register();
	public static final BlockEntry<TankardsBlock> TANKARD_EMPTY = tankards("empty").register();
	public static final BlockEntry<TankardsBlock> TANKARD_HONEYMEAD = tankards("honeymead").register();
	public static final BlockEntry<TankardsBlock> TANKARD_MILK = tankards("milk").register();
	public static final BlockEntry<TankardsBlock> TANKARD_SWEETBERRY = tankards("sweetberry").register();
	public static final BlockEntry<MushroomsBlock> MUSHROOMS_RED = mushrooms("red", MaterialColor.COLOR_RED).register();
	public static final BlockEntry<CoinStackBlock> COIN_STOCK_GOLD = coinStack("gold").register();
	public static final BlockEntry<CoinStackBlock> COIN_STOCK_IRON = coinStack("iron").register();
	public static final BlockEntry<MuffinsBlock> MUFFINS_BLUEBERRY = muffins("blueberry").register();
	public static final BlockEntry<MuffinsBlock> MUFFINS_CHOCOLATE = muffins("chocolate").register();
	public static final BlockEntry<MuffinsBlock> MUFFINS_SWEETBERRY = muffins("sweetberry").register();
	public static final BlockEntry<PaperStackBlock> PAPER_STACK = paperStack().register();
	public static final BlockEntry<CookieJarBlock> COOKIE_JAR = cookieJar().register();
	public static final BlockEntry<BrewingCauldronBlock.Dyeable> BREWING_CAULDRON = brewingCauldron(BrewingCauldronBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<FloatingTomesBlock.Dyeable> FLOATING_TOMES = floatingTomes(FloatingTomesBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<GravestoneBlock> GRAVESTONE = gravestone().register();
	public static final BlockEntry<HangingHerbsBlock> HANGING_HERBS = hangingHerbs().register();
	public static final BlockEntry<SpiderWebBlock> SPIDER_WEB_SMALL = spiderWebSmall().register();
	public static final BlockEntry<SpiderWebMultiBlock> SPIDER_WEB_WIDE = spiderWebWide().register();
	public static final BlockEntry<StackablePumpkinsBlock> STACKABLE_PUMPKINS = stackablePumpkins().register();
	public static final BlockEntry<ChainBlock> BRONZE_CHAIN = chain("bronze").register();
	public static final BlockEntry<MushroomsBlock> MUSHROOMS_BROWN = mushrooms("brown", MaterialColor.COLOR_BROWN).register();
	public static final BlockEntry<PotionBottlesBlock> POTION_BOTTLES = potionBottles(PotionBottlesBlock::new, "").register();
	public static final BlockEntry<PotionBottlesBlock.Dyeable> DYEABLE_POTION_BOTTLES = potionBottles(PotionBottlesBlock.Dyeable::new, "_dyeable").transform(ModBlocks::applyDyeable).register();

	// region: Nordic
	public static final BlockEntry<BoiledCremeTreatsBlock> NORDIC_BOILED_CREME_TREATS = boiledCremeTreats("nordic").register();
	public static final BlockEntry<SweetRollsBlock> NORDIC_SWEETROLLS = sweetRolls("nordic").register();
	public static final BlockEntry<MeadBottlesBlock> NORDIC_MEAD_BOTTLES = meadBottles("nordic").register();
	public static final BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_LIGHT = soulGems("nordic", "light").register();
	public static final BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_DARK = soulGems("nordic", "dark").register();
	// endregion

	// region: Venthyr
	public static final BlockEntry<FoodBlock> VENTHYR_FOOD_0 = food("venthyr", 0).register();
	public static final BlockEntry<FoodBlock> VENTHYR_FOOD_1 = food("venthyr", 1).register();
	public static final BlockEntry<TeaSetBlock> VENTHYR_TEA_SET = teaSet("venthyr").register();
	public static final BlockEntry<TeaCupsBlock> VENTHYR_TEA_CUPS = teaCups("venthyr").register();
	public static final BlockEntry<PlatterBlock> VENTHYR_PLATTER = platter("venthyr").register();
	public static final BlockEntry<WidowBloomBlock> VENTHYR_WIDOW_BLOOM = widowBloom("venthyr").register();
	public static final BlockEntry<TomesBlock> VENTHYR_TOMES = tomes("venthyr").register();
	public static final BlockEntry<ChalicesBlock> VENTHYR_CHALICES = chalices("venthyr", ChalicesBlock::new).register();
	public static final BlockEntry<CandleBlock> VENTHYR_CANDLES = candles("venthyr").register();
	public static final BlockEntry<BannerBlock> VENTHYR_BANNER = banner("venthyr").register();
	// endregion

	// region: Dunmer
	public static final BlockEntry<PotteryBlock> DUNMER_POTTERY_0 = pottery("dunmer", 0).register();
	public static final BlockEntry<PotteryBlock> DUNMER_POTTERY_1 = pottery("dunmer", 1).register();
	// endregion

	// region: Bone
	public static final BlockEntry<CandleBlock> BONE_CANDLES = candles("bone").register();

	// region: Skeleton
	public static final BlockEntry<ChalicesBlock> BONE_SKELETON_CHALICES = chalices("bone_skeleton", ChalicesBlock::new).lang("Bone Chalices").register();
	public static final BlockEntry<BonePileBlock> BONE_SKELETON_PILE = bonePile("bone_skeleton").lang("Bone Pile").register();
	public static final BlockEntry<SkullBlossomsBlock> BONE_SKELETON_SKULL_BLOSSOMS = skullBlossoms("bone_skeleton").lang("Skull Blossoms").register();
	// endregion

	// region: Wither
	public static final BlockEntry<ChalicesBlock> BONE_WITHER_CHALICES = chalices("bone_wither", ChalicesBlock::new).lang("Wither Bone Chalices").register();
	public static final BlockEntry<BonePileBlock> BONE_WITHER_PILE = bonePile("bone_wither").lang("Wither Bone Pile").register();
	public static final BlockEntry<SkullBlossomsBlock> BONE_WITHER_SKULL_BLOSSOMS = skullBlossoms("bone_wither").lang("Wither Skull Blossoms").register();
	// endregion
	// endregion

	// region: Royal
	public static final BlockEntry<CrownBlock.Dyeable> ROYAL_CROWN = crown("royal", CrownBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<CandelabraBlock> ROYAL_CANDELABRA = candelabra("royal").register();
	public static final BlockEntry<ChalicesBlock.Dyeable> ROYAL_CHALICES = chalices("royal", ChalicesBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<CrownBlock.Dyeable> ROYAL_CUSHIONED_CROWN = cushionedCrown("royal", CrownBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<FoodBlock> ROYAL_FOOD_0 = food("royal", 0).register();
	public static final BlockEntry<FoodBlock> ROYAL_FOOD_1 = food("royal", 1).register();
	public static final BlockEntry<PlatterBlock> ROYAL_PLATTER = platter("royal").register();
	public static final BlockEntry<CushionBlock.Dyeable> ROYAL_FLOOR_CUSHION = floorCushion("royal", CushionBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<WallMirrorBlock.Dyeable> ROYAL_WALL_MIRROR_LARGE = wallMirror("royal", "small", WallMirrorBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<WallMirrorMultiBlock.Dyeable> ROYAL_WALL_MIRROR_SMALL = wallMirrorMultiBlock("royal", "large", WallMirrorMultiBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	// endregion

	// region: Necrolord
	public static final BlockEntry<CandelabraBlock> NECROLORD_CANDELABRA = candelabra("necrolord").register();
	//endregion
	// endregion

	// region: Furniture Sets
	// region: Nordic
	public static final BlockEntry<Block> NORDIC_WOOL = wool("nordic", Block::new).register();
	public static final BlockEntry<CarpetBlock> NORDIC_CARPET = carpet("nordic", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> NORDIC_WALL_LIGHT = wallLight("nordic", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock> NORDIC_FLOOR_LIGHT = floorLight("nordic", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> NORDIC_TABLE_SMALL = tableSmall("nordic", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> NORDIC_TABLE_WIDE = tableWide("nordic", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> NORDIC_TABLE_LARGE = tableLarge("nordic", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> NORDIC_STOOL = stool("nordic", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> NORDIC_CUSHION = cushion("nordic", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> NORDIC_PAINTING_SMALL = paintingSmall("nordic", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> NORDIC_PAINTING_WIDE = paintingWide("nordic", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> NORDIC_DRAWER = drawer("nordic", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> NORDIC_SHELF = shelf("nordic", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> NORDIC_SOFA = sofa("nordic", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> NORDIC_DESK_LEFT = deskLeft("nordic", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> NORDIC_DESK_RIGHT = deskRight("nordic", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> NORDIC_CHAIR = chair("nordic", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> NORDIC_BENCH = bench("nordic", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> NORDIC_BOOKSHELF = bookshelf("nordic", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> NORDIC_CHEST = chest("nordic", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> NORDIC_DRESSER = dresser("nordic", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> NORDIC_WARDROBE_BOTTOM = wardrobeBottom("nordic", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> NORDIC_WARDROBE_TOP = wardrobeTop("nordic", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> NORDIC_BED_SINGLE = bedSingle("nordic", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> NORDIC_BED_DOUBLE = bedDouble("nordic", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> NORDIC_CHANDELIER = chandelier("nordic", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_SINGLE = doorSingle("nordic", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_DOUBLE = doorDouble("nordic", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> NORDIC_LOCKBOX = lockbox("nordic", LockboxBlock::new).register();
	public static final BlockEntry<CounterBlock> NORDIC_COUNTER = counter("nordic", CounterBlock::new).register();
	public static final BlockEntry<OvenBlock> NORDIC_OVEN = oven("nordic", OvenBlock::new).transform(ModBlocks::mineablePickaxe).register();
	// endregion

	// region: Dunmer
	public static final BlockEntry<Block> DUNMER_WOOL = wool("dunmer", Block::new).register();
	public static final BlockEntry<CarpetBlock> DUNMER_CARPET = carpet("dunmer", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> DUNMER_WALL_LIGHT = wallLight("dunmer", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	public static final BlockEntry<FloorLightBlock> DUNMER_FLOOR_LIGHT = floorLight("dunmer", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> DUNMER_TABLE_SMALL = tableSmall("dunmer", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> DUNMER_TABLE_WIDE = tableWide("dunmer", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> DUNMER_TABLE_LARGE = tableLarge("dunmer", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> DUNMER_STOOL = stool("dunmer", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> DUNMER_CUSHION = cushion("dunmer", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> DUNMER_PAINTING_SMALL = paintingSmall("dunmer", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> DUNMER_PAINTING_WIDE = paintingWide("dunmer", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> DUNMER_DRAWER = drawer("dunmer", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> DUNMER_SHELF = shelf("dunmer", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> DUNMER_SOFA = sofa("dunmer", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> DUNMER_DESK_LEFT = deskLeft("dunmer", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> DUNMER_DESK_RIGHT = deskRight("dunmer", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> DUNMER_CHAIR = chair("dunmer", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> DUNMER_BENCH = bench("dunmer", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> DUNMER_BOOKSHELF = bookshelf("dunmer", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> DUNMER_CHEST = chest("dunmer", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> DUNMER_DRESSER = dresser("dunmer", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> DUNMER_WARDROBE_BOTTOM = wardrobeBottom("dunmer", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> DUNMER_WARDROBE_TOP = wardrobeTop("dunmer", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> DUNMER_BED_SINGLE = bedSingle("dunmer", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> DUNMER_BED_DOUBLE = bedDouble("dunmer", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> DUNMER_CHANDELIER = chandelier("dunmer", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_SINGLE = doorSingle("dunmer", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_DOUBLE = doorDouble("dunmer", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> DUNMER_LOCKBOX = lockbox("dunmer", LockboxBlock::new).register();
	public static final BlockEntry<CounterBlock> DUNMER_COUNTER = counter("dunmer", CounterBlock::new).register();
	public static final BlockEntry<OvenMultiBlock> DUNMER_OVEN = ovenMultiblock("dunmer", OvenMultiBlock::new).lang("Dunmer Cooking Spit").register();
	// endregion

	// region: Venthyr
	public static final BlockEntry<Block> VENTHYR_WOOL = wool("venthyr", Block::new).register();
	public static final BlockEntry<CarpetBlock> VENTHYR_CARPET = carpet("venthyr", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> VENTHYR_WALL_LIGHT = wallLight("venthyr", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	public static final BlockEntry<FloorLightBlock> VENTHYR_FLOOR_LIGHT = floorLight("venthyr", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL = tableSmall("venthyr", TableSmallBlock::new).register();
	public static final BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL_FANCY = tableSmallFancy("venthyr", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE = tableWide("venthyr", TableWideBlock::new).register();
	public static final BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE_FANCY = tableWideFancy("venthyr", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE = tableLarge("venthyr", TableLargeBlock::new).register();
	public static final BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE_FANCY = tableLargeFancy("venthyr", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> VENTHYR_STOOL = stool("venthyr", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> VENTHYR_CUSHION = cushion("venthyr", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> VENTHYR_PAINTING_SMALL = paintingSmall("venthyr", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> VENTHYR_PAINTING_WIDE = paintingWide("venthyr", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> VENTHYR_DRAWER = drawer("venthyr", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> VENTHYR_SHELF = shelf("venthyr", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> VENTHYR_SOFA = sofa("venthyr", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> VENTHYR_DESK_LEFT = deskLeft("venthyr", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> VENTHYR_DESK_RIGHT = deskRight("venthyr", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> VENTHYR_CHAIR = chair("venthyr", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> VENTHYR_BENCH = bench("venthyr", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> VENTHYR_BOOKSHELF = bookshelf("venthyr", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> VENTHYR_CHEST = chest("venthyr", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> VENTHYR_DRESSER = dresser("venthyr", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> VENTHYR_WARDROBE_BOTTOM = wardrobeBottom("venthyr", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> VENTHYR_WARDROBE_TOP = wardrobeTop("venthyr", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> VENTHYR_BED_SINGLE = bedSingle("venthyr", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> VENTHYR_BED_DOUBLE = bedDouble("venthyr", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> VENTHYR_CHANDELIER = chandelier("venthyr", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_SINGLE = doorSingle("venthyr", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_DOUBLE = doorDouble("venthyr", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> VENTHYR_LOCKBOX = lockbox("venthyr", LockboxBlock::new).register();
	public static final BlockEntry<CounterBlock> VENTHYR_COUNTER = counter("venthyr", CounterBlock::new).register();
	public static final BlockEntry<OvenBlock> VENTHYR_OVEN = oven("venthyr", OvenBlock::new).transform(ModBlocks::mineablePickaxe).register();
	// endregion

	// region: Bone
	// region: Skeleton
	public static final BlockEntry<Block> BONE_SKELETON_WOOL = wool("bone/skeleton", Block::new).lang("Bone Wool").register();
	public static final BlockEntry<CarpetBlock> BONE_SKELETON_CARPET = carpet("bone/skeleton", CarpetBlock::new).lang("Bone Carpet").register();
	public static final BlockEntry<FurnitureWallLightBlock> BONE_SKELETON_WALL_LIGHT = wallLight("bone/skeleton", FurnitureWallLightBlock::new).lang("Bone Wall Light").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FloorLightBlock.WithFacing> BONE_SKELETON_FLOOR_LIGHT = floorLight("bone/skeleton", FloorLightBlock.WithFacing::new).lang("Bone Floor Light").blockState(ModBlocks::horizontalBlockState).transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableSmallBlock> BONE_SKELETON_TABLE_SMALL = tableSmall("bone/skeleton", TableSmallBlock::new).lang("Bone Table Small").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableWideBlock> BONE_SKELETON_TABLE_WIDE = tableWide("bone/skeleton", TableWideBlock::new).lang("Bone Table Wide").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableLargeBlock> BONE_SKELETON_TABLE_LARGE = tableLarge("bone/skeleton", TableLargeBlock::new).lang("Bone Table Large").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<StoolBlock> BONE_SKELETON_STOOL = stool("bone/skeleton", StoolBlock::new).lang("Bone Stool").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<CushionBlock> BONE_SKELETON_SKULL = skull("bone/skeleton", CushionBlock::new).lang("Bone Skull").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<PaintingSmallBlock> BONE_SKELETON_PAINTING_SMALL = paintingSmall("bone/skeleton", PaintingSmallBlock::new).lang("Bone Painting Small").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<PaintingWideBlock> BONE_SKELETON_PAINTING_WIDE = paintingWide("bone/skeleton", PaintingWideBlock::new).lang("Bone Painting Wide").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DrawerBlock> BONE_SKELETON_DRAWER = drawer("bone/skeleton", DrawerBlock::new).lang("Bone Drawer").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ShelfBlock> BONE_SKELETON_SHELF = shelf("bone/skeleton", ShelfBlock::new).lang("Bone Shelf").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<SofaBlock> BONE_SKELETON_SOFA = sofa("bone/skeleton", SofaBlock::new).lang("Bone Sofa").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DeskBlock> BONE_SKELETON_DESK_LEFT = deskLeft("bone/skeleton", DeskBlock::new).lang("Bone Desk Left").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DeskBlock> BONE_SKELETON_DESK_RIGHT = deskRight("bone/skeleton", DeskBlock::new).lang("Bone Desk Right").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChairBlock.OriginOnly> BONE_SKELETON_CHAIR = chair("bone/skeleton", ChairBlock.OriginOnly::new).lang("Bone Chair").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BenchBlock> BONE_SKELETON_BENCH = bench("bone/skeleton", BenchBlock::new).lang("Bone Bench").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BookshelfBlock> BONE_SKELETON_BOOKSHELF = bookshelf("bone/skeleton", BookshelfBlock::new).lang("Bone Bookshelf").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChestBlock> BONE_SKELETON_CHEST = chest("bone/skeleton", ChestBlock::new).lang("Bone Chest").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DresserBlock> BONE_SKELETON_DRESSER = dresser("bone/skeleton", DresserBlock::new).lang("Bone Dresser").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<WardrobeBottomBlock> BONE_SKELETON_WARDROBE_BOTTOM = wardrobeBottom("bone/skeleton", WardrobeBottomBlock::new).lang("Bone Wardrobe Bottom").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<WardrobeTopBlock> BONE_SKELETON_WARDROBE_TOP = wardrobeTop("bone/skeleton", WardrobeTopBlock::new).lang("Bone Wardrobe Top").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BedSingleBlock> BONE_SKELETON_BED_SINGLE = bedSingle("bone/skeleton", BedSingleBlock::new).lang("Bone Bed Single").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BedDoubleBlock> BONE_SKELETON_BED_DOUBLE = bedDouble("bone/skeleton", BedDoubleBlock::new).lang("Bone Bed Double").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChandelierBlock> BONE_SKELETON_CHANDELIER = chandelier("bone/skeleton", ChandelierBlock::new).lang("Bone Chandelier").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_SINGLE = doorSingle("bone/skeleton", FurnitureDoorBlock::new).lang("Bone Door Single").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_DOUBLE = doorDouble("bone/skeleton", FurnitureDoorBlock::new).lang("Bone Door Double").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<LockboxBlock> BONE_SKELETON_LOCKBOX = lockbox("bone/skeleton", LockboxBlock::new).lang("Bone Lockbox").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<CounterBlock> BONE_SKELETON_COUNTER = counter("bone/skeleton", CounterBlock::new).transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<OvenBlock> BONE_SKELETON_OVEN = oven("bone/skeleton", OvenBlock::new).transform(ModBlocks::mineablePickaxe).register();
	// endregion

	// region: Wither
	public static final BlockEntry<Block> BONE_WITHER_WOOL = wool("bone/wither", Block::new).lang("Wither Bone Wool").register();
	public static final BlockEntry<CarpetBlock> BONE_WITHER_CARPET = carpet("bone/wither", CarpetBlock::new).lang("Wither Bone Carpet").register();
	public static final BlockEntry<FurnitureWallLightBlock> BONE_WITHER_WALL_LIGHT = wallLight("bone/wither", FurnitureWallLightBlock::new).lang("Wither Bone Wall Light").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FloorLightBlock.WithFacing> BONE_WITHER_FLOOR_LIGHT = floorLight("bone/wither", FloorLightBlock.WithFacing::new).lang("Wither Bone Floor Light").blockState(ModBlocks::horizontalBlockState).transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableSmallBlock> BONE_WITHER_TABLE_SMALL = tableSmall("bone/wither", TableSmallBlock::new).lang("Wither Bone Table Small").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableWideBlock> BONE_WITHER_TABLE_WIDE = tableWide("bone/wither", TableWideBlock::new).lang("Wither Bone Table Wide").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<TableLargeBlock> BONE_WITHER_TABLE_LARGE = tableLarge("bone/wither", TableLargeBlock::new).lang("Wither Bone Table Large").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<StoolBlock> BONE_WITHER_STOOL = stool("bone/wither", StoolBlock::new).lang("Wither Bone Stool").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<CushionBlock> BONE_WITHER_SKULL = skull("bone/wither", CushionBlock::new).lang("Wither Bone Skull").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<PaintingSmallBlock> BONE_WITHER_PAINTING_SMALL = paintingSmall("bone/wither", PaintingSmallBlock::new).lang("Wither Bone Painting Small").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<PaintingWideBlock> BONE_WITHER_PAINTING_WIDE = paintingWide("bone/wither", PaintingWideBlock::new).lang("Wither Bone Painting Wide").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DrawerBlock> BONE_WITHER_DRAWER = drawer("bone/wither", DrawerBlock::new).lang("Wither Bone Drawer").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ShelfBlock> BONE_WITHER_SHELF = shelf("bone/wither", ShelfBlock::new).lang("Wither Bone Shelf").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<SofaBlock> BONE_WITHER_SOFA = sofa("bone/wither", SofaBlock::new).lang("Wither Bone Sofa").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DeskBlock> BONE_WITHER_DESK_LEFT = deskLeft("bone/wither", DeskBlock::new).lang("Wither Bone Desk Left").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DeskBlock> BONE_WITHER_DESK_RIGHT = deskRight("bone/wither", DeskBlock::new).lang("Wither Bone Desk Right").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChairBlock.OriginOnly> BONE_WITHER_CHAIR = chair("bone/wither", ChairBlock.OriginOnly::new).lang("Wither Bone Chair").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BenchBlock> BONE_WITHER_BENCH = bench("bone/wither", BenchBlock::new).lang("Wither Bone Bench").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BookshelfBlock> BONE_WITHER_BOOKSHELF = bookshelf("bone/wither", BookshelfBlock::new).lang("Wither Bone Bookshelf").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChestBlock> BONE_WITHER_CHEST = chest("bone/wither", ChestBlock::new).lang("Wither Bone Chest").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<DresserBlock> BONE_WITHER_DRESSER = dresser("bone/wither", DresserBlock::new).lang("Wither Bone Dresser").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<WardrobeBottomBlock> BONE_WITHER_WARDROBE_BOTTOM = wardrobeBottom("bone/wither", WardrobeBottomBlock::new).lang("Wither Bone Wardrobe Bottom").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<WardrobeTopBlock> BONE_WITHER_WARDROBE_TOP = wardrobeTop("bone/wither", WardrobeTopBlock::new).lang("Wither Bone Wardrobe Top").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BedSingleBlock> BONE_WITHER_BED_SINGLE = bedSingle("bone/wither", BedSingleBlock::new).lang("Wither Bone Bed Single").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<BedDoubleBlock> BONE_WITHER_BED_DOUBLE = bedDouble("bone/wither", BedDoubleBlock::new).lang("Wither Bone Bed Double").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<ChandelierBlock> BONE_WITHER_CHANDELIER = chandelier("bone/wither", ChandelierBlock::new).lang("Wither Bone Chandelier").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_SINGLE = doorSingle("bone/wither", FurnitureDoorBlock::new).lang("Wither Bone Door Single").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_DOUBLE = doorDouble("bone/wither", FurnitureDoorBlock::new).lang("Wither Bone Door Double").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<LockboxBlock> BONE_WITHER_LOCKBOX = lockbox("bone/wither", LockboxBlock::new).lang("Wither Bone Lockbox").transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<CounterBlock> BONE_WITHER_COUNTER = counter("bone/wither", CounterBlock::new).transform(ModBlocks::mineablePickaxe).register();
	public static final BlockEntry<OvenBlock> BONE_WITHER_OVEN = oven("bone/wither", OvenBlock::new).transform(ModBlocks::mineablePickaxe).register();
	// endregion
	// endregion

	// region: Royal
	public static final BlockEntry<DyeableBlock> ROYAL_WOOL = woolDyeable("royal", DyeableBlock::new).register();
	public static final BlockEntry<DyeableCarpetBlock> ROYAL_CARPET = carpetDyeable("royal", DyeableCarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> ROYAL_WALL_LIGHT = wallLight("royal", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock.WithFacing> ROYAL_FLOOR_LIGHT = floorLight("royal", FloorLightBlock.WithFacing::new).blockState(ModBlocks::horizontalBlockState).register();
	public static final BlockEntry<TableSmallBlock.Dyeable> ROYAL_TABLE_SMALL = tableSmall("royal", TableSmallBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<TableWideBlock.Dyeable> ROYAL_TABLE_WIDE = tableWide("royal", TableWideBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<TableLargeBlock.Dyeable> ROYAL_TABLE_LARGE = tableLarge("royal", TableLargeBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<StoolBlock.Dyeable> ROYAL_STOOL = stool("royal", StoolBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<CushionBlock.Dyeable> ROYAL_CUSHION = cushion("royal", CushionBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<PaintingSmallBlock> ROYAL_PAINTING_SMALL = paintingSmall("royal", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> ROYAL_PAINTING_WIDE = paintingWide("royal", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock.Dyeable> ROYAL_DRAWER = drawer("royal", DrawerBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<ShelfBlock.Dyeable> ROYAL_SHELF = shelf("royal", ShelfBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<SofaBlock.Dyeable> ROYAL_SOFA = sofa("royal", SofaBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<DeskBlock.Dyeable> ROYAL_DESK_LEFT = deskLeft("royal", DeskBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<DeskBlock.Dyeable> ROYAL_DESK_RIGHT = deskRight("royal", DeskBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<ChairBlock.DyeableOriginOnly> ROYAL_CHAIR = chair("royal", ChairBlock.DyeableOriginOnly::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<BenchBlock.Dyeable> ROYAL_BENCH = bench("royal", BenchBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<BookshelfBlock.Dyeable> ROYAL_BOOKSHELF = bookshelf("royal", BookshelfBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<ChestBlock.Dyeable> ROYAL_CHEST = chest("royal", ChestBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<DresserBlock.Dyeable> ROYAL_DRESSER = dresser("royal", DresserBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<WardrobeBottomBlock.Dyeable> ROYAL_WARDROBE_BOTTOM = wardrobeBottom("royal", WardrobeBottomBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<WardrobeTopBlock.Dyeable> ROYAL_WARDROBE_TOP = wardrobeTop("royal", WardrobeTopBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<BedSingleBlock.Dyeable> ROYAL_BED_SINGLE = bedSingle("royal", BedSingleBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<BedDoubleBlock.Dyeable> ROYAL_BED_DOUBLE = bedDouble("royal", BedDoubleBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<ChandelierBlock> ROYAL_CHANDELIER = chandelier("royal", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock.Dyeable> ROYAL_DOOR_SINGLE = doorSingle("royal", FurnitureDoorBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<FurnitureDoorBlock.Dyeable> ROYAL_DOOR_DOUBLE = doorDouble("royal", FurnitureDoorBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<LockboxBlock.Dyeable> ROYAL_LOCKBOX = lockbox("royal", LockboxBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<CounterBlock.Dyeable> ROYAL_COUNTER = counter("royal", CounterBlock.Dyeable::new).transform(ModBlocks::applyDyeable).register();
	public static final BlockEntry<OvenBlock.Dyeable> ROYAL_OVEN = oven("royal", OvenBlock.Dyeable::new).transform(ModBlocks::applyDyeable).transform(ModBlocks::mineablePickaxe).register();
	// endregion

	// region: Necrolord
	public static final BlockEntry<Block> NECROLORD_WOOL = wool("necrolord", Block::new).register();
	public static final BlockEntry<CarpetBlock> NECROLORD_CARPET = carpet("necrolord", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> NECROLORD_WALL_LIGHT = wallLight("necrolord", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock.WithFacing> NECROLORD_FLOOR_LIGHT = floorLight("necrolord", FloorLightBlock.WithFacing::new).blockState(ModBlocks::horizontalBlockState).register();
	public static final BlockEntry<TableSmallBlock> NECROLORD_TABLE_SMALL = tableSmall("necrolord", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> NECROLORD_TABLE_WIDE = tableWide("necrolord", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> NECROLORD_TABLE_LARGE = tableLarge("necrolord", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> NECROLORD_STOOL = stool("necrolord", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> NECROLORD_CUSHION = cushion("necrolord", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> NECROLORD_PAINTING_SMALL = paintingSmall("necrolord", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> NECROLORD_PAINTING_WIDE = paintingWide("necrolord", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> NECROLORD_DRAWER = drawer("necrolord", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> NECROLORD_SHELF = shelf("necrolord", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> NECROLORD_SOFA = sofa("necrolord", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> NECROLORD_DESK_LEFT = deskLeft("necrolord", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> NECROLORD_DESK_RIGHT = deskRight("necrolord", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> NECROLORD_CHAIR = chair("necrolord", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> NECROLORD_BENCH = bench("necrolord", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> NECROLORD_BOOKSHELF = bookshelf("necrolord", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> NECROLORD_CHEST = chest("necrolord", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> NECROLORD_DRESSER = dresser("necrolord", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> NECROLORD_WARDROBE_BOTTOM = wardrobeBottom("necrolord", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> NECROLORD_WARDROBE_TOP = wardrobeTop("necrolord", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> NECROLORD_BED_SINGLE = bedSingle("necrolord", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> NECROLORD_BED_DOUBLE = bedDouble("necrolord", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> NECROLORD_CHANDELIER = chandelier("necrolord", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NECROLORD_DOOR_SINGLE = doorSingle("necrolord", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NECROLORD_DOOR_DOUBLE = doorDouble("necrolord", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> NECROLORD_LOCKBOX = lockbox("necrolord", LockboxBlock::new).register();
	public static final BlockEntry<CounterBlock> NECROLORD_COUNTER = counter("necrolord", CounterBlock::new).register();
	public static final BlockEntry<OvenBlock> NECROLORD_OVEN = oven("necrolord", OvenBlock::new).transform(ModBlocks::mineablePickaxe).register();
	// endregion
	// endregion

	static void bootstrap()
	{
		REGISTRATE.addDataGenerator(LANG, provider -> REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().map(RegistryEntry::get).forEach(block -> {
			if(block instanceof ISeatBlock seat)
				provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
			if(block instanceof BedBlock bed)
				provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
			if(block instanceof StackedBlock stacked)
				provider.add(stacked.getStackableTranslationKey(), "Stackable");
		}));

		REGISTRATE.addDataGenerator(BLOCK_TAGS, provider -> provider
				.tag(ACRegistry.TAG_VISUALIZER)
				.add(REGISTRATE
						.getAll(ForgeRegistries.Keys.BLOCKS)
						.stream()
						.map(RegistryEntry::get)
						.filter(IMultiBlock.class::isInstance)
						.toArray(Block[]::new)
				)
		);

		REGISTRATE.addDataGenerator(BLOCKSTATE, provider -> {
			// fantasyfurniture:block/dyeable/cube
			var cubeDyeable = provider
					.models()
			        .withExistingParent("%s:block/dyeable/cube".formatted(Mods.FANTASY_FURNITURE), new ResourceLocation(Mods.MINECRAFT, "block/block"))
					// .renderType(new ResourceLocation(Mods.MINECRAFT, "cutout")) // NOTE: Set on the block in 1.18.2
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 16F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#%s".formatted(face.getSerializedName()))
								.cullface(face)
						)
					.end()
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 16F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#tint_%s".formatted(face.getSerializedName()))
								.cullface(face)
								.tintindex(0)
						)
					.end()
			;

			// fantasyfurniture:block/dyeable/cube_all
			provider.models()
			        .getBuilder("%s:block/dyeable/cube_all".formatted(Mods.FANTASY_FURNITURE))
					.parent(cubeDyeable)
					.texture("particle", "#tint_all")
					.texture("down", "#all")
					.texture("up", "#all")
					.texture("north", "#all")
					.texture("south", "#all")
					.texture("west", "#all")
					.texture("east", "#all")
					.texture("tint_down", "#tint_all")
					.texture("tint_up", "#tint_all")
					.texture("tint_north", "#tint_all")
					.texture("tint_south", "#tint_all")
					.texture("tint_west", "#tint_all")
					.texture("tint_east", "#tint_all")
			;

			// fantasyfurniture:block/dyeable/carpet
			provider.models()
			        .withExistingParent("%s:block/dyeable/carpet".formatted(Mods.FANTASY_FURNITURE), new ResourceLocation(Mods.MINECRAFT, "block/thin_block"))
					.texture("particle", "#tint_wool")
					// .renderType(new ResourceLocation(Mods.MINECRAFT, "cutout"))
			        .element()
						.from(0F, 0F, 0F)
						.to(16F, 1F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#wool")
								.uvs(0F, face.getAxis().isVertical() ? 0F : 15F, 16F, 16F)
								.cullface(face == Direction.UP ? null : face)
						)
					.end()
					.element()
						.from(0F, 0F, 0F)
						.to(16F, 1F, 16F)
						.allFaces((face, builder) -> builder
								.texture("#tint_wool")
								.tintindex(0)
								.uvs(0F, face.getAxis().isVertical() ? 0F : 15F, 16F, 16F)
								.cullface(face == Direction.UP ? null : face)
						)
					.end()
			;
		});
	}

	// region: Constructors
	private static BlockBuilder<BasicRegistrate, BerryBasketBlock, BasicRegistrate> berryBasket(String type)
	{
		return REGISTRATE
				.object("decorations/berry_basket_%s".formatted(type))
				.block(BerryBasketBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Basket" : "%s Basket".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, BoltsOfClothBlock, BasicRegistrate> boltsOfCloth()
	{
		return REGISTRATE
				.object("decorations/bolts_of_cloth")
				.block(BoltsOfClothBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Bolts of Cloth")
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CrownBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> crown(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_crown".formatted(furnitureType))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Crown".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CrownBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> cushionedCrown(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_cushioned_crown".formatted(furnitureType))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Cushioned Crown".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, CandelabraBlock, BasicRegistrate> candelabra(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_candelabra".formatted(furnitureType))
				.block(CandelabraBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Candelabra".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
				.lightLevel(lightLevel())
		;
	}

	private static BlockBuilder<BasicRegistrate, BookStackBlock, BasicRegistrate> bookStack()
	{
		return REGISTRATE
				.object("decorations/book_stack")
				.block(BookStackBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Book Stack")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends FloatingTomesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floatingTomes(BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/floating_tomes")
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Floating Tomes")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, StackablePumpkinsBlock, BasicRegistrate> stackablePumpkins()
	{
		return REGISTRATE
				.object("decorations/stackable_pumpkins")
				.block(StackablePumpkinsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Stackable Pumpkins")
				.initialProperties(Material.WOOD, MaterialColor.COLOR_ORANGE)
				.strength(1F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, BowlBlock, BasicRegistrate> bowl(String type)
	{
		return REGISTRATE
				.object("decorations/bowl_%s".formatted(type))
				.block(BowlBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Bowl" : "%s Bowl".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, CookieJarBlock, BasicRegistrate> cookieJar()
	{
		return REGISTRATE
				.object("decorations/cookie_jar")
				.block(CookieJarBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Cookie Jar")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, BannerBlock, BasicRegistrate> banner(String type)
	{
		return REGISTRATE
				.object("decorations/%s_banner".formatted(type))
				.block(BannerBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Banner".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noCollission()
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, TankardsBlock, BasicRegistrate> tankards(String type)
	{
		return REGISTRATE
				.object("decorations/tankards_%s".formatted(type))
				.block(TankardsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang(type.equals("empty") ? "Tankards" : type.equals("honeymead") ? "Honeyed Tankards" : "%s Tankards".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, MushroomsBlock, BasicRegistrate> mushrooms(String type, MaterialColor color)
	{
		return REGISTRATE
				.object("decorations/mushrooms_%s".formatted(type))
				.block(MushroomsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Mushrooms".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.PLANT, color)
				.sound(SoundType.GRASS)
				.noCollission()
				.randomTicks()
				.instabreak()
				.hasPostProcess(BlockHelper::always)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, CoinStackBlock, BasicRegistrate> coinStack(String type)
	{
		return REGISTRATE
				.object("decorations/coin_stack_%s".formatted(type))
				.block(CoinStackBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Coin Stack".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, MuffinsBlock, BasicRegistrate> muffins(String type)
	{
		return REGISTRATE
				.object("decorations/muffins_%s".formatted(type))
				.block(MuffinsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Muffins".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, PaperStackBlock, BasicRegistrate> paperStack()
	{
		return REGISTRATE
				.object("decorations/paper_stack")
				.block(PaperStackBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Paper Stack")
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WallMirrorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallMirror(String furnitureType, String mirrorType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_wall_mirror_%s".formatted(furnitureType, mirrorType))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Wall Mirror %s".formatted(RegistrateLangProvider.toEnglishName(furnitureType), RegistrateLangProvider.toEnglishName(mirrorType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WallMirrorMultiBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallMirrorMultiBlock(String furnitureType, String mirrorType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_wall_mirror_%s".formatted(furnitureType, mirrorType))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Wall Mirror %s".formatted(RegistrateLangProvider.toEnglishName(furnitureType), RegistrateLangProvider.toEnglishName(mirrorType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, BoiledCremeTreatsBlock, BasicRegistrate> boiledCremeTreats(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_boiled_creme_treats".formatted(furnitureType))
				.block(BoiledCremeTreatsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Boiled Creme Treats")
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, SweetRollsBlock, BasicRegistrate> sweetRolls(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_sweetrolls".formatted(furnitureType))
				.block(SweetRollsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Sweetrolls")
				.initialProperties(Material.CAKE)
				.strength(.5F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, MeadBottlesBlock, BasicRegistrate> meadBottles(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_mead_bottles".formatted(furnitureType))
				.block(MeadBottlesBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Mead Bottles")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends PotionBottlesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> potionBottles(BlockFactory<BLOCK> blockFactory, String suffix)
	{
		return REGISTRATE
				.object("decorations/potion_bottles%s".formatted(suffix))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Potion Bottles")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, SoulGemsBlock, BasicRegistrate> soulGems(String furnitureType, String type)
	{
		return REGISTRATE
				.object("decorations/%s_soul_gems_%s".formatted(furnitureType, type))
				.block(SoulGemsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang(type.equals("dark") ? "Black Soul Gems" : "Soul Gems")
				.initialProperties(Material.GLASS)
				.strength(.3F)
				.sound(SoundType.GLASS)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, GravestoneBlock, BasicRegistrate> gravestone()
	{
		return REGISTRATE
				.object("decorations/gravestone")
				.block(GravestoneBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Gravestone")
				.initialProperties(Material.STONE, MaterialColor.STONE)
				.strength(.3F)
				.sound(SoundType.STONE)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BrewingCauldronBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> brewingCauldron(BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/brewing_cauldron")
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Brewing Cauldron")
				.initialProperties(Material.STONE, MaterialColor.STONE)
				.strength(.3F)
				.sound(SoundType.STONE)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, HangingHerbsBlock, BasicRegistrate> hangingHerbs()
	{
		return REGISTRATE
				.object("decorations/hanging_herbs")
				.block(HangingHerbsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("Hanging Herbs")
				.initialProperties(Material.WOOD, MaterialColor.WOOD)
				.strength(.3F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, ChainBlock, BasicRegistrate> chain(String type)
	{
		return REGISTRATE
				.object("decorations/%s_chain".formatted(type))
				.block(ChainBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Chain".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.METAL, MaterialColor.NONE)
				.strength(5F, 6F)
				.sound(SoundType.CHAIN)
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
					var model = getModelFile(ctx, blockState, provider.models());
					var axis = blockState.getValue(RotatedPillarBlock.AXIS);

					var xRot = 0;
					var yRot = 0;

					if(axis == Direction.Axis.X)
					{
						xRot = 90;
						yRot = 90;
					}
					else if(axis == Direction.Axis.Z)
						xRot = 90;

					return ConfiguredModel
							.builder()
							.modelFile(model)
							.rotationX(xRot)
							.rotationY(yRot)
					.build();
				}))
		;
	}

	private static BlockBuilder<BasicRegistrate, SpiderWebBlock, BasicRegistrate> spiderWebSmall()
	{
		return REGISTRATE
				.object("decorations/spider_web_small")
				.block(SpiderWebBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.noCollission()
				.lang("Spider Web Small")
				.initialProperties(Material.WEB)
				.strength(4F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, SpiderWebMultiBlock, BasicRegistrate> spiderWebWide()
	{
		return REGISTRATE
				.object("decorations/spider_web_wide")
				.block(SpiderWebMultiBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.noCollission()
				.lang("Spider Web Wide")
				.initialProperties(Material.WEB)
				.strength(4F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, FoodBlock, BasicRegistrate> food(String furnitureType, int type)
	{
		return REGISTRATE
				.object("decorations/%s_food_%d".formatted(furnitureType, type))
				.block(FoodBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Food %d".formatted(RegistrateLangProvider.toEnglishName(furnitureType), type + 1))
				.initialProperties(Material.CAKE)
				.strength(2.5F)
				.sound(SoundType.WOOL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, TeaSetBlock, BasicRegistrate> teaSet(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tea_set".formatted(furnitureType))
				.block(TeaSetBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Tea Set".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, TeaCupsBlock, BasicRegistrate> teaCups(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tea_cups".formatted(furnitureType))
				.block(TeaCupsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Tea Cups".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, PlatterBlock, BasicRegistrate> platter(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_platter".formatted(furnitureType))
				.block(PlatterBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Platter".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, WidowBloomBlock, BasicRegistrate> widowBloom(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_widow_bloom".formatted(furnitureType))
				.block(WidowBloomBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("Widowbloom Vase")
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState((ctx, provider) -> provider
						.horizontalBlock(ctx.get(), provider
								.models()
								.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
								.texture("particle", "minecraft:block/basalt_top")
						)
				)
		;
	}

	private static BlockBuilder<BasicRegistrate, TomesBlock, BasicRegistrate> tomes(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_tomes".formatted(furnitureType))
				.block(TomesBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Tomes".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ChalicesBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chalices(String furnitureType, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_chalices".formatted(furnitureType))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Chalices".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, BonePileBlock, BasicRegistrate> bonePile(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_pile".formatted(furnitureType))
				.block(BonePileBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Pile".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.METAL)
				.strength(2.5F)
				.sound(SoundType.METAL)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, PotteryBlock, BasicRegistrate> pottery(String furnitureType, int type)
	{
		return REGISTRATE
				.object("decorations/%s_pottery_%d".formatted(furnitureType, type))
				.block(PotteryBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Pottery %d".formatted(RegistrateLangProvider.toEnglishName(furnitureType), type + 1))
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static BlockBuilder<BasicRegistrate, CandleBlock, BasicRegistrate> candles(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_candles".formatted(furnitureType))
				.block(CandleBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang(furnitureType.equals("bone") ? "Bone Soul Sand Candles" : "%s Candles".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.DECORATION, MaterialColor.SAND)
				.strength(.1F)
				.sound(SoundType.CANDLE)
				.blockState(ModBlocks::horizontalBlockState)
				.tag(BlockTags.Vanilla.CANDLES)
		;
	}

	private static BlockBuilder<BasicRegistrate, SkullBlossomsBlock, BasicRegistrate> skullBlossoms(String furnitureType)
	{
		return REGISTRATE
				.object("decorations/%s_skull_blossoms".formatted(furnitureType))
				.block(SkullBlossomsBlock::new)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.transform(ModBlocks::mineablePickaxe)
				.lang("%s Skull Blossoms".formatted(RegistrateLangProvider.toEnglishName(furnitureType)))
				.initialProperties(Material.DECORATION)
				.strength(2.5F)
				.sound(SoundType.STONE)
				.blockState((ctx, provider) -> provider
						.horizontalBlock(ctx.get(), provider
								.models()
								.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
								.texture("particle", "minecraft:block/basalt_top")
						)
				)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wool(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wool".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyBlockDefaults)
				.transform(ModBlocks::clearMineable)
				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockState((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.cubeAll(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
								)
						)
				)
				.tag(BlockTags.Vanilla.WOOL)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> woolDyeable(String type, BlockFactory<BLOCK> blockFactory)
	{
		return wool(type, blockFactory)
				.transform(ModBlocks::applyDyeable)
				.blockState((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
								.parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Mods.FANTASY_FURNITURE, "block/dyeable/cube_all")))
								.texture("all", new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
								.texture("tint_all", new ResourceLocation(ctx.getId().getNamespace(), "block/%s_tint".formatted(ctx.getId().getPath())))
						)
				)
		;
	}

	private static <BLOCK extends CarpetBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> carpet(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/carpet".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyBlockDefaults)
				.transform(ModBlocks::clearMineable)
				.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
				.strength(.1F)
				.sound(SoundType.WOOL)
				.blockState((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.carpet(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type))
								)
						)
				)
				.tag(BlockTags.Vanilla.CARPETS)
		;
	}

	private static <BLOCK extends CarpetBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> carpetDyeable(String type, BlockFactory<BLOCK> blockFactory)
	{
		return carpet(type, blockFactory)
				.transform(ModBlocks::applyDyeable)
				.blockState((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.getBuilder("%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()))
								.parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Mods.FANTASY_FURNITURE, "block/dyeable/carpet")))
								.texture("wool", new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type)))
								.texture("tint_wool", new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool_tint".formatted(type)))
						)
				)
		;
	}

	private static <BLOCK extends FurnitureWallLightBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wallLight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wall_light".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.lightLevel(lightLevel())
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends FloorLightBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floorLight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/floor_light".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.lightLevel(lightLevel(blockState -> blockState.getOptionalValue(FloorLightBlock.PART).orElse(FloorLightBlock.Part.BOTTOM).isTop()))
				.blockState(ModBlocks::simpleBlockState)
		;
	}

	private static <BLOCK extends TableSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableSmall(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableSmallFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableWide(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableWideFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableLargeBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableLarge(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableLargeBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> tableLargeFancy(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends StoolBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> stool(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/stool".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> cushion(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/cushion".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> floorCushion(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("decorations/%s_floor_cushion".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.lang("%s Floor Cushion".formatted(RegistrateLangProvider.toEnglishName(type)))
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CushionBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> skull(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/skull".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends PaintingSmallBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> paintingSmall(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_small".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends PaintingWideBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> paintingWide(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_wide".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DrawerBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> drawer(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/drawer".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends OvenBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> oven(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/oven".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.STONE)
				.strength(3.5F)
				.sound(SoundType.STONE)
				.lightLevel(blockState -> !BaseBlock.isWaterLogged(blockState) && blockState.getValue(BlockStateProperties.LIT) ? 13 : 0)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends OvenMultiBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> ovenMultiblock(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/oven".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.STONE)
				.strength(3.5F)
				.sound(SoundType.STONE)
				.lightLevel(blockState -> !BaseBlock.isWaterLogged(blockState) && blockState.getValue(BlockStateProperties.LIT) ? 13 : 0)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ShelfBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> shelf(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/shelf".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends SofaBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> sofa(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/sofa".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CounterBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> counter(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/counter".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DeskBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> deskLeft(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_left".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DeskBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> deskRight(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_right".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ChairBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chair(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chair".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BenchBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bench(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bench".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BookshelfBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bookshelf(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bookshelf".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ChestBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chest(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chest".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DresserBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> dresser(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/dresser".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WardrobeBottomBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wardrobeBottom(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_bottom".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WardrobeTopBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> wardrobeTop(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_top".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BedSingleBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bedSingle(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_single".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	private static <BLOCK extends BedDoubleBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> bedDouble(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_double".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockState(ModBlocks::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	private static <BLOCK extends ChandelierBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> chandelier(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chandelier".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.lightLevel(lightLevel())
				.blockState(ModBlocks::simpleBlockState)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> doorSingle(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_single".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(ModBlocks::horizontalBlockState)
				.transform(ModBlocks::applyDoorProperties)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> doorDouble(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_double".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(ModBlocks::horizontalBlockState)
				.transform(ModBlocks::applyDoorProperties)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyDoorProperties(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
					var model = getModelFile(ctx, blockState, provider.models());
					var rightHinge = blockState.getOptionalValue(DoorBlock.HINGE).orElse(DoorHingeSide.LEFT) == DoorHingeSide.RIGHT;
					var yRot = (int) BaseBlock.getFacing(blockState).toYRot();
					var open = blockState.getOptionalValue(DoorBlock.OPEN).orElse(false);

					if(open)
						yRot += 90;
					if(rightHinge && open)
						yRot += 180;

					yRot %= 360;

					return ConfiguredModel
							.builder()
							.modelFile(model)
							.rotationY(yRot)
							.build();
				}))
				.tag(BlockTags.Vanilla.WOODEN_DOORS)
		;
	}

	private static <BLOCK extends LockboxBlock> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> lockbox(String type, BlockFactory<BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/lockbox".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockState(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyBlockDefaults(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.lang(RegistrateLangProvider.toEnglishName(builder.getName().replace('/', '_')))
				.loot(ModBlocks::lootTable)
				.transform(ModBlocks::mineableAxe)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyFurnitureBlockDefaults(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(ModBlocks::applyBlockDefaults)
				.noOcclusion()
				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)
				.renderType(() -> RenderType::cutout)
		;
	}

	private static ToIntFunction<BlockState> lightLevel()
	{
		return lightLevel(Predicates.alwaysTrue());
	}

	private static ToIntFunction<BlockState> lightLevel(Predicate<BlockState> lightLevelPredicate)
	{
		return blockState -> lightLevelPredicate.test(blockState) && !BaseBlock.isWaterLogged(blockState) ? 14 : 0;
	}

	private static <BLOCK extends Block> void simpleBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		provider.getVariantBuilder(ctx.get())
		        .forAllStates(blockState -> ConfiguredModel
				        .builder()
				        .modelFile(getModelFile(ctx, blockState, provider.models()))
				        .build()
		        )
		;
	}

	private static <BLOCK extends Block> void horizontalBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		provider.horizontalBlock(ctx.get(), blockState -> getModelFile(ctx, blockState, provider.models()));
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> applyDyeable(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder.color(() -> () -> (blockstate, level, pos, tintIndex) -> tintIndex == (blockstate.getBlock() instanceof DyeableBlock || blockstate.getBlock() instanceof DyeableCarpetBlock ? 0 : 1) ? IDyeable.getDyeColor(blockstate).map(IDyeable::tintFromDyeColor).orElse(-1) : -1);
	}

	private static <BLOCK extends Block> BlockModelBuilder getModelFile(DataGenContext<Block, BLOCK> ctx, BlockState blockState, BlockModelProvider provider)
	{
		var suffix = "";

		if(ctx.get() instanceof StackedBlock block)
			suffix = "_%d".formatted(blockState.getValue(block.getStackSizeProperty()));
		else if(ctx.get() instanceof ShelfBlock)
			suffix = "_%s".formatted(blockState.getValue(ShelfBlock.CONNECTION).getSerializedName());
		else if(ctx.get() instanceof SofaBlock)
			suffix = "_%s".formatted(blockState.getValue(SofaBlock.CONNECTION).getSerializedName());
		else if(ctx.get() instanceof CounterBlock)
			suffix = "_%s".formatted(blockState.getValue(CounterBlock.CONNECTION).getSerializedName());
		else if(DUNMER_BED_DOUBLE.is(ctx.get()) || DUNMER_BED_SINGLE.is(ctx.get()))
			suffix = ((IMultiBlock) ctx.get()).isMultiBlockOrigin(blockState) ? "_head" : "_foot";
		else if(ctx.get() instanceof FurnitureDoorBlock)
		{
			var hinge = blockState.getValue(FurnitureDoorBlock.HINGE);
			var open = blockState.getValue(FurnitureDoorBlock.OPEN);

			if(open)
				suffix = hinge == DoorHingeSide.RIGHT ? "_left" : "_right";
			else
				suffix = hinge == DoorHingeSide.RIGHT ? "_right" : "_left";
		}
		else if(DUNMER_OVEN.is(ctx.get()) && blockState.getValue(BlockStateProperties.LIT))
			suffix = "_lit";
		else if(ctx.get() instanceof CookieJarBlock)
			suffix = "_%s".formatted(blockState.getValue(CookieJarBlock.FILL_STAGE).serializedName);

		return provider
				.withExistingParent(
						// <namespace>:generated/block/<path>[suffix] | Model we are generating
						"%s:generated/block/%s%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath(), suffix),
						// <namespace>:block/<path>[suffix] | Existing model, exported from BlockBench
						new ResourceLocation(ctx.getId().getNamespace(), "block/%s%s".formatted(ctx.getId().getPath(), suffix))
				)
				// .renderType(new ResourceLocation(Mods.MINECRAFT, "cutout"))
				.texture("particle", getParticlePath(ctx.getId()))
				.texture(getTextureKey(ctx.getId()), getTexturePath(ctx.getId()))
		;
	}

	private static ResourceLocation getParticlePath(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		var type = path.substring(0, path.indexOf('/'));

		if(type.equals("decorations"))
		{
			var name = path.substring(type.length() + 1);

			if(name.startsWith("berry_basket"))
				name = "berry_basket";
			else if(name.startsWith("bowl"))
				name = "bowl";
			else if(name.startsWith("tankards"))
				name = "tankards";
			else if(name.startsWith("venthyr"))
				name = "venthyr";
			else if(name.startsWith("dunmer"))
				return new ResourceLocation(registryName.getNamespace(), "particles/dunmer");
			else if(name.startsWith("bone"))
			{
				if(name.contains("wither"))
					return new ResourceLocation(registryName.getNamespace(), "particles/bone_wither");
				return new ResourceLocation(registryName.getNamespace(), "particles/bone");
			}
			else if(name.startsWith("royal"))
				return new ResourceLocation(registryName.getNamespace(), "particles/royal");
			else if(name.startsWith("necrolord"))
				return new ResourceLocation(registryName.getNamespace(), "particles/necrolord");
			else if(name.startsWith("spider_web"))
				return new ResourceLocation(registryName.getNamespace(), "particles/decorations/spider_webs");
			else if(name.startsWith("potion_bottles"))
				return new ResourceLocation(registryName.getNamespace(), "particles/decorations/potion_bottles");

			return new ResourceLocation(registryName.getNamespace(), "particles/%s/%s".formatted(type, name));
		}
		else if(type.equals("bone"))
		{
			var subType = path.substring(type.length() + 1);

			if(subType.contains("wither"))
				return new ResourceLocation(registryName.getNamespace(), "particles/bone_wither");
			return new ResourceLocation(registryName.getNamespace(), "particles/bone");
		}

		return new ResourceLocation(registryName.getNamespace(), "particles/%s".formatted(type));
	}

	private static <BLOCK extends Block> void lootTable(RegistrateBlockLootTables lootTables, BLOCK block)
	{
		final AtomicReference<LootPoolEntryContainer.Builder<?>> item = new AtomicReference<>(LootItem.lootTableItem(block));

		final AtomicReference<LootPool.Builder> pool = new AtomicReference<>( RegistrateBlockLootTables
				.applyExplosionCondition(block, LootPool.lootPool())
				.setRolls(ConstantValue.exactly(1F))
		);

		if(block instanceof StackedBlock stacked)
		{
			for(var value : stacked.getStackSizeProperty().getPossibleValues())
			{
				pool.getAndUpdate(builder -> builder.apply(SetItemCountFunction
						.setCount(ConstantValue.exactly(value + 1))
						.when(LootItemBlockStatePropertyCondition
								.hasBlockStateProperties(block)
								.setProperties(StatePropertiesPredicate.Builder
										.properties()
										.hasProperty(stacked.getStackSizeProperty(), value)
								)
						)
				));
			}
		}

		if(block instanceof FurnitureDoorBlock)
		{
			item.getAndUpdate(builder -> builder.when(LootItemBlockStatePropertyCondition
					.hasBlockStateProperties(block)
					.setProperties(StatePropertiesPredicate.Builder
							.properties()
							.hasProperty(FurnitureDoorBlock.HALF, DoubleBlockHalf.LOWER)
					)
			));
		}

		if(block instanceof IDyeable)
		{
			pool.getAndUpdate(builder -> builder.apply(CopyBlockState
					.copyState(block)
					.copy(IDyeable.BLOCKSTATE_PROPERTY)
			));
		}

		lootTables.add(block, LootTable.lootTable().withPool(pool.get().add(item.get())));
	}

	static String getTextureKey(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		return path.substring(path.lastIndexOf('/') + 1);
	}

	static ResourceLocation getTexturePath(ResourceLocation registryName)
	{
		var path = registryName.getPath();
		var type = path.substring(0, path.lastIndexOf('/'));
		var name = path.substring(type.length() + 1);

		name = switch(name) {
			case "desk_left", "desk_right" -> "desk";
			case "wardrobe_bottom", "wardrobe_top" -> "wardrobe";
			default -> name;
		};

		return new ResourceLocation(registryName.getNamespace(), "models/%s/%s".formatted(type, name));
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> clearMineable(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder.removeTag(
				BlockTags.Vanilla.MINEABLE_WITH_PICKAXE,
				BlockTags.Vanilla.MINEABLE_WITH_AXE,
				BlockTags.Vanilla.MINEABLE_WITH_SHOVEL,
				BlockTags.Vanilla.MINEABLE_WITH_HOE
		);
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> mineablePickaxe(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(ModBlocks::clearMineable)
				.tag(BlockTags.Vanilla.MINEABLE_WITH_PICKAXE)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> mineableAxe(BlockBuilder<BasicRegistrate, BLOCK, BasicRegistrate> builder)
	{
		return builder
				.transform(ModBlocks::clearMineable)
				.tag(BlockTags.Vanilla.MINEABLE_WITH_AXE)
		;
	}
	// endregion
}
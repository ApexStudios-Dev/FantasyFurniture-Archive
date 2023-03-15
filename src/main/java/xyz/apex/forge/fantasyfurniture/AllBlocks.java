package xyz.apex.forge.fantasyfurniture;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.registrate.entry.BlockEntry;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.common.block.FurnitureStationBlock;
import xyz.apex.forge.fantasyfurniture.common.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.common.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.*;
import xyz.apex.forge.fantasyfurniture.core.registrate.BlockTransformers;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;
import static xyz.apex.forge.fantasyfurniture.core.registrate.BlockBuilders.*;

public interface AllBlocks
{
	// region: Decorations
	BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY = berryBasket("empty").register();
	BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = berryBasket("sweetberry").register();
	BlockEntry<BerryBasketBlock> BERRY_BASKET_BLUEBERRY = berryBasket("blueberry").register();
	BlockEntry<BerryBasketBlock> BERRY_BASKET_STRAWBERRYBERRY = berryBasket("strawberry").register();
	BlockEntry<BoltsOfClothBlock> BOLTS_OF_CLOTH = boltsOfCloth().register();
	BlockEntry<BookStackBlock> BOOK_STACK = bookStack().register();
	BlockEntry<BowlBlock> BOWL_EMPTY = bowl("empty").register();
	BlockEntry<BowlBlock> BOWL_BERRTROOT_SOUP = bowl("beetroot_soup").register();
	BlockEntry<BowlBlock> BOWL_MUSHROOM_STEW = bowl("mushroom_stew").register();
	BlockEntry<TankardsBlock> TANKARD_EMPTY = tankards("empty").register();
	BlockEntry<TankardsBlock> TANKARD_HONEYMEAD = tankards("honeymead").register();
	BlockEntry<TankardsBlock> TANKARD_MILK = tankards("milk").register();
	BlockEntry<TankardsBlock> TANKARD_SWEETBERRY = tankards("sweetberry").register();
	BlockEntry<MushroomsBlock> MUSHROOMS_RED = mushrooms("red", MaterialColor.COLOR_RED).register();
	BlockEntry<CoinStackBlock> COIN_STOCK_GOLD = coinStack("gold").register();
	BlockEntry<CoinStackBlock> COIN_STOCK_IRON = coinStack("iron").register();
	BlockEntry<MuffinsBlock> MUFFINS_BLUEBERRY = muffins("blueberry").register();
	BlockEntry<MuffinsBlock> MUFFINS_CHOCOLATE = muffins("chocolate").register();
	BlockEntry<MuffinsBlock> MUFFINS_SWEETBERRY = muffins("sweetberry").register();
	BlockEntry<PaperStackBlock> PAPER_STACK = paperStack().register();
	BlockEntry<CookieJarBlock> COOKIE_JAR = cookieJar().register();
	BlockEntry<BrewingCauldronBlock.Dyeable> BREWING_CAULDRON = brewingCauldron(BrewingCauldronBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<FloatingTomesBlock.Dyeable> FLOATING_TOMES = floatingTomes(FloatingTomesBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<GravestoneBlock> GRAVESTONE = gravestone().register();
	BlockEntry<HangingHerbsBlock> HANGING_HERBS = hangingHerbs().register();
	BlockEntry<SpiderWebBlock> SPIDER_WEB_SMALL = spiderWebSmall().register();
	BlockEntry<SpiderWebMultiBlock> SPIDER_WEB_WIDE = spiderWebWide().register();
	BlockEntry<StackablePumpkinsBlock> STACKABLE_PUMPKINS = stackablePumpkins().register();
	BlockEntry<ChainBlock> BRONZE_CHAIN = chain("bronze").register();
	BlockEntry<MushroomsBlock> MUSHROOMS_BROWN = mushrooms("brown", MaterialColor.COLOR_BROWN).register();
	BlockEntry<PotionBottlesBlock> POTION_BOTTLES = potionBottles(PotionBottlesBlock::new, "").register();
	BlockEntry<PotionBottlesBlock.Dyeable> DYEABLE_POTION_BOTTLES = potionBottles(PotionBottlesBlock.Dyeable::new, "_dyeable").transform(BlockTransformers::applyDyeable).register();
	BlockEntry<FairyLightsBlock.Dyeable> FAIRY_LIGHTS_1 = fairyLights(FairyLightsBlock.Dyeable::new, 1).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<FairyLightsBlock> FAIRY_LIGHTS_2 = fairyLights(FairyLightsBlock::new, 2).register();
	BlockEntry<PresentsStackBlock> PRESENTS_STACK_1 = presentsStack(PresentsStackBlock::new, 1).register();
	BlockEntry<PresentsStackBlock.Dyeable> PRESENTS_STACK_2 = presentsStack(PresentsStackBlock.Dyeable::new, 2).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<SnowballsBlock> SNOWBALLS = snowballs().register();
	BlockEntry<StockingBlock> STOCKING = stocking().transform(BlockTransformers::applyDyeable).register();

	// region: Nordic
	BlockEntry<BoiledCremeTreatsBlock> NORDIC_BOILED_CREME_TREATS = boiledCremeTreats("nordic").register();
	BlockEntry<SweetRollsBlock> NORDIC_SWEETROLLS = sweetRolls("nordic").register();
	BlockEntry<MeadBottlesBlock> NORDIC_MEAD_BOTTLES = meadBottles("nordic").register();
	BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_LIGHT = soulGems("nordic", "light").register();
	BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_DARK = soulGems("nordic", "dark").register();
	// endregion

	// region: Venthyr
	BlockEntry<FoodBlock> VENTHYR_FOOD_0 = food("venthyr", 0).register();
	BlockEntry<FoodBlock> VENTHYR_FOOD_1 = food("venthyr", 1).register();
	BlockEntry<TeaSetBlock> VENTHYR_TEA_SET = teaSet("venthyr").register();
	BlockEntry<TeaCupsBlock> VENTHYR_TEA_CUPS = teaCups("venthyr").register();
	BlockEntry<PlatterBlock> VENTHYR_PLATTER = platter("venthyr").register();
	BlockEntry<WidowBloomBlock> VENTHYR_WIDOW_BLOOM = widowBloom("venthyr").register();
	BlockEntry<TomesBlock> VENTHYR_TOMES = tomes("venthyr").register();
	BlockEntry<ChalicesBlock> VENTHYR_CHALICES = chalices("venthyr", ChalicesBlock::new).register();
	BlockEntry<CandleBlock> VENTHYR_CANDLES = candles("venthyr").register();
	BlockEntry<BannerBlock> VENTHYR_BANNER = banner("venthyr").register();
	// endregion

	// region: Dunmer
	BlockEntry<PotteryBlock> DUNMER_POTTERY_0 = pottery("dunmer", 0).register();
	BlockEntry<PotteryBlock> DUNMER_POTTERY_1 = pottery("dunmer", 1).register();
	// endregion

	// region: Bone
	BlockEntry<CandleBlock> BONE_CANDLES = candles("bone").register();

	// region: Skeleton
	BlockEntry<ChalicesBlock> BONE_SKELETON_CHALICES = chalices("bone_skeleton", ChalicesBlock::new).lang("Bone Chalices").register();
	BlockEntry<BonePileBlock> BONE_SKELETON_PILE = bonePile("bone_skeleton").lang("Bone Pile").register();
	BlockEntry<SkullBlossomsBlock> BONE_SKELETON_SKULL_BLOSSOMS = skullBlossoms("bone_skeleton").lang("Skull Blossoms").register();
	// endregion

	// region: Wither
	BlockEntry<ChalicesBlock> BONE_WITHER_CHALICES = chalices("bone_wither", ChalicesBlock::new).lang("Wither Bone Chalices").register();
	BlockEntry<BonePileBlock> BONE_WITHER_PILE = bonePile("bone_wither").lang("Wither Bone Pile").register();
	BlockEntry<SkullBlossomsBlock> BONE_WITHER_SKULL_BLOSSOMS = skullBlossoms("bone_wither").lang("Wither Skull Blossoms").register();
	// endregion
	// endregion

	// region: Royal
	BlockEntry<CrownBlock.Dyeable> ROYAL_CROWN = crown("royal", CrownBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<CandelabraBlock> ROYAL_CANDELABRA = candelabra("royal").register();
	BlockEntry<ChalicesBlock.Dyeable> ROYAL_CHALICES = chalices("royal", ChalicesBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<CrownBlock.Dyeable> ROYAL_CUSHIONED_CROWN = cushionedCrown("royal", CrownBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<FoodBlock> ROYAL_FOOD_0 = food("royal", 0).register();
	BlockEntry<FoodBlock> ROYAL_FOOD_1 = food("royal", 1).register();
	BlockEntry<PlatterBlock> ROYAL_PLATTER = platter("royal").register();
	BlockEntry<CushionBlock.Dyeable> ROYAL_FLOOR_CUSHION = floorCushion("royal", CushionBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<WallMirrorBlock.Dyeable> ROYAL_WALL_MIRROR_LARGE = wallMirror("royal", "small", WallMirrorBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<WallMirrorMultiBlock.Dyeable> ROYAL_WALL_MIRROR_SMALL = wallMirrorMultiBlock("royal", "large", WallMirrorMultiBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	// endregion

	// region: Necrolord
	BlockEntry<CandelabraBlock> NECROLORD_CANDELABRA = candelabra("necrolord").register();
	//endregion
	// endregion

	// region: Furniture Sets
	// region: Nordic
	BlockEntry<Block> NORDIC_WOOL = wool("nordic", Block::new).register();
	BlockEntry<CarpetBlock> NORDIC_CARPET = carpet("nordic", CarpetBlock::new).register();
	BlockEntry<FurnitureWallLightBlock> NORDIC_WALL_LIGHT = wallLight("nordic", FurnitureWallLightBlock::new).register();
	BlockEntry<FloorLightBlock> NORDIC_FLOOR_LIGHT = floorLight("nordic", FloorLightBlock::new).register();
	BlockEntry<TableSmallBlock> NORDIC_TABLE_SMALL = tableSmall("nordic", TableSmallBlock::new).register();
	BlockEntry<TableWideBlock> NORDIC_TABLE_WIDE = tableWide("nordic", TableWideBlock::new).register();
	BlockEntry<TableLargeBlock> NORDIC_TABLE_LARGE = tableLarge("nordic", TableLargeBlock::new).register();
	BlockEntry<StoolBlock> NORDIC_STOOL = stool("nordic", StoolBlock::new).register();
	BlockEntry<CushionBlock> NORDIC_CUSHION = cushion("nordic", CushionBlock::new).register();
	BlockEntry<PaintingSmallBlock> NORDIC_PAINTING_SMALL = paintingSmall("nordic", PaintingSmallBlock::new).register();
	BlockEntry<PaintingWideBlock> NORDIC_PAINTING_WIDE = paintingWide("nordic", PaintingWideBlock::new).register();
	BlockEntry<DrawerBlock> NORDIC_DRAWER = drawer("nordic", DrawerBlock::new).register();
	BlockEntry<ShelfBlock> NORDIC_SHELF = shelf("nordic", ShelfBlock::new).register();
	BlockEntry<SofaBlock> NORDIC_SOFA = sofa("nordic", SofaBlock::new).register();
	BlockEntry<DeskBlock> NORDIC_DESK_LEFT = deskLeft("nordic", DeskBlock::new).register();
	BlockEntry<DeskBlock> NORDIC_DESK_RIGHT = deskRight("nordic", DeskBlock::new).register();
	BlockEntry<ChairBlock.OriginOnly> NORDIC_CHAIR = chair("nordic", ChairBlock.OriginOnly::new).register();
	BlockEntry<BenchBlock> NORDIC_BENCH = bench("nordic", BenchBlock::new).register();
	BlockEntry<BookshelfBlock> NORDIC_BOOKSHELF = bookshelf("nordic", BookshelfBlock::new).register();
	BlockEntry<ChestBlock> NORDIC_CHEST = chest("nordic", ChestBlock::new).register();
	BlockEntry<DresserBlock> NORDIC_DRESSER = dresser("nordic", DresserBlock::new).register();
	BlockEntry<WardrobeBottomBlock> NORDIC_WARDROBE_BOTTOM = wardrobeBottom("nordic", WardrobeBottomBlock::new).register();
	BlockEntry<WardrobeTopBlock> NORDIC_WARDROBE_TOP = wardrobeTop("nordic", WardrobeTopBlock::new).register();
	BlockEntry<BedSingleBlock> NORDIC_BED_SINGLE = bedSingle("nordic", BedSingleBlock::new).register();
	BlockEntry<BedDoubleBlock> NORDIC_BED_DOUBLE = bedDouble("nordic", BedDoubleBlock::new).register();
	BlockEntry<ChandelierBlock> NORDIC_CHANDELIER = chandelier("nordic", ChandelierBlock::new).register();
	BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_SINGLE = doorSingle("nordic", FurnitureDoorBlock::new).register();
	BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_DOUBLE = doorDouble("nordic", FurnitureDoorBlock::new).register();
	BlockEntry<LockboxBlock> NORDIC_LOCKBOX = lockbox("nordic", LockboxBlock::new).register();
	BlockEntry<CounterBlock> NORDIC_COUNTER = counter("nordic", CounterBlock::new).register();
	BlockEntry<OvenBlock> NORDIC_OVEN = oven("nordic", OvenBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	// endregion

	// region: Dunmer
	BlockEntry<Block> DUNMER_WOOL = wool("dunmer", Block::new).register();
	BlockEntry<CarpetBlock> DUNMER_CARPET = carpet("dunmer", CarpetBlock::new).register();
	BlockEntry<FurnitureWallLightBlock> DUNMER_WALL_LIGHT = wallLight("dunmer", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	BlockEntry<FloorLightBlock> DUNMER_FLOOR_LIGHT = floorLight("dunmer", FloorLightBlock::new).register();
	BlockEntry<TableSmallBlock> DUNMER_TABLE_SMALL = tableSmall("dunmer", TableSmallBlock::new).register();
	BlockEntry<TableWideBlock> DUNMER_TABLE_WIDE = tableWide("dunmer", TableWideBlock::new).register();
	BlockEntry<TableLargeBlock> DUNMER_TABLE_LARGE = tableLarge("dunmer", TableLargeBlock::new).register();
	BlockEntry<StoolBlock> DUNMER_STOOL = stool("dunmer", StoolBlock::new).register();
	BlockEntry<CushionBlock> DUNMER_CUSHION = cushion("dunmer", CushionBlock::new).register();
	BlockEntry<PaintingSmallBlock> DUNMER_PAINTING_SMALL = paintingSmall("dunmer", PaintingSmallBlock::new).register();
	BlockEntry<PaintingWideBlock> DUNMER_PAINTING_WIDE = paintingWide("dunmer", PaintingWideBlock::new).register();
	BlockEntry<DrawerBlock> DUNMER_DRAWER = drawer("dunmer", DrawerBlock::new).register();
	BlockEntry<ShelfBlock> DUNMER_SHELF = shelf("dunmer", ShelfBlock::new).register();
	BlockEntry<SofaBlock> DUNMER_SOFA = sofa("dunmer", SofaBlock::new).register();
	BlockEntry<DeskBlock> DUNMER_DESK_LEFT = deskLeft("dunmer", DeskBlock::new).register();
	BlockEntry<DeskBlock> DUNMER_DESK_RIGHT = deskRight("dunmer", DeskBlock::new).register();
	BlockEntry<ChairBlock.OriginOnly> DUNMER_CHAIR = chair("dunmer", ChairBlock.OriginOnly::new).register();
	BlockEntry<BenchBlock> DUNMER_BENCH = bench("dunmer", BenchBlock::new).register();
	BlockEntry<BookshelfBlock> DUNMER_BOOKSHELF = bookshelf("dunmer", BookshelfBlock::new).register();
	BlockEntry<ChestBlock> DUNMER_CHEST = chest("dunmer", ChestBlock::new).register();
	BlockEntry<DresserBlock> DUNMER_DRESSER = dresser("dunmer", DresserBlock::new).register();
	BlockEntry<WardrobeBottomBlock> DUNMER_WARDROBE_BOTTOM = wardrobeBottom("dunmer", WardrobeBottomBlock::new).register();
	BlockEntry<WardrobeTopBlock> DUNMER_WARDROBE_TOP = wardrobeTop("dunmer", WardrobeTopBlock::new).register();
	BlockEntry<BedSingleBlock> DUNMER_BED_SINGLE = bedSingle("dunmer", BedSingleBlock::new).register();
	BlockEntry<BedDoubleBlock> DUNMER_BED_DOUBLE = bedDouble("dunmer", BedDoubleBlock::new).register();
	BlockEntry<ChandelierBlock> DUNMER_CHANDELIER = chandelier("dunmer", ChandelierBlock::new).register();
	BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_SINGLE = doorSingle("dunmer", FurnitureDoorBlock::new).register();
	BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_DOUBLE = doorDouble("dunmer", FurnitureDoorBlock::new).register();
	BlockEntry<LockboxBlock> DUNMER_LOCKBOX = lockbox("dunmer", LockboxBlock::new).register();
	BlockEntry<CounterBlock> DUNMER_COUNTER = counter("dunmer", CounterBlock::new).register();
	BlockEntry<OvenMultiBlock> DUNMER_OVEN = ovenMultiblock("dunmer", OvenMultiBlock::new).lang("Dunmer Cooking Spit").register();
	// endregion

	// region: Venthyr
	BlockEntry<Block> VENTHYR_WOOL = wool("venthyr", Block::new).register();
	BlockEntry<CarpetBlock> VENTHYR_CARPET = carpet("venthyr", CarpetBlock::new).register();
	BlockEntry<FurnitureWallLightBlock> VENTHYR_WALL_LIGHT = wallLight("venthyr", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	BlockEntry<FloorLightBlock> VENTHYR_FLOOR_LIGHT = floorLight("venthyr", FloorLightBlock::new).register();
	BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL = tableSmall("venthyr", TableSmallBlock::new).register();
	BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL_FANCY = tableSmallFancy("venthyr", TableSmallBlock::new).register();
	BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE = tableWide("venthyr", TableWideBlock::new).register();
	BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE_FANCY = tableWideFancy("venthyr", TableWideBlock::new).register();
	BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE = tableLarge("venthyr", TableLargeBlock::new).register();
	BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE_FANCY = tableLargeFancy("venthyr", TableLargeBlock::new).register();
	BlockEntry<StoolBlock> VENTHYR_STOOL = stool("venthyr", StoolBlock::new).register();
	BlockEntry<CushionBlock> VENTHYR_CUSHION = cushion("venthyr", CushionBlock::new).register();
	BlockEntry<PaintingSmallBlock> VENTHYR_PAINTING_SMALL = paintingSmall("venthyr", PaintingSmallBlock::new).register();
	BlockEntry<PaintingWideBlock> VENTHYR_PAINTING_WIDE = paintingWide("venthyr", PaintingWideBlock::new).register();
	BlockEntry<DrawerBlock> VENTHYR_DRAWER = drawer("venthyr", DrawerBlock::new).register();
	BlockEntry<ShelfBlock> VENTHYR_SHELF = shelf("venthyr", ShelfBlock::new).register();
	BlockEntry<SofaBlock> VENTHYR_SOFA = sofa("venthyr", SofaBlock::new).register();
	BlockEntry<DeskBlock> VENTHYR_DESK_LEFT = deskLeft("venthyr", DeskBlock::new).register();
	BlockEntry<DeskBlock> VENTHYR_DESK_RIGHT = deskRight("venthyr", DeskBlock::new).register();
	BlockEntry<ChairBlock.OriginOnly> VENTHYR_CHAIR = chair("venthyr", ChairBlock.OriginOnly::new).register();
	BlockEntry<BenchBlock> VENTHYR_BENCH = bench("venthyr", BenchBlock::new).register();
	BlockEntry<BookshelfBlock> VENTHYR_BOOKSHELF = bookshelf("venthyr", BookshelfBlock::new).register();
	BlockEntry<ChestBlock> VENTHYR_CHEST = chest("venthyr", ChestBlock::new).register();
	BlockEntry<DresserBlock> VENTHYR_DRESSER = dresser("venthyr", DresserBlock::new).register();
	BlockEntry<WardrobeBottomBlock> VENTHYR_WARDROBE_BOTTOM = wardrobeBottom("venthyr", WardrobeBottomBlock::new).register();
	BlockEntry<WardrobeTopBlock> VENTHYR_WARDROBE_TOP = wardrobeTop("venthyr", WardrobeTopBlock::new).register();
	BlockEntry<BedSingleBlock> VENTHYR_BED_SINGLE = bedSingle("venthyr", BedSingleBlock::new).register();
	BlockEntry<BedDoubleBlock> VENTHYR_BED_DOUBLE = bedDouble("venthyr", BedDoubleBlock::new).register();
	BlockEntry<ChandelierBlock> VENTHYR_CHANDELIER = chandelier("venthyr", ChandelierBlock::new).register();
	BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_SINGLE = doorSingle("venthyr", FurnitureDoorBlock::new).register();
	BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_DOUBLE = doorDouble("venthyr", FurnitureDoorBlock::new).register();
	BlockEntry<LockboxBlock> VENTHYR_LOCKBOX = lockbox("venthyr", LockboxBlock::new).register();
	BlockEntry<CounterBlock> VENTHYR_COUNTER = counter("venthyr", CounterBlock::new).register();
	BlockEntry<OvenBlock> VENTHYR_OVEN = oven("venthyr", OvenBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	// endregion

	// region: Bone
	// region: Skeleton
	BlockEntry<Block> BONE_SKELETON_WOOL = wool("bone/skeleton", Block::new).lang("Bone Wool").register();
	BlockEntry<CarpetBlock> BONE_SKELETON_CARPET = carpet("bone/skeleton", CarpetBlock::new).lang("Bone Carpet").register();
	BlockEntry<FurnitureWallLightBlock> BONE_SKELETON_WALL_LIGHT = wallLight("bone/skeleton", FurnitureWallLightBlock::new).lang("Bone Wall Light").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FloorLightBlock.WithFacing> BONE_SKELETON_FLOOR_LIGHT = floorLight("bone/skeleton", FloorLightBlock.WithFacing::new).lang("Bone Floor Light").blockState(BlockTransformers::horizontalBlockState).transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableSmallBlock> BONE_SKELETON_TABLE_SMALL = tableSmall("bone/skeleton", TableSmallBlock::new).lang("Bone Table Small").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableWideBlock> BONE_SKELETON_TABLE_WIDE = tableWide("bone/skeleton", TableWideBlock::new).lang("Bone Table Wide").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableLargeBlock> BONE_SKELETON_TABLE_LARGE = tableLarge("bone/skeleton", TableLargeBlock::new).lang("Bone Table Large").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<StoolBlock> BONE_SKELETON_STOOL = stool("bone/skeleton", StoolBlock::new).lang("Bone Stool").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<CushionBlock> BONE_SKELETON_SKULL = skull("bone/skeleton", CushionBlock::new).lang("Bone Skull").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<PaintingSmallBlock> BONE_SKELETON_PAINTING_SMALL = paintingSmall("bone/skeleton", PaintingSmallBlock::new).lang("Bone Painting Small").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<PaintingWideBlock> BONE_SKELETON_PAINTING_WIDE = paintingWide("bone/skeleton", PaintingWideBlock::new).lang("Bone Painting Wide").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DrawerBlock> BONE_SKELETON_DRAWER = drawer("bone/skeleton", DrawerBlock::new).lang("Bone Drawer").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ShelfBlock> BONE_SKELETON_SHELF = shelf("bone/skeleton", ShelfBlock::new).lang("Bone Shelf").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<SofaBlock> BONE_SKELETON_SOFA = sofa("bone/skeleton", SofaBlock::new).lang("Bone Sofa").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DeskBlock> BONE_SKELETON_DESK_LEFT = deskLeft("bone/skeleton", DeskBlock::new).lang("Bone Desk Left").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DeskBlock> BONE_SKELETON_DESK_RIGHT = deskRight("bone/skeleton", DeskBlock::new).lang("Bone Desk Right").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChairBlock.OriginOnly> BONE_SKELETON_CHAIR = chair("bone/skeleton", ChairBlock.OriginOnly::new).lang("Bone Chair").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BenchBlock> BONE_SKELETON_BENCH = bench("bone/skeleton", BenchBlock::new).lang("Bone Bench").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BookshelfBlock> BONE_SKELETON_BOOKSHELF = bookshelf("bone/skeleton", BookshelfBlock::new).lang("Bone Bookshelf").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChestBlock> BONE_SKELETON_CHEST = chest("bone/skeleton", ChestBlock::new).lang("Bone Chest").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DresserBlock> BONE_SKELETON_DRESSER = dresser("bone/skeleton", DresserBlock::new).lang("Bone Dresser").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<WardrobeBottomBlock> BONE_SKELETON_WARDROBE_BOTTOM = wardrobeBottom("bone/skeleton", WardrobeBottomBlock::new).lang("Bone Wardrobe Bottom").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<WardrobeTopBlock> BONE_SKELETON_WARDROBE_TOP = wardrobeTop("bone/skeleton", WardrobeTopBlock::new).lang("Bone Wardrobe Top").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BedSingleBlock> BONE_SKELETON_BED_SINGLE = bedSingle("bone/skeleton", BedSingleBlock::new).lang("Bone Bed Single").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BedDoubleBlock> BONE_SKELETON_BED_DOUBLE = bedDouble("bone/skeleton", BedDoubleBlock::new).lang("Bone Bed Double").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChandelierBlock> BONE_SKELETON_CHANDELIER = chandelier("bone/skeleton", ChandelierBlock::new).lang("Bone Chandelier").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_SINGLE = doorSingle("bone/skeleton", FurnitureDoorBlock::new).lang("Bone Door Single").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_DOUBLE = doorDouble("bone/skeleton", FurnitureDoorBlock::new).lang("Bone Door Double").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<LockboxBlock> BONE_SKELETON_LOCKBOX = lockbox("bone/skeleton", LockboxBlock::new).lang("Bone Lockbox").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<CounterBlock> BONE_SKELETON_COUNTER = counter("bone/skeleton", CounterBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<OvenBlock> BONE_SKELETON_OVEN = oven("bone/skeleton", OvenBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	// endregion

	// region: Wither
	BlockEntry<Block> BONE_WITHER_WOOL = wool("bone/wither", Block::new).lang("Wither Bone Wool").register();
	BlockEntry<CarpetBlock> BONE_WITHER_CARPET = carpet("bone/wither", CarpetBlock::new).lang("Wither Bone Carpet").register();
	BlockEntry<FurnitureWallLightBlock> BONE_WITHER_WALL_LIGHT = wallLight("bone/wither", FurnitureWallLightBlock::new).lang("Wither Bone Wall Light").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FloorLightBlock.WithFacing> BONE_WITHER_FLOOR_LIGHT = floorLight("bone/wither", FloorLightBlock.WithFacing::new).lang("Wither Bone Floor Light").blockState(BlockTransformers::horizontalBlockState).transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableSmallBlock> BONE_WITHER_TABLE_SMALL = tableSmall("bone/wither", TableSmallBlock::new).lang("Wither Bone Table Small").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableWideBlock> BONE_WITHER_TABLE_WIDE = tableWide("bone/wither", TableWideBlock::new).lang("Wither Bone Table Wide").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<TableLargeBlock> BONE_WITHER_TABLE_LARGE = tableLarge("bone/wither", TableLargeBlock::new).lang("Wither Bone Table Large").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<StoolBlock> BONE_WITHER_STOOL = stool("bone/wither", StoolBlock::new).lang("Wither Bone Stool").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<CushionBlock> BONE_WITHER_SKULL = skull("bone/wither", CushionBlock::new).lang("Wither Bone Skull").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<PaintingSmallBlock> BONE_WITHER_PAINTING_SMALL = paintingSmall("bone/wither", PaintingSmallBlock::new).lang("Wither Bone Painting Small").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<PaintingWideBlock> BONE_WITHER_PAINTING_WIDE = paintingWide("bone/wither", PaintingWideBlock::new).lang("Wither Bone Painting Wide").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DrawerBlock> BONE_WITHER_DRAWER = drawer("bone/wither", DrawerBlock::new).lang("Wither Bone Drawer").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ShelfBlock> BONE_WITHER_SHELF = shelf("bone/wither", ShelfBlock::new).lang("Wither Bone Shelf").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<SofaBlock> BONE_WITHER_SOFA = sofa("bone/wither", SofaBlock::new).lang("Wither Bone Sofa").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DeskBlock> BONE_WITHER_DESK_LEFT = deskLeft("bone/wither", DeskBlock::new).lang("Wither Bone Desk Left").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DeskBlock> BONE_WITHER_DESK_RIGHT = deskRight("bone/wither", DeskBlock::new).lang("Wither Bone Desk Right").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChairBlock.OriginOnly> BONE_WITHER_CHAIR = chair("bone/wither", ChairBlock.OriginOnly::new).lang("Wither Bone Chair").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BenchBlock> BONE_WITHER_BENCH = bench("bone/wither", BenchBlock::new).lang("Wither Bone Bench").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BookshelfBlock> BONE_WITHER_BOOKSHELF = bookshelf("bone/wither", BookshelfBlock::new).lang("Wither Bone Bookshelf").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChestBlock> BONE_WITHER_CHEST = chest("bone/wither", ChestBlock::new).lang("Wither Bone Chest").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<DresserBlock> BONE_WITHER_DRESSER = dresser("bone/wither", DresserBlock::new).lang("Wither Bone Dresser").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<WardrobeBottomBlock> BONE_WITHER_WARDROBE_BOTTOM = wardrobeBottom("bone/wither", WardrobeBottomBlock::new).lang("Wither Bone Wardrobe Bottom").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<WardrobeTopBlock> BONE_WITHER_WARDROBE_TOP = wardrobeTop("bone/wither", WardrobeTopBlock::new).lang("Wither Bone Wardrobe Top").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BedSingleBlock> BONE_WITHER_BED_SINGLE = bedSingle("bone/wither", BedSingleBlock::new).lang("Wither Bone Bed Single").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<BedDoubleBlock> BONE_WITHER_BED_DOUBLE = bedDouble("bone/wither", BedDoubleBlock::new).lang("Wither Bone Bed Double").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<ChandelierBlock> BONE_WITHER_CHANDELIER = chandelier("bone/wither", ChandelierBlock::new).lang("Wither Bone Chandelier").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_SINGLE = doorSingle("bone/wither", FurnitureDoorBlock::new).lang("Wither Bone Door Single").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_DOUBLE = doorDouble("bone/wither", FurnitureDoorBlock::new).lang("Wither Bone Door Double").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<LockboxBlock> BONE_WITHER_LOCKBOX = lockbox("bone/wither", LockboxBlock::new).lang("Wither Bone Lockbox").transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<CounterBlock> BONE_WITHER_COUNTER = counter("bone/wither", CounterBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	BlockEntry<OvenBlock> BONE_WITHER_OVEN = oven("bone/wither", OvenBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	// endregion
	// endregion

	// region: Royal
	BlockEntry<DyeableBlock> ROYAL_WOOL = woolDyeable("royal", DyeableBlock::new).register();
	BlockEntry<DyeableCarpetBlock> ROYAL_CARPET = carpetDyeable("royal", DyeableCarpetBlock::new).register();
	BlockEntry<FurnitureWallLightBlock> ROYAL_WALL_LIGHT = wallLight("royal", FurnitureWallLightBlock::new).register();
	BlockEntry<FloorLightBlock.WithFacing> ROYAL_FLOOR_LIGHT = floorLight("royal", FloorLightBlock.WithFacing::new).blockState(BlockTransformers::horizontalBlockState).register();
	BlockEntry<TableSmallBlock.Dyeable> ROYAL_TABLE_SMALL = tableSmall("royal", TableSmallBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<TableWideBlock.Dyeable> ROYAL_TABLE_WIDE = tableWide("royal", TableWideBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<TableLargeBlock.Dyeable> ROYAL_TABLE_LARGE = tableLarge("royal", TableLargeBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<StoolBlock.Dyeable> ROYAL_STOOL = stool("royal", StoolBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<CushionBlock.Dyeable> ROYAL_CUSHION = cushion("royal", CushionBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<PaintingSmallBlock> ROYAL_PAINTING_SMALL = paintingSmall("royal", PaintingSmallBlock::new).register();
	BlockEntry<PaintingWideBlock> ROYAL_PAINTING_WIDE = paintingWide("royal", PaintingWideBlock::new).register();
	BlockEntry<DrawerBlock.Dyeable> ROYAL_DRAWER = drawer("royal", DrawerBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<ShelfBlock.Dyeable> ROYAL_SHELF = shelf("royal", ShelfBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<SofaBlock.Dyeable> ROYAL_SOFA = sofa("royal", SofaBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<DeskBlock.Dyeable> ROYAL_DESK_LEFT = deskLeft("royal", DeskBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<DeskBlock.Dyeable> ROYAL_DESK_RIGHT = deskRight("royal", DeskBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<ChairBlock.DyeableOriginOnly> ROYAL_CHAIR = chair("royal", ChairBlock.DyeableOriginOnly::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<BenchBlock.Dyeable> ROYAL_BENCH = bench("royal", BenchBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<BookshelfBlock.Dyeable> ROYAL_BOOKSHELF = bookshelf("royal", BookshelfBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<ChestBlock.Dyeable> ROYAL_CHEST = chest("royal", ChestBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<DresserBlock.Dyeable> ROYAL_DRESSER = dresser("royal", DresserBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<WardrobeBottomBlock.Dyeable> ROYAL_WARDROBE_BOTTOM = wardrobeBottom("royal", WardrobeBottomBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<WardrobeTopBlock.Dyeable> ROYAL_WARDROBE_TOP = wardrobeTop("royal", WardrobeTopBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<BedSingleBlock.Dyeable> ROYAL_BED_SINGLE = bedSingle("royal", BedSingleBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<BedDoubleBlock.Dyeable> ROYAL_BED_DOUBLE = bedDouble("royal", BedDoubleBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<ChandelierBlock> ROYAL_CHANDELIER = chandelier("royal", ChandelierBlock::new).register();
	BlockEntry<FurnitureDoorBlock.Dyeable> ROYAL_DOOR_SINGLE = doorSingle("royal", FurnitureDoorBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<FurnitureDoorBlock.Dyeable> ROYAL_DOOR_DOUBLE = doorDouble("royal", FurnitureDoorBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<LockboxBlock.Dyeable> ROYAL_LOCKBOX = lockbox("royal", LockboxBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<CounterBlock.Dyeable> ROYAL_COUNTER = counter("royal", CounterBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).register();
	BlockEntry<OvenBlock.Dyeable> ROYAL_OVEN = oven("royal", OvenBlock.Dyeable::new).transform(BlockTransformers::applyDyeable).transform(BlockTransformers::mineablePickaxe).register();
	// endregion

	// region: Necrolord
	BlockEntry<Block> NECROLORD_WOOL = wool("necrolord", Block::new).register();
	BlockEntry<CarpetBlock> NECROLORD_CARPET = carpet("necrolord", CarpetBlock::new).register();
	BlockEntry<FurnitureWallLightBlock> NECROLORD_WALL_LIGHT = wallLight("necrolord", FurnitureWallLightBlock::new).register();
	BlockEntry<FloorLightBlock.WithFacing> NECROLORD_FLOOR_LIGHT = floorLight("necrolord", FloorLightBlock.WithFacing::new).blockState(BlockTransformers::horizontalBlockState).register();
	BlockEntry<TableSmallBlock> NECROLORD_TABLE_SMALL = tableSmall("necrolord", TableSmallBlock::new).register();
	BlockEntry<TableWideBlock> NECROLORD_TABLE_WIDE = tableWide("necrolord", TableWideBlock::new).register();
	BlockEntry<TableLargeBlock> NECROLORD_TABLE_LARGE = tableLarge("necrolord", TableLargeBlock::new).register();
	BlockEntry<StoolBlock> NECROLORD_STOOL = stool("necrolord", StoolBlock::new).register();
	BlockEntry<CushionBlock> NECROLORD_CUSHION = cushion("necrolord", CushionBlock::new).register();
	BlockEntry<PaintingSmallBlock> NECROLORD_PAINTING_SMALL = paintingSmall("necrolord", PaintingSmallBlock::new).register();
	BlockEntry<PaintingWideBlock> NECROLORD_PAINTING_WIDE = paintingWide("necrolord", PaintingWideBlock::new).register();
	BlockEntry<DrawerBlock> NECROLORD_DRAWER = drawer("necrolord", DrawerBlock::new).register();
	BlockEntry<ShelfBlock> NECROLORD_SHELF = shelf("necrolord", ShelfBlock::new).register();
	BlockEntry<SofaBlock> NECROLORD_SOFA = sofa("necrolord", SofaBlock::new).register();
	BlockEntry<DeskBlock> NECROLORD_DESK_LEFT = deskLeft("necrolord", DeskBlock::new).register();
	BlockEntry<DeskBlock> NECROLORD_DESK_RIGHT = deskRight("necrolord", DeskBlock::new).register();
	BlockEntry<ChairBlock.OriginOnly> NECROLORD_CHAIR = chair("necrolord", ChairBlock.OriginOnly::new).register();
	BlockEntry<BenchBlock> NECROLORD_BENCH = bench("necrolord", BenchBlock::new).register();
	BlockEntry<BookshelfBlock> NECROLORD_BOOKSHELF = bookshelf("necrolord", BookshelfBlock::new).register();
	BlockEntry<ChestBlock> NECROLORD_CHEST = chest("necrolord", ChestBlock::new).register();
	BlockEntry<DresserBlock> NECROLORD_DRESSER = dresser("necrolord", DresserBlock::new).register();
	BlockEntry<WardrobeBottomBlock> NECROLORD_WARDROBE_BOTTOM = wardrobeBottom("necrolord", WardrobeBottomBlock::new).register();
	BlockEntry<WardrobeTopBlock> NECROLORD_WARDROBE_TOP = wardrobeTop("necrolord", WardrobeTopBlock::new).register();
	BlockEntry<BedSingleBlock> NECROLORD_BED_SINGLE = bedSingle("necrolord", BedSingleBlock::new).register();
	BlockEntry<BedDoubleBlock> NECROLORD_BED_DOUBLE = bedDouble("necrolord", BedDoubleBlock::new).register();
	BlockEntry<ChandelierBlock> NECROLORD_CHANDELIER = chandelier("necrolord", ChandelierBlock::new).register();
	BlockEntry<FurnitureDoorBlock> NECROLORD_DOOR_SINGLE = doorSingle("necrolord", FurnitureDoorBlock::new).register();
	BlockEntry<FurnitureDoorBlock> NECROLORD_DOOR_DOUBLE = doorDouble("necrolord", FurnitureDoorBlock::new).register();
	BlockEntry<LockboxBlock> NECROLORD_LOCKBOX = lockbox("necrolord", LockboxBlock::new).register();
	BlockEntry<CounterBlock> NECROLORD_COUNTER = counter("necrolord", CounterBlock::new).register();
	BlockEntry<OvenBlock> NECROLORD_OVEN = oven("necrolord", OvenBlock::new).transform(BlockTransformers::mineablePickaxe).register();
	// endregion
	// endregion

	// region: Furniture Station
	BlockEntry<FurnitureStationBlock> FURNITURE_STATION = REGISTRATE
			.object("furniture_station")
			.block(FurnitureStationBlock::new)
			.lang("Furniture Station")
			.initialProperties(Material.WOOD)
			.strength(2.5F)
			.tag(BlockTags.Vanilla.MINEABLE_WITH_AXE)
			.sound(SoundType.WOOD)
			.noOcclusion()
			.isValidSpawn(BlockHelper::never)
			.isRedstoneConductor(BlockHelper::never)
			.isSuffocating(BlockHelper::never)
			.isViewBlocking(BlockHelper::never)
			.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStatesExcept(blockState -> ConfiguredModel
							.builder()
							.modelFile(provider
									.models()
									.getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
							)
							.build(),
					BlockTransformers.getIgnoredProperties(ctx.get(), ctx.getId().getPath()))
			)
			.recipe((ctx, provider) -> SmithingTransformRecipeBuilder
					.smithing(Ingredient.EMPTY, DataIngredient.items(Items.CRAFTING_TABLE), DataIngredient.tag(ItemTags.Forge.LEATHER), RecipeCategory.DECORATIONS, ctx.get().asItem())
					.unlocks("has_crafting_table", RegistrateRecipeProvider.has(Items.CRAFTING_TABLE))
					.unlocks("has_leather", RegistrateRecipeProvider.has(ItemTags.Forge.LEATHER))
					.save(provider, ctx.getId())
			)
			.renderType(() -> RenderType::cutout)

			.item()
			.model((ctx, provider) -> provider
					.withExistingParent(
							"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
							new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
					)
			)
			.build()

			.simpleBlockEntity(FurnitureStationBlockEntity::new)
			.register();
	// endregion

	static void bootstrap()
	{
	}
}

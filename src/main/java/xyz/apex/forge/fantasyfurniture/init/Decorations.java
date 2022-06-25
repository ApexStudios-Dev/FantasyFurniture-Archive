package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.block.base.set.StackedBlock;
import xyz.apex.forge.fantasyfurniture.block.decorations.*;
import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.renderer.entity.WidowBloomBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.item.WidowBloomBlockItem;

import static com.tterrag.registrate.providers.ProviderType.LANG;

@SuppressWarnings("SameParameterValue")
public final class Decorations
{
	public static final TagKey<Item> ITEM_GROUP_CATEGORY_TAG = ItemTags.tag(Mods.FANTASY_FURNITURE, "item_category/decorations");

	// region: Berry Basket
	// region: Empty
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY_BLOCK = berryBasket("empty");
	public static final ItemEntry<BlockItem> BERRY_BASKET_EMPTY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_EMPTY_BLOCK);
	// endregion

	// region: Sweetberry
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY_BLOCK = berryBasket("sweetberry");
	public static final ItemEntry<BlockItem> BERRY_BASKET_SWEETBERRY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_SWEETBERRY_BLOCK);
	// endregion

	// region: Blueberry
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_BLUEBERRY_BLOCK = berryBasket("blueberry");
	public static final ItemEntry<BlockItem> BERRY_BASKET_BLUEBERRY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_BLUEBERRY_BLOCK);
	// endregion

	// region: Strawberry
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_STRAWBERRY_BLOCK = berryBasket("strawberry");
	public static final ItemEntry<BlockItem> BERRY_BASKET_STRAWBERRY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_STRAWBERRY_BLOCK);
	// endregion

	private static BlockEntry<BerryBasketBlock> berryBasket(String type)
	{
		String englishName;

		if(type.equals("empty"))
			englishName = "Berry Basket";
		else
			englishName = "%s Basket".formatted(RegistrateLangProvider.toEnglishName(type));

		return FFRegistry.INSTANCE
				.object("decorations/berry_basket_%s".formatted(type))
				.block(BerryBasketBlock::new)
					.lang(englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Bolts of Cloth
	public static final BlockEntry<BoltsOfClothBlock> BOLTS_OF_CLOTH_BLOCK = boltsOfCloth();
	public static final ItemEntry<BlockItem> BOLTS_OF_CLOTH_BLOCK_ITEM = Registrations.blockItem(BOLTS_OF_CLOTH_BLOCK);

	private static BlockEntry<BoltsOfClothBlock> boltsOfCloth()
	{
		return FFRegistry.INSTANCE
				.object("decorations/bolts_of_cloth")
				.block(BoltsOfClothBlock::new)
					.lang("Bolts of Cloth")

					.initialProperties(Material.WOOL)
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Book Stack
	public static final BlockEntry<BookStackBlock> BOOK_STACK_BLOCK = bookStack();
	public static final ItemEntry<BlockItem> BOOK_STACK_BLOCK_ITEM = Registrations.blockItem(BOOK_STACK_BLOCK);

	private static BlockEntry<BookStackBlock> bookStack()
	{
		return FFRegistry.INSTANCE
				.object("decorations/book_stack")
				.block(BookStackBlock::new)
					.lang("Book Stack")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, BookStackBlock.BOOKS))
					// .loot((lootTables, block) -> droppingStacked(lootTables, block, Items.BOOK, BookStackBlock.BOOKS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, BookStackBlock.BOOKS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, BookStackBlock.BOOKS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Bowl
	// region: Empty
	public static final BlockEntry<BowlBlock> BOWL_EMPTY_BLOCK = bowl("empty");
	public static final ItemEntry<BlockItem> BOWL_EMPTY_BLOCK_ITEM = Registrations.blockItem(BOWL_EMPTY_BLOCK);
	// endregion

	// region: Beetroot Soup
	public static final BlockEntry<BowlBlock> BOWL_BEETROOT_SOUP_BLOCK = bowl("beetroot_soup");
	public static final ItemEntry<BlockItem> BOWL_BEETROOT_SOUP_BLOCK_ITEM = Registrations.blockItem(BOWL_BEETROOT_SOUP_BLOCK);
	// endregion

	// region: Mushroom Stew
	public static final BlockEntry<BowlBlock> BOWL_MUSHROOM_STEW_BLOCK = bowl("mushroom_stew");
	public static final ItemEntry<BlockItem> BOWL_MUSHROOM_STEW_BLOCK_ITEM = Registrations.blockItem(BOWL_MUSHROOM_STEW_BLOCK);
	// endregion

	private static BlockEntry<BowlBlock> bowl(String type)
	{
		String englishName;

		if(type.equals("empty"))
			englishName = "Bowl";
		else
			englishName = "%s Bowl".formatted(RegistrateLangProvider.toEnglishName(type));

		return FFRegistry.INSTANCE
				.object("decorations/bowl_%s".formatted(type))
				.block(BowlBlock::new)
					.lang(englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Tankards
	// region: Empty
	public static final BlockEntry<TankardsBlock> TANKARD_EMPTY_BLOCK = tankards("empty");
	public static final ItemEntry<BlockItem> TANKARD_EMPTY_BLOCK_ITEM = Registrations.blockItem(TANKARD_EMPTY_BLOCK);
	// endregion

	// region: Honeymead
	public static final BlockEntry<TankardsBlock> TANKARD_HONEYMEAD_BLOCK = tankards("honeymead");
	public static final ItemEntry<BlockItem> TANKARD_HONEYMEAD_BLOCK_ITEM = Registrations.blockItem(TANKARD_HONEYMEAD_BLOCK);
	// endregion

	// region: Milk
	public static final BlockEntry<TankardsBlock> TANKARD_MILK_BLOCK = tankards("milk");
	public static final ItemEntry<BlockItem> TANKARD_MILK_BLOCK_ITEM = Registrations.blockItem(TANKARD_MILK_BLOCK);
	// endregion

	// region: Sweetberry
	public static final BlockEntry<TankardsBlock> TANKARD_SWEETBERRY_BLOCK = tankards("sweetberry");
	public static final ItemEntry<BlockItem> TANKARD_SWEETBERRY_BLOCK_ITEM = Registrations.blockItem(TANKARD_SWEETBERRY_BLOCK);
	// endregion

	private static BlockEntry<TankardsBlock> tankards(String type)
	{
		String englishName;

		if(type.equals("empty"))
			englishName = "Tankards";
		else if(type.equals("honeymead"))
			englishName = "Honeyed Tankards";
		else
			englishName = "%s Tankards".formatted(RegistrateLangProvider.toEnglishName(type));

		return FFRegistry.INSTANCE
				.object("decorations/tankards_%s".formatted(type))
				.block(TankardsBlock::new)
					.lang(englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, TankardsBlock.TANKARDS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, TankardsBlock.TANKARDS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, TankardsBlock.TANKARDS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Red Mushrooms
	public static final BlockEntry<MushroomsRedBlock> MUSHROOMS_RED_BLOCK = mushroomsRed();
	public static final ItemEntry<BlockItem> MUSHROOMS_RED_BLOCK_ITEM = Registrations.blockItem(MUSHROOMS_RED_BLOCK);

	private static BlockEntry<MushroomsRedBlock> mushroomsRed()
	{
		return FFRegistry.INSTANCE
				.object("decorations/mushrooms_red")
				.block(MushroomsRedBlock::new)
					.lang("Red Mushrooms")

					.initialProperties(Material.PLANT, MaterialColor.COLOR_RED)
					.sound(SoundType.GRASS)
					.noOcclusion()
					.noCollission()
					.randomTicks()
					.instabreak()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, MushroomsRedBlock.MUSHROOMS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, MushroomsRedBlock.MUSHROOMS))

					.hasPostProcess(BlockHelper::always)
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, MushroomsRedBlock.MUSHROOMS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Coin Stack
	// region: Gold
	public static final BlockEntry<CoinStackBlock> COIN_STOCK_GOLD_BLOCK = coinStack("gold");
	public static final ItemEntry<BlockItem> COIN_STOCK_GOLD_BLOCK_ITEM = Registrations.blockItem(COIN_STOCK_GOLD_BLOCK);
	// endregion

	// region: Iron
	public static final BlockEntry<CoinStackBlock> COIN_STOCK_IRON_BLOCK = coinStack("iron");
	public static final ItemEntry<BlockItem> COIN_STOCK_IRON_BLOCK_ITEM = Registrations.blockItem(COIN_STOCK_IRON_BLOCK);
	// endregion

	private static BlockEntry<CoinStackBlock> coinStack(String type)
	{
		return FFRegistry.INSTANCE
				.object("decorations/coin_stack_%s".formatted(type))
				.block(CoinStackBlock::new)
					.lang("%s Coin Stack".formatted(RegistrateLangProvider.toEnglishName(type)))

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Muffins
	// region: Blueberry
	public static final BlockEntry<MuffinsBlock> MUFFINS_BLUEBERRY_BLOCK = muffins("blueberry");
	public static final ItemEntry<BlockItem> MUFFINS_BLUEBERRY_BLOCK_ITEM = Registrations.blockItem(MUFFINS_BLUEBERRY_BLOCK);
	// endregion

	// region: Chocolate
	public static final BlockEntry<MuffinsBlock> MUFFINS_CHOCOLATE_BLOCK = muffins("chocolate");
	public static final ItemEntry<BlockItem> MUFFINS_CHOCOLATE_BLOCK_ITEM = Registrations.blockItem(MUFFINS_CHOCOLATE_BLOCK);
	// endregion

	// region: Sweetberry
	public static final BlockEntry<MuffinsBlock> MUFFINS_SWEETBERRY_BLOCK = muffins("sweetberry");
	public static final ItemEntry<BlockItem> MUFFINS_SWEETBERRY_BLOCK_ITEM = Registrations.blockItem(MUFFINS_SWEETBERRY_BLOCK);
	// endregion

	private static BlockEntry<MuffinsBlock> muffins(String type)
	{
		return FFRegistry.INSTANCE
				.object("decorations/muffins_%s".formatted(type))
				.block(MuffinsBlock::new)
					.lang("%s Muffins".formatted(RegistrateLangProvider.toEnglishName(type)))

					.initialProperties(Material.CAKE)
					.strength(.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, MuffinsBlock.MUFFINS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, MuffinsBlock.MUFFINS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, MuffinsBlock.MUFFINS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Paper Stack
	public static final BlockEntry<PaperStackBlock> PAPER_STACK_BLOCK = paperStack();
	public static final ItemEntry<BlockItem> PAPER_STACK_BLOCK_ITEM = Registrations.blockItem(PAPER_STACK_BLOCK);

	private static BlockEntry<PaperStackBlock> paperStack()
	{
		return FFRegistry.INSTANCE
				.object("decorations/paper_stack")
				.block(PaperStackBlock::new)
					.lang("Paper Stack")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Nordic
	// region: Boiled Crème Treats
	public static final BlockEntry<BoiledCremeTreatsBlock> BOILED_CREME_TREATS_BLOCK = boiledCremeTreats(NordicBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> BOILED_CREME_TREATS_BLOCK_ITEM = Registrations.blockItem(BOILED_CREME_TREATS_BLOCK);

	private static BlockEntry<BoiledCremeTreatsBlock> boiledCremeTreats(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/boiled_creme_treats".formatted(furnitureSet.serializedName))
				.block(BoiledCremeTreatsBlock::new)
					.lang("Boiled Creme Treats")

					.initialProperties(Material.CAKE)
					.strength(.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, BoiledCremeTreatsBlock.TREATS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, BoiledCremeTreatsBlock.TREATS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, BoiledCremeTreatsBlock.TREATS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Sweetrolls
	public static final BlockEntry<SweetRollsBlock> SWEETROLLS_BLOCK = sweetRolls(NordicBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> SWEETROLLS_BLOCK_ITEM = Registrations.blockItem(SWEETROLLS_BLOCK);

	private static BlockEntry<SweetRollsBlock> sweetRolls(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/sweetrolls".formatted(furnitureSet.serializedName))
				.block(SweetRollsBlock::new)
					.lang("Sweetrolls")

					.initialProperties(Material.CAKE)
					.strength(.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, SweetRollsBlock.ROLLS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, SweetRollsBlock.ROLLS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, SweetRollsBlock.ROLLS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Mead Bottles
	public static final BlockEntry<MeadBottlesBlock> MEAD_BOTTLES_BLOCK = meadBottles(NordicBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> MEAD_BOTTLES_BLOCK_ITEM = Registrations.blockItem(MEAD_BOTTLES_BLOCK);

	private static BlockEntry<MeadBottlesBlock> meadBottles(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/mead_bottles".formatted(furnitureSet.serializedName))
				.block(MeadBottlesBlock::new)
					.lang("Mead Bottles")

					.initialProperties(Material.GLASS)
					.strength(.3F)
					.sound(SoundType.GLASS)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, MeadBottlesBlock.BOTTLES))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, MeadBottlesBlock.BOTTLES))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, MeadBottlesBlock.BOTTLES))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Soul Gems
	// region: Light
	public static final BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_LIGHT_BLOCK = soulGems(NordicBlocks.INSTANCE, "light");
	public static final ItemEntry<BlockItem> NORDIC_SOUL_GEMS_LIGHT_BLOCK_ITEM = Registrations.blockItem(NORDIC_SOUL_GEMS_LIGHT_BLOCK);
	// endregion

	// region: Dark
	public static final BlockEntry<SoulGemsBlock> NORDIC_SOUL_GEMS_DARK_BLOCK = soulGems(NordicBlocks.INSTANCE, "dark");
	public static final ItemEntry<BlockItem> NORDIC_SOUL_GEMS_DARK_BLOCK_ITEM = Registrations.blockItem(NORDIC_SOUL_GEMS_DARK_BLOCK);
	// endregion

	private static BlockEntry<SoulGemsBlock> soulGems(ModBlocks furnitureSet, String type)
	{
		String englishName = "Soul Gems";

		if(type.equals("dark"))
			englishName = "Black %s".formatted(englishName);

		return FFRegistry.INSTANCE
				.object("decorations/%s/soul_gems_%s".formatted(furnitureSet.serializedName, type))
				.block(SoulGemsBlock::new)
					.lang(englishName)

					.initialProperties(Material.GLASS)
					.strength(.3F)
					.sound(SoundType.GLASS)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Venthyr
	// region: Food
	// region: 0
	public static final BlockEntry<FoodBlock> VENTHYR_FOOD_0_BLOCK = food(VenthyrBlocks.INSTANCE, 0);
	public static final ItemEntry<BlockItem> VENTHYR_FOOD_0_BLOCK_ITEM = Registrations.blockItem(VENTHYR_FOOD_0_BLOCK);
	// endregion

	// region: 1
	public static final BlockEntry<FoodBlock> VENTHYR_FOOD_1_BLOCK = food(VenthyrBlocks.INSTANCE, 1);
	public static final ItemEntry<BlockItem> VENTHYR_FOOD_1_BLOCK_ITEM = Registrations.blockItem(VENTHYR_FOOD_1_BLOCK);
	// endregion

	private static BlockEntry<FoodBlock> food(ModBlocks furnitureSet, int index)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/food_%d".formatted(furnitureSet.serializedName, index))
				.block(FoodBlock::new)
					.lang("%s Food %d".formatted(furnitureSet.englishName, index + 1))

					.initialProperties(Material.CAKE)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Tea Set
	public static final BlockEntry<TeaSetBlock> VENTHYR_TEA_SET_BLOCK = teaSet(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_TEA_SET_BLOCK_ITEM = Registrations.blockItem(VENTHYR_TEA_SET_BLOCK);

	private static BlockEntry<TeaSetBlock> teaSet(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/tea_set".formatted(furnitureSet.serializedName))
				.block(TeaSetBlock::new)
					.lang("%s Tea Set".formatted(furnitureSet.englishName))

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Tea Cups
	public static final BlockEntry<TeaCupsBlock> VENTHYR_TEA_CUPS_BLOCK = teaCups(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_TEA_CUPS_BLOCK_ITEM = Registrations.blockItem(VENTHYR_TEA_CUPS_BLOCK);

	private static BlockEntry<TeaCupsBlock> teaCups(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/tea_cups".formatted(furnitureSet.serializedName))
				.block(TeaCupsBlock::new)
					.lang("%s Tea Cups".formatted(furnitureSet.englishName))

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, TeaCupsBlock.TEA_CUPS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, TeaCupsBlock.TEA_CUPS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, TeaCupsBlock.TEA_CUPS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Platter
	public static final BlockEntry<PlatterBlock> VENTHYR_PLATTER_BLOCK = platter(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_PLATTER_BLOCK_ITEM = Registrations.blockItem(VENTHYR_PLATTER_BLOCK);

	private static BlockEntry<PlatterBlock> platter(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/platter".formatted(furnitureSet.serializedName))
				.block(PlatterBlock::new)
					.lang("%s Platter".formatted(furnitureSet.englishName))

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, PlatterBlock.PLATTER))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, PlatterBlock.PLATTER))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, PlatterBlock.PLATTER))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Widow Bloom
	public static final BlockEntry<WidowBloomBlock> VENTHYR_WIDOW_BLOOM_BLOCK = widowBloom(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_WIDOW_BLOOM_BLOCK_ITEM = Registrations.blockItem(VENTHYR_WIDOW_BLOOM_BLOCK);
	public static final BlockEntityEntry<WidowBloomBlockEntity> VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY = Registrations.blockEntity(VENTHYR_WIDOW_BLOOM_BLOCK);

	private static BlockEntry<WidowBloomBlock> widowBloom(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/widow_bloom".formatted(furnitureSet.serializedName))
				.block(WidowBloomBlock::new)
					.lang("Widowbloom Vase")

					.initialProperties(Material.DECORATION)
					.strength(2.5F)
					.sound(SoundType.STONE)
					.noOcclusion()

					.blockstate((ctx, provider) -> provider
							.getVariantBuilder(ctx.get())
							.forAllStates(blockState -> ConfiguredModel
									.builder()
										.modelFile(provider
												.models()
												.getBuilder(ctx.getName())
												.texture("particle", "minecraft:block/basalt_top")
										)
									.build()
							)
					)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item(WidowBloomBlockItem::new)
						.model((ctx, provider) -> {
							var id = ctx.getId();
							var builtInEntity = new ModelFile.UncheckedModelFile("minecraft:builtin/entity");

							provider.getBuilder("%s:item/%s".formatted(id.getNamespace(), id.getPath()))
						        .parent(builtInEntity)
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
								.end();
						})
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()

					.blockEntity(WidowBloomBlockEntity::new)
						.renderer(() -> WidowBloomBlockEntityRenderer::new)
					.build()
		.register();
	}
	// endregion

	// region: Tomes
	public static final BlockEntry<TomesBlock> VENTHYR_TOMES_BLOCK = tomes(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_TOMES_BLOCK_ITEM = Registrations.blockItem(VENTHYR_TOMES_BLOCK);

	private static BlockEntry<TomesBlock> tomes(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/tomes".formatted(furnitureSet.serializedName))
				.block(TomesBlock::new)
					.lang("%s Tomes".formatted(furnitureSet.englishName))

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, TomesBlock.TOMES))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, TomesBlock.TOMES))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, TomesBlock.TOMES))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion

	// region: Chalices
	public static final BlockEntry<ChalicesBlock> VENTHYR_CHALICES_BLOCK = chalices(VenthyrBlocks.INSTANCE);
	public static final ItemEntry<BlockItem> VENTHYR_CHALICES_BLOCK_ITEM = Registrations.blockItem(VENTHYR_CHALICES_BLOCK);

	private static BlockEntry<ChalicesBlock> chalices(ModBlocks furnitureSet)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/chalices".formatted(furnitureSet.serializedName))
				.block(ChalicesBlock::new)
					.lang("%s Chalices".formatted(furnitureSet.englishName))

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockstate((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, ChalicesBlock.CHALICES))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, ChalicesBlock.CHALICES))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, ChalicesBlock.CHALICES))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Dunmer
	// region: Pottery
	// region: 0
	public static final BlockEntry<PotteryBlock> DUNMER_POTTERY_0_BLOCK = pottery(DunmerBlocks.INSTANCE, 0);
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_0_BLOCK_ITEM = Registrations.blockItem(DUNMER_POTTERY_0_BLOCK);
	// endregion

	// region: 1
	public static final BlockEntry<PotteryBlock> DUNMER_POTTERY_1_BLOCK = pottery(DunmerBlocks.INSTANCE, 1);
	public static final ItemEntry<BlockItem> DUNMER_POTTERY_1_BLOCK_ITEM = Registrations.blockItem(DUNMER_POTTERY_1_BLOCK);
	// endregion

	private static BlockEntry<PotteryBlock> pottery(ModBlocks furnitureSet, int index)
	{
		return FFRegistry.INSTANCE
				.object("decorations/%s/pottery_%s".formatted(furnitureSet.serializedName, index))
				.block(PotteryBlock::new)
					.lang("%s Pottery %s".formatted(furnitureSet.englishName, index + 1))

					.initialProperties(Material.DECORATION)
					.strength(2.5F)
					.sound(SoundType.STONE)
					.noOcclusion()

					.blockstate(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addLayer(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG, furnitureSet.itemGroupCategoryTag)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Item Group Category
	public static final ItemGroupCategory ITEM_GROUP_CATEGORY = ItemGroupCategory
			.builder(ITEM_GROUP_CATEGORY_TAG.location().toString())
				.tagged(ITEM_GROUP_CATEGORY_TAG)
				.defaultIcon(SWEETROLLS_BLOCK::asStack)
			.build();
	// endregion

	static void bootstrap()
	{
		ITEM_GROUP_CATEGORY.addTranslationGenerator(FFRegistry.INSTANCE, "Decorations");

		BlockEntry<?>[] blocks = new BlockEntry[] {
				BERRY_BASKET_EMPTY_BLOCK,
				BERRY_BASKET_SWEETBERRY_BLOCK,
				BERRY_BASKET_BLUEBERRY_BLOCK,
				BERRY_BASKET_STRAWBERRY_BLOCK,
				BOLTS_OF_CLOTH_BLOCK,
				BOOK_STACK_BLOCK,
				BOWL_EMPTY_BLOCK,
				BOWL_BEETROOT_SOUP_BLOCK,
				BOWL_MUSHROOM_STEW_BLOCK,
				TANKARD_EMPTY_BLOCK,
				TANKARD_HONEYMEAD_BLOCK,
				TANKARD_MILK_BLOCK,
				TANKARD_SWEETBERRY_BLOCK,
				MUSHROOMS_RED_BLOCK,
				COIN_STOCK_GOLD_BLOCK,
				COIN_STOCK_IRON_BLOCK,
				MUFFINS_BLUEBERRY_BLOCK,
				MUFFINS_CHOCOLATE_BLOCK,
				MUFFINS_SWEETBERRY_BLOCK,
				PAPER_STACK_BLOCK,
				BOILED_CREME_TREATS_BLOCK,
				SWEETROLLS_BLOCK,
				MEAD_BOTTLES_BLOCK,
				NORDIC_SOUL_GEMS_LIGHT_BLOCK,
				NORDIC_SOUL_GEMS_DARK_BLOCK,
				VENTHYR_FOOD_0_BLOCK,
				VENTHYR_FOOD_1_BLOCK,
				VENTHYR_TEA_SET_BLOCK,
				VENTHYR_TEA_CUPS_BLOCK,
				VENTHYR_PLATTER_BLOCK,
				VENTHYR_WIDOW_BLOOM_BLOCK,
				VENTHYR_TOMES_BLOCK,
				VENTHYR_CHALICES_BLOCK,
				DUNMER_POTTERY_0_BLOCK,
				DUNMER_POTTERY_1_BLOCK
		};

		ItemEntry<?>[] items = new ItemEntry[] {
				BERRY_BASKET_EMPTY_BLOCK_ITEM,
				BERRY_BASKET_SWEETBERRY_BLOCK_ITEM,
				BERRY_BASKET_BLUEBERRY_BLOCK_ITEM,
				BERRY_BASKET_STRAWBERRY_BLOCK_ITEM,
				BOLTS_OF_CLOTH_BLOCK_ITEM,
				BOOK_STACK_BLOCK_ITEM,
				BOWL_EMPTY_BLOCK_ITEM,
				BOWL_BEETROOT_SOUP_BLOCK_ITEM,
				BOWL_MUSHROOM_STEW_BLOCK_ITEM,
				TANKARD_EMPTY_BLOCK_ITEM,
				TANKARD_HONEYMEAD_BLOCK_ITEM,
				TANKARD_MILK_BLOCK_ITEM,
				TANKARD_SWEETBERRY_BLOCK_ITEM,
				MUSHROOMS_RED_BLOCK_ITEM,
				COIN_STOCK_GOLD_BLOCK_ITEM,
				COIN_STOCK_IRON_BLOCK_ITEM,
				MUFFINS_BLUEBERRY_BLOCK_ITEM,
				MUFFINS_CHOCOLATE_BLOCK_ITEM,
				MUFFINS_SWEETBERRY_BLOCK_ITEM,
				PAPER_STACK_BLOCK_ITEM,
				BOILED_CREME_TREATS_BLOCK_ITEM,
				SWEETROLLS_BLOCK_ITEM,
				MEAD_BOTTLES_BLOCK_ITEM,
				NORDIC_SOUL_GEMS_LIGHT_BLOCK_ITEM,
				NORDIC_SOUL_GEMS_DARK_BLOCK_ITEM,
				VENTHYR_FOOD_0_BLOCK_ITEM,
				VENTHYR_FOOD_1_BLOCK_ITEM,
				VENTHYR_TEA_SET_BLOCK_ITEM,
				VENTHYR_TEA_CUPS_BLOCK_ITEM,
				VENTHYR_PLATTER_BLOCK_ITEM,
				VENTHYR_WIDOW_BLOOM_BLOCK_ITEM,
				VENTHYR_TOMES_BLOCK_ITEM,
				VENTHYR_CHALICES_BLOCK_ITEM,
				DUNMER_POTTERY_0_BLOCK_ITEM,
				DUNMER_POTTERY_1_BLOCK_ITEM
		};

		FFRegistry.INSTANCE.addDataGenerator(LANG, provider -> {
			for(BlockEntry<?> entry : blocks)
			{
				entry.ifPresent(block -> {
					if(block instanceof StackedBlock stacked)
						provider.add(stacked.getStackableTranslationKey(), "Stackable");
				});
			}
		});

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> {
			ItemGroupCategoryManager instance = ItemGroupCategoryManager.getInstance(FFRegistry.MOD_ITEM_GROUP);
			instance.addCategory(ITEM_GROUP_CATEGORY);
		});
	}
}

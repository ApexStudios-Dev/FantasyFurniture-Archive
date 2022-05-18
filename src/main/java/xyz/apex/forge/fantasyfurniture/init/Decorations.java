package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategory;
import xyz.apex.forge.apexcore.lib.item.ItemGroupCategoryManager;
import xyz.apex.forge.apexcore.lib.util.EventBusHelper;
import xyz.apex.forge.fantasyfurniture.block.decorations.*;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class Decorations
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();
	public static final ITag.INamedTag<Item> ITEM_GROUP_CATEGORY_TAG = REGISTRY.moddedItemTag("item_category/decorations");

	// region: Berry Basket
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY_BLOCK = berryBasket("empty");
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY_BLOCK = berryBasket("sweetberry");

	public static final ItemEntry<BlockItem> BERRY_BASKET_EMPTY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_EMPTY_BLOCK);
	public static final ItemEntry<BlockItem> BERRY_BASKET_SWEETBERRY_BLOCK_ITEM = Registrations.blockItem(BERRY_BASKET_SWEETBERRY_BLOCK);

	private static BlockEntry<BerryBasketBlock> berryBasket(String type)
	{
		String codeName = "decorations/berry_basket_" + type;
		String englishName;

		if(type.equals("empty"))
			englishName = "Berry Basket";
		else
			englishName = RegistrateLangProvider.toEnglishName(type) + " Basket";

		return REGISTRY
				.block(codeName, BerryBasketBlock::new)
					.lang(englishName)
					.lang(EN_GB, englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Boiled Crème Treats
	public static final BlockEntry<BoiledCremeTreatsBlock> BOILED_CREME_TREATS_BLOCK = boiledCremeTreats();
	public static final ItemEntry<BlockItem> BOILED_CREME_TREATS_BLOCK_ITEM = Registrations.blockItem(BOILED_CREME_TREATS_BLOCK);

	private static BlockEntry<BoiledCremeTreatsBlock> boiledCremeTreats()
	{
		return REGISTRY
				.block("decorations/boiled_creme_treats", BoiledCremeTreatsBlock::new)
					.lang("Boiled Creme Treats")
					.lang(EN_GB, "Boiled Creme Treats")

					.initialProperties(Material.CAKE)
					.strength(.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, BoiledCremeTreatsBlock.TREATS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, BoiledCremeTreatsBlock.TREATS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, BoiledCremeTreatsBlock.TREATS))
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
		return REGISTRY
				.block("decorations/bolts_of_cloth", BoltsOfClothBlock::new)
					.lang("Bolts of Cloth")
					.lang(EN_GB,"Bolts of Cloth")

					.initialProperties(Material.WOOL)
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

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
		return REGISTRY
				.block("decorations/book_stack", BookStackBlock::new)
					.lang("Book Stack")
					.lang(EN_GB, "Book Stack")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, BookStackBlock.BOOKS))
					// .loot((lootTables, block) -> droppingStacked(lootTables, block, Items.BOOK, BookStackBlock.BOOKS))
				.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, BookStackBlock.BOOKS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, BookStackBlock.BOOKS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Bowl
	public static final BlockEntry<BowlBlock> BOWL_EMPTY_BLOCK = bowl("empty");
	public static final BlockEntry<BowlBlock> BOWL_BEETROOT_SOUP_BLOCK = bowl("beetroot_soup");
	public static final BlockEntry<BowlBlock> BOWL_MUSHROOM_STEW_BLOCK = bowl("mushroom_stew");

	public static final ItemEntry<BlockItem> BOWL_EMPTY_BLOCK_ITEM = Registrations.blockItem(BOWL_EMPTY_BLOCK);
	public static final ItemEntry<BlockItem> BOWL_BEETROOT_SOUP_BLOCK_ITEM = Registrations.blockItem(BOWL_BEETROOT_SOUP_BLOCK);
	public static final ItemEntry<BlockItem> BOWL_MUSHROOM_STEW_BLOCK_ITEM = Registrations.blockItem(BOWL_MUSHROOM_STEW_BLOCK);

	private static BlockEntry<BowlBlock> bowl(String type)
	{
		String codeName = "decorations/bowl_" + type;
		String englishName;

		if(type.equals("empty"))
			englishName = "Bowl";
		else
			englishName = RegistrateLangProvider.toEnglishName(type) + " Bowl";

		return REGISTRY
				.block(codeName, BowlBlock::new)
					.lang(englishName)
					.lang(EN_GB, englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Sweetrolls
	public static final BlockEntry<SweetRollsBlock> SWEETROLLS_BLOCK = sweetRolls();
	public static final ItemEntry<BlockItem> SWEETROLLS_BLOCK_ITEM = Registrations.blockItem(SWEETROLLS_BLOCK);

	private static BlockEntry<SweetRollsBlock> sweetRolls()
	{
		return REGISTRY
				.block("decorations/sweetrolls", SweetRollsBlock::new)
					.lang("Sweetrolls")
					.lang(EN_GB, "Sweetrolls")

					.initialProperties(Material.CAKE)
					.strength(.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, SweetRollsBlock.ROLLS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, SweetRollsBlock.ROLLS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, SweetRollsBlock.ROLLS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Mead Bottles
	public static final BlockEntry<MeadBottlesBlock> MEAD_BOTTLES_BLOCK = meadBottles();
	public static final ItemEntry<BlockItem> MEAD_BOTTLES_BLOCK_ITEM = Registrations.blockItem(MEAD_BOTTLES_BLOCK);

	private static BlockEntry<MeadBottlesBlock> meadBottles()
	{
		return REGISTRY
				.block("decorations/mead_bottles", MeadBottlesBlock::new)
					.lang("Mead Bottles")
					.lang(EN_GB, "Mead Bottles")

					.initialProperties(Material.GLASS)
					.strength(.3F)
					.sound(SoundType.GLASS)
					.noOcclusion()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, MeadBottlesBlock.BOTTLES))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, MeadBottlesBlock.BOTTLES))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, MeadBottlesBlock.BOTTLES))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Tankards
	public static final BlockEntry<TankardsBlock> TANKARD_EMPTY_BLOCK = tankards("empty");
	public static final BlockEntry<TankardsBlock> TANKARD_HONEYMEAD_BLOCK = tankards("honeymead");
	public static final BlockEntry<TankardsBlock> TANKARD_MILK_BLOCK = tankards("milk");
	public static final BlockEntry<TankardsBlock> TANKARD_SWEETBERRY_BLOCK = tankards("sweetberry");

	public static final ItemEntry<BlockItem> TANKARD_EMPTY_BLOCK_ITEM = Registrations.blockItem(TANKARD_EMPTY_BLOCK);
	public static final ItemEntry<BlockItem> TANKARD_HONEYMEAD_BLOCK_ITEM = Registrations.blockItem(TANKARD_HONEYMEAD_BLOCK);
	public static final ItemEntry<BlockItem> TANKARD_MILK_BLOCK_ITEM = Registrations.blockItem(TANKARD_MILK_BLOCK);
	public static final ItemEntry<BlockItem> TANKARD_SWEETBERRY_BLOCK_ITEM = Registrations.blockItem(TANKARD_SWEETBERRY_BLOCK);

	private static BlockEntry<TankardsBlock> tankards(String type)
	{
		String codeName = "decorations/tankards_" + type;
		String englishName;

		if(type.equals("empty"))
			englishName = "Tankards";
		else if(type.equals("honeymead"))
			englishName = "Honeyed Tankards";
		else
			englishName = RegistrateLangProvider.toEnglishName(type) + " Tankards";

		return REGISTRY
				.block(codeName, TankardsBlock::new)
					.lang(englishName)
					.lang(EN_GB, englishName)

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, TankardsBlock.TANKARDS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, TankardsBlock.TANKARDS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

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
		return REGISTRY
				.block("decorations/mushrooms_red", MushroomsRedBlock::new)
					.lang("Red Mushrooms")
					.lang(EN_GB, "Red Mushrooms")

					.initialProperties(Material.PLANT, MaterialColor.COLOR_RED)
					.sound(SoundType.GRASS)
					.noOcclusion()
					.noCollission()
					.randomTicks()
					.instabreak()

					.blockState((ctx, provider) -> Registrations.horizontalBlock(ctx, provider, MushroomsRedBlock.MUSHROOMS))
					.loot((lootTables, block) -> Registrations.droppingStacked(lootTables, block, MushroomsRedBlock.MUSHROOMS))

					.hasPostProcess(BlockHelper::always)
					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> Registrations.blockItemStacked(ctx, provider, MushroomsRedBlock.MUSHROOMS))
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Coin Stack Gold
	public static final BlockEntry<CoinStackBlock> COIN_STOCK_GOLD_BLOCK = coinStackGold();
	public static final ItemEntry<BlockItem> COIN_STOCK_GOLD_BLOCK_ITEM = Registrations.blockItem(COIN_STOCK_GOLD_BLOCK);

	private static BlockEntry<CoinStackBlock> coinStackGold()
	{
		return REGISTRY
				.block("decorations/coin_stack_gold", CoinStackBlock::new)
					.lang("Gold Coin Stack")
					.lang(EN_GB, "Gold Coin Stack")

					.initialProperties(Material.METAL)
					.strength(2.5F)
					.sound(SoundType.METAL)
					.noOcclusion()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ITEM_GROUP_CATEGORY_TAG)
					.build()
		.register();
	}
	// endregion

	// region: Item Group Category
	public static final ItemGroupCategory ITEM_GROUP_CATEGORY = ItemGroupCategory
			.builder(ITEM_GROUP_CATEGORY_TAG.getName().toString())
				.tagged(ITEM_GROUP_CATEGORY_TAG)
				.defaultIcon(SWEETROLLS_BLOCK::asItemStack)
			.build();
	// endregion

	static void bootstrap()
	{
		ITEM_GROUP_CATEGORY
				.addTranslationGenerator(REGISTRY, "Decorations")
				.addTranslationGenerator(REGISTRY, EN_GB, "Decorations");

		EventBusHelper.addEnqueuedListener(FMLCommonSetupEvent.class, event -> {
			ItemGroupCategoryManager instance = ItemGroupCategoryManager.getInstance(FFRegistry.MOD_ITEM_GROUP);
			instance.addCategory(ITEM_GROUP_CATEGORY);
		});
	}
}

package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.*;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class Decorations
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Berry Basket
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_EMPTY = berryBasket("empty");
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET_SWEETBERRY = berryBasket("sweetberry");

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

					.blockState(Decorations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Decorations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Boiled Crème Treats
	public static final BlockEntry<BoiledCremeTreatsBlock> BOILED_CREME_TREATS = boiledCremeTreats();

	private static BlockEntry<BoiledCremeTreatsBlock> boiledCremeTreats()
	{
		return REGISTRY
				.block("decorations/boiled_creme_treats", BoiledCremeTreatsBlock::new)
					.lang("Boiled Creme Treats")
					.lang(EN_GB, "Boiled Creme Treats")

					.initialProperties(Material.CAKE)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlock(ctx, provider, BoiledCremeTreatsBlock.TREATS))
					.loot((lootTables, block) -> droppingStacked(lootTables, block, BoiledCremeTreatsBlock.TREATS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> blockItemStacked(ctx, provider, BoiledCremeTreatsBlock.TREATS))
					.build()
		.register();
	}
	// endregion

	// region: Bolts of Cloth
	public static final BlockEntry<BoltsOfClothBlock> BOLTS_OF_CLOTH = boltsOfCloth();

	private static BlockEntry<BoltsOfClothBlock> boltsOfCloth()
	{
		return REGISTRY
				.block("decorations/bolts_of_cloth", BoltsOfClothBlock::new)
					.lang("Bolts of Cloth")
					.lang(EN_GB,"Bolts of Cloth")

					.initialProperties(Material.WOOL)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState(Decorations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Decorations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Book Stack
	public static final BlockEntry<BookStackBlock> BOOK_STACK = bookStack();

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

					.blockState((ctx, provider) -> horizontalBlock(ctx, provider, BookStackBlock.BOOKS))
					// .loot((lootTables, block) -> droppingStacked(lootTables, block, Items.BOOK, BookStackBlock.BOOKS))
				.loot((lootTables, block) -> droppingStacked(lootTables, block, BookStackBlock.BOOKS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> blockItemStacked(ctx, provider, BookStackBlock.BOOKS))
					.build()
		.register();
	}
	// endregion

	// region: Bowl
	public static final BlockEntry<BowlBlock> BOWL_EMPTY = bowl("empty");
	public static final BlockEntry<BowlBlock> BOWL_BEETROOT_SOUP = bowl("beetroot_soup");
	public static final BlockEntry<BowlBlock> BOWL_MUSHROOM_STEW = bowl("mushroom_stew");

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

					.blockState(Decorations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Decorations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Sweetrolls
	public static final BlockEntry<SweetRollsBlock> SWEETROLLS = sweetRolls();

	private static BlockEntry<SweetRollsBlock> sweetRolls()
	{
		return REGISTRY
				.block("decorations/sweetrolls", SweetRollsBlock::new)
					.lang("Sweetrolls")
					.lang(EN_GB, "Sweetrolls")

					.initialProperties(Material.CAKE)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlock(ctx, provider, SweetRollsBlock.ROLLS))
					.loot((lootTables, block) -> droppingStacked(lootTables, block, SweetRollsBlock.ROLLS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> blockItemStacked(ctx, provider, SweetRollsBlock.ROLLS))
					.build()
		.register();
	}
	// endregion

	// region: Mead Bottles
	public static final BlockEntry<MeadBottlesBlock> MEAD_BOTTLES = meadBottles();

	private static BlockEntry<MeadBottlesBlock> meadBottles()
	{
		return REGISTRY
				.block("decorations/mead_bottles", MeadBottlesBlock::new)
					.lang("Mead Bottles")
					.lang(EN_GB, "Mead Bottles")

					.initialProperties(Material.GLASS)
					.strength(2.5F)
					.sound(SoundType.GLASS)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlock(ctx, provider, MeadBottlesBlock.BOTTLES))
					.loot((lootTables, block) -> droppingStacked(lootTables, block, MeadBottlesBlock.BOTTLES))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> blockItemStacked(ctx, provider, MeadBottlesBlock.BOTTLES))
					.build()
		.register();
	}
	// endregion

	// region: Tankards
	public static final BlockEntry<TankardsBlock> TANKARD_EMPTY = tankards("empty");
	public static final BlockEntry<TankardsBlock> TANKARD_HONEYMEAD = tankards("honeymead");
	public static final BlockEntry<TankardsBlock> TANKARD_MILK = tankards("milk");
	public static final BlockEntry<TankardsBlock> TANKARD_SWEETBERRY = tankards("sweetberry");

	private static BlockEntry<TankardsBlock> tankards(String type)
	{
		String codeName = "decorations/tankards_" + type;
		String englishName;

		if(type.equals("empty"))
			englishName = "Tankards";
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
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlock(ctx, provider, TankardsBlock.TANKARDS))
					.loot((lootTables, block) -> droppingStacked(lootTables, block, TankardsBlock.TANKARDS))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> blockItemStacked(ctx, provider, TankardsBlock.TANKARDS))
					.build()
		.register();
	}
	// endregion

	static void bootstrap()
	{
	}

	private static <BLOCK extends Block> void droppingStacked(RegistrateBlockLootTables lootTables, BLOCK block, IntegerProperty property)
	{
		LootTable.Builder lootTable = LootTable.lootTable();

		for(int value : property.getPossibleValues())
		{
			lootTable = lootTable
					.withPool(BlockLootTables
							.applyExplosionCondition(block, LootPool
									.lootPool()
									.setRolls(ConstantRange.exactly(1))
									.add(ItemLootEntry.lootTableItem(block))
									.when(BlockStateProperty
											.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder
													.properties()
													.hasProperty(property, value)
											)
									)
									.apply(SetCount.setCount(ConstantRange.exactly(value + 1)))
							)
					);
		}

		lootTables.add(block, lootTable);
	}

	private static <ITEM extends BlockItem> void blockItem(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider)
	{
		ResourceLocation id = ctx.getId();
		provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), getExistingModelPath(id, ""));
	}

	private static <ITEM extends BlockItem> void blockItemStacked(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider, IntegerProperty property)
	{
		ResourceLocation id = ctx.getId();
		int maxValue = SimpleFourWayStackedBlock.getMaxValue(property);
		provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), getExistingModelPath(id, "_" + maxValue));
	}

	private static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		ResourceLocation id = ctx.getId();
		provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(getExistingModelPath(id, "")));
	}

	private static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, IntegerProperty countProperty)
	{
		ResourceLocation id = ctx.getId();

		provider.horizontalBlock(ctx.get(), blockState -> {
			int count = blockState.getValue(countProperty);
			return provider.models().getExistingFile(getExistingModelPath(id, "_" + count));
		});
	}

	private static ResourceLocation getExistingModelPath(ResourceLocation registryName, String suffix)
	{
		String namespace = registryName.getNamespace();
		String path = registryName.getPath();
		return new ResourceLocation(namespace, "block/" + path + suffix);
	}
}

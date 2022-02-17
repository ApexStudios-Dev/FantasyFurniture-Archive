package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BedItem;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.village.PointOfInterestType;

import xyz.apex.forge.apexcore.lib.util.reflection.FieldHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.*;
import xyz.apex.forge.fantasyfurniture.block.decorations.BerryBasketBlock;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.utility.registrator.builder.BlockBuilder;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.factory.BlockFactory;
import xyz.apex.repack.com.tterrag.registrate.providers.RegistrateLangProvider;
import xyz.apex.repack.com.tterrag.registrate.util.entry.RegistryEntry;

import java.util.Set;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static xyz.apex.repack.com.tterrag.registrate.providers.ProviderType.LANG;

public final class FFBlocks
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Nordic
	public static final BlockEntry<NordicBedSingleBlock> NORDIC_BED_SINGLE = bedSingle("nordic", NordicBedSingleBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicChairBlock> NORDIC_CHAIR = chair("nordic", NordicChairBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicCushionBlock> NORDIC_CUSHION = cushion("nordic", NordicCushionBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicShelfBlock> NORDIC_SHELF = shelf("nordic", NordicShelfBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicTableWideBlock> NORDIC_TABLE_WIDE = tableWide("nordic", NordicTableWideBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// endregion

	// region: Decorations
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET = berryBasket();
	// endregion

	static void bootstrap()
	{
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED_SINGLE, blockState -> blockState.getValue(NordicBedSingleBlock.PART) == BedPart.HEAD);

		REGISTRY.addDataGenerator(LANG, provider -> {
			REGISTRY.getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(BaseSeatBlock.class::isInstance)
			        .map(Block::getDescriptionId)
			        .forEach(s -> provider.add(s + ".occupied", "This seat is occupied"));
		});

		REGISTRY.addDataGenerator(LANG_EXT_PROVIDER, provider -> {
			REGISTRY.getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(BaseSeatBlock.class::isInstance)
			        .map(Block::getDescriptionId)
			        .forEach(s -> provider.add(EN_GB, s + ".occupied", "This seat is occupied"));
		});
	}

	private static <BLOCK extends BaseBedBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bedSingle(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_bed_singe", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Bed Single")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Bed Single")

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/" + type + "/bed_single")), 0))
					.loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, NordicBedSingleBlock.PART, BedPart.HEAD)))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(blockTag, FFTags.Blocks.BEDS)

					.addRenderType(() -> RenderType::cutout)

					.item(BedItem::new)
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/" + type + "/bed_single")))
						.tag(itemTag, FFTags.Items.BEDS)
					.build()
				;
	}

	private static <BLOCK extends BaseSeatBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> chair(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_chair", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Chair")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Chair")

					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/" + type + "/chair")), 0))
					.loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseSeatDoubleBlock.HALF, DoubleBlockHalf.LOWER)))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(blockTag, FFTags.Blocks.CHAIRS)

					.addRenderType(() -> RenderType::cutout)

					.item(BedItem::new)
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/" + type + "/chair")))
						.tag(itemTag, FFTags.Items.CHAIRS)
					.build()
				;
	}

	private static <BLOCK extends BaseSeatBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> cushion(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_cushion", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Cushion")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Cushion")

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOL)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/" + type + "/cushion")), 0))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(blockTag, FFTags.Blocks.CUSHIONS)

					.addRenderType(() -> RenderType::cutout)

					.item(BedItem::new)
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/" + type + "/cushion")))
						.tag(itemTag, FFTags.Items.CUSHIONS)
					.build()
				;
	}

	private static <BLOCK extends BaseShelfBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> shelf(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_shelf", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Shelf")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Shelf")

					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()
					.randomTicks()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						BaseShelfBlock.ConnectionType connectionType = blockState.getValue(BaseShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == BaseShelfBlock.ConnectionType.LEFT || connectionType == BaseShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == BaseShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(REGISTRY.id("block/" + type + "/shelf" + suffix));
					}, 0))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(blockTag, FFTags.Blocks.SHELVES)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/" + type + "/shelf")))
						.tag(itemTag, FFTags.Items.SHELVES)
					.build()
				;
	}

	private static <BLOCK extends BaseTableWideBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> tableWide(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_table_wide", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Table Wide")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Table Wide")

					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/" + type + "/table_wide")), 90))
					.loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(blockTag, FFTags.Blocks.TABLES)

					.addRenderType(() -> RenderType::cutout)

					.item(BedItem::new)
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/" + type + "/table_wide")))
						.tag(itemTag, FFTags.Items.TABLES)
					.build()
				;
	}

	private static BlockEntry<BerryBasketBlock> berryBasket()
	{
		return REGISTRY
				.block("berry_basket", BerryBasketBlock::new)
					.lang("Berry Basket")
					.lang(EN_GB, "Berry Basket")

					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/decorations/berry_basket"))))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(FFTags.Blocks.DECORATIONS)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/decorations/berry_basket")))
						.tag(FFTags.Items.DECORATIONS)
					.build()
				.register()
				;
	}

	private static final Set<Item> EXPLOSION_RESISTANT = FieldHelper.getPrivateValue(BlockLootTables.class, null, "EXPLOSION_RESISTANT");

	private static <T extends Comparable<T> & IStringSerializable> LootTable.Builder createSinglePropConditionTable(Block block, Property<T> property, T value)
	{
		return LootTable
				.lootTable()
				.withPool(
						applyExplosionCondition(block, LootPool
								.lootPool()
								.setRolls(ConstantRange.exactly(1))
								.add(
										ItemLootEntry
												.lootTableItem(block)
												.when(
														BlockStateProperty
																.hasBlockStateProperties(block)
																.setProperties(
																		StatePropertiesPredicate.Builder
																				.properties()
																				.hasProperty(property, value)
																)
												)
								)
						)
				);
	}

	private static <T> T applyExplosionCondition(IItemProvider item, ILootConditionConsumer<T> condition)
	{
		return EXPLOSION_RESISTANT.contains(item.asItem()) ? condition.unwrap() : condition.when(SurvivesExplosion.survivesExplosion());
	}
}

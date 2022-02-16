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
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.village.PointOfInterestType;

import xyz.apex.forge.apexcore.lib.util.reflection.FieldHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.NordicBedSingleBlock;
import xyz.apex.forge.utility.registrator.builder.BlockBuilder;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.factory.BlockFactory;
import xyz.apex.repack.com.tterrag.registrate.providers.RegistrateLangProvider;

import java.util.Set;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FFBlocks
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Nordic
	// region: Bed Single
	public static final BlockEntry<NordicBedSingleBlock> NORDIC_BED_SINGLE = singleBed("nordic", NordicBedSingleBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC)
			.register();
	// endregion
	// endregion

	static void bootstrap()
	{
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED_SINGLE, blockState -> blockState.getValue(NordicBedSingleBlock.PART) == BedPart.HEAD);
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> singleBed(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return REGISTRY
				.block(type + "_bed_singe", blockFactory)
					.lang(RegistrateLangProvider.toEnglishName(type) + " Bed Single")
					.lang(EN_GB, RegistrateLangProvider.toEnglishName(type) + " Bed Single")

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					.blockState((ctx, provider) -> {
						provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/" + type + "/bed_single")), 0);
					})
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

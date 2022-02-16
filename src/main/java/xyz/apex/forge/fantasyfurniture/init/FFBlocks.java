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
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.village.PointOfInterestType;

import xyz.apex.forge.apexcore.lib.util.reflection.FieldHelper;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.NordicBedSingleBlock;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

import java.util.Set;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FFBlocks
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final BlockEntry<NordicBedSingleBlock> NORDIC_BED_SINGLE = REGISTRY
			.block("nordic_bed_single", NordicBedSingleBlock::new)
				.lang("Nordic Bed Single")
				.lang(EN_GB, "Nordic Bed Single")

				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.sound(SoundType.WOOD)
				.strength(.2F)
				.noOcclusion()

				.blockState((ctx, provider) -> {
					provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(Names.NORDIC_BED_SINGLE_TEMPLATE), 0);
				})
				.loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, NordicBedSingleBlock.PART, BedPart.HEAD)))
				.recipe((ctx, provider) -> {
					// TODO:
				})
				.tag(BlockTags.BEDS)

				.addRenderType(() -> RenderType::cutout)

				.item(BedItem::new)
					.stacksTo(1)

					.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), Names.NORDIC_BED_SINGLE_TEMPLATE))
					.tag(ItemTags.BEDS)
				.build()
			.register();

	static void bootstrap()
	{
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED_SINGLE, blockState -> blockState.getValue(NordicBedSingleBlock.PART) == BedPart.HEAD);
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

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
import xyz.apex.forge.fantasyfurniture.block.NordicBedBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicBedBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.renderer.CustomItemStackTileEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.renderer.block.NordicBedBlockEntityRenderer;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;

import java.util.Set;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FFBlocks
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final BlockEntry<NordicBedBlock> NORDIC_BED = REGISTRY
			.block("nordic_bed", NordicBedBlock::new)
				.lang("Nordic Bed")
				.lang(EN_GB, "Nordic Bed")

				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.sound(SoundType.WOOD)
				.strength(.2F)
				.noOcclusion()

				.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().withExistingParent(ctx.getName(), "minecraft:block/bed")))
				.loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, NordicBedBlock.PART, BedPart.HEAD)))
				.recipe((ctx, provider) -> {
					// TODO:
				})
				.tag(BlockTags.BEDS)

				.addRenderType(() -> RenderType::cutout)

				.item(BedItem::new)
					.stacksTo(1)

					.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), "minecraft:item/white_bed"))
					.tag(ItemTags.BEDS)

					.setISTER(() -> () -> CustomItemStackTileEntityRenderer.INSTANCE)
				.build()

				.blockEntity(NordicBedBlockEntity::new)
					.renderer(() -> NordicBedBlockEntityRenderer::new)
				.build()
			.register();

	static void bootstrap()
	{
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED, blockState -> blockState.getValue(NordicBedBlock.PART) == BedPart.HEAD);
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

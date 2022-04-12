package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
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

import xyz.apex.forge.fantasyfurniture.block.base.IStackedBlock;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

public final class Registrations
{
	static <BLOCK extends Block, BLOCK_ITEM extends BlockItem> ItemEntry<BLOCK_ITEM> blockItem(BlockEntry<BLOCK> block)
	{
		return ItemEntry.cast(block.getSibling(Item.class));
	}

	static <BLOCK extends Block> void droppingStacked(RegistrateBlockLootTables lootTables, BLOCK block, IntegerProperty property)
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

	static <ITEM extends BlockItem> void blockItem(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider)
	{
		ResourceLocation id = ctx.getId();
		provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), getExistingModelPath(id, ""));
	}

	static <ITEM extends BlockItem> void blockItemStacked(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider, IntegerProperty property)
	{
		ResourceLocation id = ctx.getId();
		int maxValue = IStackedBlock.getMaxValue(property);
		provider.withExistingParent(id.getNamespace() + ":item/" + id.getPath(), getExistingModelPath(id, "_" + maxValue));
	}

	static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		ResourceLocation id = ctx.getId();
		provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(getExistingModelPath(id, "")));
	}

	static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, IntegerProperty countProperty)
	{
		ResourceLocation id = ctx.getId();

		provider.horizontalBlock(ctx.get(), blockState -> {
			int count = blockState.getValue(countProperty);
			return provider.models().getExistingFile(getExistingModelPath(id, "_" + count));
		});
	}

	static ResourceLocation getExistingModelPath(ResourceLocation registryName, String suffix)
	{
		String namespace = registryName.getNamespace();
		String path = registryName.getPath();
		return new ResourceLocation(namespace, "block/" + path + suffix);
	}
}

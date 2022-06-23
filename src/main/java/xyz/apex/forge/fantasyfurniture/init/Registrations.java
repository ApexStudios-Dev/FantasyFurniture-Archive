package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.builders.MenuBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.MenuEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import xyz.apex.forge.fantasyfurniture.block.base.set.StackedBlock;

public final class Registrations
{
	static void bootstrap()
	{
	}

	static <BLOCK extends Block, BLOCK_ITEM extends BlockItem> ItemEntry<BLOCK_ITEM> blockItem(BlockEntry<BLOCK> block)
	{
		return ItemEntry.cast(block.getSibling(Registry.ITEM_REGISTRY));
	}

	static <BLOCK extends Block, BLOCK_ENTITY extends BlockEntity> BlockEntityEntry<BLOCK_ENTITY> blockEntity(BlockEntry<BLOCK> block)
	{
		return BlockEntityEntry.cast(block.getSibling(Registry.BLOCK_ENTITY_TYPE_REGISTRY));
	}

	static <BLOCK extends Block> void droppingStacked(RegistrateBlockLootTables lootTables, BLOCK block, IntegerProperty property)
	{
		var lootTable = LootTable.lootTable();

		for(var value : property.getPossibleValues())
		{
			lootTable = lootTable
					.withPool(RegistrateBlockLootTables
							.applyExplosionCondition(block, LootPool
									.lootPool()
									.setRolls(ConstantValue.exactly(1))
									.add(LootItem.lootTableItem(block))
									.when(LootItemBlockStatePropertyCondition
											.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder
													.properties()
													.hasProperty(property, value)
											)
									)
									.apply(SetItemCountFunction.setCount(ConstantValue.exactly(value + 1)))
							)
					);
		}

		lootTables.add(block, lootTable);
	}

	static <ITEM extends BlockItem> void blockItem(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider)
	{
		var id = ctx.getId();
		provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), getExistingModelPath(id, ""));
	}

	static <ITEM extends BlockItem> void blockItemStacked(DataGenContext<Item, ITEM> ctx, RegistrateItemModelProvider provider, IntegerProperty property)
	{
		var id = ctx.getId();
		var maxValue = StackedBlock.getMaxValue(property);
		provider.withExistingParent("%s:item/%s".formatted(id.getNamespace(), id.getPath()), getExistingModelPath(id, "_%d".formatted(maxValue)));
	}

	static <BLOCK extends Block> void simpleBlockWithStates(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		var id = ctx.getId();

		provider.getVariantBuilder(ctx.get())
		        .forAllStates(blockState -> ConfiguredModel
				        .builder()
				        .modelFile(provider
						        .models()
						        .getExistingFile(getExistingModelPath(id, ""))
				        )
				        .build()
		        );
	}

	static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		var id = ctx.getId();
		provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(getExistingModelPath(id, "")));
	}

	static <BLOCK extends Block> void horizontalBlock(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, IntegerProperty countProperty)
	{
		var id = ctx.getId();

		provider.horizontalBlock(ctx.get(), blockState -> {
			var count = blockState.getValue(countProperty);
			return provider.models().getExistingFile(getExistingModelPath(id, "_%d".formatted(count)));
		});
	}

	static ResourceLocation getExistingModelPath(ResourceLocation registryName, String suffix)
	{
		var namespace = registryName.getNamespace();
		var path = registryName.getPath();
		return new ResourceLocation(namespace, "block/%s%s".formatted(path, suffix));
	}

	static <MENU extends AbstractContainerMenu, SCREEN extends AbstractContainerScreen<MENU> & MenuAccess<MENU>> MenuEntry<MENU> container(String registryName, MenuBuilder.ForgeMenuFactory<MENU> menuFactory, NonNullSupplier<MenuBuilder.ScreenFactory<MENU, SCREEN>> screenFactory)
	{
		return FFRegistry.INSTANCE
				.object(registryName)
				.menu(
					menuFactory,
					screenFactory
				)
		.register();
	}

	static <BLOCK extends Block, MENU extends AbstractContainerMenu, SCREEN extends AbstractContainerScreen<MENU> & MenuAccess<MENU>> MenuEntry<MENU> container(BlockEntry<BLOCK> block, MenuBuilder.ForgeMenuFactory<MENU> menuFactory, NonNullSupplier<MenuBuilder.ScreenFactory<MENU, SCREEN>> screenFactory)
	{
		var blockName = block.getId().getPath();
		return container(blockName, menuFactory, screenFactory);
	}
}

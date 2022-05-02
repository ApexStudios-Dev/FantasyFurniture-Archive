package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.base.FurnitureStationBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.FurnitureStationBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ContainerEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class FurnitureStation
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final int CLAY_SLOT = 0;
	public static final int RED_DYE_SLOT = 1;
	public static final int YELLOW_DYE_SLOT = 2;
	public static final int BLUE_DYE_SLOT = 3;
	public static final int RESULT_SLOT = 4;

	public static final BlockEntry<FurnitureStationBlock> BLOCK = block();
	public static final ItemEntry<BlockItem> BLOCK_ITEM = Registrations.blockItem(BLOCK);
	public static final BlockEntityEntry<FurnitureStationBlockEntity> BLOCK_ENTITY = Registrations.blockEntity(BLOCK);
	public static final ContainerEntry<FurnitureStationContainer> CONTAINER = container();

	// items with this tag can be crafted from the furniture station
	public static final ITag.INamedTag<Item> CRAFTABLE = REGISTRY.moddedItemTag("craftable");
	// used to support any axe type, we tag the vanilla axes
	// unsure if a community accepted name exists for this
	// used common name space and similar naming to ores
	// forge:tools/axe
	public static final ITag.INamedTag<Item> TOOLS_AXE = REGISTRY.forgeItemTag("tools/axe");

	static void bootstrap()
	{
		REGISTRY.addDataGenerator(ProviderType.ITEM_TAGS, provider -> {
			provider.tag(CRAFTABLE);

			provider.tag(TOOLS_AXE).add(
					Items.WOODEN_AXE,
					Items.STONE_AXE,
					Items.IRON_AXE,
					Items.GOLDEN_AXE,
					Items.DIAMOND_AXE,
					Items.NETHERITE_AXE
			);
		});
	}

	private static BlockEntry<FurnitureStationBlock> block()
	{
		return REGISTRY
				.block("furniture_station", FurnitureStationBlock::new)
				.lang("Furniture Station")
				.lang(EN_GB, "Furniture Station")

				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()

				.blockState(Registrations::horizontalBlock)

				.recipe((ctx, provider) -> SmithingRecipeBuilder
						.smithing(DataIngredient.items(Items.CRAFTING_TABLE), DataIngredient.tag(Tags.Items.LEATHER), ctx.get().asItem())
						.unlocks("has_crafting_table", RegistrateRecipeProvider.hasItem(Items.CRAFTING_TABLE))
						.unlocks("has_leather", RegistrateRecipeProvider.hasItem(Tags.Items.LEATHER))
						.save(provider, ctx.getId())
				)

				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)

				.addRenderType(() -> RenderType::cutout)

				.item()
					.model(Registrations::blockItem)
				.build()

				.simpleBlockEntity(FurnitureStationBlockEntity::new)
		.register();
	}

	private static ContainerEntry<FurnitureStationContainer> container()
	{
		return REGISTRY
				.container(
						"furniture_station",
						(containerType, windowId, playerInventory, buffer) -> new FurnitureStationContainer(containerType, windowId, playerInventory, IWorldPosCallable.NULL),
						() -> FurnitureStationContainerScreen::new
				)
		.register();
	}
}

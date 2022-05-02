package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.ProviderType;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IWorldPosCallable;

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

	static void bootstrap()
	{
		REGISTRY.addDataGenerator(ProviderType.ITEM_TAGS, provider -> provider.tag(CRAFTABLE));
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

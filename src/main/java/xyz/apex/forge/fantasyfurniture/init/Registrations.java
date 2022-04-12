package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import xyz.apex.forge.fantasyfurniture.block.base.IStackedBlock;
import xyz.apex.forge.fantasyfurniture.client.renderer.entity.SeatEntityRenderer;
import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;
import xyz.apex.forge.utility.registrator.entry.*;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class Registrations
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Seat Entity
	public static final EntityEntry<SeatEntity> SEAT_ENTITY = REGISTRY
			.<SeatEntity>entity("seat", EntityClassification.MISC, SeatEntity::new)
				.lang("Seat")
				.lang(EN_GB, "Seat")

				.sized(0F, 0F)
				.setCustomClientFactory((spawnEntity, level) -> new SeatEntity(level))

				.noSummon()
				.fireImmune()
				.immuneTo(() -> Blocks.TNT, () -> Blocks.LAVA)

				.renderer(() -> SeatEntityRenderer::new)
			.register();
	// endregion

	public static final ResourceLocation SMALL_STORAGE_TEXTURE = REGISTRY.id("textures/gui/container/small_storage.png");
	public static final ResourceLocation MEDIUM_STORAGE_TEXTURE = REGISTRY.id("textures/gui/container/medium_storage.png");
	public static final ResourceLocation LARGE_STORGE_TEXTURE = REGISTRY.id("textures/gui/container/large_storage.png");

	static void bootstrap()
	{
	}

	static <BLOCK extends Block, BLOCK_ITEM extends BlockItem> ItemEntry<BLOCK_ITEM> blockItem(BlockEntry<BLOCK> block)
	{
		return ItemEntry.cast(block.getSibling(Item.class));
	}

	static <BLOCK extends Block, BLOCK_ENTITY extends TileEntity> BlockEntityEntry<BLOCK_ENTITY> blockEntity(BlockEntry<BLOCK> block)
	{
		return BlockEntityEntry.cast(block.getSibling(TileEntityType.class));
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

	static <CONTAINER extends Container, SCREEN extends Screen & IHasContainer<CONTAINER>> ContainerEntry<CONTAINER> container(String registryName, int rows, int cols, ContainerFactory<CONTAINER> containerFactory, xyz.apex.forge.utility.registrator.factory.ContainerFactory.ScreenFactory<CONTAINER, SCREEN> screenFactory)
	{
		return REGISTRY.container(
				registryName,
				(containerType, windowId, playerInventory, buffer) -> containerFactory.create(containerType, windowId, playerInventory, new ItemStackHandler(rows * cols)),
				() -> screenFactory
		).register();
	}

	static <BLOCK extends Block, CONTAINER extends Container, SCREEN extends Screen & IHasContainer<CONTAINER>> ContainerEntry<CONTAINER> container(BlockEntry<BLOCK> block, int rows, int cols, ContainerFactory<CONTAINER> containerFactory, xyz.apex.forge.utility.registrator.factory.ContainerFactory.ScreenFactory<CONTAINER, SCREEN> screenFactory)
	{
		String blockName = block.getId().getPath();
		return container(blockName, rows, cols, containerFactory, screenFactory);
	}

	@FunctionalInterface
	public interface ContainerFactory<CONTAINER extends Container>
	{
		CONTAINER create(ContainerType<?> containerType, int windowId, PlayerInventory playerInventory, IItemHandler itemHandler);
	}
}

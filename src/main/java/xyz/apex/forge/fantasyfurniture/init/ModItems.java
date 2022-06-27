package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.block.furniture.BedBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.FurnitureDoorBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.furniture.SofaBlock;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class ModItems
{
	public static final ItemEntry<BlockItem> NORDIC_WOOL = wool(ModBlocks.NORDIC_WOOL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CARPET = carpet(ModBlocks.NORDIC_CARPET).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WALL_LIGHT = blockItem(ModBlocks.NORDIC_WALL_LIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_FLOOR_LIGHT = blockItem(ModBlocks.NORDIC_FLOOR_LIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_SMALL = blockItem(ModBlocks.NORDIC_TABLE_SMALL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_WIDE = blockItem(ModBlocks.NORDIC_TABLE_WIDE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_TABLE_LARGE = blockItem(ModBlocks.NORDIC_TABLE_LARGE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_STOOL = blockItem(ModBlocks.NORDIC_STOOL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CUSHION = blockItem(ModBlocks.NORDIC_CUSHION).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_SMALL = blockItem(ModBlocks.NORDIC_PAINTING_SMALL).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_PAINTING_WIDE = blockItem(ModBlocks.NORDIC_PAINTING_WIDE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DRAWER = blockItem(ModBlocks.NORDIC_DRAWER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_SHELF = shelf(ModBlocks.NORDIC_SHELF).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_SOFA = sofa(ModBlocks.NORDIC_SOFA).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DESK_LEFT = blockItem(ModBlocks.NORDIC_DESK_LEFT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DESK_RIGHT = blockItem(ModBlocks.NORDIC_DESK_RIGHT).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHAIR = blockItem(ModBlocks.NORDIC_CHAIR).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BENCH = blockItem(ModBlocks.NORDIC_BENCH).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BOOKSHELF = blockItem(ModBlocks.NORDIC_BOOKSHELF).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHEST = blockItem(ModBlocks.NORDIC_CHEST).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DRESSER = blockItem(ModBlocks.NORDIC_DRESSER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WARDROBE_BOTTOM = blockItem(ModBlocks.NORDIC_WARDROBE_BOTTOM).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_WARDROBE_TOP = blockItem(ModBlocks.NORDIC_WARDROBE_TOP).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BED_SINGLE = bed(ModBlocks.NORDIC_BED_SINGLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_BED_DOUBLE = bed(ModBlocks.NORDIC_BED_DOUBLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_CHANDELIER = blockItem(ModBlocks.NORDIC_CHANDELIER).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DOOR_SINGLE = door(ModBlocks.NORDIC_DOOR_SINGLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_DOOR_DOUBLE = door(ModBlocks.NORDIC_DOOR_DOUBLE).tag(ModItemGroupCategories.NORDIC_TAG).register();
	public static final ItemEntry<BlockItem> NORDIC_LOCKBOX = blockItem(ModBlocks.NORDIC_LOCKBOX).tag(ModItemGroupCategories.NORDIC_TAG).register();

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <BLOCK extends Block> ItemBuilder<Registrate, BlockItem, Registrate> wool(BlockEntry<BLOCK> block)
	{
		return blockItem(block).tag(ItemTags.Vanilla.WOOL);
	}

	private static <BLOCK extends CarpetBlock> ItemBuilder<Registrate, BlockItem, Registrate> carpet(BlockEntry<BLOCK> block)
	{
		return blockItem(block).tag(ItemTags.Vanilla.CARPETS);
	}

	private static <BLOCK extends ShelfBlock> ItemBuilder<Registrate, BlockItem, Registrate> shelf(BlockEntry<BLOCK> block)
	{
		return blockItem(block)
				.tag(ItemTags.Vanilla.BEDS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends SofaBlock> ItemBuilder<Registrate, BlockItem, Registrate> sofa(BlockEntry<BLOCK> block)
	{
		return blockItem(block)
				.tag(ItemTags.Vanilla.BEDS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_single".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends BedBlock> ItemBuilder<Registrate, BlockItem, Registrate> bed(BlockEntry<BLOCK> block)
	{
		return blockItem(block).tag(ItemTags.Vanilla.BEDS);
	}

	private static <BLOCK extends FurnitureDoorBlock> ItemBuilder<Registrate, BlockItem, Registrate> door(BlockEntry<BLOCK> block)
	{
		return blockItem(block)
				.tag(ItemTags.Vanilla.WOODEN_DOORS)
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s_left".formatted(ctx.getId().getPath()))
						)
				)
		;
	}

	private static <BLOCK extends Block> ItemBuilder<Registrate, BlockItem, Registrate> blockItem(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new BlockItem(block.get(), properties))
				.setData(LANG, NonNullBiConsumer.noop())
				.model((ctx, provider) -> provider
						.withExistingParent(
								"%s:item/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
								new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
						)
				)
		;
	}
	// endregion
}
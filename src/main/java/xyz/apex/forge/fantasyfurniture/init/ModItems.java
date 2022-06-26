package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.forge.commonality.tags.ItemTags;
import xyz.apex.forge.fantasyfurniture.block.FurnitureWallLightBlock;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class ModItems
{
	public static final ItemEntry<BlockItem> NORDIC_WOOL = wool(ModBlocks.NORDIC_WOOL).register();
	public static final ItemEntry<BlockItem> NORDIC_CARPET = carpet(ModBlocks.NORDIC_CARPET).register();
	public static final ItemEntry<BlockItem> NORDIC_WALLLIGHT = wallLight(ModBlocks.NORDIC_WALLIGHT).register();

	static void bootstrap()
	{
	}

	// region: Constructors
	private static <BLOCK extends Block> ItemBuilder<Registrate, BlockItem, Registrate> wool(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new BlockItem(block.get(), properties))
				.transform(ModItems::applyBlockItemDefaults)
				.tag(ItemTags.Vanilla.WOOL)
		;
	}

	private static <BLOCK extends CarpetBlock> ItemBuilder<Registrate, BlockItem, Registrate> carpet(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new BlockItem(block.get(), properties))
				.transform(ModItems::applyBlockItemDefaults)
				.tag(ItemTags.Vanilla.CARPETS)
		;
	}

	private static <BLOCK extends FurnitureWallLightBlock> ItemBuilder<Registrate, BlockItem, Registrate> wallLight(BlockEntry<BLOCK> block)
	{
		return REGISTRATE
				.object(block.getId().getPath())
				.item(properties -> new BlockItem(block.get(), properties))
				.transform(ModItems::applyBlockItemDefaults)
		;
	}

	private static <ITEM extends Item> ItemBuilder<Registrate, ITEM, Registrate> applyBlockItemDefaults(ItemBuilder<Registrate, ITEM, Registrate> builder)
	{
		return builder
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
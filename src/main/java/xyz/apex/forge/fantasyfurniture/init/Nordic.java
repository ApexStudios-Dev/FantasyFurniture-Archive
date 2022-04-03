package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.block.BaseCarpetBlock;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ItemEntry;

import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;

public final class Nordic
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	// region: Wool
	public static final BlockEntry<Block> WOOL_BLOCK = wool();

	public static final ItemEntry<BlockItem> WOOL_BLOCK_ITEM = Registrations.blockItem(WOOL_BLOCK);

	private static BlockEntry<Block> wool()
	{
		String location = REGISTRY.idString("block/nordic/wool");

		return REGISTRY
				.block("nordic/wool")
					.lang("Nordic Wool")
					.lang(EN_GB, "Nordic Wool")

					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location))))

					.item()
						.model(Registrations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Carpet
	public static final BlockEntry<BaseCarpetBlock> CARPET_BLOCK = carpet();

	public static final ItemEntry<BlockItem> CARPET_BLOCK_ITEM = Registrations.blockItem(CARPET_BLOCK);

	private static BlockEntry<BaseCarpetBlock> carpet()
	{
		ResourceLocation locationWool = REGISTRY.id("block/nordic/wool");
		String location = REGISTRY.idString("block/nordic/carpet");

		return REGISTRY
				.block("nordic/carpet", BaseCarpetBlock::new)
					.lang("Nordic Carpet")
					.lang(EN_GB, "Nordic Carpet")

					.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
					.strength(2.5F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().carpet(location, locationWool)))

					.item()
						.model(Registrations::blockItem)
					.build()
		.register();
	}
	// endregion

	static void bootstrap()
	{
	}
}

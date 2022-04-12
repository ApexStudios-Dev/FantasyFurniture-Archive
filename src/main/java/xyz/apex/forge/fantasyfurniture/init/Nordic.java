package xyz.apex.forge.fantasyfurniture.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.base.BaseCarpetBlock;
import xyz.apex.forge.fantasyfurniture.block.nordic.NordicCushionBlock;
import xyz.apex.forge.fantasyfurniture.block.nordic.NordicStoolBlock;
import xyz.apex.forge.fantasyfurniture.block.nordic.NordicTableSmall;
import xyz.apex.forge.fantasyfurniture.block.nordic.NordicWallLightBlock;
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

	// region: Wall Light
	public static final BlockEntry<NordicWallLightBlock> WALL_LIGHT_BLOCK = wallLight();

	public static final ItemEntry<BlockItem> WALL_LIGHT_BLOCK_ITEM = Registrations.blockItem(WALL_LIGHT_BLOCK);

	private static BlockEntry<NordicWallLightBlock> wallLight()
	{
		return REGISTRY
				.block("nordic/wall_light", NordicWallLightBlock::new)
					.lang("Nordic Wall Light")
					.lang(EN_GB, "Nordic Wall Light")

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()
					.lightLevel(blockState -> 14)

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Table Small
	public static final BlockEntry<NordicTableSmall> TABLE_SMALL_BLOCK = tableSmall();

	public static final ItemEntry<BlockItem> TABLE_SMALL_BLOCK_ITEM = Registrations.blockItem(TABLE_SMALL_BLOCK);

	private static BlockEntry<NordicTableSmall> tableSmall()
	{
		return REGISTRY
				.block("nordic/table_small", NordicTableSmall::new)
					.lang("Nordic Table Small")
					.lang(EN_GB, "Nordic Table Small")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Stool
	public static final BlockEntry<NordicStoolBlock> STOOL_BLOCK = stool();

	public static final ItemEntry<BlockItem> STOOL_BLOCK_ITEM = Registrations.blockItem(STOOL_BLOCK);

	private static BlockEntry<NordicStoolBlock> stool()
	{
		return REGISTRY
				.block("nordic/stool", NordicStoolBlock::new)
					.lang("Nordic Stool")
					.lang(EN_GB, "Nordic Stool")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
					.build()
		.register();
	}
	// endregion

	// region: Cushion
	public static final BlockEntry<NordicCushionBlock> CUSHION_BLOCK = cushion();

	public static final ItemEntry<BlockItem> CUSHION_BLOCK_ITEM = Registrations.blockItem(CUSHION_BLOCK);

	private static BlockEntry<NordicCushionBlock> cushion()
	{
		return REGISTRY
				.block("nordic/cushion", NordicCushionBlock::new)
					.lang("Nordic Cushion")
					.lang(EN_GB, "Nordic Cushion")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()

					.blockState(Registrations::horizontalBlock)

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

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

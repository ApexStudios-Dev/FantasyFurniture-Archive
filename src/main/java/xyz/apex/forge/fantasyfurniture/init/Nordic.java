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
import xyz.apex.forge.fantasyfurniture.block.base.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicDrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.fantasyfurniture.client.screen.NordicDrawerContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.NordicDrawerContainer;
import xyz.apex.forge.utility.registrator.entry.BlockEntityEntry;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.entry.ContainerEntry;
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

	// region: Painting Small
	public static final BlockEntry<NordicPaintingBlock> PAINTING_SMALL_BLOCK = paintingSmall();
	public static final ItemEntry<BlockItem> PAINTING_SMALL_BLOCK_ITEM = Registrations.blockItem(PAINTING_SMALL_BLOCK);

	private static BlockEntry<NordicPaintingBlock> paintingSmall()
	{
		return REGISTRY
				.block("nordic/painting_small", NordicPaintingBlock::new)
					.lang("Nordic Painting Small")
					.lang(EN_GB, "Nordic Painting Small")

					.initialProperties(Material.WOOD)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.noCollission()

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

	// region: Drawer
	public static final BlockEntry<NordicDrawerBlock> DRAWER_BLOCK = drawer();
	public static final ItemEntry<BlockItem> DRAWER_BLOCK_ITEM = Registrations.blockItem(DRAWER_BLOCK);
	public static final ContainerEntry<NordicDrawerContainer> DRAWER_CONTAINER = Registrations.container(DRAWER_BLOCK, NordicDrawerContainer.ROWS, NordicDrawerContainer.COLS, NordicDrawerContainer::new, NordicDrawerContainerScreen::new);
	public static final BlockEntityEntry<NordicDrawerBlockEntity> DRAWER_BLOCK_ENTITY = Registrations.blockEntity(DRAWER_BLOCK);

	private static BlockEntry<NordicDrawerBlock> drawer()
	{
		return REGISTRY
				.block("nordic/drawer", NordicDrawerBlock::new)
					.lang("Nordic Drawer")
					.lang(EN_GB, "Nordic Drawer")

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

					.simpleBlockEntity(NordicDrawerBlockEntity::new)
		.register();
	}
	// endregion

	// region: Shelf
	public static final BlockEntry<NordicShelfBlock> SHELF_BLOCK = shelf();
	public static final ItemEntry<BlockItem> SHELF_BLOCK_ITEM = Registrations.blockItem(SHELF_BLOCK);

	private static BlockEntry<NordicShelfBlock> shelf()
	{
		return REGISTRY
				.block("nordic/shelf", NordicShelfBlock::new)
					.lang("Nordic Shelf")
					.lang(EN_GB, "Nordic Shelf")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						ShelfBlock.ConnectionType connectionType = blockState.getValue(ShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == ShelfBlock.ConnectionType.LEFT || connectionType == ShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == ShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(REGISTRY.id("block/nordic/shelf" + suffix));
					}, 180))

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

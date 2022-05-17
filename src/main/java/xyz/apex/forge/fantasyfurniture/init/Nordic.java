package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.BlockItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.base.BaseCarpetBlock;
import xyz.apex.forge.fantasyfurniture.block.base.ShelfBlock;
import xyz.apex.forge.fantasyfurniture.block.base.SofaBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicChestBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicDrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicDresserBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.entity.NordicWardrobeBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.fantasyfurniture.client.screen.NordicChestContainerScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.NordicDrawerContainerScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.NordicDresserContainerScreen;
import xyz.apex.forge.fantasyfurniture.client.screen.NordicWardrobeContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.NordicChestContainer;
import xyz.apex.forge.fantasyfurniture.container.NordicDrawerContainer;
import xyz.apex.forge.fantasyfurniture.container.NordicDresserContainer;
import xyz.apex.forge.fantasyfurniture.container.NordicWardrobeContainer;
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
					.strength(.8F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location))))

					.tag(BlockTags.WOOL)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.WOOL)
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
					.strength(.1F)
					.sound(SoundType.WOOL)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().carpet(location, locationWool)))

					.tag(BlockTags.CARPETS)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE, ItemTags.CARPETS)
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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Floor Light
	public static final BlockEntry<NordicFloorLightBlock> FLOOR_LIGHT_BLOCK = floorLight();
	public static final ItemEntry<BlockItem> FLOOR_LIGHT_BLOCK_ITEM = Registrations.blockItem(FLOOR_LIGHT_BLOCK);

	private static BlockEntry<NordicFloorLightBlock> floorLight()
	{
		return REGISTRY
				.multiBlock("nordic/floor_light", NordicFloorLightBlock::new, FFPatterns.PATTERN_2x1_FLOOR_LIGHT)
					.lang("Nordic Floor Light")
					.lang(EN_GB, "Nordic Floor Light")

					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.noOcclusion()
					.instabreak()
					.lightLevel(blockState -> blockState.getValue(NordicFloorLightBlock.SIDE) == NordicFloorLightBlock.Side.TOP ? 14 : 0)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().getExistingFile(Registrations.getExistingModelPath(ctx.getId(), ""))))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model(Registrations::blockItem)
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Table
	// region: Small
	public static final BlockEntry<NordicTableSmallBlock> TABLE_SMALL_BLOCK = tableSmall();
	public static final ItemEntry<BlockItem> TABLE_SMALL_BLOCK_ITEM = Registrations.blockItem(TABLE_SMALL_BLOCK);

	private static BlockEntry<NordicTableSmallBlock> tableSmall()
	{
		return REGISTRY
				.block("nordic/table_small", NordicTableSmallBlock::new)
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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Wide
	public static final BlockEntry<NordicTableWideBlock> TABLE_WIDE_BLOCK = tableWide();
	public static final ItemEntry<BlockItem> TABLE_WIDE_BLOCK_ITEM = Registrations.blockItem(TABLE_WIDE_BLOCK);

	private static BlockEntry<NordicTableWideBlock> tableWide()
	{
		return REGISTRY
				.multiBlock("nordic/table_wide", NordicTableWideBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Table Wide")
					.lang(EN_GB, "Nordic Table Wide")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Large
	public static final BlockEntry<NordicTableLargeBlock> TABLE_LARGE_BLOCK = tableLarge();
	public static final ItemEntry<BlockItem> TABLE_LARGE_BLOCK_ITEM = Registrations.blockItem(TABLE_LARGE_BLOCK);

	private static BlockEntry<NordicTableLargeBlock> tableLarge()
	{
		return REGISTRY
				.multiBlock("nordic/table_large", NordicTableLargeBlock::new, FFPatterns.PATTERN_2x2)
					.lang("Nordic Table Large")
					.lang(EN_GB, "Nordic Table Large")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion
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
						.tag(FurnitureStation.CRAFTABLE)
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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Painting
	// region: Small
	public static final BlockEntry<NordicPaintingSmallBlock> PAINTING_SMALL_BLOCK = paintingSmall();
	public static final ItemEntry<BlockItem> PAINTING_SMALL_BLOCK_ITEM = Registrations.blockItem(PAINTING_SMALL_BLOCK);

	private static BlockEntry<NordicPaintingSmallBlock> paintingSmall()
	{
		return REGISTRY
				.block("nordic/painting_small", NordicPaintingSmallBlock::new)
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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Wide
	public static final BlockEntry<NordicPaintingWideBlock> PAINTING_WIDE_BLOCK = paintingWide();
	public static final ItemEntry<BlockItem> PAINTING_WIDE_BLOCK_ITEM = Registrations.blockItem(PAINTING_WIDE_BLOCK);

	private static BlockEntry<NordicPaintingWideBlock> paintingWide()
	{
		return REGISTRY
				.multiBlock("nordic/painting_wide", NordicPaintingWideBlock::new, FFPatterns.PATTERN_1x2_PAINTING)
					.lang("Nordic Painting Wide")
					.lang(EN_GB, "Nordic Painting Wide")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion
	// endregion

	// region: Drawer
	public static final BlockEntry<NordicDrawerBlock> DRAWER_BLOCK = drawer();
	public static final ItemEntry<BlockItem> DRAWER_BLOCK_ITEM = Registrations.blockItem(DRAWER_BLOCK);
	public static final ContainerEntry<NordicDrawerContainer> DRAWER_CONTAINER = Registrations.container(DRAWER_BLOCK, NordicDrawerContainer.ROWS, NordicDrawerContainer.COLS, NordicDrawerContainer::new, () -> NordicDrawerContainerScreen::new);
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
						.tag(FurnitureStation.CRAFTABLE)
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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Sofa
	public static final BlockEntry<NordicSofaBlock> SOFA_BLOCK = sofa();
	public static final ItemEntry<BlockItem> SOFA_BLOCK_ITEM = Registrations.blockItem(SOFA_BLOCK);

	private static BlockEntry<NordicSofaBlock> sofa()
	{
		return REGISTRY
				.block("nordic/sofa", NordicSofaBlock::new)
					.lang("Nordic Sofa")
					.lang(EN_GB, "Nordic Sofa")

					.initialProperties(Material.WOOD)
					.strength(2.5F)
					.sound(SoundType.WOOD)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						Direction facing = blockState.getValue(SofaBlock.FACING);
						SofaBlock.ConnectionType connectionType = blockState.getValue(SofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(REGISTRY.id("block/nordic/sofa_" + connectionType.getSerializedName())))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))

					.isValidSpawn(BlockHelper::never)
					.isRedstoneConductor(BlockHelper::never)
					.isSuffocating(BlockHelper::never)
					.isViewBlocking(BlockHelper::never)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), REGISTRY.id("block/nordic/sofa_single")))
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Desk
	public static final BlockEntry<NordicDeskBlock> DESK_LEFT_BLOCK = desk("left");
	public static final ItemEntry<BlockItem> DESK_LEFT_BLOCK_ITEM = Registrations.blockItem(DESK_LEFT_BLOCK);

	public static final BlockEntry<NordicDeskBlock> DESK_RIGHT_BLOCK = desk("right");
	public static final ItemEntry<BlockItem> DESK_RIGHT_BLOCK_ITEM = Registrations.blockItem(DESK_RIGHT_BLOCK);

	private static BlockEntry<NordicDeskBlock> desk(String side)
	{
		String engName = RegistrateLangProvider.toEnglishName(side);

		return REGISTRY
				.multiBlock("nordic/desk_" + side, NordicDeskBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Desk " + engName)
					.lang(EN_GB, "Nordic Desk " + engName)

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Chair
	public static final BlockEntry<NordicChairBlock> CHAIR_BLOCK = chair();
	public static final ItemEntry<BlockItem> CHAIR_BLOCK_ITEM = Registrations.blockItem(CHAIR_BLOCK);

	private static BlockEntry<NordicChairBlock> chair()
	{
		return REGISTRY
				.multiBlock("nordic/chair", NordicChairBlock::new, FFPatterns.PATTERN_2x1)
					.lang("Nordic Chair")
					.lang(EN_GB, "Nordic Chair")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Bench
	public static final BlockEntry<NordicBenchBlock> BENCH_BLOCK = bench();
	public static final ItemEntry<BlockItem> BENCH_BLOCK_ITEM = Registrations.blockItem(BENCH_BLOCK);

	private static BlockEntry<NordicBenchBlock> bench()
	{
		return REGISTRY
				.multiBlock("nordic/bench", NordicBenchBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Bench")
					.lang(EN_GB, "Nordic Bench")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Bookshelf
	public static final BlockEntry<NordicBookshelfBlock> BOOKSHELF_BLOCK = bookshelf();
	public static final ItemEntry<BlockItem> BOOKSHELF_BLOCK_ITEM = Registrations.blockItem(BOOKSHELF_BLOCK);

	private static BlockEntry<NordicBookshelfBlock> bookshelf()
	{
		return REGISTRY
				.multiBlock("nordic/bookshelf", NordicBookshelfBlock::new, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang("Nordic Bookshelf")
					.lang(EN_GB, "Nordic Bookshelf")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion

	// region: Chest
	public static final BlockEntry<NordicChestBlock> CHEST_BLOCK = chest();
	public static final ItemEntry<BlockItem> CHEST_BLOCK_ITEM = Registrations.blockItem(CHEST_BLOCK);
	public static final ContainerEntry<NordicChestContainer> CHEST_CONTAINER = Registrations.container(CHEST_BLOCK, NordicChestContainer.ROWS, NordicChestContainer.COLS, NordicChestContainer::new, () -> NordicChestContainerScreen::new);
	public static final BlockEntityEntry<NordicChestBlockEntity> CHEST_BLOCK_ENTITY = Registrations.blockEntity(CHEST_BLOCK);

	private static BlockEntry<NordicChestBlock> chest()
	{
		return REGISTRY
				.multiBlock("nordic/chest", NordicChestBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Chest")
					.lang(EN_GB, "Nordic Chest")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()

					.simpleBlockEntity(NordicChestBlockEntity::new)
		.register();
	}
	// endregion

	// region: Dresser
	public static final BlockEntry<NordicDresserBlock> DRESSER_BLOCK = dresser();
	public static final ItemEntry<BlockItem> DRESSER_BLOCK_ITEM = Registrations.blockItem(DRESSER_BLOCK);
	public static final ContainerEntry<NordicDresserContainer> DRESSER_CONTAINER = Registrations.container(DRESSER_BLOCK, NordicDresserContainer.ROWS, NordicDresserContainer.COLS, NordicDresserContainer::new, () -> NordicDresserContainerScreen::new);
	public static final BlockEntityEntry<NordicDresserBlockEntity> DRESSER_BLOCK_ENTITY = Registrations.blockEntity(DRESSER_BLOCK);

	private static BlockEntry<NordicDresserBlock> dresser()
	{
		return REGISTRY
				.multiBlock("nordic/dresser", NordicDresserBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Dresser")
					.lang(EN_GB, "Nordic Dresser")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()

					.simpleBlockEntity(NordicDresserBlockEntity::new)
		.register();
	}
	// endregion

	// region: Wardrobe
	// region: Bottom
	public static final BlockEntry<NordicWardrobeBlock> WARDROBE_BLOCK = wardrobeBottom();
	public static final ItemEntry<BlockItem> WARDROBE_BLOCK_ITEM = Registrations.blockItem(WARDROBE_BLOCK);
	public static final ContainerEntry<NordicWardrobeContainer> WARDROBE_CONTAINER = Registrations.container(WARDROBE_BLOCK, NordicWardrobeContainer.ROWS, NordicWardrobeContainer.COLS, NordicWardrobeContainer::new, () -> NordicWardrobeContainerScreen::new);
	public static final BlockEntityEntry<NordicWardrobeBlockEntity> WARDROBE_BLOCK_ENTITY = Registrations.blockEntity(WARDROBE_BLOCK);

	private static BlockEntry<NordicWardrobeBlock> wardrobeBottom()
	{
		return REGISTRY
				.multiBlock("nordic/wardrobe_bottom", NordicWardrobeBlock::new, FFPatterns.PATTERN_2x2_VERTICAL)
					.lang("Nordic Wardrobe")
					.lang(EN_GB, "Nordic Wardrobe")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()

					.simpleBlockEntity(NordicWardrobeBlockEntity::new)
		.register();
	}
	// endregion

	// region: Top
	public static final BlockEntry<NordicWardrobeTopperBlock> WARDROBE_TOPPER_BLOCK = wardrobeTop();
	public static final ItemEntry<BlockItem> WARDROBE_TOPPER_BLOCK_ITEM = Registrations.blockItem(WARDROBE_TOPPER_BLOCK);

	private static BlockEntry<NordicWardrobeTopperBlock> wardrobeTop()
	{
		return REGISTRY
				.multiBlock("nordic/wardrobe_top", NordicWardrobeTopperBlock::new, FFPatterns.PATTERN_1x2)
					.lang("Nordic Wardrobe Top")
					.lang(EN_GB, "Nordic Wardrobe Top")

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
						.tag(FurnitureStation.CRAFTABLE)
					.build()
		.register();
	}
	// endregion
	// endregion

	static void bootstrap()
	{
	}
}

package xyz.apex.forge.fantasyfurniture.init;

import com.google.common.base.Predicates;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.ISeatBlock;
import xyz.apex.forge.commonality.tags.BlockTags;
import xyz.apex.forge.fantasyfurniture.block.furniture.*;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static xyz.apex.forge.fantasyfurniture.init.ModRegistry.REGISTRATE;
import static com.tterrag.registrate.providers.ProviderType.LANG;

public final class ModBlocks
{
	public static final BlockEntry<Block> NORDIC_WOOL = wool("nordic", Block::new).register();
	public static final BlockEntry<CarpetBlock> NORDIC_CARPET = carpet("nordic", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> NORDIC_WALL_LIGHT = wallLight("nordic", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock> NORDIC_FLOOR_LIGHT = floorLight("nordic", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> NORDIC_TABLE_SMALL = tableSmall("nordic", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> NORDIC_TABLE_WIDE = tableWide("nordic", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> NORDIC_TABLE_LARGE = tableLarge("nordic", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> NORDIC_STOOL = stool("nordic", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> NORDIC_CUSHION = cushion("nordic", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> NORDIC_PAINTING_SMALL = paintingSmall("nordic", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> NORDIC_PAINTING_WIDE = paintingWide("nordic", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> NORDIC_DRAWER = drawer("nordic", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> NORDIC_SHELF = shelf("nordic", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> NORDIC_SOFA = sofa("nordic", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> NORDIC_DESK_LEFT = deskLeft("nordic", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> NORDIC_DESK_RIGHT = deskRight("nordic", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> NORDIC_CHAIR = chair("nordic", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> NORDIC_BENCH = bench("nordic", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> NORDIC_BOOKSHELF = bookshelf("nordic", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> NORDIC_CHEST = chest("nordic", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> NORDIC_DRESSER = dresser("nordic", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> NORDIC_WARDROBE_BOTTOM = wardrobeBottom("nordic", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> NORDIC_WARDROBE_TOP = wardrobeTop("nordic", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> NORDIC_BED_SINGLE = bedSingle("nordic", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> NORDIC_BED_DOUBLE = bedDouble("nordic", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> NORDIC_CHANDELIER = chandelier("nordic", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_SINGLE = doorSingle("nordic", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> NORDIC_DOOR_DOUBLE = doorDouble("nordic", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> NORDIC_LOCKBOX = lockbox("nordic", LockboxBlock::new).register();

	public static final BlockEntry<Block> DUNMER_WOOL = wool("dunmer", Block::new).register();
	public static final BlockEntry<CarpetBlock> DUNMER_CARPET = carpet("dunmer", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> DUNMER_WALL_LIGHT = wallLight("dunmer", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	public static final BlockEntry<FloorLightBlock> DUNMER_FLOOR_LIGHT = floorLight("dunmer", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> DUNMER_TABLE_SMALL = tableSmall("dunmer", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> DUNMER_TABLE_WIDE = tableWide("dunmer", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> DUNMER_TABLE_LARGE = tableLarge("dunmer", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> DUNMER_STOOL = stool("dunmer", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> DUNMER_CUSHION = cushion("dunmer", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> DUNMER_PAINTING_SMALL = paintingSmall("dunmer", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> DUNMER_PAINTING_WIDE = paintingWide("dunmer", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> DUNMER_DRAWER = drawer("dunmer", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> DUNMER_SHELF = shelf("dunmer", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> DUNMER_SOFA = sofa("dunmer", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> DUNMER_DESK_LEFT = deskLeft("dunmer", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> DUNMER_DESK_RIGHT = deskRight("dunmer", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> DUNMER_CHAIR = chair("dunmer", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> DUNMER_BENCH = bench("dunmer", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> DUNMER_BOOKSHELF = bookshelf("dunmer", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> DUNMER_CHEST = chest("dunmer", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> DUNMER_DRESSER = dresser("dunmer", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> DUNMER_WARDROBE_BOTTOM = wardrobeBottom("dunmer", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> DUNMER_WARDROBE_TOP = wardrobeTop("dunmer", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> DUNMER_BED_SINGLE = bedSingle("dunmer", BedSingleBlock::new).transform(ModBlocks::dunmerBed).register();
	public static final BlockEntry<BedDoubleBlock> DUNMER_BED_DOUBLE = bedDouble("dunmer", BedDoubleBlock::new).transform(ModBlocks::dunmerBed).register();
	public static final BlockEntry<ChandelierBlock> DUNMER_CHANDELIER = chandelier("dunmer", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_SINGLE = doorSingle("dunmer", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> DUNMER_DOOR_DOUBLE = doorDouble("dunmer", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> DUNMER_LOCKBOX = lockbox("dunmer", LockboxBlock::new).register();

	public static final BlockEntry<Block> VENTHYR_WOOL = wool("venthyr", Block::new).register();
	public static final BlockEntry<CarpetBlock> VENTHYR_CARPET = carpet("venthyr", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> VENTHYR_WALL_LIGHT = wallLight("venthyr", FurnitureWallLightBlock::new).lightLevel(blockState -> 14).register();
	public static final BlockEntry<FloorLightBlock> VENTHYR_FLOOR_LIGHT = floorLight("venthyr", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL = tableSmall("venthyr", TableSmallBlock::new).register();
	public static final BlockEntry<TableSmallBlock> VENTHYR_TABLE_SMALL_FANCY = tableSmallFancy("venthyr", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE = tableWide("venthyr", TableWideBlock::new).register();
	public static final BlockEntry<TableWideBlock> VENTHYR_TABLE_WIDE_FANCY = tableWideFancy("venthyr", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE = tableLarge("venthyr", TableLargeBlock::new).register();
	public static final BlockEntry<TableLargeBlock> VENTHYR_TABLE_LARGE_FANCY = tableLargeFancy("venthyr", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> VENTHYR_STOOL = stool("venthyr", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> VENTHYR_CUSHION = cushion("venthyr", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> VENTHYR_PAINTING_SMALL = paintingSmall("venthyr", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> VENTHYR_PAINTING_WIDE = paintingWide("venthyr", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> VENTHYR_DRAWER = drawer("venthyr", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> VENTHYR_SHELF = shelf("venthyr", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> VENTHYR_SOFA = sofa("venthyr", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> VENTHYR_DESK_LEFT = deskLeft("venthyr", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> VENTHYR_DESK_RIGHT = deskRight("venthyr", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> VENTHYR_CHAIR = chair("venthyr", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> VENTHYR_BENCH = bench("venthyr", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> VENTHYR_BOOKSHELF = bookshelf("venthyr", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> VENTHYR_CHEST = chest("venthyr", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> VENTHYR_DRESSER = dresser("venthyr", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> VENTHYR_WARDROBE_BOTTOM = wardrobeBottom("venthyr", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> VENTHYR_WARDROBE_TOP = wardrobeTop("venthyr", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> VENTHYR_BED_SINGLE = bedSingle("venthyr", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> VENTHYR_BED_DOUBLE = bedDouble("venthyr", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> VENTHYR_CHANDELIER = chandelier("venthyr", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_SINGLE = doorSingle("venthyr", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> VENTHYR_DOOR_DOUBLE = doorDouble("venthyr", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> VENTHYR_LOCKBOX = lockbox("venthyr", LockboxBlock::new).register();

	public static final BlockEntry<Block> BONE_SKELETON_WOOL = wool("bone/skeleton", Block::new).register();
	public static final BlockEntry<CarpetBlock> BONE_SKELETON_CARPET = carpet("bone/skeleton", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> BONE_SKELETON_WALL_LIGHT = wallLight("bone/skeleton", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock> BONE_SKELETON_FLOOR_LIGHT = floorLight("bone/skeleton", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> BONE_SKELETON_TABLE_SMALL = tableSmall("bone/skeleton", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> BONE_SKELETON_TABLE_WIDE = tableWide("bone/skeleton", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> BONE_SKELETON_TABLE_LARGE = tableLarge("bone/skeleton", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> BONE_SKELETON_STOOL = stool("bone/skeleton", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> BONE_SKELETON_SKULL = skull("bone/skeleton", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> BONE_SKELETON_PAINTING_SMALL = paintingSmall("bone/skeleton", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> BONE_SKELETON_PAINTING_WIDE = paintingWide("bone/skeleton", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> BONE_SKELETON_DRAWER = drawer("bone/skeleton", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> BONE_SKELETON_SHELF = shelf("bone/skeleton", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> BONE_SKELETON_SOFA = sofa("bone/skeleton", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> BONE_SKELETON_DESK_LEFT = deskLeft("bone/skeleton", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> BONE_SKELETON_DESK_RIGHT = deskRight("bone/skeleton", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> BONE_SKELETON_CHAIR = chair("bone/skeleton", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> BONE_SKELETON_BENCH = bench("bone/skeleton", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> BONE_SKELETON_BOOKSHELF = bookshelf("bone/skeleton", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> BONE_SKELETON_CHEST = chest("bone/skeleton", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> BONE_SKELETON_DRESSER = dresser("bone/skeleton", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> BONE_SKELETON_WARDROBE_BOTTOM = wardrobeBottom("bone/skeleton", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> BONE_SKELETON_WARDROBE_TOP = wardrobeTop("bone/skeleton", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> BONE_SKELETON_BED_SINGLE = bedSingle("bone/skeleton", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> BONE_SKELETON_BED_DOUBLE = bedDouble("bone/skeleton", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> BONE_SKELETON_CHANDELIER = chandelier("bone/skeleton", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_SINGLE = doorSingle("bone/skeleton", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_SKELETON_DOOR_DOUBLE = doorDouble("bone/skeleton", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> BONE_SKELETON_LOCKBOX = lockbox("bone/skeleton", LockboxBlock::new).register();

	public static final BlockEntry<Block> BONE_WITHER_WOOL = wool("bone/wither", Block::new).register();
	public static final BlockEntry<CarpetBlock> BONE_WITHER_CARPET = carpet("bone/wither", CarpetBlock::new).register();
	public static final BlockEntry<FurnitureWallLightBlock> BONE_WITHER_WALL_LIGHT = wallLight("bone/wither", FurnitureWallLightBlock::new).register();
	public static final BlockEntry<FloorLightBlock> BONE_WITHER_FLOOR_LIGHT = floorLight("bone/wither", FloorLightBlock::new).register();
	public static final BlockEntry<TableSmallBlock> BONE_WITHER_TABLE_SMALL = tableSmall("bone/wither", TableSmallBlock::new).register();
	public static final BlockEntry<TableWideBlock> BONE_WITHER_TABLE_WIDE = tableWide("bone/wither", TableWideBlock::new).register();
	public static final BlockEntry<TableLargeBlock> BONE_WITHER_TABLE_LARGE = tableLarge("bone/wither", TableLargeBlock::new).register();
	public static final BlockEntry<StoolBlock> BONE_WITHER_STOOL = stool("bone/wither", StoolBlock::new).register();
	public static final BlockEntry<CushionBlock> BONE_WITHER_SKULL = skull("bone/wither", CushionBlock::new).register();
	public static final BlockEntry<PaintingSmallBlock> BONE_WITHER_PAINTING_SMALL = paintingSmall("bone/wither", PaintingSmallBlock::new).register();
	public static final BlockEntry<PaintingWideBlock> BONE_WITHER_PAINTING_WIDE = paintingWide("bone/wither", PaintingWideBlock::new).register();
	public static final BlockEntry<DrawerBlock> BONE_WITHER_DRAWER = drawer("bone/wither", DrawerBlock::new).register();
	public static final BlockEntry<ShelfBlock> BONE_WITHER_SHELF = shelf("bone/wither", ShelfBlock::new).register();
	public static final BlockEntry<SofaBlock> BONE_WITHER_SOFA = sofa("bone/wither", SofaBlock::new).register();
	public static final BlockEntry<DeskBlock> BONE_WITHER_DESK_LEFT = deskLeft("bone/wither", DeskBlock::new).register();
	public static final BlockEntry<DeskBlock> BONE_WITHER_DESK_RIGHT = deskRight("bone/wither", DeskBlock::new).register();
	public static final BlockEntry<ChairBlock.OriginOnly> BONE_WITHER_CHAIR = chair("bone/wither", ChairBlock.OriginOnly::new).register();
	public static final BlockEntry<BenchBlock> BONE_WITHER_BENCH = bench("bone/wither", BenchBlock::new).register();
	public static final BlockEntry<BookshelfBlock> BONE_WITHER_BOOKSHELF = bookshelf("bone/wither", BookshelfBlock::new).register();
	public static final BlockEntry<ChestBlock> BONE_WITHER_CHEST = chest("bone/wither", ChestBlock::new).register();
	public static final BlockEntry<DresserBlock> BONE_WITHER_DRESSER = dresser("bone/wither", DresserBlock::new).register();
	public static final BlockEntry<WardrobeBottomBlock> BONE_WITHER_WARDROBE_BOTTOM = wardrobeBottom("bone/wither", WardrobeBottomBlock::new).register();
	public static final BlockEntry<WardrobeTopBlock> BONE_WITHER_WARDROBE_TOP = wardrobeTop("bone/wither", WardrobeTopBlock::new).register();
	public static final BlockEntry<BedSingleBlock> BONE_WITHER_BED_SINGLE = bedSingle("bone/wither", BedSingleBlock::new).register();
	public static final BlockEntry<BedDoubleBlock> BONE_WITHER_BED_DOUBLE = bedDouble("bone/wither", BedDoubleBlock::new).register();
	public static final BlockEntry<ChandelierBlock> BONE_WITHER_CHANDELIER = chandelier("bone/wither", ChandelierBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_SINGLE = doorSingle("bone/wither", FurnitureDoorBlock::new).register();
	public static final BlockEntry<FurnitureDoorBlock> BONE_WITHER_DOOR_DOUBLE = doorDouble("bone/wither", FurnitureDoorBlock::new).register();
	public static final BlockEntry<LockboxBlock> BONE_WITHER_LOCKBOX = lockbox("bone/wither", LockboxBlock::new).register();

	static void bootstrap()
	{
		REGISTRATE.addDataGenerator(LANG, provider -> REGISTRATE.getAll(Registry.BLOCK_REGISTRY).stream().map(RegistryEntry::get).forEach(block -> {
			if(block instanceof ISeatBlock seat)
				provider.add(seat.getOccupiedTranslationKey(), "This seat is occupied");
			if(block instanceof BedBlock bed)
				provider.add(bed.getOccupiedTranslationKey(), "This bed is occupied");
		}));
	}

	// region: Constructors
	private static <BLOCK extends Block> BlockBuilder<Registrate, BLOCK, Registrate> wool(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wool".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyBlockDefaults)
				.initialProperties(Material.WOOL, MaterialColor.WOOL)
				.strength(.8F)
				.sound(SoundType.WOOL)
				.blockstate((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.cubeAll(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath()))
								)
						)
				)
				.tag(BlockTags.Vanilla.WOOL)
		;
	}

	private static <BLOCK extends CarpetBlock> BlockBuilder<Registrate, BLOCK, Registrate> carpet(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/carpet".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyBlockDefaults)
				.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
				.strength(.1F)
				.sound(SoundType.WOOL)
				.blockstate((ctx, provider) -> provider
						.simpleBlock(ctx.get(), provider
								.models()
								.carpet(
										"%s:block/%s".formatted(ctx.getId().getNamespace(), ctx.getId().getPath()),
										new ResourceLocation(ctx.getId().getNamespace(), "block/%s/wool".formatted(type))
								)
						)
				)
				.tag(BlockTags.Vanilla.CARPETS)
		;
	}

	private static <BLOCK extends FurnitureWallLightBlock> BlockBuilder<Registrate, BLOCK, Registrate> wallLight(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wall_light".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.lightLevel(lightLevel())
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends FloorLightBlock> BlockBuilder<Registrate, BLOCK, Registrate> floorLight(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/floor_light".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.DECORATION)
				.sound(SoundType.WOOD)
				.instabreak()
				.lightLevel(lightLevel(blockState -> blockState.getOptionalValue(FloorLightBlock.PART).orElse(FloorLightBlock.Part.BOTTOM).isTop()))
				.blockstate(ModBlocks::simpleBlockState)
		;
	}

	private static <BLOCK extends TableSmallBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableSmall(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableSmallBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableSmallFancy(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_small_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableWideBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableWide(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableWideBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableWideFancy(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_wide_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableLargeBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableLarge(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends TableLargeBlock> BlockBuilder<Registrate, BLOCK, Registrate> tableLargeFancy(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/table_large_fancy".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends StoolBlock> BlockBuilder<Registrate, BLOCK, Registrate> stool(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/stool".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CushionBlock> BlockBuilder<Registrate, BLOCK, Registrate> cushion(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/cushion".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends CushionBlock> BlockBuilder<Registrate, BLOCK, Registrate> skull(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/skull".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends PaintingSmallBlock> BlockBuilder<Registrate, BLOCK, Registrate> paintingSmall(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_small".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends PaintingWideBlock> BlockBuilder<Registrate, BLOCK, Registrate> paintingWide(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/painting_wide".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.sound(SoundType.WOOD)
				.instabreak()
				.noCollission()
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DrawerBlock> BlockBuilder<Registrate, BLOCK, Registrate> drawer(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/drawer".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ShelfBlock> BlockBuilder<Registrate, BLOCK, Registrate> shelf(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/shelf".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockstate -> {
					var connection = blockstate.getOptionalValue(ShelfBlock.CONNECTION).orElse(ShelfBlock.Connection.SINGLE);
					return provider.models().getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s_%s".formatted(ctx.getId().getPath(), connection.serializedName)));
				}))
		;
	}

	private static <BLOCK extends SofaBlock> BlockBuilder<Registrate, BLOCK, Registrate> sofa(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/sofa".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockstate -> {
					var connection = blockstate.getOptionalValue(SofaBlock.CONNECTION).orElse(SofaBlock.Connection.SINGLE);
					return provider.models().getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s_%s".formatted(ctx.getId().getPath(), connection.serializedName)));
				}))
		;
	}

	private static <BLOCK extends DeskBlock> BlockBuilder<Registrate, BLOCK, Registrate> deskLeft(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_left".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DeskBlock> BlockBuilder<Registrate, BLOCK, Registrate> deskRight(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/desk_right".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ChairBlock> BlockBuilder<Registrate, BLOCK, Registrate> chair(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chair".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BenchBlock> BlockBuilder<Registrate, BLOCK, Registrate> bench(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bench".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BookshelfBlock> BlockBuilder<Registrate, BLOCK, Registrate> bookshelf(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bookshelf".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends ChestBlock> BlockBuilder<Registrate, BLOCK, Registrate> chest(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chest".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends DresserBlock> BlockBuilder<Registrate, BLOCK, Registrate> dresser(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/dresser".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WardrobeBottomBlock> BlockBuilder<Registrate, BLOCK, Registrate> wardrobeBottom(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_bottom".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends WardrobeTopBlock> BlockBuilder<Registrate, BLOCK, Registrate> wardrobeTop(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/wardrobe_top".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends BedSingleBlock> BlockBuilder<Registrate, BLOCK, Registrate> bedSingle(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_single".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	private static <BLOCK extends BedDoubleBlock> BlockBuilder<Registrate, BLOCK, Registrate> bedDouble(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/bed_double".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.blockstate(ModBlocks::horizontalBlockState)
				.tag(BlockTags.Vanilla.BEDS)
		;
	}

	private static <BLOCK extends ChandelierBlock> BlockBuilder<Registrate, BLOCK, Registrate> chandelier(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/chandelier".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.lightLevel(lightLevel())
				.blockstate(ModBlocks::simpleBlockState)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<Registrate, BLOCK, Registrate> doorSingle(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_single".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockstate(ModBlocks::horizontalBlockState)
				.transform(ModBlocks::applyDoorProperties)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<Registrate, BLOCK, Registrate> doorDouble(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/door_double".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockstate(ModBlocks::horizontalBlockState)
				.transform(ModBlocks::applyDoorProperties)
		;
	}

	private static <BLOCK extends FurnitureDoorBlock> BlockBuilder<Registrate, BLOCK, Registrate> applyDoorProperties(BlockBuilder<Registrate, BLOCK, Registrate> builder)
	{
		return builder
				.blockstate((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
					var leftModel = provider.models().getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s_left".formatted(ctx.getId().getPath())));
					var rightModel = provider.models().getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s_right".formatted(ctx.getId().getPath())));
					var model = leftModel;

					var rightHinge = blockState.getOptionalValue(DoorBlock.HINGE).orElse(DoorHingeSide.LEFT) == DoorHingeSide.RIGHT;
					var yRot = (int) BaseBlock.getFacing(blockState).toYRot();
					var open = blockState.getOptionalValue(DoorBlock.OPEN).orElse(false);

					if(open)
					{
						yRot += 90;
						model = rightHinge ? leftModel : rightModel;
					}
					else
						model = rightHinge ? rightModel : leftModel;

					if(rightHinge && open)
						yRot += 180;

					yRot %= 360;

					return ConfiguredModel
							.builder()
							.modelFile(model)
							.rotationY(yRot)
							.build();
				}))
				.tag(BlockTags.Vanilla.WOODEN_DOORS)
				.loot((lootTables, block) -> lootTables.add(block, RegistrateBlockLootTables.createDoorTable(block)))
		;
	}

	private static <BLOCK extends LockboxBlock> BlockBuilder<Registrate, BLOCK, Registrate> lockbox(String type, NonNullFunction<BlockBehaviour.Properties, BLOCK> blockFactory)
	{
		return REGISTRATE
				.object("%s/lockbox".formatted(type))
				.block(blockFactory)
				.transform(ModBlocks::applyFurnitureBlockDefaults)
				.initialProperties(Material.WOOD)
				.strength(2.5F)
				.sound(SoundType.WOOD)
				.noOcclusion()
				.blockstate(ModBlocks::horizontalBlockState)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<Registrate, BLOCK, Registrate> applyBlockDefaults(BlockBuilder<Registrate, BLOCK, Registrate> builder)
	{
		return builder.lang(RegistrateLangProvider.toEnglishName(builder.getName().replace('/', '_')));
	}

	private static <BLOCK extends Block> BlockBuilder<Registrate, BLOCK, Registrate> applyFurnitureBlockDefaults(BlockBuilder<Registrate, BLOCK, Registrate> builder)
	{
		return builder
				.transform(ModBlocks::applyBlockDefaults)
				.noOcclusion()
				.isValidSpawn(BlockHelper::never)
				.isRedstoneConductor(BlockHelper::never)
				.isSuffocating(BlockHelper::never)
				.isViewBlocking(BlockHelper::never)
				.addLayer(() -> RenderType::cutout)
		;
	}

	private static ToIntFunction<BlockState> lightLevel()
	{
		return lightLevel(Predicates.alwaysTrue());
	}

	private static ToIntFunction<BlockState> lightLevel(Predicate<BlockState> lightLevelPredicate)
	{
		return blockState -> lightLevelPredicate.test(blockState) && !BaseBlock.isWaterLogged(blockState) ? 14 : 0;
	}

	private static <BLOCK extends Block> void simpleBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		provider.getVariantBuilder(ctx.get())
		        .forAllStates(blockState -> ConfiguredModel
				        .builder()
				        .modelFile(provider
						        .models()
						        .getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
				        )
				        .build()
		        )
		;
	}

	private static <BLOCK extends Block> void horizontalBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider)
	{
		provider.horizontalBlock(ctx.get(), provider
						.models()
						.getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s".formatted(ctx.getId().getPath())))
		);
	}

	private static <BLOCK extends BedBlock> BlockBuilder<Registrate, BLOCK, Registrate> dunmerBed(BlockBuilder<Registrate, BLOCK, Registrate> builder)
	{
		return builder
				.blockstate((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
					var suffix = ctx.get().isMultiBlockOrigin(blockState) ? "head" : "foot";
					return provider.models().getExistingFile(new ResourceLocation(ctx.getId().getNamespace(), "block/%s_%s".formatted(ctx.getId().getPath(), suffix)));
				}))
		;
	}
	// endregion
}
package xyz.apex.forge.fantasyfurniture;

import net.minecraft.core.registries.Registries;

import xyz.apex.forge.apexcore.registrate.entry.BlockEntityEntry;
import xyz.apex.forge.fantasyfurniture.client.renderer.SkullBlossomsBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.client.renderer.WidowBloomBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.common.block.entity.*;

import static xyz.apex.forge.fantasyfurniture.core.ModRegistry.REGISTRATE;

public interface AllBlockEntities
{
	BlockEntityEntry<BookshelfBlockEntity> BOOKSHELF_BLOCK_ENTITY = REGISTRATE
			.object("bookshelf")
			.blockEntity(BookshelfBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_BOOKSHELF, AllBlocks.DUNMER_BOOKSHELF,
					AllBlocks.VENTHYR_BOOKSHELF, AllBlocks.BONE_SKELETON_BOOKSHELF,
					AllBlocks.BONE_WITHER_BOOKSHELF, AllBlocks.ROYAL_BOOKSHELF,
					AllBlocks.NECROLORD_BOOKSHELF
			)
			.register()
	;

	BlockEntityEntry<ChestBlockEntity> CHEST_BLOCK_ENTITY = REGISTRATE
			.object("chest")
			.blockEntity(ChestBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_CHEST, AllBlocks.DUNMER_CHEST,
					AllBlocks.VENTHYR_CHEST, AllBlocks.BONE_SKELETON_CHEST,
					AllBlocks.BONE_WITHER_CHEST, AllBlocks.ROYAL_CHEST,
					AllBlocks.NECROLORD_CHEST
			)
			.register()
	;

	BlockEntityEntry<DeskBlockEntity> DESK_BLOCK_ENTITY = REGISTRATE
			.object("desk")
			.blockEntity(DeskBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_DESK_LEFT, AllBlocks.NORDIC_DESK_RIGHT,
					AllBlocks.DUNMER_DESK_LEFT, AllBlocks.DUNMER_DESK_RIGHT,
					AllBlocks.VENTHYR_DESK_LEFT, AllBlocks.VENTHYR_DESK_RIGHT,
					AllBlocks.BONE_SKELETON_DESK_LEFT, AllBlocks.BONE_SKELETON_DESK_RIGHT,
					AllBlocks.BONE_WITHER_DESK_LEFT, AllBlocks.BONE_WITHER_DESK_RIGHT,
					AllBlocks.ROYAL_DESK_LEFT, AllBlocks.ROYAL_DESK_RIGHT,
					AllBlocks.NECROLORD_DESK_LEFT, AllBlocks.NECROLORD_DESK_RIGHT
			)
			.register()
	;

	BlockEntityEntry<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = REGISTRATE
			.object("drawer")
			.blockEntity(DrawerBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_DRAWER, AllBlocks.DUNMER_DRAWER,
					AllBlocks.VENTHYR_DRAWER, AllBlocks.BONE_SKELETON_DRAWER,
					AllBlocks.BONE_WITHER_DRAWER, AllBlocks.ROYAL_DRAWER,
					AllBlocks.NECROLORD_DRAWER
			)
			.register()
	;

	BlockEntityEntry<DresserBlockEntity> DRESSER_BLOCK_ENTITY = REGISTRATE
			.object("dresser")
			.blockEntity(DresserBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_DRESSER, AllBlocks.DUNMER_DRESSER,
					AllBlocks.VENTHYR_DRESSER, AllBlocks.BONE_SKELETON_DRESSER,
					AllBlocks.BONE_WITHER_DRESSER, AllBlocks.ROYAL_DRESSER,
					AllBlocks.NECROLORD_DRESSER
			)
	.register();

	BlockEntityEntry<LockboxBlockEntity> LOCKBOX_BLOCK_ENTITY = REGISTRATE
			.object("lockbox")
			.blockEntity(LockboxBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_LOCKBOX, AllBlocks.DUNMER_LOCKBOX,
					AllBlocks.VENTHYR_LOCKBOX, AllBlocks.BONE_SKELETON_LOCKBOX,
					AllBlocks.BONE_WITHER_LOCKBOX, AllBlocks.ROYAL_LOCKBOX,
					AllBlocks.NECROLORD_LOCKBOX
			)
			.register()
	;

	BlockEntityEntry<WardrobeBlockEntity> WARDROBE_BLOCK_ENTITY = REGISTRATE
			.object("wardrobe")
			.blockEntity(WardrobeBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_WARDROBE_BOTTOM, AllBlocks.DUNMER_WARDROBE_BOTTOM,
					AllBlocks.VENTHYR_WARDROBE_BOTTOM, AllBlocks.BONE_SKELETON_WARDROBE_BOTTOM,
					AllBlocks.BONE_WITHER_WARDROBE_BOTTOM, AllBlocks.ROYAL_WARDROBE_BOTTOM,
					AllBlocks.NECROLORD_WARDROBE_BOTTOM
			)
			.register()
	;

	BlockEntityEntry<CounterBlockEntity> COUNTER_BLOCK_ENTITY = REGISTRATE
			.object("counter")
			.blockEntity(CounterBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_COUNTER, AllBlocks.DUNMER_COUNTER,
					AllBlocks.VENTHYR_COUNTER, AllBlocks.BONE_SKELETON_COUNTER,
					AllBlocks.BONE_WITHER_COUNTER, AllBlocks.ROYAL_COUNTER,
					AllBlocks.NECROLORD_COUNTER
			)
			.register()
	;

	BlockEntityEntry<OvenBlockEntity> OVEN_BLOCK_ENTITY = REGISTRATE
			.object("oven")
			.blockEntity(OvenBlockEntity::new)
			.validBlock(
					AllBlocks.NORDIC_OVEN, AllBlocks.DUNMER_OVEN,
					AllBlocks.VENTHYR_OVEN, AllBlocks.BONE_SKELETON_OVEN,
					AllBlocks.BONE_WITHER_OVEN, AllBlocks.ROYAL_OVEN,
					AllBlocks.NECROLORD_OVEN
			)
			.register()
	;

	BlockEntityEntry<CookieJarBlockEntity> COOKIE_JAR_BLOCK_ENTITY = REGISTRATE
			.object("cookie_jar")
			.blockEntity(CookieJarBlockEntity::new)
			.validBlock(AllBlocks.COOKIE_JAR)
			.register()
	;

	BlockEntityEntry<WidowBloomBlockEntity> VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY = REGISTRATE
			.object("decorations/widow_bloom")
			.blockEntity(WidowBloomBlockEntity::new)
			.validBlock(AllBlocks.VENTHYR_WIDOW_BLOOM)
			.renderer(() -> WidowBloomBlockEntityRenderer::new)
			.register()
	;

	BlockEntityEntry<SkullBlossomsBlockEntity> BONE_SKULL_BLOSSOMS_BLOCK_ENTITY = REGISTRATE
			.object("decorations/bone_skull_blossoms")
			.blockEntity(SkullBlossomsBlockEntity::new)
			.validBlock(AllBlocks.BONE_SKELETON_SKULL_BLOSSOMS, AllBlocks.BONE_WITHER_SKULL_BLOSSOMS)
			.renderer(() -> SkullBlossomsBlockEntityRenderer::new)
			.register()
	;

	BlockEntityEntry<FurnitureStationBlockEntity> FURNITURE_STATION = BlockEntityEntry.cast(AllBlocks.FURNITURE_STATION.getSibling(Registries.BLOCK_ENTITY_TYPE));

	BlockEntityEntry<StockingBlockEntity> STOCKING_BLOCK_ENTITY = REGISTRATE
			.object("stocking")
			.blockEntity(StockingBlockEntity::new)
			.validBlock(AllBlocks.STOCKING)
			.register()
	;
	static void bootstrap()
	{
	}
}

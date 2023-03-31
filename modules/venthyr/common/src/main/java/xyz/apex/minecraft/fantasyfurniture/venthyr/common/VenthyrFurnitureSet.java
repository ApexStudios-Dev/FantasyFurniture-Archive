package xyz.apex.minecraft.fantasyfurniture.venthyr.common;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.CarpetBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.*;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity.*;

public interface VenthyrFurnitureSet
{
    String BASE_ID = "venthyr";
    String ID = "fantasyfurniture_venthyr";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        VenthyrVoxelShapes.bootstrap();
        Blocks.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Blocks
    {
        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, null, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, null, CarpetBlock::new).register();
        BlockEntry<VenthyrWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, null, VenthyrWallLightBlock::new).register();
        BlockEntry<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, null, VenthyrFloorLightBlock::new).register();
        BlockEntry<VenthyrTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, null, VenthyrTableLargeBlock::new).register();
        BlockEntry<VenthyrTableLargeBlock> TABLE_LARGE_FANCY = FurnitureSets.tableLargeFancy(ID, null, VenthyrTableLargeBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, null, VenthyrTableSmallBlock::new).register();
        BlockEntry<VenthyrTableSmallBlock> TABLE_SMALL_FANCY = FurnitureSets.tableSmallFancy(ID, null, VenthyrTableSmallBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, null, VenthyrTableWideBlock::new).register();
        BlockEntry<VenthyrTableWideBlock> TABLE_WIDE_FANCY = FurnitureSets.tableWideFancy(ID, null, VenthyrTableWideBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrBenchBlock> BENCH = FurnitureSets.bench(ID, null, VenthyrBenchBlock::new).register();
        BlockEntry<VenthyrChairBlock> CHAIR = FurnitureSets.chair(ID, null, VenthyrChairBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, null, VenthyrCeilingLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrCushionBlock> CUSHION = FurnitureSets.cushion(ID, null, VenthyrCushionBlock::new).register();
        BlockEntry<VenthyrStoolBlock> STOOL = FurnitureSets.stool(ID, null, VenthyrStoolBlock::new).register();
        BlockEntry<VenthyrChestBlock> CHEST = FurnitureSets.chest(ID, null, VenthyrChestBlock::new).register();
        BlockEntry<VenthyrBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, null, VenthyrBookshelfBlock::new).register();
        BlockEntry<VenthyrDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, null, VenthyrDeskBlock::new).register();
        BlockEntry<VenthyrDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, null, VenthyrDeskBlock::new).register();
        BlockEntry<VenthyrDrawerBlock> DRAWER = FurnitureSets.drawer(ID, null, VenthyrDrawerBlock::new).register();
        BlockEntry<VenthyrDresserBlock> DRESSER = FurnitureSets.dresser(ID, null, VenthyrDresserBlock::new).register();
        BlockEntry<VenthyrLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, null, VenthyrLockboxBlock::new).register();
        BlockEntry<VenthyrWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, null, VenthyrWardrobeBottomBlock::new).register();
        BlockEntry<VenthyrWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, null, VenthyrWardrobeTopBlock::new).register();
        BlockEntry<VenthyrPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, null, VenthyrPaintingSmallBlock::new).register();
        BlockEntry<VenthyrPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, null, VenthyrPaintingWideBlock::new).register();
        BlockEntry<VenthyrOvenBlock> OVEN = FurnitureSets.oven(ID, null, VenthyrOvenBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<VenthyrDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, null, VenthyrDoorBlock::new).register();
        BlockEntry<VenthyrDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, null, VenthyrDoorBlock::new).register();
        BlockEntry<VenthyrBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, null, VenthyrBedBlock::new).register();
        BlockEntry<VenthyrBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, null, VenthyrBedBlock::new).register();
        BlockEntry<VenthyrShelfBlock> SHELF = FurnitureSets.shelf(ID, null, VenthyrShelfBlock::new).register();
        BlockEntry<VenthyrSofaBlock> SOFA = FurnitureSets.sofa(ID, null, VenthyrSofaBlock::new).register();
        BlockEntry<VenthyrCounterBlock> COUNTER = FurnitureSets.counter(ID, null, VenthyrCounterBlock::new).register();

        private static void bootstrap()
        {
            FurnitureSets.creativeModeTab(ID, null, builder -> builder
                    .icon(BED_SINGLE::asStack)
                    .displayItems((params, output) -> {
                        output.accept(WOOL);
                        output.accept(CARPET);
                        output.accept(WALL_LIGHT);
                        output.accept(FLOOR_LIGHT);
                        output.accept(TABLE_LARGE);
                        output.accept(TABLE_LARGE_FANCY);
                        output.accept(TABLE_SMALL);
                        output.accept(TABLE_SMALL_FANCY);
                        output.accept(TABLE_WIDE);
                        output.accept(TABLE_WIDE_FANCY);
                        output.accept(BENCH);
                        output.accept(CHAIR);
                        output.accept(CEILING_LIGHT);
                        output.accept(CUSHION);
                        output.accept(STOOL);
                        output.accept(CHEST);
                        output.accept(BOOKSHELF);
                        output.accept(DESK_LEFT);
                        output.accept(DESK_RIGHT);
                        output.accept(DRAWER);
                        output.accept(DRESSER);
                        output.accept(LOCKBOX);
                        output.accept(WARDROBE_BOTTOM);
                        output.accept(WARDROBE_TOP);
                        output.accept(PAINTING_SMALL);
                        output.accept(PAINTING_WIDE);
                        output.accept(OVEN);
                        output.accept(DOOR_DOUBLE);
                        output.accept(DOOR_SINGLE);
                        output.accept(BED_SINGLE);
                        output.accept(BED_DOUBLE);
                        output.accept(SHELF);
                        output.accept(SOFA);
                        output.accept(COUNTER);
                    })
            );
        }
    }

    interface BlockEntityTypes
    {
        BlockEntityEntry<VenthyrChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, VenthyrChestBlockEntity::new)
            .validBlocks(Blocks.CHEST)
            .register();

        BlockEntityEntry<VenthyrBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, VenthyrBookshelfBlockEntity::new)
                .validBlocks(Blocks.BOOKSHELF)
                .register();

        BlockEntityEntry<VenthyrDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, VenthyrDeskBlockEntity::new)
                .validBlocks(Blocks.DESK_LEFT, Blocks.DESK_RIGHT)
                .register();

        BlockEntityEntry<VenthyrDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, VenthyrDrawerBlockEntity::new)
                .validBlocks(Blocks.DRAWER)
                .register();

        BlockEntityEntry<VenthyrDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, VenthyrDresserBlockEntity::new)
                .validBlocks(Blocks.DRESSER)
                .register();

        BlockEntityEntry<VenthyrLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, VenthyrLockboxBlockEntity::new)
                .validBlocks(Blocks.LOCKBOX)
                .register();

        BlockEntityEntry<VenthyrWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, VenthyrWardrobeBlockEntity::new)
                .validBlocks(Blocks.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<VenthyrOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", VenthyrOvenBlockEntity::new)
                .validBlocks(Blocks.OVEN)
                .register();

        BlockEntityEntry<VenthyrCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, VenthyrCounterBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

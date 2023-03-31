package xyz.apex.minecraft.fantasyfurniture.necrolord.common;

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
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.*;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity.*;

public interface NecrolordFurnitureSet
{
    String BASE_ID = "necrolord";
    String ID = "fantasyfurniture_necrolord";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        NecrolordVoxelShapes.bootstrap();
        Blocks.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Blocks
    {
        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, null, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, null, CarpetBlock::new).register();
        BlockEntry<NecrolordWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, null, NecrolordWallLightBlock::new).register();
        BlockEntry<NecrolordFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, null, NecrolordFloorLightBlock::new).register();
        BlockEntry<NecrolordTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, null, NecrolordTableLargeBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, null, NecrolordTableSmallBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, null, NecrolordTableWideBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordBenchBlock> BENCH = FurnitureSets.bench(ID, null, NecrolordBenchBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordChairBlock> CHAIR = FurnitureSets.chair(ID, null, NecrolordChairBlock::new).register();
        BlockEntry<NecrolordCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, null, NecrolordCeilingLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordCushionBlock> CUSHION = FurnitureSets.cushion(ID, null, NecrolordCushionBlock::new).register();
        BlockEntry<NecrolordStoolBlock> STOOL = FurnitureSets.stool(ID, null, NecrolordStoolBlock::new).register();
        BlockEntry<NecrolordChestBlock> CHEST = FurnitureSets.chest(ID, null, NecrolordChestBlock::new).register();
        BlockEntry<NecrolordBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, null, NecrolordBookshelfBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, null, NecrolordDeskBlock::new).register();
        BlockEntry<NecrolordDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, null, NecrolordDeskBlock::new).register();
        BlockEntry<NecrolordDrawerBlock> DRAWER = FurnitureSets.drawer(ID, null, NecrolordDrawerBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordDresserBlock> DRESSER = FurnitureSets.dresser(ID, null, NecrolordDresserBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, null, NecrolordLockboxBlock::new).register();
        BlockEntry<NecrolordWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, null, NecrolordWardrobeBottomBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, null, NecrolordWardrobeTopBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, null, NecrolordPaintingSmallBlock::new).register();
        BlockEntry<NecrolordPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, null, NecrolordPaintingWideBlock::new).register();
        BlockEntry<NecrolordOvenBlock> OVEN = FurnitureSets.oven(ID, null, NecrolordOvenBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, null, NecrolordDoorBlock::new).register();
        BlockEntry<NecrolordDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, null, NecrolordDoorBlock::new).register();
        BlockEntry<NecrolordBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, null, NecrolordBedBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, null, NecrolordBedBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NecrolordShelfBlock> SHELF = FurnitureSets.shelf(ID, null, NecrolordShelfBlock::new).register();
        BlockEntry<NecrolordSofaBlock> SOFA = FurnitureSets.sofa(ID, null, NecrolordSofaBlock::new).register();
        BlockEntry<NecrolordCounterBlock> COUNTER = FurnitureSets.counter(ID, null, NecrolordCounterBlock::new).register();

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
                        output.accept(TABLE_SMALL);
                        output.accept(TABLE_WIDE);
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
        BlockEntityEntry<NecrolordChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, NecrolordChestBlockEntity::new)
            .validBlocks(Blocks.CHEST)
            .register();

        BlockEntityEntry<NecrolordBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, NecrolordBookshelfBlockEntity::new)
                .validBlocks(Blocks.BOOKSHELF)
                .register();

        BlockEntityEntry<NecrolordDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, NecrolordDeskBlockEntity::new)
                .validBlocks(Blocks.DESK_LEFT, Blocks.DESK_RIGHT)
                .register();

        BlockEntityEntry<NecrolordDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, NecrolordDrawerBlockEntity::new)
                .validBlocks(Blocks.DRAWER)
                .register();

        BlockEntityEntry<NecrolordDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, NecrolordDresserBlockEntity::new)
                .validBlocks(Blocks.DRESSER)
                .register();

        BlockEntityEntry<NecrolordLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, NecrolordLockboxBlockEntity::new)
                .validBlocks(Blocks.LOCKBOX)
                .register();

        BlockEntityEntry<NecrolordWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, NecrolordWardrobeBlockEntity::new)
                .validBlocks(Blocks.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<NecrolordOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", NecrolordOvenBlockEntity::new)
                .validBlocks(Blocks.OVEN)
                .register();

        BlockEntityEntry<NecrolordCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, NecrolordCounterBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

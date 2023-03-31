package xyz.apex.minecraft.fantasyfurniture.royal.common;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.apexcore.common.registry.builder.BlockEntityBuilder;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.init.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.*;
import xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity.*;

public interface RoyalFurnitureSet
{
    String BASE_ID = "royal";
    String ID = "fantasyfurniture_royal";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        RoyalVoxelShapes.bootstrap();
        Blocks.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Blocks
    {
        BlockEntry<RoyalDyeableBlock> WOOL = FurnitureSets.wool(ID, null, RoyalDyeableBlock::new).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalCarpetBlock> CARPET = FurnitureSets.carpet(ID, null, RoyalCarpetBlock::new).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, null, RoyalWallLightBlock::new).register();
        BlockEntry<RoyalFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, null, RoyalFloorLightBlock::new).register();
        BlockEntry<RoyalTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, null, RoyalTableLargeBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, null, RoyalTableSmallBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, null, RoyalTableWideBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalBenchBlock> BENCH = FurnitureSets.bench(ID, null, RoyalBenchBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalChairBlock> CHAIR = FurnitureSets.chair(ID, null, RoyalChairBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, null, RoyalCeilingLightBlock::new).register();
        BlockEntry<RoyalCushionBlock> CUSHION = FurnitureSets.cushion(ID, null, RoyalCushionBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalStoolBlock> STOOL = FurnitureSets.stool(ID, null, RoyalStoolBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalChestBlock> CHEST = FurnitureSets.chest(ID, null, RoyalChestBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, null, RoyalBookshelfBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, null, RoyalDeskBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, null, RoyalDeskBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDrawerBlock> DRAWER = FurnitureSets.drawer(ID, null, RoyalDrawerBlock::new).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDresserBlock> DRESSER = FurnitureSets.dresser(ID, null, RoyalDresserBlock::new).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, null, RoyalLockboxBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, null, RoyalWardrobeBottomBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, null, RoyalWardrobeTopBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, null, RoyalPaintingSmallBlock::new).register();
        BlockEntry<RoyalPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, null, RoyalPaintingWideBlock::new).register();
        BlockEntry<RoyalOvenBlock> OVEN = FurnitureSets.oven(ID, null, RoyalOvenBlock::new).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, null, RoyalDoorBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, null, RoyalDoorBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, null, RoyalBedBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, null, RoyalBedBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalShelfBlock> SHELF = FurnitureSets.shelf(ID, null, RoyalShelfBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalSofaBlock> SOFA = FurnitureSets.sofa(ID, null, RoyalSofaBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
        BlockEntry<RoyalCounterBlock> COUNTER = FurnitureSets.counter(ID, null, RoyalCounterBlock::new).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();

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
        BlockEntityEntry<RoyalChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, RoyalChestBlockEntity::new)
            .validBlocks(Blocks.CHEST)
            .register();

        BlockEntityEntry<RoyalBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, RoyalBookshelfBlockEntity::new)
                .validBlocks(Blocks.BOOKSHELF)
                .register();

        BlockEntityEntry<RoyalDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, RoyalDeskBlockEntity::new)
                .validBlocks(Blocks.DESK_LEFT, Blocks.DESK_RIGHT)
                .register();

        BlockEntityEntry<RoyalDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, RoyalDrawerBlockEntity::new)
                .validBlocks(Blocks.DRAWER)
                .register();

        BlockEntityEntry<RoyalDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, RoyalDresserBlockEntity::new)
                .validBlocks(Blocks.DRESSER)
                .register();

        BlockEntityEntry<RoyalLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, RoyalLockboxBlockEntity::new)
                .validBlocks(Blocks.LOCKBOX)
                .register();

        BlockEntityEntry<RoyalWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, RoyalWardrobeBlockEntity::new)
                .validBlocks(Blocks.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<RoyalOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", RoyalOvenBlockEntity::new)
                .validBlocks(Blocks.OVEN)
                .register();

        BlockEntityEntry<RoyalCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, RoyalCounterBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        BlockEntityEntry<RoyalDyeableBlockEntity> DYEABLE = BlockEntityBuilder
                .builderWithComponents(ID, "dyeable", registrar -> {}, RoyalDyeableBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

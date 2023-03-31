package xyz.apex.minecraft.fantasyfurniture.nordic.common;

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
import xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.*;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity.*;

public interface NordicFurnitureSet
{
    String BASE_ID = "nordic";
    String ID = "fantasyfurniture_nordic";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        NordicVoxelShapes.bootstrap();
        Blocks.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Blocks
    {
        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, null, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, null, CarpetBlock::new).register();
        BlockEntry<NordicWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, null, NordicWallLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, null, NordicFloorLightBlock::new).register();
        BlockEntry<NordicTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, null, NordicTableLargeBlock::new).register();
        BlockEntry<NordicTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, null, NordicTableSmallBlock::new).register();
        BlockEntry<NordicTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, null, NordicTableWideBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicBenchBlock> BENCH = FurnitureSets.bench(ID, null, NordicBenchBlock::new).register();
        BlockEntry<NordicChairBlock> CHAIR = FurnitureSets.chair(ID, null, NordicChairBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, null, NordicCeilingLightBlock::new).register();
        BlockEntry<NordicCushionBlock> CUSHION = FurnitureSets.cushion(ID, null, NordicCushionBlock::new).register();
        BlockEntry<NordicStoolBlock> STOOL = FurnitureSets.stool(ID, null, NordicStoolBlock::new).register();
        BlockEntry<NordicChestBlock> CHEST = FurnitureSets.chest(ID, null, NordicChestBlock::new).register();
        BlockEntry<NordicBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, null, NordicBookshelfBlock::new).register();
        BlockEntry<NordicDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, null, NordicDeskBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, null, NordicDeskBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicDrawerBlock> DRAWER = FurnitureSets.drawer(ID, null, NordicDrawerBlock::new).register();
        BlockEntry<NordicDresserBlock> DRESSER = FurnitureSets.dresser(ID, null, NordicDresserBlock::new).register();
        BlockEntry<NordicLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, null, NordicLockboxBlock::new).register();
        BlockEntry<NordicWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, null, NordicWardrobeBottomBlock::new).register();
        BlockEntry<NordicWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, null, NordicWardrobeTopBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, null, NordicPaintingSmallBlock::new).register();
        BlockEntry<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, null, NordicPaintingWideBlock::new).register();
        BlockEntry<NordicOvenBlock> OVEN = FurnitureSets.oven(ID, null, NordicOvenBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, null, NordicDoorBlock::new).register();
        BlockEntry<NordicDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, null, NordicDoorBlock::new).register();
        BlockEntry<NordicBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, null, NordicBedBlock::new).register();
        BlockEntry<NordicBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, null, NordicBedBlock::new).register();
        BlockEntry<NordicShelfBlock> SHELF = FurnitureSets.shelf(ID, null, NordicShelfBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<NordicSofaBlock> SOFA = FurnitureSets.sofa(ID, null, NordicSofaBlock::new).register();
        BlockEntry<NordicCounterBlock> COUNTER = FurnitureSets.counter(ID, null, NordicCounterBlock::new).register();

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
        BlockEntityEntry<NordicChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, NordicChestBlockEntity::new)
            .validBlocks(Blocks.CHEST)
            .register();

        BlockEntityEntry<NordicBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, NordicBookshelfBlockEntity::new)
                .validBlocks(Blocks.BOOKSHELF)
                .register();

        BlockEntityEntry<NordicDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, NordicDeskBlockEntity::new)
                .validBlocks(Blocks.DESK_LEFT, Blocks.DESK_RIGHT)
                .register();

        BlockEntityEntry<NordicDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, NordicDrawerBlockEntity::new)
                .validBlocks(Blocks.DRAWER)
                .register();

        BlockEntityEntry<NordicDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, NordicDresserBlockEntity::new)
                .validBlocks(Blocks.DRESSER)
                .register();

        BlockEntityEntry<NordicLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, NordicLockboxBlockEntity::new)
                .validBlocks(Blocks.LOCKBOX)
                .register();

        BlockEntityEntry<NordicWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, NordicWardrobeBlockEntity::new)
                .validBlocks(Blocks.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<NordicOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", NordicOvenBlockEntity::new)
                .validBlocks(Blocks.OVEN)
                .register();

        BlockEntityEntry<NordicCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, NordicCounterBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

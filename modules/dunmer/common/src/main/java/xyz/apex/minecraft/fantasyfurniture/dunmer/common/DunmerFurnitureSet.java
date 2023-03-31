package xyz.apex.minecraft.fantasyfurniture.dunmer.common;

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
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.*;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity.*;

public interface DunmerFurnitureSet
{
    String BASE_ID = "dunmer";
    String ID = "fantasyfurniture_dunmer";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        DunmerVoxelShapes.bootstrap();
        Blocks.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Blocks
    {
        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, null, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, null, CarpetBlock::new).register();
        BlockEntry<DunmerWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, null, DunmerWallLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, null, DunmerFloorLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, null, DunmerTableLargeBlock::new).register();
        BlockEntry<DunmerTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, null, DunmerTableSmallBlock::new).register();
        BlockEntry<DunmerTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, null, DunmerTableWideBlock::new).register();
        BlockEntry<DunmerBenchBlock> BENCH = FurnitureSets.bench(ID, null, DunmerBenchBlock::new).register();
        BlockEntry<DunmerChairBlock> CHAIR = FurnitureSets.chair(ID, null, DunmerChairBlock::new).register();
        BlockEntry<DunmerCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, null, DunmerCeilingLightBlock::new).register();
        BlockEntry<DunmerCushionBlock> CUSHION = FurnitureSets.cushion(ID, null, DunmerCushionBlock::new).register();
        BlockEntry<DunmerStoolBlock> STOOL = FurnitureSets.stool(ID, null, DunmerStoolBlock::new).register();
        BlockEntry<DunmerChestBlock> CHEST = FurnitureSets.chest(ID, null, DunmerChestBlock::new).register();
        BlockEntry<DunmerBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, null, DunmerBookshelfBlock::new).register();
        BlockEntry<DunmerDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, null, DunmerDeskBlock::new).register();
        BlockEntry<DunmerDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, null, DunmerDeskBlock::new).register();
        BlockEntry<DunmerDrawerBlock> DRAWER = FurnitureSets.drawer(ID, null, DunmerDrawerBlock::new).register();
        BlockEntry<DunmerDresserBlock> DRESSER = FurnitureSets.dresser(ID, null, DunmerDresserBlock::new).register();
        BlockEntry<DunmerLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, null, DunmerLockboxBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, null, DunmerWardrobeBottomBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, null, DunmerWardrobeTopBlock::new).register();
        BlockEntry<DunmerPaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, null, DunmerPaintingSmallBlock::new).register();
        BlockEntry<DunmerPaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, null, DunmerPaintingWideBlock::new).register();
        BlockEntry<DunmerOvenBlock> OVEN = FurnitureSets.oven(ID, null, DunmerOvenBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, null, DunmerDoorBlock::new).register();
        BlockEntry<DunmerDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, null, DunmerDoorBlock::new).register();
        BlockEntry<DunmerBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, null, DunmerBedBlock::new).register();
        BlockEntry<DunmerBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, null, DunmerBedBlock::new).register();
        BlockEntry<DunmerShelfBlock> SHELF = FurnitureSets.shelf(ID, null, DunmerShelfBlock::new).register();
        BlockEntry<DunmerSofaBlock> SOFA = FurnitureSets.sofa(ID, null, DunmerSofaBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<DunmerCounterBlock> COUNTER = FurnitureSets.counter(ID, null, DunmerCounterBlock::new).renderType(() -> RenderType::cutout).register();

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
        BlockEntityEntry<DunmerChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, DunmerChestBlockEntity::new)
            .validBlocks(Blocks.CHEST)
            .register();

        BlockEntityEntry<DunmerBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, DunmerBookshelfBlockEntity::new)
                .validBlocks(Blocks.BOOKSHELF)
                .register();

        BlockEntityEntry<DunmerDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, DunmerDeskBlockEntity::new)
                .validBlocks(Blocks.DESK_LEFT, Blocks.DESK_RIGHT)
                .register();

        BlockEntityEntry<DunmerDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, DunmerDrawerBlockEntity::new)
                .validBlocks(Blocks.DRAWER)
                .register();

        BlockEntityEntry<DunmerDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, DunmerDresserBlockEntity::new)
                .validBlocks(Blocks.DRESSER)
                .register();

        BlockEntityEntry<DunmerLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, DunmerLockboxBlockEntity::new)
                .validBlocks(Blocks.LOCKBOX)
                .register();

        BlockEntityEntry<DunmerWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, DunmerWardrobeBlockEntity::new)
                .validBlocks(Blocks.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<DunmerOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", DunmerOvenBlockEntity::new)
                .validBlocks(Blocks.OVEN)
                .register();

        BlockEntityEntry<DunmerCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, DunmerCounterBlockEntity::new)
                .validBlocks(Blocks.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

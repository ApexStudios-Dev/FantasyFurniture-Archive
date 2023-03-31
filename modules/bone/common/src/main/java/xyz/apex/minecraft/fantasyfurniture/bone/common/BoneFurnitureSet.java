package xyz.apex.minecraft.fantasyfurniture.bone.common;

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
import xyz.apex.minecraft.fantasyfurniture.bone.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity.*;
import xyz.apex.minecraft.fantasyfurniture.common.block.CarpetBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.FurnitureSets;

public interface BoneFurnitureSet
{
    String BASE_ID = "bone";
    String ID = "fantasyfurniture_bone";

    TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, BASE_ID);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, BASE_ID);

    default void bootstrap()
    {
        BoneVoxelShapes.bootstrap();
        Wither.bootstrap();
        Skeleton.bootstrap();
        BlockEntityTypes.bootstrap();
        RegistryManager.register(ID);
    }

    interface Wither
    {
        String SUBSET_ID = "wither";

        TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, "%s/%s".formatted(BASE_ID, SUBSET_ID));
        TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, "%s/%s".formatted(BASE_ID, SUBSET_ID));

        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, SUBSET_ID, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, SUBSET_ID, CarpetBlock::new).register();
        BlockEntry<BoneWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, SUBSET_ID, BoneWallLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<BoneFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, SUBSET_ID, BoneFloorLightBlock::new).register();
        BlockEntry<BoneTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, SUBSET_ID, BoneTableLargeBlock::new).register();
        BlockEntry<BoneTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, SUBSET_ID, BoneTableSmallBlock::new).register();
        BlockEntry<BoneTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, SUBSET_ID, BoneTableWideBlock::new).register();
        BlockEntry<BoneBenchBlock> BENCH = FurnitureSets.bench(ID, SUBSET_ID, BoneBenchBlock::new).register();
        BlockEntry<BoneChairBlock> CHAIR = FurnitureSets.chair(ID, SUBSET_ID, BoneChairBlock::new).register();
        BlockEntry<BoneCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, SUBSET_ID, BoneCeilingLightBlock::new).register();
        BlockEntry<BoneCushionBlock> CUSHION = FurnitureSets.cushion(ID, SUBSET_ID, BoneCushionBlock::new).register();
        BlockEntry<BoneStoolBlock> STOOL = FurnitureSets.stool(ID, SUBSET_ID, BoneStoolBlock::new).register();
        BlockEntry<BoneChestBlock> CHEST = FurnitureSets.chest(ID, SUBSET_ID, BoneChestBlock::new).register();
        BlockEntry<BoneBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, SUBSET_ID, BoneBookshelfBlock::new).register();
        BlockEntry<BoneDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, SUBSET_ID, BoneDeskBlock::new).register();
        BlockEntry<BoneDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, SUBSET_ID, BoneDeskBlock::new).register();
        BlockEntry<BoneDrawerBlock> DRAWER = FurnitureSets.drawer(ID, SUBSET_ID, BoneDrawerBlock::new).register();
        BlockEntry<BoneDresserBlock> DRESSER = FurnitureSets.dresser(ID, SUBSET_ID, BoneDresserBlock::new).register();
        BlockEntry<BoneLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, SUBSET_ID, BoneLockboxBlock::new).register();
        BlockEntry<BoneWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, SUBSET_ID, BoneWardrobeBottomBlock::new).register();
        BlockEntry<BoneWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, SUBSET_ID, BoneWardrobeTopBlock::new).register();
        BlockEntry<BonePaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, SUBSET_ID, BonePaintingSmallBlock::new).register();
        BlockEntry<BonePaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, SUBSET_ID, BonePaintingWideBlock::new).register();
        BlockEntry<BoneOvenBlock> OVEN = FurnitureSets.oven(ID, SUBSET_ID, BoneOvenBlock::new).register();
        BlockEntry<BoneDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, SUBSET_ID, BoneDoorBlock::new).register();
        BlockEntry<BoneDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, SUBSET_ID, BoneDoorBlock::new).register();
        BlockEntry<BoneBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, SUBSET_ID, BoneBedBlock::new).register();
        BlockEntry<BoneBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, SUBSET_ID, BoneBedBlock::new).register();
        BlockEntry<BoneShelfBlock> SHELF = FurnitureSets.shelf(ID, SUBSET_ID, BoneShelfBlock::new).register();
        BlockEntry<BoneSofaBlock> SOFA = FurnitureSets.sofa(ID, SUBSET_ID, BoneSofaBlock::new).register();
        BlockEntry<BoneCounterBlock> COUNTER = FurnitureSets.counter(ID, SUBSET_ID, BoneCounterBlock::new).register();

        private static void bootstrap()
        {
            FurnitureSets.creativeModeTab(ID, SUBSET_ID, builder -> builder
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

    interface Skeleton
    {
        String SUBSET_ID = "skeleton";

        TagKey<Item> ITEM_TAG = TagHelper.itemTag(ID, "%s/%s".formatted(BASE_ID, SUBSET_ID));
        TagKey<Block> BLOCK_TAG = TagHelper.blockTag(ID, "%s/%s".formatted(BASE_ID, SUBSET_ID));

        BlockEntry<BaseBlockComponentHolder> WOOL = FurnitureSets.wool(ID, SUBSET_ID, BaseBlockComponentHolder::new).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(ID, SUBSET_ID, CarpetBlock::new).register();
        BlockEntry<BoneWallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(ID, SUBSET_ID, BoneWallLightBlock::new).renderType(() -> RenderType::cutout).register();
        BlockEntry<BoneFloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(ID, SUBSET_ID, BoneFloorLightBlock::new).register();
        BlockEntry<BoneTableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(ID, SUBSET_ID, BoneTableLargeBlock::new).register();
        BlockEntry<BoneTableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(ID, SUBSET_ID, BoneTableSmallBlock::new).register();
        BlockEntry<BoneTableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(ID, SUBSET_ID, BoneTableWideBlock::new).register();
        BlockEntry<BoneBenchBlock> BENCH = FurnitureSets.bench(ID, SUBSET_ID, BoneBenchBlock::new).register();
        BlockEntry<BoneChairBlock> CHAIR = FurnitureSets.chair(ID, SUBSET_ID, BoneChairBlock::new).register();
        BlockEntry<BoneCeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(ID, SUBSET_ID, BoneCeilingLightBlock::new).register();
        BlockEntry<BoneCushionBlock> CUSHION = FurnitureSets.cushion(ID, SUBSET_ID, BoneCushionBlock::new).register();
        BlockEntry<BoneStoolBlock> STOOL = FurnitureSets.stool(ID, SUBSET_ID, BoneStoolBlock::new).register();
        BlockEntry<BoneChestBlock> CHEST = FurnitureSets.chest(ID, SUBSET_ID, BoneChestBlock::new).register();
        BlockEntry<BoneBookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(ID, SUBSET_ID, BoneBookshelfBlock::new).register();
        BlockEntry<BoneDeskBlock> DESK_LEFT = FurnitureSets.deskLeft(ID, SUBSET_ID, BoneDeskBlock::new).register();
        BlockEntry<BoneDeskBlock> DESK_RIGHT = FurnitureSets.deskRight(ID, SUBSET_ID, BoneDeskBlock::new).register();
        BlockEntry<BoneDrawerBlock> DRAWER = FurnitureSets.drawer(ID, SUBSET_ID, BoneDrawerBlock::new).register();
        BlockEntry<BoneDresserBlock> DRESSER = FurnitureSets.dresser(ID, SUBSET_ID, BoneDresserBlock::new).register();
        BlockEntry<BoneLockboxBlock> LOCKBOX = FurnitureSets.lockbox(ID, SUBSET_ID, BoneLockboxBlock::new).register();
        BlockEntry<BoneWardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(ID, SUBSET_ID, BoneWardrobeBottomBlock::new).register();
        BlockEntry<BoneWardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(ID, SUBSET_ID, BoneWardrobeTopBlock::new).register();
        BlockEntry<BonePaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(ID, SUBSET_ID, BonePaintingSmallBlock::new).register();
        BlockEntry<BonePaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(ID, SUBSET_ID, BonePaintingWideBlock::new).register();
        BlockEntry<BoneOvenBlock> OVEN = FurnitureSets.oven(ID, SUBSET_ID, BoneOvenBlock::new).register();
        BlockEntry<BoneDoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(ID, SUBSET_ID, BoneDoorBlock::new).register();
        BlockEntry<BoneDoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(ID, SUBSET_ID, BoneDoorBlock::new).register();
        BlockEntry<BoneBedBlock> BED_SINGLE = FurnitureSets.bedSingle(ID, SUBSET_ID, BoneBedBlock::new).register();
        BlockEntry<BoneBedBlock> BED_DOUBLE = FurnitureSets.bedDouble(ID, SUBSET_ID, BoneBedBlock::new).register();
        BlockEntry<BoneShelfBlock> SHELF = FurnitureSets.shelf(ID, SUBSET_ID, BoneShelfBlock::new).register();
        BlockEntry<BoneSofaBlock> SOFA = FurnitureSets.sofa(ID, SUBSET_ID, BoneSofaBlock::new).register();
        BlockEntry<BoneCounterBlock> COUNTER = FurnitureSets.counter(ID, SUBSET_ID, BoneCounterBlock::new).register();

        private static void bootstrap()
        {
            FurnitureSets.creativeModeTab(ID, SUBSET_ID, builder -> builder
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
        BlockEntityEntry<BoneChestBlockEntity> CHEST = BlockEntityBuilder
            .builderWithComponents(ID, "chest", registrar -> {}, BoneChestBlockEntity::new)
            .validBlocks(Wither.CHEST, Skeleton.CHEST)
            .register();

        BlockEntityEntry<BoneBookshelfBlockEntity> BOOKSHELF = BlockEntityBuilder
                .builderWithComponents(ID, "bookshelf", registrar -> {}, BoneBookshelfBlockEntity::new)
                .validBlocks(Wither.BOOKSHELF, Skeleton.BOOKSHELF)
                .register();

        BlockEntityEntry<BoneDeskBlockEntity> DESK = BlockEntityBuilder
                .builderWithComponents(ID, "desk", registrar -> {}, BoneDeskBlockEntity::new)
                .validBlocks(Wither.DESK_LEFT, Wither.DESK_RIGHT, Skeleton.DESK_LEFT, Skeleton.DESK_RIGHT)
                .register();

        BlockEntityEntry<BoneDrawerBlockEntity> DRAWER = BlockEntityBuilder
                .builderWithComponents(ID, "drawer", registrar -> {}, BoneDrawerBlockEntity::new)
                .validBlocks(Wither.DRAWER, Skeleton.DRAWER)
                .register();

        BlockEntityEntry<BoneDresserBlockEntity> DRESSER = BlockEntityBuilder
                .builderWithComponents(ID, "dresser", registrar -> {}, BoneDresserBlockEntity::new)
                .validBlocks(Wither.DRESSER, Skeleton.DRESSER)
                .register();

        BlockEntityEntry<BoneLockboxBlockEntity> LOCKBOX = BlockEntityBuilder
                .builderWithComponents(ID, "lockbox", registrar -> {}, BoneLockboxBlockEntity::new)
                .validBlocks(Wither.LOCKBOX, Skeleton.LOCKBOX)
                .register();

        BlockEntityEntry<BoneWardrobeBlockEntity> WARDROBE = BlockEntityBuilder
                .builderWithComponents(ID, "wardrobe", registrar -> {}, BoneWardrobeBlockEntity::new)
                .validBlocks(Wither.WARDROBE_BOTTOM, Skeleton.WARDROBE_BOTTOM)
                .register();

        BlockEntityEntry<BoneOvenBlockEntity> OVEN = BlockEntityBuilder
                .builder(ID, "oven", BoneOvenBlockEntity::new)
                .validBlocks(Wither.OVEN, Skeleton.OVEN)
                .register();

        BlockEntityEntry<BoneCounterBlockEntity> COUNTER = BlockEntityBuilder
                .builderWithComponents(ID, "counter", registrar -> {}, BoneCounterBlockEntity::new)
                .validBlocks(Wither.COUNTER, Skeleton.COUNTER)
                .register();

        private static void bootstrap() {}
    }
}

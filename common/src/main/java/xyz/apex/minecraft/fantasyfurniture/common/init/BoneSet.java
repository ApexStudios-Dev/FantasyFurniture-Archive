package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.ApexTags;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;

public interface BoneSet
{
    String BASE_NAME = "bone";
    DoorMultiBlock.DoorSounds DOOR_SOUNDS = new DoorMultiBlock.DoorSounds(SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE);
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, BASE_NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, BASE_NAME);

    interface Wither
    {
        String NAME = "%s/wither".formatted(BASE_NAME);
        DoorMultiBlock.DoorSounds DOOR_SOUNDS = new DoorMultiBlock.DoorSounds(SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE);
        TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
        TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

        BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
        BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Bone.WALL_LIGHT).renderType(() -> RenderType::cutout).item(ItemNameBlockItem::new).build().register();
        BlockEntry<FloorLightBlock.WithHorizontalFacing> FLOOR_LIGHT = FurnitureSets.floorLightFacing(NAME, AllMultiBlockTypes.MB_1x2x1, () -> AllVoxelShapes.Bone.FLOOR_LIGHT).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Bone.TABLE_LARGE).register();
        BlockEntry<SimpleHorizontalFacingBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Bone.TABLE_SMALL).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Bone.TABLE_WIDE).register();
        BlockEntry<SimpleSeatBlock.WithMultiBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Bone.BENCH).register();
        BlockEntry<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Bone.CHAIR).register();
        BlockEntry<LightBlock> CHANDELIER = FurnitureSets.chandelier(NAME, () -> AllVoxelShapes.Bone.CHANDELIER).register();
        BlockEntry<SimpleSeatBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Bone.CUSHION).register();
        BlockEntry<SimpleSeatBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Bone.STOOL).register();
        BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Bone.CHEST).register();
        BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Bone.BOOKSHELF).register();
        BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Bone.DESK_LEFT).register();
        BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Bone.DESK_RIGHT).register();
        BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Bone.DRAWER).register();
        BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Bone.DRESSER).register();
        BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Bone.LOCKBOX).register();
        BlockEntry<WardrobeBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Bone.WARDROBE_BOTTOM).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Bone.WARDROBE_TOP).register();
        BlockEntry<SimpleHorizontalFacingBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Bone.PAINTING_SMALL).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Bone.PAINTING_WIDE).register();
        BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Bone.OVEN).register();
        BlockEntry<DoorMultiBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Bone.DOOR_DOUBLE).register();
        BlockEntry<DoorMultiBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Bone.DOOR_SINGLE).register();
        BlockEntry<BedMultiBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Bone.BED_SINGLE).register();
        BlockEntry<BedMultiBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Bone.BED_DOUBLE).register();
        BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Bone.SHELF_SINGLE, AllVoxelShapes.Bone::getShelfShape).register();
        BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Bone.SOFA_SINGLE, AllVoxelShapes.Bone::getSofaShape).register();
        BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Bone.COUNTER_SINGLE, AllVoxelShapes.Bone::getCounterShape).register();

        static void bootstrap()
        {
            FurnitureSets.creativeModeTab(NAME, builder -> builder
                    .icon(BED_SINGLE::asStack)
                    .displayItems(BoneSet.Wither::addTabItems)
            );
        }

        private static void addTabItems(FeatureFlagSet enabledFeatures, CreativeModeTab.Output output, boolean showAdmin)
        {
            output.accept(WOOL);
            output.accept(CARPET);
            output.accept(WALL_LIGHT);
            output.accept(FLOOR_LIGHT);
            output.accept(TABLE_LARGE);
            output.accept(TABLE_SMALL);
            output.accept(TABLE_WIDE);
            output.accept(BENCH);
            output.accept(CHAIR);
            output.accept(CHANDELIER);
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
            output.accept(PAINTING_WIDE);
            output.accept(PAINTING_SMALL);
            output.accept(OVEN);
            output.accept(DOOR_DOUBLE);
            output.accept(DOOR_SINGLE);
            output.accept(BED_SINGLE);
            output.accept(BED_DOUBLE);
            output.accept(SHELF);
            output.accept(SOFA);
            output.accept(COUNTER);
        }
    }

    interface Skeleton
    {
        String NAME = "%s/skeleton".formatted(BASE_NAME);
        TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
        TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

        BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
        BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
        BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Bone.WALL_LIGHT).renderType(() -> RenderType::cutout).item(ItemNameBlockItem::new).build().register();
        BlockEntry<FloorLightBlock.WithHorizontalFacing> FLOOR_LIGHT = FurnitureSets.floorLightFacing(NAME, AllMultiBlockTypes.MB_1x2x1, () -> AllVoxelShapes.Bone.FLOOR_LIGHT).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Bone.TABLE_LARGE).register();
        BlockEntry<SimpleHorizontalFacingBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Bone.TABLE_SMALL).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Bone.TABLE_WIDE).register();
        BlockEntry<SimpleSeatBlock.WithMultiBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Bone.BENCH).register();
        BlockEntry<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Bone.CHAIR).register();
        BlockEntry<LightBlock> CHANDELIER = FurnitureSets.chandelier(NAME, () -> AllVoxelShapes.Bone.CHANDELIER).register();
        BlockEntry<SimpleSeatBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Bone.CUSHION).register();
        BlockEntry<SimpleSeatBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Bone.STOOL).register();
        BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Bone.CHEST).register();
        BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Bone.BOOKSHELF).register();
        BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Bone.DESK_LEFT).register();
        BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Bone.DESK_RIGHT).register();
        BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Bone.DRAWER).register();
        BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Bone.DRESSER).register();
        BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Bone.LOCKBOX).register();
        BlockEntry<WardrobeBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Bone.WARDROBE_BOTTOM).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Bone.WARDROBE_TOP).register();
        BlockEntry<SimpleHorizontalFacingBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Bone.PAINTING_SMALL).register();
        BlockEntry<SimpleMultiBlock.WithHorizontalFacing> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Bone.PAINTING_WIDE).register();
        BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Bone.OVEN).register();
        BlockEntry<DoorMultiBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Bone.DOOR_DOUBLE).register();
        BlockEntry<DoorMultiBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Bone.DOOR_SINGLE).register();
        BlockEntry<BedMultiBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Bone.BED_SINGLE).register();
        BlockEntry<BedMultiBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Bone.BED_DOUBLE).register();
        BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Bone.SHELF_SINGLE, AllVoxelShapes.Bone::getShelfShape).register();
        BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Bone.SOFA_SINGLE, AllVoxelShapes.Bone::getSofaShape).register();
        BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Bone.COUNTER_SINGLE, AllVoxelShapes.Bone::getCounterShape).register();

        static void bootstrap()
        {
            FurnitureSets.creativeModeTab(NAME, builder -> builder
                    .icon(BED_SINGLE::asStack)
                    .displayItems(BoneSet.Skeleton::addTabItems)
            );
        }

        private static void addTabItems(FeatureFlagSet enabledFeatures, CreativeModeTab.Output output, boolean showAdmin)
        {
            output.accept(WOOL);
            output.accept(CARPET);
            output.accept(WALL_LIGHT);
            output.accept(FLOOR_LIGHT);
            output.accept(TABLE_LARGE);
            output.accept(TABLE_SMALL);
            output.accept(TABLE_WIDE);
            output.accept(BENCH);
            output.accept(CHAIR);
            output.accept(CHANDELIER);
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
            output.accept(PAINTING_WIDE);
            output.accept(PAINTING_SMALL);
            output.accept(OVEN);
            output.accept(DOOR_DOUBLE);
            output.accept(DOOR_SINGLE);
            output.accept(BED_SINGLE);
            output.accept(BED_DOUBLE);
            output.accept(SHELF);
            output.accept(SOFA);
            output.accept(COUNTER);
        }
    }

    static void bootstrap()
    {
        Wither.bootstrap();
        Skeleton.bootstrap();

        FurnitureSets.creativeModeTab(BASE_NAME, builder -> builder
                .icon(BoneSet.Wither.BED_SINGLE::asStack)
                .displayItems((enabledFeatures, output, showAdmin) -> {
                    if(showAdmin)
                    {
                        BoneSet.Wither.addTabItems(enabledFeatures, output, showAdmin);
                        BoneSet.Skeleton.addTabItems(enabledFeatures, output, showAdmin);
                    }
                })
        );
    }
}

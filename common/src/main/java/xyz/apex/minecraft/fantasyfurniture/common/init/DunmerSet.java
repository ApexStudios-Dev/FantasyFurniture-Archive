package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.ApexTags;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;

public interface DunmerSet
{
    String NAME = "dunmer";
    DoorMultiBlock.DoorSounds DOOR_SOUNDS = new DoorMultiBlock.DoorSounds(SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE);
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Dunmer.WALL_LIGHT).renderType(() -> RenderType::cutout).item(ItemNameBlockItem::new).build().register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, AllMultiBlockTypes.MB_1x2x1, () -> AllVoxelShapes.Dunmer.FLOOR_LIGHT).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Dunmer.TABLE_LARGE).register();
    BlockEntry<SimpleHorizontalFacingBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Dunmer.TABLE_SMALL).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Dunmer.TABLE_WIDE).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Dunmer.BENCH).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Dunmer.CHAIR).register();
    BlockEntry<LightBlock> CHANDELIER = FurnitureSets.chandelier(NAME, () -> AllVoxelShapes.Dunmer.CHANDELIER).register();
    BlockEntry<SimpleSeatBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Dunmer.CUSHION).register();
    BlockEntry<SimpleSeatBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Dunmer.STOOL).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Dunmer.CHEST).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Dunmer.BOOKSHELF).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Dunmer.DESK_LEFT).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Dunmer.DESK_RIGHT).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Dunmer.DRAWER).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Dunmer.DRESSER).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Dunmer.LOCKBOX).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Dunmer.WARDROBE_BOTTOM).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Dunmer.WARDROBE_TOP).register();
    BlockEntry<SimpleHorizontalFacingBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Dunmer.PAINTING_SMALL).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Dunmer.PAINTING_WIDE).register();
    BlockEntry<OvenBlock.AsMultiBlock> OVEN = FurnitureSets.ovenMultiBlock(NAME, () -> AllVoxelShapes.Dunmer.OVEN).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorMultiBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Dunmer.DOOR_DOUBLE).register();
    BlockEntry<DoorMultiBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Dunmer.DOOR_SINGLE).register();
    BlockEntry<BedMultiBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Dunmer.BED_SINGLE).register();
    BlockEntry<BedMultiBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Dunmer.BED_DOUBLE).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Dunmer.SHELF_SINGLE, AllVoxelShapes.Dunmer::getShelfShape).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Dunmer.SOFA_SINGLE, AllVoxelShapes.Dunmer::getSofaShape).renderType(() -> RenderType::cutout).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Dunmer.COUNTER_SINGLE, AllVoxelShapes.Dunmer::getCounterShape).renderType(() -> RenderType::cutout).register();

    static void bootstrap()
    {
        FurnitureSets.creativeModeTab(NAME, builder -> builder
                .icon(BED_SINGLE::asStack)
                .displayItems((featureFlagSet, output, b) -> {
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
                })
        );
    }
}

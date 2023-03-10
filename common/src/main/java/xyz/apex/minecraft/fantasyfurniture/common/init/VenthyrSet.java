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

public interface VenthyrSet
{
    String NAME = "venthyr";
    DoorMultiBlock.DoorSounds DOOR_SOUNDS = new DoorMultiBlock.DoorSounds(SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE);
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Venthyr.WALL_LIGHT).item(ItemNameBlockItem::new).build().register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, AllMultiBlockTypes.MB_1x2x1, () -> AllVoxelShapes.Venthyr.FLOOR_LIGHT).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Venthyr.TABLE_LARGE).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE_FANCY = FurnitureSets.tableLargeFancy(NAME, () -> AllVoxelShapes.Venthyr.TABLE_LARGE).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleHorizontalFacingBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Venthyr.TABLE_SMALL).register();
    BlockEntry<SimpleHorizontalFacingBlock> TABLE_SMALL_FANCY = FurnitureSets.tableSmallFancy(NAME).hitbox(() -> AllVoxelShapes.Venthyr.TABLE_SMALL).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Venthyr.TABLE_WIDE).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE_FANCY = FurnitureSets.tableWideFancy(NAME, () -> AllVoxelShapes.Venthyr.TABLE_WIDE).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Venthyr.BENCH).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Venthyr.CHAIR).renderType(() -> RenderType::cutout).register();
    BlockEntry<LightBlock> CHANDELIER = FurnitureSets.chandelier(NAME, () -> AllVoxelShapes.Venthyr.CHANDELIER).renderType(() -> RenderType::cutout).register();
    BlockEntry<SimpleSeatBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Venthyr.CUSHION).register();
    BlockEntry<SimpleSeatBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Venthyr.STOOL).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Venthyr.CHEST).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Venthyr.BOOKSHELF).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Venthyr.DESK_LEFT).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Venthyr.DESK_RIGHT).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Venthyr.DRAWER).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Venthyr.DRESSER).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Venthyr.LOCKBOX).register();
    BlockEntry<WardrobeBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Venthyr.WARDROBE_BOTTOM).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Venthyr.WARDROBE_TOP).register();
    BlockEntry<SimpleHorizontalFacingBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Venthyr.PAINTING_SMALL).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Venthyr.PAINTING_WIDE).register();
    BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Venthyr.OVEN).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorMultiBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Venthyr.DOOR_DOUBLE).register();
    BlockEntry<DoorMultiBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, DOOR_SOUNDS, () -> AllVoxelShapes.Venthyr.DOOR_SINGLE).register();
    BlockEntry<BedMultiBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Venthyr.BED_SINGLE).register();
    BlockEntry<BedMultiBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Venthyr.BED_DOUBLE).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Venthyr.SHELF_SINGLE, AllVoxelShapes.Venthyr::getShelfShape).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Venthyr.SOFA_SINGLE, AllVoxelShapes.Venthyr::getSofaShape).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Venthyr.COUNTER_SINGLE, AllVoxelShapes.Venthyr::getCounterShape).register();

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
                    output.accept(TABLE_LARGE_FANCY);
                    output.accept(TABLE_SMALL);
                    output.accept(TABLE_SMALL_FANCY);
                    output.accept(TABLE_WIDE);
                    output.accept(TABLE_WIDE_FANCY);
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

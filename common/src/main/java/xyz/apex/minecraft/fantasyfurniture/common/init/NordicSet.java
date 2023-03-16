package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.ApexTags;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;

public interface NordicSet
{
    String NAME = "nordic";
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Nordic.WALL_LIGHT).renderType(() -> RenderType::cutout).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, () -> AllVoxelShapes.Nordic.FLOOR_LIGHT).register();
    BlockEntry<TableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Nordic.TABLE_LARGE).register();
    BlockEntry<TableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Nordic.TABLE_SMALL).register();
    BlockEntry<TableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Nordic.TABLE_WIDE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BenchBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Nordic.BENCH).register();
    BlockEntry<ChairBlock> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Nordic.CHAIR).renderType(() -> RenderType::cutout).register();
    BlockEntry<CeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(NAME, () -> AllVoxelShapes.Nordic.CEILING_LIGHT).register();
    BlockEntry<CushionBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Nordic.CUSHION).register();
    BlockEntry<StoolBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Nordic.STOOL).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Nordic.CHEST).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Nordic.BOOKSHELF).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Nordic.DESK_LEFT).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Nordic.DESK_RIGHT).renderType(() -> RenderType::cutout).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Nordic.DRAWER).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Nordic.DRESSER).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Nordic.LOCKBOX).register();
    BlockEntry<WardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Nordic.WARDROBE_BOTTOM).register();
    BlockEntry<WardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Nordic.WARDROBE_TOP).renderType(() -> RenderType::cutout).register();
    BlockEntry<PaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Nordic.PAINTING_SMALL).register();
    BlockEntry<PaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Nordic.PAINTING_WIDE).register();
    BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Nordic.OVEN).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, () -> AllVoxelShapes.Nordic.DOOR_DOUBLE).register();
    BlockEntry<DoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, () -> AllVoxelShapes.Nordic.DOOR_SINGLE).register();
    BlockEntry<BedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Nordic.BED_SINGLE).register();
    BlockEntry<BedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Nordic.BED_DOUBLE).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Nordic.SHELF_SINGLE, AllVoxelShapes.Nordic::getShelfShape).renderType(() -> RenderType::cutout).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Nordic.SOFA_SINGLE, AllVoxelShapes.Nordic::getSofaShape).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Nordic.COUNTER_SINGLE, AllVoxelShapes.Nordic::getCounterShape).register();

    static void bootstrap()
    {
        FurnitureSets.creativeModeTab(NAME, builder -> builder
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

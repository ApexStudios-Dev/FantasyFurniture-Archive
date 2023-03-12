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

public interface NecrolordSet
{
    String NAME = "necrolord";
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Necrolord.WALL_LIGHT).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, () -> AllVoxelShapes.Necrolord.FLOOR_LIGHT).register();
    BlockEntry<TableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Necrolord.TABLE_LARGE).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Necrolord.TABLE_SMALL).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Necrolord.TABLE_WIDE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BenchBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Necrolord.BENCH).renderType(() -> RenderType::cutout).register();
    BlockEntry<ChairBlock> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Necrolord.CHAIR).register();
    BlockEntry<CeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(NAME, () -> AllVoxelShapes.Necrolord.CEILING_LIGHT).renderType(() -> RenderType::cutout).register();
    BlockEntry<CushionBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Necrolord.CUSHION).register();
    BlockEntry<StoolBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Necrolord.STOOL).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Necrolord.CHEST).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Necrolord.BOOKSHELF).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Necrolord.DESK_LEFT).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Necrolord.DESK_RIGHT).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Necrolord.DRAWER).renderType(() -> RenderType::cutout).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Necrolord.DRESSER).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Necrolord.LOCKBOX).register();
    BlockEntry<WardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Necrolord.WARDROBE_BOTTOM).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Necrolord.WARDROBE_TOP).renderType(() -> RenderType::cutout).register();
    BlockEntry<PaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Necrolord.PAINTING_SMALL).register();
    BlockEntry<PaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Necrolord.PAINTING_WIDE).register();
    BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Necrolord.OVEN).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, () -> AllVoxelShapes.Necrolord.DOOR_DOUBLE).register();
    BlockEntry<DoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, () -> AllVoxelShapes.Necrolord.DOOR_SINGLE).register();
    BlockEntry<BedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Necrolord.BED_SINGLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Necrolord.BED_DOUBLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Necrolord.SHELF_SINGLE, AllVoxelShapes.Necrolord::getShelfShape).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Necrolord.SOFA_SINGLE, AllVoxelShapes.Necrolord::getSofaShape).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Necrolord.COUNTER_SINGLE, AllVoxelShapes.Necrolord::getCounterShape).register();

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

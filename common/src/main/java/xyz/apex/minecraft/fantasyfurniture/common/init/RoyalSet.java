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

public interface RoyalSet
{
    String NAME = "royal";
    TagKey<Item> ITEM_TAG = ApexTags.Items.tag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = ApexTags.Blocks.tag(FantasyFurniture.ID, NAME);

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME, () -> AllVoxelShapes.Royal.WALL_LIGHT).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, () -> AllVoxelShapes.Royal.FLOOR_LIGHT).register();
    BlockEntry<TableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Royal.TABLE_LARGE).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Royal.TABLE_SMALL).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Royal.TABLE_WIDE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BenchBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Royal.BENCH).renderType(() -> RenderType::cutout).register();
    BlockEntry<ChairBlock> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Royal.CHAIR).renderType(() -> RenderType::cutout).register();
    BlockEntry<CeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(NAME, () -> AllVoxelShapes.Royal.CEILING_LIGHT).register();
    BlockEntry<CushionBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Royal.CUSHION).renderType(() -> RenderType::cutout).register();
    BlockEntry<StoolBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Royal.STOOL).renderType(() -> RenderType::cutout).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(NAME, () -> AllVoxelShapes.Royal.CHEST).renderType(() -> RenderType::cutout).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(NAME, () -> AllVoxelShapes.Royal.BOOKSHELF).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(NAME, () -> AllVoxelShapes.Royal.DESK_LEFT).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(NAME, () -> AllVoxelShapes.Royal.DESK_RIGHT).renderType(() -> RenderType::cutout).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(NAME, () -> AllVoxelShapes.Royal.DRAWER).renderType(() -> RenderType::cutout).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(NAME, () -> AllVoxelShapes.Royal.DRESSER).renderType(() -> RenderType::cutout).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(NAME, () -> AllVoxelShapes.Royal.LOCKBOX).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(NAME, () -> AllVoxelShapes.Royal.WARDROBE_BOTTOM).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(NAME, () -> AllVoxelShapes.Royal.WARDROBE_TOP).renderType(() -> RenderType::cutout).register();
    BlockEntry<PaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(NAME, () -> AllVoxelShapes.Royal.PAINTING_SMALL).register();
    BlockEntry<PaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(NAME, () -> AllVoxelShapes.Royal.PAINTING_WIDE).register();
    BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(NAME, () -> AllVoxelShapes.Royal.OVEN).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(NAME, () -> AllVoxelShapes.Royal.DOOR_DOUBLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(NAME, () -> AllVoxelShapes.Royal.DOOR_SINGLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(NAME, () -> AllVoxelShapes.Royal.BED_SINGLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<BedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(NAME, () -> AllVoxelShapes.Royal.BED_DOUBLE).renderType(() -> RenderType::cutout).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(NAME, () -> AllVoxelShapes.Royal.SHELF_SINGLE, AllVoxelShapes.Royal::getShelfShape).renderType(() -> RenderType::cutout).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(NAME, () -> AllVoxelShapes.Royal.SOFA_SINGLE, AllVoxelShapes.Royal::getSofaShape).renderType(() -> RenderType::cutout).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(NAME, () -> AllVoxelShapes.Royal.COUNTER_SINGLE, AllVoxelShapes.Royal::getCounterShape).renderType(() -> RenderType::cutout).register();

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

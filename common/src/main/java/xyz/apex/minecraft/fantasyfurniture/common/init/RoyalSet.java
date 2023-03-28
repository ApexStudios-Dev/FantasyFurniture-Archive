package xyz.apex.minecraft.fantasyfurniture.common.init;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntry;
import xyz.apex.minecraft.apexcore.common.util.TagHelper;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.*;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.CarpetBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.DyeableBlockComponent;

public interface RoyalSet
{
    String NAME = "royal";
    TagKey<Item> ITEM_TAG = TagHelper.itemTag(FantasyFurniture.ID, NAME);
    TagKey<Block> BLOCK_TAG = TagHelper.blockTag(FantasyFurniture.ID, NAME);

    BlockEntry<DyeableBlock> WOOL = FurnitureSets.wool(FantasyFurniture.ID, NAME, DyeableBlock::new).registerComponent(DyeableBlockComponent.COMPONENT_TYPE).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
    BlockEntry<DyeableBlock> CARPET = FurnitureSets.carpet(FantasyFurniture.ID, NAME, DyeableBlock::new).registerComponent(DyeableBlockComponent.COMPONENT_TYPE).registerComponent(CarpetBlockComponent.COMPONENT_TYPE).renderType(() -> RenderType::cutout).blockColor(FurnitureSets.blockColor()).item(builder -> builder.itemColor(FurnitureSets.itemColor())).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(FantasyFurniture.ID, NAME).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(FantasyFurniture.ID, NAME).register();
    BlockEntry<TableLargeBlock> TABLE_LARGE = FurnitureSets.tableLarge(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableSmallBlock> TABLE_SMALL = FurnitureSets.tableSmall(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<TableWideBlock> TABLE_WIDE = FurnitureSets.tableWide(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<BenchBlock> BENCH = FurnitureSets.bench(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<ChairBlock> CHAIR = FurnitureSets.chair(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<CeilingLightBlock> CEILING_LIGHT = FurnitureSets.ceilingLight(FantasyFurniture.ID, NAME).register();
    BlockEntry<CushionBlock> CUSHION = FurnitureSets.cushion(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<StoolBlock> STOOL = FurnitureSets.stool(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<ChestBlock> CHEST = FurnitureSets.chest(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<BookshelfBlock> BOOKSHELF = FurnitureSets.bookshelf(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_LEFT = FurnitureSets.deskLeft(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DeskBlock> DESK_RIGHT = FurnitureSets.deskRight(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DrawerBlock> DRAWER = FurnitureSets.drawer(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DresserBlock> DRESSER = FurnitureSets.dresser(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<LockboxBlock> LOCKBOX = FurnitureSets.lockbox(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeBottomBlock> WARDROBE_BOTTOM = FurnitureSets.wardrobeBottom(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<WardrobeTopBlock> WARDROBE_TOP = FurnitureSets.wardrobeTop(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<PaintingSmallBlock> PAINTING_SMALL = FurnitureSets.paintingSmall(FantasyFurniture.ID, NAME).register();
    BlockEntry<PaintingWideBlock> PAINTING_WIDE = FurnitureSets.paintingWide(FantasyFurniture.ID, NAME).register();
    BlockEntry<OvenBlock> OVEN = FurnitureSets.oven(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_DOUBLE = FurnitureSets.doorDouble(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<DoorBlock> DOOR_SINGLE = FurnitureSets.doorSingle(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<BedSingleBlock> BED_SINGLE = FurnitureSets.bedSingle(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<BedDoubleBlock> BED_DOUBLE = FurnitureSets.bedDouble(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<ShelfBlock> SHELF = FurnitureSets.shelf(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<SofaBlock> SOFA = FurnitureSets.sofa(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();
    BlockEntry<CounterBlock> COUNTER = FurnitureSets.counter(FantasyFurniture.ID, NAME).renderType(() -> RenderType::cutout).register();

    static void bootstrap()
    {
        FurnitureSets.creativeModeTab(FantasyFurniture.ID, NAME, builder -> builder
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

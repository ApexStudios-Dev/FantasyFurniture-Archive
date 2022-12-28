package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockTypes;
import xyz.apex.minecraft.apexcore.shared.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.LightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.SimpleSeatBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

public interface NordicSet
{
    String NAME = "nordic";

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME).item(ItemNameBlockItem::new).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, MultiBlockTypes.MB_1x2x1, () -> AllVoxelShapes.Nordic.FLOOR_LIGHT).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_LARGE = FurnitureSets.tableLarge(NAME, () -> AllVoxelShapes.Nordic.TABLE_LARGE).register();
    BlockEntry<Block> TABLE_SMALL = FurnitureSets.tableSmall(NAME).hitbox(() -> AllVoxelShapes.Nordic.TABLE_SMALL).register();
    BlockEntry<SimpleMultiBlock.WithHorizontalFacing> TABLE_WIDE = FurnitureSets.tableWide(NAME, () -> AllVoxelShapes.Nordic.TABLE_WIDE).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock> BENCH = FurnitureSets.bench(NAME, () -> AllVoxelShapes.Nordic.BENCH).register();
    BlockEntry<SimpleSeatBlock.WithMultiBlock.AtOriginOnly> CHAIR = FurnitureSets.chair(NAME, () -> AllVoxelShapes.Nordic.CHAIR).register();
    BlockEntry<LightBlock> CHANDELIER = FurnitureSets.chandelier(NAME, () -> AllVoxelShapes.Nordic.CHANDELIER).register();
    BlockEntry<SimpleSeatBlock> CUSHION = FurnitureSets.cushion(NAME, () -> AllVoxelShapes.Nordic.CUSHION).register();
    BlockEntry<SimpleSeatBlock> STOOL = FurnitureSets.stool(NAME, () -> AllVoxelShapes.Nordic.STOOL).register();

    static void bootstrap()
    {
        FurnitureSets.creativeModeTab(NAME, builder -> builder
                .icon(WOOL::asStack)
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
                })
        );
    }
}

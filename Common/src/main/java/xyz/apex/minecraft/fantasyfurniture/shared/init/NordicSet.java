package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockTypes;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.SimpleBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.TableMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

public interface NordicSet
{
    String NAME = "nordic";

    BlockEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME).item(ItemNameBlockItem::new).register();
    BlockEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME, MultiBlockTypes.MB_1x2x1).register();
    BlockEntry<TableMultiBlock> TABLE_LARGE = FurnitureSets.tableLarge(NAME).register();
    BlockEntry<SimpleBlock> TABLE_SMALL = FurnitureSets.tableSmall(NAME).register();
    BlockEntry<TableMultiBlock> TABLE_WIDE = FurnitureSets.tableWide(NAME).register();

    static void bootstrap()
    {
    }
}

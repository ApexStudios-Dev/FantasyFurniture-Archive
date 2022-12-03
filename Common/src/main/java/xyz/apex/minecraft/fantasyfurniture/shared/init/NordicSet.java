package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.block.FloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.shared.block.WallLightBlock;

public interface NordicSet
{
    String NAME = "nordic";

    BlockRegistryEntry<Block> WOOL = FurnitureSets.wool(NAME).register();
    BlockRegistryEntry<CarpetBlock> CARPET = FurnitureSets.carpet(NAME).register();
    BlockRegistryEntry<WallLightBlock> WALL_LIGHT = FurnitureSets.wallLight(NAME).register();
    BlockRegistryEntry<FloorLightBlock> FLOOR_LIGHT = FurnitureSets.floorLight(NAME).register();

    static void bootstrap()
    {
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(WOOL).register();
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(CARPET).register();
        FantasyFurniture.Registries.ITEMS.blockBuilder(WALL_LIGHT, ItemNameBlockItem::new).register();
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(FLOOR_LIGHT).register();
    }
}

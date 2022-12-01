package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public interface NordicSet
{
    BlockRegistryEntry<Block> WOOL = FantasyFurniture.Registries.BLOCKS.generic("nordic/wool").initialProperties(FantasyFurniture.WOOL_PROPERTIES).register();
    BlockRegistryEntry<CarpetBlock> CARPET = FantasyFurniture.Registries.BLOCKS.builder("nordic/carpet", CarpetBlock::new).initialProperties(FantasyFurniture.CARPET_PROPERTIES).register();

    static void bootstrap()
    {
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(WOOL).register();
        FantasyFurniture.Registries.ITEMS.genericBlockBuilder(CARPET).register();
    }
}

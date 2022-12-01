package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.level.block.Block;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockBuilder;
import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;
import xyz.apex.minecraft.apexcore.shared.registry.item.ItemBuilder;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public interface NordicSet
{
    BlockRegistryEntry<Block> WOOL = BlockBuilder.generic(FantasyFurniture.Registries.BLOCKS, "nordic/wool").initialProperties(FantasyFurniture.WOOL_PROPERTIES).register();

    static void bootstrap()
    {
        ItemBuilder.forBlockGeneric(FantasyFurniture.Registries.ITEMS, WOOL).initialProperties(FantasyFurniture.ITEM_PROPERTIES).register();
    }
}

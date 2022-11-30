package xyz.apex.minecraft.fantasyfurniture.shared.init;

import net.minecraft.world.level.block.Block;

import xyz.apex.minecraft.apexcore.shared.registry.block.BlockRegistryEntry;

public interface NordicSet
{
    BlockRegistryEntry<Block> WOOL = Builders.wool("nordic/wool");

    static void bootstrap()
    {
    }
}

package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

public final class BlockLootTableGenerator extends BlockLoot
{
    @Override
    protected void addTables()
    {
        dropSelf(NordicSet.WOOL.get());
        dropSelf(NordicSet.CARPET.get());
        dropSelf(NordicSet.WALL_LIGHT.get());
        dropSelf(NordicSet.FLOOR_LIGHT.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return FantasyFurniture.Registries.BLOCKS.values();
    }
}

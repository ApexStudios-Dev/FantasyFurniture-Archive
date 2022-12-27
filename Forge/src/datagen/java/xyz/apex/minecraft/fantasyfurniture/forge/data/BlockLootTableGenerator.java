package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import xyz.apex.minecraft.apexcore.shared.platform.Platform;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

import java.util.Set;

public final class BlockLootTableGenerator extends BlockLootSubProvider
{
    BlockLootTableGenerator()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        dropSelf(NordicSet.WOOL.get());
        dropSelf(NordicSet.CARPET.get());
        dropSelf(NordicSet.WALL_LIGHT.get());
        dropSelf(NordicSet.FLOOR_LIGHT.get());
        dropSelf(NordicSet.TABLE_LARGE.get());
        dropSelf(NordicSet.TABLE_WIDE.get());
        dropSelf(NordicSet.TABLE_SMALL.get());
        dropSelf(NordicSet.BENCH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return Platform.INSTANCE.registries().getAllKnown(Registries.BLOCK, FantasyFurniture.ID);
    }
}

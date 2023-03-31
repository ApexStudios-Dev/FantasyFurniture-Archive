package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Set;
import java.util.stream.Collectors;

final class BlockLootTableGenerator extends BlockLootSubProvider
{
    BlockLootTableGenerator()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        dropSelf(FantasyFurniture.FURNITURE_STATION_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(FantasyFurniture.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

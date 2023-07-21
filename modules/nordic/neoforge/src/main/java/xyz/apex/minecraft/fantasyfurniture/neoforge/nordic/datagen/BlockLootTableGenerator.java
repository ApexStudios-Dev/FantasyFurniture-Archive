package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.lib.registry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicBlocks;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicFurnitureSet;

import java.util.Set;

@ApiStatus.Internal
final class BlockLootTableGenerator extends BlockLootSubProvider
{
    BlockLootTableGenerator()
    {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
    }

    @Override
    protected void generate()
    {
        dropSelf(NordicBlocks.WOOL.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return NordicFurnitureSet.REGISTRAR_MANAGER.get(Registries.BLOCK).entries().filter(RegistryEntry::isPresent).map(RegistryEntry::get).toList();
    }
}

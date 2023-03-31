package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

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
        dropSelf(NordicFurnitureSet.Blocks.WOOL.get());
        dropSelf(NordicFurnitureSet.Blocks.CARPET.get());
        dropSelf(NordicFurnitureSet.Blocks.WALL_LIGHT.get());
        dropSelf(NordicFurnitureSet.Blocks.FLOOR_LIGHT.get());
        dropSelf(NordicFurnitureSet.Blocks.TABLE_LARGE.get());
        dropSelf(NordicFurnitureSet.Blocks.TABLE_WIDE.get());
        dropSelf(NordicFurnitureSet.Blocks.TABLE_SMALL.get());
        dropSelf(NordicFurnitureSet.Blocks.BENCH.get());
        dropSelf(NordicFurnitureSet.Blocks.CHAIR.get());
        dropSelf(NordicFurnitureSet.Blocks.CEILING_LIGHT.get());
        dropSelf(NordicFurnitureSet.Blocks.CUSHION.get());
        dropSelf(NordicFurnitureSet.Blocks.STOOL.get());
        dropSelf(NordicFurnitureSet.Blocks.CHEST.get());
        dropSelf(NordicFurnitureSet.Blocks.BOOKSHELF.get());
        dropSelf(NordicFurnitureSet.Blocks.DESK_LEFT.get());
        dropSelf(NordicFurnitureSet.Blocks.DESK_RIGHT.get());
        dropSelf(NordicFurnitureSet.Blocks.DRAWER.get());
        dropSelf(NordicFurnitureSet.Blocks.DRESSER.get());
        dropSelf(NordicFurnitureSet.Blocks.LOCKBOX.get());
        dropSelf(NordicFurnitureSet.Blocks.WARDROBE_BOTTOM.get());
        dropSelf(NordicFurnitureSet.Blocks.WARDROBE_TOP.get());
        dropSelf(NordicFurnitureSet.Blocks.PAINTING_WIDE.get());
        dropSelf(NordicFurnitureSet.Blocks.PAINTING_SMALL.get());
        dropSelf(NordicFurnitureSet.Blocks.OVEN.get());
        dropSelf(NordicFurnitureSet.Blocks.DOOR_DOUBLE.get());
        dropSelf(NordicFurnitureSet.Blocks.DOOR_SINGLE.get());
        dropSelf(NordicFurnitureSet.Blocks.BED_SINGLE.get());
        dropSelf(NordicFurnitureSet.Blocks.BED_DOUBLE.get());
        dropSelf(NordicFurnitureSet.Blocks.SHELF.get());
        dropSelf(NordicFurnitureSet.Blocks.SOFA.get());
        dropSelf(NordicFurnitureSet.Blocks.COUNTER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(NordicFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

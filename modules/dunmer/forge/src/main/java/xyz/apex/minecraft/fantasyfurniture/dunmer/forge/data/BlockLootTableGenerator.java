package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

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
        dropSelf(DunmerFurnitureSet.Blocks.WOOL.get());
        dropSelf(DunmerFurnitureSet.Blocks.CARPET.get());
        dropSelf(DunmerFurnitureSet.Blocks.WALL_LIGHT.get());
        dropSelf(DunmerFurnitureSet.Blocks.FLOOR_LIGHT.get());
        dropSelf(DunmerFurnitureSet.Blocks.TABLE_LARGE.get());
        dropSelf(DunmerFurnitureSet.Blocks.TABLE_WIDE.get());
        dropSelf(DunmerFurnitureSet.Blocks.TABLE_SMALL.get());
        dropSelf(DunmerFurnitureSet.Blocks.BENCH.get());
        dropSelf(DunmerFurnitureSet.Blocks.CHAIR.get());
        dropSelf(DunmerFurnitureSet.Blocks.CEILING_LIGHT.get());
        dropSelf(DunmerFurnitureSet.Blocks.CUSHION.get());
        dropSelf(DunmerFurnitureSet.Blocks.STOOL.get());
        dropSelf(DunmerFurnitureSet.Blocks.CHEST.get());
        dropSelf(DunmerFurnitureSet.Blocks.BOOKSHELF.get());
        dropSelf(DunmerFurnitureSet.Blocks.DESK_LEFT.get());
        dropSelf(DunmerFurnitureSet.Blocks.DESK_RIGHT.get());
        dropSelf(DunmerFurnitureSet.Blocks.DRAWER.get());
        dropSelf(DunmerFurnitureSet.Blocks.DRESSER.get());
        dropSelf(DunmerFurnitureSet.Blocks.LOCKBOX.get());
        dropSelf(DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM.get());
        dropSelf(DunmerFurnitureSet.Blocks.WARDROBE_TOP.get());
        dropSelf(DunmerFurnitureSet.Blocks.PAINTING_WIDE.get());
        dropSelf(DunmerFurnitureSet.Blocks.PAINTING_SMALL.get());
        dropSelf(DunmerFurnitureSet.Blocks.OVEN.get());
        dropSelf(DunmerFurnitureSet.Blocks.DOOR_DOUBLE.get());
        dropSelf(DunmerFurnitureSet.Blocks.DOOR_SINGLE.get());
        dropSelf(DunmerFurnitureSet.Blocks.BED_SINGLE.get());
        dropSelf(DunmerFurnitureSet.Blocks.BED_DOUBLE.get());
        dropSelf(DunmerFurnitureSet.Blocks.SHELF.get());
        dropSelf(DunmerFurnitureSet.Blocks.SOFA.get());
        dropSelf(DunmerFurnitureSet.Blocks.COUNTER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(DunmerFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

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
        dropSelf(NecrolordFurnitureSet.Blocks.WOOL.get());
        dropSelf(NecrolordFurnitureSet.Blocks.CARPET.get());
        dropSelf(NecrolordFurnitureSet.Blocks.WALL_LIGHT.get());
        dropSelf(NecrolordFurnitureSet.Blocks.FLOOR_LIGHT.get());
        dropSelf(NecrolordFurnitureSet.Blocks.TABLE_LARGE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.TABLE_WIDE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.TABLE_SMALL.get());
        dropSelf(NecrolordFurnitureSet.Blocks.BENCH.get());
        dropSelf(NecrolordFurnitureSet.Blocks.CHAIR.get());
        dropSelf(NecrolordFurnitureSet.Blocks.CEILING_LIGHT.get());
        dropSelf(NecrolordFurnitureSet.Blocks.CUSHION.get());
        dropSelf(NecrolordFurnitureSet.Blocks.STOOL.get());
        dropSelf(NecrolordFurnitureSet.Blocks.CHEST.get());
        dropSelf(NecrolordFurnitureSet.Blocks.BOOKSHELF.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DESK_LEFT.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DESK_RIGHT.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DRAWER.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DRESSER.get());
        dropSelf(NecrolordFurnitureSet.Blocks.LOCKBOX.get());
        dropSelf(NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM.get());
        dropSelf(NecrolordFurnitureSet.Blocks.WARDROBE_TOP.get());
        dropSelf(NecrolordFurnitureSet.Blocks.PAINTING_WIDE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.PAINTING_SMALL.get());
        dropSelf(NecrolordFurnitureSet.Blocks.OVEN.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DOOR_DOUBLE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.DOOR_SINGLE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.BED_SINGLE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.BED_DOUBLE.get());
        dropSelf(NecrolordFurnitureSet.Blocks.SHELF.get());
        dropSelf(NecrolordFurnitureSet.Blocks.SOFA.get());
        dropSelf(NecrolordFurnitureSet.Blocks.COUNTER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(NecrolordFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

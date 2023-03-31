package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

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
        dropSelf(RoyalFurnitureSet.Blocks.WOOL.get());
        dropSelf(RoyalFurnitureSet.Blocks.CARPET.get());
        dropSelf(RoyalFurnitureSet.Blocks.WALL_LIGHT.get());
        dropSelf(RoyalFurnitureSet.Blocks.FLOOR_LIGHT.get());
        dropSelf(RoyalFurnitureSet.Blocks.TABLE_LARGE.get());
        dropSelf(RoyalFurnitureSet.Blocks.TABLE_WIDE.get());
        dropSelf(RoyalFurnitureSet.Blocks.TABLE_SMALL.get());
        dropSelf(RoyalFurnitureSet.Blocks.BENCH.get());
        dropSelf(RoyalFurnitureSet.Blocks.CHAIR.get());
        dropSelf(RoyalFurnitureSet.Blocks.CEILING_LIGHT.get());
        dropSelf(RoyalFurnitureSet.Blocks.CUSHION.get());
        dropSelf(RoyalFurnitureSet.Blocks.STOOL.get());
        dropSelf(RoyalFurnitureSet.Blocks.CHEST.get());
        dropSelf(RoyalFurnitureSet.Blocks.BOOKSHELF.get());
        dropSelf(RoyalFurnitureSet.Blocks.DESK_LEFT.get());
        dropSelf(RoyalFurnitureSet.Blocks.DESK_RIGHT.get());
        dropSelf(RoyalFurnitureSet.Blocks.DRAWER.get());
        dropSelf(RoyalFurnitureSet.Blocks.DRESSER.get());
        dropSelf(RoyalFurnitureSet.Blocks.LOCKBOX.get());
        dropSelf(RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM.get());
        dropSelf(RoyalFurnitureSet.Blocks.WARDROBE_TOP.get());
        dropSelf(RoyalFurnitureSet.Blocks.PAINTING_WIDE.get());
        dropSelf(RoyalFurnitureSet.Blocks.PAINTING_SMALL.get());
        dropSelf(RoyalFurnitureSet.Blocks.OVEN.get());
        dropSelf(RoyalFurnitureSet.Blocks.DOOR_DOUBLE.get());
        dropSelf(RoyalFurnitureSet.Blocks.DOOR_SINGLE.get());
        dropSelf(RoyalFurnitureSet.Blocks.BED_SINGLE.get());
        dropSelf(RoyalFurnitureSet.Blocks.BED_DOUBLE.get());
        dropSelf(RoyalFurnitureSet.Blocks.SHELF.get());
        dropSelf(RoyalFurnitureSet.Blocks.SOFA.get());
        dropSelf(RoyalFurnitureSet.Blocks.COUNTER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(RoyalFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

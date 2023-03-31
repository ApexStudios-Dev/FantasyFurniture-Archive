package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

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
        dropSelf(VenthyrFurnitureSet.Blocks.WOOL.get());
        dropSelf(VenthyrFurnitureSet.Blocks.CARPET.get());
        dropSelf(VenthyrFurnitureSet.Blocks.WALL_LIGHT.get());
        dropSelf(VenthyrFurnitureSet.Blocks.FLOOR_LIGHT.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_LARGE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_WIDE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_SMALL.get());
        dropSelf(VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY.get());
        dropSelf(VenthyrFurnitureSet.Blocks.BENCH.get());
        dropSelf(VenthyrFurnitureSet.Blocks.CHAIR.get());
        dropSelf(VenthyrFurnitureSet.Blocks.CEILING_LIGHT.get());
        dropSelf(VenthyrFurnitureSet.Blocks.CUSHION.get());
        dropSelf(VenthyrFurnitureSet.Blocks.STOOL.get());
        dropSelf(VenthyrFurnitureSet.Blocks.CHEST.get());
        dropSelf(VenthyrFurnitureSet.Blocks.BOOKSHELF.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DESK_LEFT.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DESK_RIGHT.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DRAWER.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DRESSER.get());
        dropSelf(VenthyrFurnitureSet.Blocks.LOCKBOX.get());
        dropSelf(VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM.get());
        dropSelf(VenthyrFurnitureSet.Blocks.WARDROBE_TOP.get());
        dropSelf(VenthyrFurnitureSet.Blocks.PAINTING_WIDE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.PAINTING_SMALL.get());
        dropSelf(VenthyrFurnitureSet.Blocks.OVEN.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DOOR_DOUBLE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.DOOR_SINGLE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.BED_SINGLE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.BED_DOUBLE.get());
        dropSelf(VenthyrFurnitureSet.Blocks.SHELF.get());
        dropSelf(VenthyrFurnitureSet.Blocks.SOFA.get());
        dropSelf(VenthyrFurnitureSet.Blocks.COUNTER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(VenthyrFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }
}

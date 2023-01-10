package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import xyz.apex.minecraft.fantasyfurniture.forge.FantasyFurnitureDataMod;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

import java.util.Set;

public final class BlockLootTableGenerator extends BlockLootSubProvider
{
    public BlockLootTableGenerator()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        dropSelf(FantasyFurnitureDataMod.FURNITURE_STATION_BLOCK.get());

        dropSelf(Nordic.WOOL.get());
        dropSelf(Nordic.CARPET.get());
        dropSelf(Nordic.WALL_LIGHT.get());
        dropSelf(Nordic.FLOOR_LIGHT.get());
        dropSelf(Nordic.TABLE_LARGE.get());
        dropSelf(Nordic.TABLE_WIDE.get());
        dropSelf(Nordic.TABLE_SMALL.get());
        dropSelf(Nordic.BENCH.get());
        dropSelf(Nordic.CHAIR.get());
        dropSelf(Nordic.CHANDELIER.get());
        dropSelf(Nordic.CUSHION.get());
        dropSelf(Nordic.STOOL.get());
        dropSelf(Nordic.CHEST.get());
        dropSelf(Nordic.BOOKSHELF.get());
        dropSelf(Nordic.DESK_LEFT.get());
        dropSelf(Nordic.DESK_RIGHT.get());
        dropSelf(Nordic.DRAWER.get());
        dropSelf(Nordic.DRESSER.get());
        dropSelf(Nordic.LOCKBOX.get());
        dropSelf(Nordic.WARDROBE_BOTTOM.get());
        dropSelf(Nordic.WARDROBE_TOP.get());
        dropSelf(Nordic.PAINTING_WIDE.get());
        dropSelf(Nordic.PAINTING_SMALL.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return FantasyFurnitureDataMod.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}

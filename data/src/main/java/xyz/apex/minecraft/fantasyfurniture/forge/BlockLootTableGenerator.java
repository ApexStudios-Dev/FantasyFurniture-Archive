package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.registry.entry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

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
        dropSelf(FantasyFurniture.FURNITURE_STATION_BLOCK.get());

        nordic();
        venthyr();
        dunmer();
    }

    private void nordic()
    {
        dropSelf(NordicSet.WOOL.get());
        dropSelf(NordicSet.CARPET.get());
        dropSelf(NordicSet.WALL_LIGHT.get());
        dropSelf(NordicSet.FLOOR_LIGHT.get());
        dropSelf(NordicSet.TABLE_LARGE.get());
        dropSelf(NordicSet.TABLE_WIDE.get());
        dropSelf(NordicSet.TABLE_SMALL.get());
        dropSelf(NordicSet.BENCH.get());
        dropSelf(NordicSet.CHAIR.get());
        dropSelf(NordicSet.CHANDELIER.get());
        dropSelf(NordicSet.CUSHION.get());
        dropSelf(NordicSet.STOOL.get());
        dropSelf(NordicSet.CHEST.get());
        dropSelf(NordicSet.BOOKSHELF.get());
        dropSelf(NordicSet.DESK_LEFT.get());
        dropSelf(NordicSet.DESK_RIGHT.get());
        dropSelf(NordicSet.DRAWER.get());
        dropSelf(NordicSet.DRESSER.get());
        dropSelf(NordicSet.LOCKBOX.get());
        dropSelf(NordicSet.WARDROBE_BOTTOM.get());
        dropSelf(NordicSet.WARDROBE_TOP.get());
        dropSelf(NordicSet.PAINTING_WIDE.get());
        dropSelf(NordicSet.PAINTING_SMALL.get());
        dropSelf(NordicSet.OVEN.get());
        dropSelf(NordicSet.DOOR_DOUBLE.get());
        dropSelf(NordicSet.DOOR_SINGLE.get());
        dropSelf(NordicSet.BED_SINGLE.get());
        dropSelf(NordicSet.BED_DOUBLE.get());
        dropSelf(NordicSet.SHELF.get());
        dropSelf(NordicSet.SOFA.get());
        dropSelf(NordicSet.COUNTER.get());
    }

    private void venthyr()
    {
        dropSelf(VenthyrSet.WOOL.get());
        dropSelf(VenthyrSet.CARPET.get());
        dropSelf(VenthyrSet.WALL_LIGHT.get());
        dropSelf(VenthyrSet.FLOOR_LIGHT.get());
        dropSelf(VenthyrSet.TABLE_LARGE.get());
        dropSelf(VenthyrSet.TABLE_LARGE_FANCY.get());
        dropSelf(VenthyrSet.TABLE_WIDE.get());
        dropSelf(VenthyrSet.TABLE_WIDE_FANCY.get());
        dropSelf(VenthyrSet.TABLE_SMALL.get());
        dropSelf(VenthyrSet.TABLE_SMALL_FANCY.get());
        dropSelf(VenthyrSet.BENCH.get());
        dropSelf(VenthyrSet.CHAIR.get());
        dropSelf(VenthyrSet.CHANDELIER.get());
        dropSelf(VenthyrSet.CUSHION.get());
        dropSelf(VenthyrSet.STOOL.get());
        dropSelf(VenthyrSet.CHEST.get());
        dropSelf(VenthyrSet.BOOKSHELF.get());
        dropSelf(VenthyrSet.DESK_LEFT.get());
        dropSelf(VenthyrSet.DESK_RIGHT.get());
        dropSelf(VenthyrSet.DRAWER.get());
        dropSelf(VenthyrSet.DRESSER.get());
        dropSelf(VenthyrSet.LOCKBOX.get());
        dropSelf(VenthyrSet.WARDROBE_BOTTOM.get());
        dropSelf(VenthyrSet.WARDROBE_TOP.get());
        dropSelf(VenthyrSet.PAINTING_WIDE.get());
        dropSelf(VenthyrSet.PAINTING_SMALL.get());
        dropSelf(VenthyrSet.OVEN.get());
        dropSelf(VenthyrSet.DOOR_DOUBLE.get());
        dropSelf(VenthyrSet.DOOR_SINGLE.get());
        dropSelf(VenthyrSet.BED_SINGLE.get());
        dropSelf(VenthyrSet.BED_DOUBLE.get());
        dropSelf(VenthyrSet.SHELF.get());
        dropSelf(VenthyrSet.SOFA.get());
        dropSelf(VenthyrSet.COUNTER.get());
    }

    private void dunmer()
    {
        dropSelf(DunmerSet.WOOL.get());
        dropSelf(DunmerSet.CARPET.get());
        dropSelf(DunmerSet.WALL_LIGHT.get());
        dropSelf(DunmerSet.FLOOR_LIGHT.get());
        dropSelf(DunmerSet.TABLE_LARGE.get());
        dropSelf(DunmerSet.TABLE_WIDE.get());
        dropSelf(DunmerSet.TABLE_SMALL.get());
        dropSelf(DunmerSet.BENCH.get());
        dropSelf(DunmerSet.CHAIR.get());
        dropSelf(DunmerSet.CHANDELIER.get());
        dropSelf(DunmerSet.CUSHION.get());
        dropSelf(DunmerSet.STOOL.get());
        dropSelf(DunmerSet.CHEST.get());
        dropSelf(DunmerSet.BOOKSHELF.get());
        dropSelf(DunmerSet.DESK_LEFT.get());
        dropSelf(DunmerSet.DESK_RIGHT.get());
        dropSelf(DunmerSet.DRAWER.get());
        dropSelf(DunmerSet.DRESSER.get());
        dropSelf(DunmerSet.LOCKBOX.get());
        dropSelf(DunmerSet.WARDROBE_BOTTOM.get());
        dropSelf(DunmerSet.WARDROBE_TOP.get());
        dropSelf(DunmerSet.PAINTING_WIDE.get());
        dropSelf(DunmerSet.PAINTING_SMALL.get());
        dropSelf(DunmerSet.OVEN.get());
        dropSelf(DunmerSet.DOOR_DOUBLE.get());
        dropSelf(DunmerSet.DOOR_SINGLE.get());
        dropSelf(DunmerSet.BED_SINGLE.get());
        dropSelf(DunmerSet.BED_DOUBLE.get());
        dropSelf(DunmerSet.SHELF.get());
        dropSelf(DunmerSet.SOFA.get());
        dropSelf(DunmerSet.COUNTER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return FantasyFurniture.REGISTRAR.stream(ForgeRegistries.Keys.BLOCKS).map(RegistryEntry::get).toList();
    }
}

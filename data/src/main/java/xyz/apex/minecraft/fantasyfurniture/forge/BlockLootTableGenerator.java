package xyz.apex.minecraft.fantasyfurniture.forge;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.minecraft.apexcore.common.registry.entry.RegistryEntry;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

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
        boneWither();
        boneSkeleton();
        necrolord();
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

    private void boneWither()
    {
        dropSelf(BoneSet.Wither.WOOL.get());
        dropSelf(BoneSet.Wither.CARPET.get());
        dropSelf(BoneSet.Wither.WALL_LIGHT.get());
        dropSelf(BoneSet.Wither.FLOOR_LIGHT.get());
        dropSelf(BoneSet.Wither.TABLE_LARGE.get());
        dropSelf(BoneSet.Wither.TABLE_WIDE.get());
        dropSelf(BoneSet.Wither.TABLE_SMALL.get());
        dropSelf(BoneSet.Wither.BENCH.get());
        dropSelf(BoneSet.Wither.CHAIR.get());
        dropSelf(BoneSet.Wither.CHANDELIER.get());
        dropSelf(BoneSet.Wither.CUSHION.get());
        dropSelf(BoneSet.Wither.STOOL.get());
        dropSelf(BoneSet.Wither.CHEST.get());
        dropSelf(BoneSet.Wither.BOOKSHELF.get());
        dropSelf(BoneSet.Wither.DESK_LEFT.get());
        dropSelf(BoneSet.Wither.DESK_RIGHT.get());
        dropSelf(BoneSet.Wither.DRAWER.get());
        dropSelf(BoneSet.Wither.DRESSER.get());
        dropSelf(BoneSet.Wither.LOCKBOX.get());
        dropSelf(BoneSet.Wither.WARDROBE_BOTTOM.get());
        dropSelf(BoneSet.Wither.WARDROBE_TOP.get());
        dropSelf(BoneSet.Wither.PAINTING_WIDE.get());
        dropSelf(BoneSet.Wither.PAINTING_SMALL.get());
        dropSelf(BoneSet.Wither.OVEN.get());
        dropSelf(BoneSet.Wither.DOOR_DOUBLE.get());
        dropSelf(BoneSet.Wither.DOOR_SINGLE.get());
        dropSelf(BoneSet.Wither.BED_SINGLE.get());
        dropSelf(BoneSet.Wither.BED_DOUBLE.get());
        dropSelf(BoneSet.Wither.SHELF.get());
        dropSelf(BoneSet.Wither.SOFA.get());
        dropSelf(BoneSet.Wither.COUNTER.get());
    }

    private void boneSkeleton()
    {
        dropSelf(BoneSet.Skeleton.WOOL.get());
        dropSelf(BoneSet.Skeleton.CARPET.get());
        dropSelf(BoneSet.Skeleton.WALL_LIGHT.get());
        dropSelf(BoneSet.Skeleton.FLOOR_LIGHT.get());
        dropSelf(BoneSet.Skeleton.TABLE_LARGE.get());
        dropSelf(BoneSet.Skeleton.TABLE_WIDE.get());
        dropSelf(BoneSet.Skeleton.TABLE_SMALL.get());
        dropSelf(BoneSet.Skeleton.BENCH.get());
        dropSelf(BoneSet.Skeleton.CHAIR.get());
        dropSelf(BoneSet.Skeleton.CHANDELIER.get());
        dropSelf(BoneSet.Skeleton.CUSHION.get());
        dropSelf(BoneSet.Skeleton.STOOL.get());
        dropSelf(BoneSet.Skeleton.CHEST.get());
        dropSelf(BoneSet.Skeleton.BOOKSHELF.get());
        dropSelf(BoneSet.Skeleton.DESK_LEFT.get());
        dropSelf(BoneSet.Skeleton.DESK_RIGHT.get());
        dropSelf(BoneSet.Skeleton.DRAWER.get());
        dropSelf(BoneSet.Skeleton.DRESSER.get());
        dropSelf(BoneSet.Skeleton.LOCKBOX.get());
        dropSelf(BoneSet.Skeleton.WARDROBE_BOTTOM.get());
        dropSelf(BoneSet.Skeleton.WARDROBE_TOP.get());
        dropSelf(BoneSet.Skeleton.PAINTING_WIDE.get());
        dropSelf(BoneSet.Skeleton.PAINTING_SMALL.get());
        dropSelf(BoneSet.Skeleton.OVEN.get());
        dropSelf(BoneSet.Skeleton.DOOR_DOUBLE.get());
        dropSelf(BoneSet.Skeleton.DOOR_SINGLE.get());
        dropSelf(BoneSet.Skeleton.BED_SINGLE.get());
        dropSelf(BoneSet.Skeleton.BED_DOUBLE.get());
        dropSelf(BoneSet.Skeleton.SHELF.get());
        dropSelf(BoneSet.Skeleton.SOFA.get());
        dropSelf(BoneSet.Skeleton.COUNTER.get());
    }

    private void necrolord()
    {
        dropSelf(NecrolordSet.WOOL.get());
        dropSelf(NecrolordSet.CARPET.get());
        dropSelf(NecrolordSet.WALL_LIGHT.get());
        dropSelf(NecrolordSet.FLOOR_LIGHT.get());
        dropSelf(NecrolordSet.TABLE_LARGE.get());
        dropSelf(NecrolordSet.TABLE_WIDE.get());
        dropSelf(NecrolordSet.TABLE_SMALL.get());
        dropSelf(NecrolordSet.BENCH.get());
        dropSelf(NecrolordSet.CHAIR.get());
        dropSelf(NecrolordSet.CHANDELIER.get());
        dropSelf(NecrolordSet.CUSHION.get());
        dropSelf(NecrolordSet.STOOL.get());
        dropSelf(NecrolordSet.CHEST.get());
        dropSelf(NecrolordSet.BOOKSHELF.get());
        dropSelf(NecrolordSet.DESK_LEFT.get());
        dropSelf(NecrolordSet.DESK_RIGHT.get());
        dropSelf(NecrolordSet.DRAWER.get());
        dropSelf(NecrolordSet.DRESSER.get());
        dropSelf(NecrolordSet.LOCKBOX.get());
        dropSelf(NecrolordSet.WARDROBE_BOTTOM.get());
        dropSelf(NecrolordSet.WARDROBE_TOP.get());
        dropSelf(NecrolordSet.PAINTING_WIDE.get());
        dropSelf(NecrolordSet.PAINTING_SMALL.get());
        dropSelf(NecrolordSet.OVEN.get());
        dropSelf(NecrolordSet.DOOR_DOUBLE.get());
        dropSelf(NecrolordSet.DOOR_SINGLE.get());
        dropSelf(NecrolordSet.BED_SINGLE.get());
        dropSelf(NecrolordSet.BED_DOUBLE.get());
        dropSelf(NecrolordSet.SHELF.get());
        dropSelf(NecrolordSet.SOFA.get());
        dropSelf(NecrolordSet.COUNTER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return FantasyFurniture.REGISTRAR.stream(ForgeRegistries.Keys.BLOCKS).map(RegistryEntry::get).toList();
    }
}

package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.apex.minecraft.apexcore.common.registry.RegistryEntry;
import xyz.apex.minecraft.apexcore.common.registry.RegistryManager;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;

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
        boneWither();
        boneSkeleton();
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return RegistryManager
                .get(BoneFurnitureSet.ID)
                .getRegistry(ForgeRegistries.Keys.BLOCKS)
                .entries()
                .stream()
                .filter(RegistryEntry::isPresent)
                .map(RegistryEntry::get)
                .collect(Collectors.toList())
        ;
    }

    private void boneWither()
    {
        dropSelf(BoneFurnitureSet.Wither.WOOL.get());
        dropSelf(BoneFurnitureSet.Wither.CARPET.get());
        dropSelf(BoneFurnitureSet.Wither.WALL_LIGHT.get());
        dropSelf(BoneFurnitureSet.Wither.FLOOR_LIGHT.get());
        dropSelf(BoneFurnitureSet.Wither.TABLE_LARGE.get());
        dropSelf(BoneFurnitureSet.Wither.TABLE_WIDE.get());
        dropSelf(BoneFurnitureSet.Wither.TABLE_SMALL.get());
        dropSelf(BoneFurnitureSet.Wither.BENCH.get());
        dropSelf(BoneFurnitureSet.Wither.CHAIR.get());
        dropSelf(BoneFurnitureSet.Wither.CEILING_LIGHT.get());
        dropSelf(BoneFurnitureSet.Wither.CUSHION.get());
        dropSelf(BoneFurnitureSet.Wither.STOOL.get());
        dropSelf(BoneFurnitureSet.Wither.CHEST.get());
        dropSelf(BoneFurnitureSet.Wither.BOOKSHELF.get());
        dropSelf(BoneFurnitureSet.Wither.DESK_LEFT.get());
        dropSelf(BoneFurnitureSet.Wither.DESK_RIGHT.get());
        dropSelf(BoneFurnitureSet.Wither.DRAWER.get());
        dropSelf(BoneFurnitureSet.Wither.DRESSER.get());
        dropSelf(BoneFurnitureSet.Wither.LOCKBOX.get());
        dropSelf(BoneFurnitureSet.Wither.WARDROBE_BOTTOM.get());
        dropSelf(BoneFurnitureSet.Wither.WARDROBE_TOP.get());
        dropSelf(BoneFurnitureSet.Wither.PAINTING_WIDE.get());
        dropSelf(BoneFurnitureSet.Wither.PAINTING_SMALL.get());
        dropSelf(BoneFurnitureSet.Wither.OVEN.get());
        dropSelf(BoneFurnitureSet.Wither.DOOR_DOUBLE.get());
        dropSelf(BoneFurnitureSet.Wither.DOOR_SINGLE.get());
        dropSelf(BoneFurnitureSet.Wither.BED_SINGLE.get());
        dropSelf(BoneFurnitureSet.Wither.BED_DOUBLE.get());
        dropSelf(BoneFurnitureSet.Wither.SHELF.get());
        dropSelf(BoneFurnitureSet.Wither.SOFA.get());
        dropSelf(BoneFurnitureSet.Wither.COUNTER.get());
    }

    private void boneSkeleton()
    {
        dropSelf(BoneFurnitureSet.Skeleton.WOOL.get());
        dropSelf(BoneFurnitureSet.Skeleton.CARPET.get());
        dropSelf(BoneFurnitureSet.Skeleton.WALL_LIGHT.get());
        dropSelf(BoneFurnitureSet.Skeleton.FLOOR_LIGHT.get());
        dropSelf(BoneFurnitureSet.Skeleton.TABLE_LARGE.get());
        dropSelf(BoneFurnitureSet.Skeleton.TABLE_WIDE.get());
        dropSelf(BoneFurnitureSet.Skeleton.TABLE_SMALL.get());
        dropSelf(BoneFurnitureSet.Skeleton.BENCH.get());
        dropSelf(BoneFurnitureSet.Skeleton.CHAIR.get());
        dropSelf(BoneFurnitureSet.Skeleton.CEILING_LIGHT.get());
        dropSelf(BoneFurnitureSet.Skeleton.CUSHION.get());
        dropSelf(BoneFurnitureSet.Skeleton.STOOL.get());
        dropSelf(BoneFurnitureSet.Skeleton.CHEST.get());
        dropSelf(BoneFurnitureSet.Skeleton.BOOKSHELF.get());
        dropSelf(BoneFurnitureSet.Skeleton.DESK_LEFT.get());
        dropSelf(BoneFurnitureSet.Skeleton.DESK_RIGHT.get());
        dropSelf(BoneFurnitureSet.Skeleton.DRAWER.get());
        dropSelf(BoneFurnitureSet.Skeleton.DRESSER.get());
        dropSelf(BoneFurnitureSet.Skeleton.LOCKBOX.get());
        dropSelf(BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM.get());
        dropSelf(BoneFurnitureSet.Skeleton.WARDROBE_TOP.get());
        dropSelf(BoneFurnitureSet.Skeleton.PAINTING_WIDE.get());
        dropSelf(BoneFurnitureSet.Skeleton.PAINTING_SMALL.get());
        dropSelf(BoneFurnitureSet.Skeleton.OVEN.get());
        dropSelf(BoneFurnitureSet.Skeleton.DOOR_DOUBLE.get());
        dropSelf(BoneFurnitureSet.Skeleton.DOOR_SINGLE.get());
        dropSelf(BoneFurnitureSet.Skeleton.BED_SINGLE.get());
        dropSelf(BoneFurnitureSet.Skeleton.BED_DOUBLE.get());
        dropSelf(BoneFurnitureSet.Skeleton.SHELF.get());
        dropSelf(BoneFurnitureSet.Skeleton.SOFA.get());
        dropSelf(BoneFurnitureSet.Skeleton.COUNTER.get());
    }
}

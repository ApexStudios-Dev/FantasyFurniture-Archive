package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockBenchProvider;

import java.util.Arrays;

final class BlockBenchConverter extends BlockBenchProvider
{
    BlockBenchConverter(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void convertModels()
    {
        boneWither();
        boneSkeleton();
    }

    private void boneWither()
    {
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "floor_light", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "table_small", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "table_large", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "table_wide", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "bench", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "chair", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "ceiling_light", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "cushion", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "stool", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "chest", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "bookshelf", false);
        convertDesk(BoneFurnitureSet.Wither.SUBSET_ID, "left", false);
        convertDesk(BoneFurnitureSet.Wither.SUBSET_ID, "right", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "drawer", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "dresser", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "lockbox", false);
        convertWardrobe(BoneFurnitureSet.Wither.SUBSET_ID, "bottom", false);
        convertWardrobe(BoneFurnitureSet.Wither.SUBSET_ID, "top", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "painting_wide", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "painting_small", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "oven", false);
        convertFurnitureEx(BoneFurnitureSet.Wither.SUBSET_ID, "oven_lit", "oven", false);
        convertDoor(BoneFurnitureSet.Wither.SUBSET_ID, "double", true, false);
        convertDoor(BoneFurnitureSet.Wither.SUBSET_ID, "double", false, false);
        convertDoor(BoneFurnitureSet.Wither.SUBSET_ID, "single", true, false);
        convertDoor(BoneFurnitureSet.Wither.SUBSET_ID, "single", false, false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "bed_single", false);
        convertFurniture(BoneFurnitureSet.Wither.SUBSET_ID, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneFurnitureSet.Wither.SUBSET_ID, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneFurnitureSet.Wither.SUBSET_ID, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneFurnitureSet.Wither.SUBSET_ID, counterType, false));
    }

    private void boneSkeleton()
    {
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "floor_light", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "table_small", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "table_large", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "table_wide", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "bench", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "chair", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "ceiling_light", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "cushion", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "stool", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "chest", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "bookshelf", false);
        convertDesk(BoneFurnitureSet.Skeleton.SUBSET_ID, "left", false);
        convertDesk(BoneFurnitureSet.Skeleton.SUBSET_ID, "right", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "drawer", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "dresser", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "lockbox", false);
        convertWardrobe(BoneFurnitureSet.Skeleton.SUBSET_ID, "bottom", false);
        convertWardrobe(BoneFurnitureSet.Skeleton.SUBSET_ID, "top", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "painting_wide", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "painting_small", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "oven", false);
        convertFurnitureEx(BoneFurnitureSet.Skeleton.SUBSET_ID, "oven_lit", "oven", false);
        convertDoor(BoneFurnitureSet.Skeleton.SUBSET_ID, "double", true, false);
        convertDoor(BoneFurnitureSet.Skeleton.SUBSET_ID, "double", false, false);
        convertDoor(BoneFurnitureSet.Skeleton.SUBSET_ID, "single", true, false);
        convertDoor(BoneFurnitureSet.Skeleton.SUBSET_ID, "single", false, false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "bed_single", false);
        convertFurniture(BoneFurnitureSet.Skeleton.SUBSET_ID, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(BoneFurnitureSet.Skeleton.SUBSET_ID, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(BoneFurnitureSet.Skeleton.SUBSET_ID, sofaType, false));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(BoneFurnitureSet.Skeleton.SUBSET_ID, counterType, false));
    }
}

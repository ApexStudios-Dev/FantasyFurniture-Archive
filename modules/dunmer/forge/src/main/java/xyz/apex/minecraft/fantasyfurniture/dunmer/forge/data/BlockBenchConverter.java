package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
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
        convertFurniture(null, "wall_light", false).renderType(renderTypeCutout);
        convertFurniture(null, "floor_light", false).renderType(renderTypeCutout);
        convertFurniture(null, "table_small", false);
        convertFurniture(null, "table_large", false);
        convertFurniture(null, "table_wide", false);
        convertFurniture(null, "bench", false);
        convertFurniture(null, "chair", false);
        convertFurniture(null, "ceiling_light", false);
        convertFurniture(null, "cushion", false);
        convertFurniture(null, "stool", false);
        convertFurniture(null, "chest", false);
        convertFurniture(null, "bookshelf", false);
        convertDesk(null, "left", false);
        convertDesk(null, "right", false);
        convertFurniture(null, "drawer", false);
        convertFurniture(null, "dresser", false);
        convertFurniture(null, "lockbox", false).renderType(renderTypeCutout);
        convertWardrobe(null, "bottom", false).renderType(renderTypeCutout);
        convertWardrobe(null, "top", false);
        convertFurniture(null, "painting_wide", false);
        convertFurniture(null, "painting_small", false);
        convertFurniture(null, "oven", false);
        convertFurnitureEx(null, "oven_lit", "oven", false).renderType(renderTypeCutout).texture("oven_fire", "block/oven_fire");
        convertDoor(null, "double", true, false);
        convertDoor(null, "double", false, false);
        convertDoor(null, "single", true, false);
        convertDoor(null, "single", false, false);
        convertFurniture(null, "bed_single", false);
        convertFurniture(null, "bed_double", false);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(null, shelfType, false));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(null, sofaType, false).renderType(renderTypeCutout));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(null, counterType, false).renderType(renderTypeCutout));
    }
}

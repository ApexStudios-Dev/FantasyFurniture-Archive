package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

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
        convertFurniture(null, "wall_light", false);
        convertFurniture(null, "floor_light", false);
        convertFurniture(null, "table_small", true);
        convertFurniture(null, "table_large", true);
        convertFurniture(null, "table_wide", true);
        convertFurniture(null, "bench", true);
        convertFurniture(null, "chair", true);
        convertFurniture(null, "ceiling_light", false);
        convertFurniture(null, "cushion", true);
        convertFurniture(null, "stool", true);
        convertFurniture(null, "chest", true);
        convertFurniture(null, "bookshelf", true);
        convertDesk(null, "left", true);
        convertDesk(null, "right", true);
        convertFurniture(null, "drawer", true).renderType(renderTypeCutout);
        convertFurniture(null, "dresser", true).renderType(renderTypeCutout);
        convertFurniture(null, "lockbox", true);
        convertWardrobe(null, "bottom", true);
        convertWardrobe(null, "top", true);
        convertFurniture(null, "painting_wide", false);
        convertFurniture(null, "painting_small", false);
        convertFurniture(null, "oven", true).renderType(renderTypeCutout);
        convertFurnitureEx(null, "oven_lit", "oven", true).renderType(renderTypeCutout);
        convertDoor(null, "double", true, true);
        convertDoor(null, "double", false, true);
        convertDoor(null, "single", true, true);
        convertDoor(null, "single", false, true);
        convertFurniture(null, "bed_single", true);
        convertFurniture(null, "bed_double", true);
        Arrays.stream(ShelfType.values()).forEach(shelfType -> convertShelf(null, shelfType, true));
        Arrays.stream(SofaType.values()).forEach(sofaType -> convertSofa(null, sofaType, true));
        Arrays.stream(CounterType.values()).forEach(counterType -> convertCounter(null, counterType, true));
    }
}

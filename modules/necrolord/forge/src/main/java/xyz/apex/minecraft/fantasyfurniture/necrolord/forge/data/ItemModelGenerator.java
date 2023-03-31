package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.ItemModelProvider;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerModels()
    {
        blockItem(NecrolordFurnitureSet.Blocks.WOOL);
        blockItem(NecrolordFurnitureSet.Blocks.CARPET);
        blockItem(NecrolordFurnitureSet.Blocks.WALL_LIGHT);
        blockItem(NecrolordFurnitureSet.Blocks.FLOOR_LIGHT);
        blockItem(NecrolordFurnitureSet.Blocks.TABLE_LARGE);
        blockItem(NecrolordFurnitureSet.Blocks.TABLE_SMALL);
        blockItem(NecrolordFurnitureSet.Blocks.TABLE_WIDE);
        blockItem(NecrolordFurnitureSet.Blocks.BENCH);
        blockItem(NecrolordFurnitureSet.Blocks.CHAIR);
        blockItem(NecrolordFurnitureSet.Blocks.CEILING_LIGHT);
        blockItem(NecrolordFurnitureSet.Blocks.CUSHION);
        blockItem(NecrolordFurnitureSet.Blocks.STOOL);
        blockItem(NecrolordFurnitureSet.Blocks.CHEST);
        blockItem(NecrolordFurnitureSet.Blocks.BOOKSHELF);
        blockItem(NecrolordFurnitureSet.Blocks.DESK_LEFT);
        blockItem(NecrolordFurnitureSet.Blocks.DESK_RIGHT);
        blockItem(NecrolordFurnitureSet.Blocks.DRAWER);
        blockItem(NecrolordFurnitureSet.Blocks.DRESSER);
        blockItem(NecrolordFurnitureSet.Blocks.LOCKBOX);
        blockItem(NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM);
        blockItem(NecrolordFurnitureSet.Blocks.WARDROBE_TOP);
        blockItem(NecrolordFurnitureSet.Blocks.PAINTING_SMALL);
        blockItem(NecrolordFurnitureSet.Blocks.PAINTING_WIDE);
        blockItem(NecrolordFurnitureSet.Blocks.OVEN);
        doorBlockItem(NecrolordFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlockItem(NecrolordFurnitureSet.Blocks.DOOR_SINGLE);
        blockItem(NecrolordFurnitureSet.Blocks.BED_SINGLE);
        blockItem(NecrolordFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlockItem(NecrolordFurnitureSet.Blocks.SHELF);
        sofaBlockItem(NecrolordFurnitureSet.Blocks.SOFA);
        counterBlockItem(NecrolordFurnitureSet.Blocks.COUNTER);
    }
}

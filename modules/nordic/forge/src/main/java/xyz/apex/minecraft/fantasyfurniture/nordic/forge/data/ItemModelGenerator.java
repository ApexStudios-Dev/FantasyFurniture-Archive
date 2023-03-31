package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.ItemModelProvider;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerModels()
    {
        blockItem(NordicFurnitureSet.Blocks.WOOL);
        blockItem(NordicFurnitureSet.Blocks.CARPET);
        blockItem(NordicFurnitureSet.Blocks.WALL_LIGHT);
        blockItem(NordicFurnitureSet.Blocks.FLOOR_LIGHT);
        blockItem(NordicFurnitureSet.Blocks.TABLE_LARGE);
        blockItem(NordicFurnitureSet.Blocks.TABLE_SMALL);
        blockItem(NordicFurnitureSet.Blocks.TABLE_WIDE);
        blockItem(NordicFurnitureSet.Blocks.BENCH);
        blockItem(NordicFurnitureSet.Blocks.CHAIR);
        blockItem(NordicFurnitureSet.Blocks.CEILING_LIGHT);
        blockItem(NordicFurnitureSet.Blocks.CUSHION);
        blockItem(NordicFurnitureSet.Blocks.STOOL);
        blockItem(NordicFurnitureSet.Blocks.CHEST);
        blockItem(NordicFurnitureSet.Blocks.BOOKSHELF);
        blockItem(NordicFurnitureSet.Blocks.DESK_LEFT);
        blockItem(NordicFurnitureSet.Blocks.DESK_RIGHT);
        blockItem(NordicFurnitureSet.Blocks.DRAWER);
        blockItem(NordicFurnitureSet.Blocks.DRESSER);
        blockItem(NordicFurnitureSet.Blocks.LOCKBOX);
        blockItem(NordicFurnitureSet.Blocks.WARDROBE_BOTTOM);
        blockItem(NordicFurnitureSet.Blocks.WARDROBE_TOP);
        blockItem(NordicFurnitureSet.Blocks.PAINTING_SMALL);
        blockItem(NordicFurnitureSet.Blocks.PAINTING_WIDE);
        blockItem(NordicFurnitureSet.Blocks.OVEN);
        doorBlockItem(NordicFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlockItem(NordicFurnitureSet.Blocks.DOOR_SINGLE);
        blockItem(NordicFurnitureSet.Blocks.BED_SINGLE);
        blockItem(NordicFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlockItem(NordicFurnitureSet.Blocks.SHELF);
        sofaBlockItem(NordicFurnitureSet.Blocks.SOFA);
        counterBlockItem(NordicFurnitureSet.Blocks.COUNTER);
    }
}

package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.ItemModelProvider;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerModels()
    {
        blockItem(VenthyrFurnitureSet.Blocks.WOOL);
        blockItem(VenthyrFurnitureSet.Blocks.CARPET);
        blockItem(VenthyrFurnitureSet.Blocks.WALL_LIGHT);
        blockItem(VenthyrFurnitureSet.Blocks.FLOOR_LIGHT);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_LARGE);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_SMALL);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_WIDE);
        blockItem(VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY);
        blockItem(VenthyrFurnitureSet.Blocks.BENCH);
        blockItem(VenthyrFurnitureSet.Blocks.CHAIR);
        blockItem(VenthyrFurnitureSet.Blocks.CEILING_LIGHT);
        blockItem(VenthyrFurnitureSet.Blocks.CUSHION);
        blockItem(VenthyrFurnitureSet.Blocks.STOOL);
        blockItem(VenthyrFurnitureSet.Blocks.CHEST);
        blockItem(VenthyrFurnitureSet.Blocks.BOOKSHELF);
        blockItem(VenthyrFurnitureSet.Blocks.DESK_LEFT);
        blockItem(VenthyrFurnitureSet.Blocks.DESK_RIGHT);
        blockItem(VenthyrFurnitureSet.Blocks.DRAWER);
        blockItem(VenthyrFurnitureSet.Blocks.DRESSER);
        blockItem(VenthyrFurnitureSet.Blocks.LOCKBOX);
        blockItem(VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM);
        blockItem(VenthyrFurnitureSet.Blocks.WARDROBE_TOP);
        blockItem(VenthyrFurnitureSet.Blocks.PAINTING_SMALL);
        blockItem(VenthyrFurnitureSet.Blocks.PAINTING_WIDE);
        blockItem(VenthyrFurnitureSet.Blocks.OVEN);
        doorBlockItem(VenthyrFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlockItem(VenthyrFurnitureSet.Blocks.DOOR_SINGLE);
        blockItem(VenthyrFurnitureSet.Blocks.BED_SINGLE);
        blockItem(VenthyrFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlockItem(VenthyrFurnitureSet.Blocks.SHELF);
        sofaBlockItem(VenthyrFurnitureSet.Blocks.SOFA);
        counterBlockItem(VenthyrFurnitureSet.Blocks.COUNTER);
    }
}

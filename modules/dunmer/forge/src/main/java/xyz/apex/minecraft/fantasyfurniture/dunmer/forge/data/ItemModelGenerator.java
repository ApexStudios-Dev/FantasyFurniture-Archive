package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.ItemModelProvider;

final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerModels()
    {
        blockItem(DunmerFurnitureSet.Blocks.WOOL);
        blockItem(DunmerFurnitureSet.Blocks.CARPET);
        blockItem(DunmerFurnitureSet.Blocks.WALL_LIGHT);
        blockItem(DunmerFurnitureSet.Blocks.FLOOR_LIGHT);
        blockItem(DunmerFurnitureSet.Blocks.TABLE_LARGE);
        blockItem(DunmerFurnitureSet.Blocks.TABLE_SMALL);
        blockItem(DunmerFurnitureSet.Blocks.TABLE_WIDE);
        blockItem(DunmerFurnitureSet.Blocks.BENCH);
        blockItem(DunmerFurnitureSet.Blocks.CHAIR);
        blockItem(DunmerFurnitureSet.Blocks.CEILING_LIGHT);
        blockItem(DunmerFurnitureSet.Blocks.CUSHION);
        blockItem(DunmerFurnitureSet.Blocks.STOOL);
        blockItem(DunmerFurnitureSet.Blocks.CHEST);
        blockItem(DunmerFurnitureSet.Blocks.BOOKSHELF);
        blockItem(DunmerFurnitureSet.Blocks.DESK_LEFT);
        blockItem(DunmerFurnitureSet.Blocks.DESK_RIGHT);
        blockItem(DunmerFurnitureSet.Blocks.DRAWER);
        blockItem(DunmerFurnitureSet.Blocks.DRESSER);
        blockItem(DunmerFurnitureSet.Blocks.LOCKBOX);
        blockItem(DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM);
        blockItem(DunmerFurnitureSet.Blocks.WARDROBE_TOP);
        blockItem(DunmerFurnitureSet.Blocks.PAINTING_SMALL);
        blockItem(DunmerFurnitureSet.Blocks.PAINTING_WIDE);
        blockItem(DunmerFurnitureSet.Blocks.OVEN);
        doorBlockItem(DunmerFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlockItem(DunmerFurnitureSet.Blocks.DOOR_SINGLE);
        blockItem(DunmerFurnitureSet.Blocks.BED_SINGLE);
        blockItem(DunmerFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlockItem(DunmerFurnitureSet.Blocks.SHELF);
        sofaBlockItem(DunmerFurnitureSet.Blocks.SOFA);
        counterBlockItem(DunmerFurnitureSet.Blocks.COUNTER);
    }
}

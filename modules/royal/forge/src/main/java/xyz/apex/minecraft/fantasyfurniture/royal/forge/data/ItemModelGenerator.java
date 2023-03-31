package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.ItemModelProvider;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

final class ItemModelGenerator extends ItemModelProvider
{
    ItemModelGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerModels()
    {
        blockItem(RoyalFurnitureSet.Blocks.WOOL);
        blockItem(RoyalFurnitureSet.Blocks.CARPET);
        blockItem(RoyalFurnitureSet.Blocks.WALL_LIGHT);
        blockItem(RoyalFurnitureSet.Blocks.FLOOR_LIGHT);
        blockItem(RoyalFurnitureSet.Blocks.TABLE_LARGE);
        blockItem(RoyalFurnitureSet.Blocks.TABLE_SMALL);
        blockItem(RoyalFurnitureSet.Blocks.TABLE_WIDE);
        blockItem(RoyalFurnitureSet.Blocks.BENCH);
        blockItem(RoyalFurnitureSet.Blocks.CHAIR);
        blockItem(RoyalFurnitureSet.Blocks.CEILING_LIGHT);
        blockItem(RoyalFurnitureSet.Blocks.CUSHION);
        blockItem(RoyalFurnitureSet.Blocks.STOOL);
        blockItem(RoyalFurnitureSet.Blocks.CHEST);
        blockItem(RoyalFurnitureSet.Blocks.BOOKSHELF);
        blockItem(RoyalFurnitureSet.Blocks.DESK_LEFT);
        blockItem(RoyalFurnitureSet.Blocks.DESK_RIGHT);
        blockItem(RoyalFurnitureSet.Blocks.DRAWER);
        blockItem(RoyalFurnitureSet.Blocks.DRESSER);
        blockItem(RoyalFurnitureSet.Blocks.LOCKBOX);
        blockItem(RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM);
        blockItem(RoyalFurnitureSet.Blocks.WARDROBE_TOP);
        blockItem(RoyalFurnitureSet.Blocks.PAINTING_SMALL);
        blockItem(RoyalFurnitureSet.Blocks.PAINTING_WIDE);
        blockItem(RoyalFurnitureSet.Blocks.OVEN);
        doorBlockItem(RoyalFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlockItem(RoyalFurnitureSet.Blocks.DOOR_SINGLE);
        blockItem(RoyalFurnitureSet.Blocks.BED_SINGLE);
        blockItem(RoyalFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlockItem(RoyalFurnitureSet.Blocks.SHELF);
        sofaBlockItem(RoyalFurnitureSet.Blocks.SOFA);
        counterBlockItem(RoyalFurnitureSet.Blocks.COUNTER);
    }
}

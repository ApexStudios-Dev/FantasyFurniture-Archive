package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
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
        wither();
        skeleton();
    }

    private void wither()
    {
        blockItem(BoneFurnitureSet.Wither.WOOL);
        blockItem(BoneFurnitureSet.Wither.CARPET);
        blockItem(BoneFurnitureSet.Wither.WALL_LIGHT);
        blockItem(BoneFurnitureSet.Wither.FLOOR_LIGHT);
        blockItem(BoneFurnitureSet.Wither.TABLE_LARGE);
        blockItem(BoneFurnitureSet.Wither.TABLE_SMALL);
        blockItem(BoneFurnitureSet.Wither.TABLE_WIDE);
        blockItem(BoneFurnitureSet.Wither.BENCH);
        blockItem(BoneFurnitureSet.Wither.CHAIR);
        blockItem(BoneFurnitureSet.Wither.CEILING_LIGHT);
        blockItem(BoneFurnitureSet.Wither.CUSHION);
        blockItem(BoneFurnitureSet.Wither.STOOL);
        blockItem(BoneFurnitureSet.Wither.CHEST);
        blockItem(BoneFurnitureSet.Wither.BOOKSHELF);
        blockItem(BoneFurnitureSet.Wither.DESK_LEFT);
        blockItem(BoneFurnitureSet.Wither.DESK_RIGHT);
        blockItem(BoneFurnitureSet.Wither.DRAWER);
        blockItem(BoneFurnitureSet.Wither.DRESSER);
        blockItem(BoneFurnitureSet.Wither.LOCKBOX);
        blockItem(BoneFurnitureSet.Wither.WARDROBE_BOTTOM);
        blockItem(BoneFurnitureSet.Wither.WARDROBE_TOP);
        blockItem(BoneFurnitureSet.Wither.PAINTING_SMALL);
        blockItem(BoneFurnitureSet.Wither.PAINTING_WIDE);
        blockItem(BoneFurnitureSet.Wither.OVEN);
        doorBlockItem(BoneFurnitureSet.Wither.DOOR_DOUBLE);
        doorBlockItem(BoneFurnitureSet.Wither.DOOR_SINGLE);
        blockItem(BoneFurnitureSet.Wither.BED_SINGLE);
        blockItem(BoneFurnitureSet.Wither.BED_DOUBLE);
        shelfBlockItem(BoneFurnitureSet.Wither.SHELF);
        sofaBlockItem(BoneFurnitureSet.Wither.SOFA);
        counterBlockItem(BoneFurnitureSet.Wither.COUNTER);
    }

    private void skeleton()
    {
        blockItem(BoneFurnitureSet.Skeleton.WOOL);
        blockItem(BoneFurnitureSet.Skeleton.CARPET);
        blockItem(BoneFurnitureSet.Skeleton.WALL_LIGHT);
        blockItem(BoneFurnitureSet.Skeleton.FLOOR_LIGHT);
        blockItem(BoneFurnitureSet.Skeleton.TABLE_LARGE);
        blockItem(BoneFurnitureSet.Skeleton.TABLE_SMALL);
        blockItem(BoneFurnitureSet.Skeleton.TABLE_WIDE);
        blockItem(BoneFurnitureSet.Skeleton.BENCH);
        blockItem(BoneFurnitureSet.Skeleton.CHAIR);
        blockItem(BoneFurnitureSet.Skeleton.CEILING_LIGHT);
        blockItem(BoneFurnitureSet.Skeleton.CUSHION);
        blockItem(BoneFurnitureSet.Skeleton.STOOL);
        blockItem(BoneFurnitureSet.Skeleton.CHEST);
        blockItem(BoneFurnitureSet.Skeleton.BOOKSHELF);
        blockItem(BoneFurnitureSet.Skeleton.DESK_LEFT);
        blockItem(BoneFurnitureSet.Skeleton.DESK_RIGHT);
        blockItem(BoneFurnitureSet.Skeleton.DRAWER);
        blockItem(BoneFurnitureSet.Skeleton.DRESSER);
        blockItem(BoneFurnitureSet.Skeleton.LOCKBOX);
        blockItem(BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM);
        blockItem(BoneFurnitureSet.Skeleton.WARDROBE_TOP);
        blockItem(BoneFurnitureSet.Skeleton.PAINTING_SMALL);
        blockItem(BoneFurnitureSet.Skeleton.PAINTING_WIDE);
        blockItem(BoneFurnitureSet.Skeleton.OVEN);
        doorBlockItem(BoneFurnitureSet.Skeleton.DOOR_DOUBLE);
        doorBlockItem(BoneFurnitureSet.Skeleton.DOOR_SINGLE);
        blockItem(BoneFurnitureSet.Skeleton.BED_SINGLE);
        blockItem(BoneFurnitureSet.Skeleton.BED_DOUBLE);
        shelfBlockItem(BoneFurnitureSet.Skeleton.SHELF);
        sofaBlockItem(BoneFurnitureSet.Skeleton.SOFA);
        counterBlockItem(BoneFurnitureSet.Skeleton.COUNTER);
    }
}

package xyz.apex.minecraft.fantasyfurniture.bone.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        wither();
        skeleton();
    }

    private void wither()
    {
        simpleBlock(BoneFurnitureSet.Wither.WOOL);
        carpet(BoneFurnitureSet.Wither.CARPET, BoneFurnitureSet.Wither.WOOL);
        wallLight(BoneFurnitureSet.Wither.WALL_LIGHT);
        simpleExistingBlock(BoneFurnitureSet.Wither.FLOOR_LIGHT);
        facingBlock(BoneFurnitureSet.Wither.TABLE_LARGE);
        facingBlock(BoneFurnitureSet.Wither.TABLE_SMALL);
        facingBlock(BoneFurnitureSet.Wither.TABLE_WIDE);
        facingBlock(BoneFurnitureSet.Wither.BENCH);
        facingBlock(BoneFurnitureSet.Wither.CHAIR);
        simpleExistingBlock(BoneFurnitureSet.Wither.CEILING_LIGHT);
        facingBlock(BoneFurnitureSet.Wither.CUSHION);
        facingBlock(BoneFurnitureSet.Wither.STOOL);
        facingBlock(BoneFurnitureSet.Wither.CHEST);
        facingBlock(BoneFurnitureSet.Wither.BOOKSHELF);
        facingBlock(BoneFurnitureSet.Wither.DESK_LEFT);
        facingBlock(BoneFurnitureSet.Wither.DESK_RIGHT);
        facingBlock(BoneFurnitureSet.Wither.DRAWER);
        facingBlock(BoneFurnitureSet.Wither.DRESSER);
        facingBlock(BoneFurnitureSet.Wither.LOCKBOX);
        facingBlock(BoneFurnitureSet.Wither.WARDROBE_BOTTOM);
        facingBlock(BoneFurnitureSet.Wither.WARDROBE_TOP);
        facingBlock(BoneFurnitureSet.Wither.PAINTING_SMALL);
        facingBlock(BoneFurnitureSet.Wither.PAINTING_WIDE);
        ovenBlock(BoneFurnitureSet.Wither.OVEN);
        doorBlock(BoneFurnitureSet.Wither.DOOR_DOUBLE);
        doorBlock(BoneFurnitureSet.Wither.DOOR_SINGLE);
        facingBlock(BoneFurnitureSet.Wither.BED_SINGLE);
        facingBlock(BoneFurnitureSet.Wither.BED_DOUBLE);
        shelfBlock(BoneFurnitureSet.Wither.SHELF);
        sofaBlock(BoneFurnitureSet.Wither.SOFA);
        counterBlock(BoneFurnitureSet.Wither.COUNTER);
    }

    private void skeleton()
    {
        simpleBlock(BoneFurnitureSet.Skeleton.WOOL);
        carpet(BoneFurnitureSet.Skeleton.CARPET, BoneFurnitureSet.Skeleton.WOOL);
        wallLight(BoneFurnitureSet.Skeleton.WALL_LIGHT);
        simpleExistingBlock(BoneFurnitureSet.Skeleton.FLOOR_LIGHT);
        facingBlock(BoneFurnitureSet.Skeleton.TABLE_LARGE);
        facingBlock(BoneFurnitureSet.Skeleton.TABLE_SMALL);
        facingBlock(BoneFurnitureSet.Skeleton.TABLE_WIDE);
        facingBlock(BoneFurnitureSet.Skeleton.BENCH);
        facingBlock(BoneFurnitureSet.Skeleton.CHAIR);
        simpleExistingBlock(BoneFurnitureSet.Skeleton.CEILING_LIGHT);
        facingBlock(BoneFurnitureSet.Skeleton.CUSHION);
        facingBlock(BoneFurnitureSet.Skeleton.STOOL);
        facingBlock(BoneFurnitureSet.Skeleton.CHEST);
        facingBlock(BoneFurnitureSet.Skeleton.BOOKSHELF);
        facingBlock(BoneFurnitureSet.Skeleton.DESK_LEFT);
        facingBlock(BoneFurnitureSet.Skeleton.DESK_RIGHT);
        facingBlock(BoneFurnitureSet.Skeleton.DRAWER);
        facingBlock(BoneFurnitureSet.Skeleton.DRESSER);
        facingBlock(BoneFurnitureSet.Skeleton.LOCKBOX);
        facingBlock(BoneFurnitureSet.Skeleton.WARDROBE_BOTTOM);
        facingBlock(BoneFurnitureSet.Skeleton.WARDROBE_TOP);
        facingBlock(BoneFurnitureSet.Skeleton.PAINTING_SMALL);
        facingBlock(BoneFurnitureSet.Skeleton.PAINTING_WIDE);
        ovenBlock(BoneFurnitureSet.Skeleton.OVEN);
        doorBlock(BoneFurnitureSet.Skeleton.DOOR_DOUBLE);
        doorBlock(BoneFurnitureSet.Skeleton.DOOR_SINGLE);
        facingBlock(BoneFurnitureSet.Skeleton.BED_SINGLE);
        facingBlock(BoneFurnitureSet.Skeleton.BED_DOUBLE);
        shelfBlock(BoneFurnitureSet.Skeleton.SHELF);
        sofaBlock(BoneFurnitureSet.Skeleton.SOFA);
        counterBlock(BoneFurnitureSet.Skeleton.COUNTER);
    }
}

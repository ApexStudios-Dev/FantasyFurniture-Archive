package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(VenthyrFurnitureSet.Blocks.WOOL);
        carpet(VenthyrFurnitureSet.Blocks.CARPET, VenthyrFurnitureSet.Blocks.WOOL);
        wallLight(VenthyrFurnitureSet.Blocks.WALL_LIGHT);
        simpleExistingBlock(VenthyrFurnitureSet.Blocks.FLOOR_LIGHT);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_LARGE);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_SMALL);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_WIDE);
        facingBlock(VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY);
        facingBlock(VenthyrFurnitureSet.Blocks.BENCH);
        facingBlock(VenthyrFurnitureSet.Blocks.CHAIR);
        simpleExistingBlock(VenthyrFurnitureSet.Blocks.CEILING_LIGHT);
        facingBlock(VenthyrFurnitureSet.Blocks.CUSHION);
        facingBlock(VenthyrFurnitureSet.Blocks.STOOL);
        facingBlock(VenthyrFurnitureSet.Blocks.CHEST);
        facingBlock(VenthyrFurnitureSet.Blocks.BOOKSHELF);
        facingBlock(VenthyrFurnitureSet.Blocks.DESK_LEFT);
        facingBlock(VenthyrFurnitureSet.Blocks.DESK_RIGHT);
        facingBlock(VenthyrFurnitureSet.Blocks.DRAWER);
        facingBlock(VenthyrFurnitureSet.Blocks.DRESSER);
        facingBlock(VenthyrFurnitureSet.Blocks.LOCKBOX);
        facingBlock(VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM);
        facingBlock(VenthyrFurnitureSet.Blocks.WARDROBE_TOP);
        facingBlock(VenthyrFurnitureSet.Blocks.PAINTING_SMALL);
        facingBlock(VenthyrFurnitureSet.Blocks.PAINTING_WIDE);
        ovenBlock(VenthyrFurnitureSet.Blocks.OVEN);
        doorBlock(VenthyrFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlock(VenthyrFurnitureSet.Blocks.DOOR_SINGLE);
        facingBlock(VenthyrFurnitureSet.Blocks.BED_SINGLE);
        facingBlock(VenthyrFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlock(VenthyrFurnitureSet.Blocks.SHELF);
        sofaBlock(VenthyrFurnitureSet.Blocks.SOFA);
        counterBlock(VenthyrFurnitureSet.Blocks.COUNTER);
    }
}

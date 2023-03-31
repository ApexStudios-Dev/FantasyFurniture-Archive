package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(NordicFurnitureSet.Blocks.WOOL);
        carpet(NordicFurnitureSet.Blocks.CARPET, NordicFurnitureSet.Blocks.WOOL);
        wallLight(NordicFurnitureSet.Blocks.WALL_LIGHT);
        simpleExistingBlock(NordicFurnitureSet.Blocks.FLOOR_LIGHT);
        facingBlock(NordicFurnitureSet.Blocks.TABLE_LARGE);
        facingBlock(NordicFurnitureSet.Blocks.TABLE_SMALL);
        facingBlock(NordicFurnitureSet.Blocks.TABLE_WIDE);
        facingBlock(NordicFurnitureSet.Blocks.BENCH);
        facingBlock(NordicFurnitureSet.Blocks.CHAIR);
        simpleExistingBlock(NordicFurnitureSet.Blocks.CEILING_LIGHT);
        facingBlock(NordicFurnitureSet.Blocks.CUSHION);
        facingBlock(NordicFurnitureSet.Blocks.STOOL);
        facingBlock(NordicFurnitureSet.Blocks.CHEST);
        facingBlock(NordicFurnitureSet.Blocks.BOOKSHELF);
        facingBlock(NordicFurnitureSet.Blocks.DESK_LEFT);
        facingBlock(NordicFurnitureSet.Blocks.DESK_RIGHT);
        facingBlock(NordicFurnitureSet.Blocks.DRAWER);
        facingBlock(NordicFurnitureSet.Blocks.DRESSER);
        facingBlock(NordicFurnitureSet.Blocks.LOCKBOX);
        facingBlock(NordicFurnitureSet.Blocks.WARDROBE_BOTTOM);
        facingBlock(NordicFurnitureSet.Blocks.WARDROBE_TOP);
        facingBlock(NordicFurnitureSet.Blocks.PAINTING_SMALL);
        facingBlock(NordicFurnitureSet.Blocks.PAINTING_WIDE);
        ovenBlock(NordicFurnitureSet.Blocks.OVEN);
        doorBlock(NordicFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlock(NordicFurnitureSet.Blocks.DOOR_SINGLE);
        facingBlock(NordicFurnitureSet.Blocks.BED_SINGLE);
        facingBlock(NordicFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlock(NordicFurnitureSet.Blocks.SHELF);
        sofaBlock(NordicFurnitureSet.Blocks.SOFA);
        counterBlock(NordicFurnitureSet.Blocks.COUNTER);
    }
}

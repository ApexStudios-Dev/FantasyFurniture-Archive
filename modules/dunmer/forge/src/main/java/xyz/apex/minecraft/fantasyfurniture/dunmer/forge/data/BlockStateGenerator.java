package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
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
        simpleBlock(DunmerFurnitureSet.Blocks.WOOL);
        carpet(DunmerFurnitureSet.Blocks.CARPET, DunmerFurnitureSet.Blocks.WOOL);
        wallLight(DunmerFurnitureSet.Blocks.WALL_LIGHT);
        simpleExistingBlock(DunmerFurnitureSet.Blocks.FLOOR_LIGHT);
        facingBlock(DunmerFurnitureSet.Blocks.TABLE_LARGE);
        facingBlock(DunmerFurnitureSet.Blocks.TABLE_SMALL);
        facingBlock(DunmerFurnitureSet.Blocks.TABLE_WIDE);
        facingBlock(DunmerFurnitureSet.Blocks.BENCH);
        facingBlock(DunmerFurnitureSet.Blocks.CHAIR);
        simpleExistingBlock(DunmerFurnitureSet.Blocks.CEILING_LIGHT);
        facingBlock(DunmerFurnitureSet.Blocks.CUSHION);
        facingBlock(DunmerFurnitureSet.Blocks.STOOL);
        facingBlock(DunmerFurnitureSet.Blocks.CHEST);
        facingBlock(DunmerFurnitureSet.Blocks.BOOKSHELF);
        facingBlock(DunmerFurnitureSet.Blocks.DESK_LEFT);
        facingBlock(DunmerFurnitureSet.Blocks.DESK_RIGHT);
        facingBlock(DunmerFurnitureSet.Blocks.DRAWER);
        facingBlock(DunmerFurnitureSet.Blocks.DRESSER);
        facingBlock(DunmerFurnitureSet.Blocks.LOCKBOX);
        facingBlock(DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM);
        facingBlock(DunmerFurnitureSet.Blocks.WARDROBE_TOP);
        facingBlock(DunmerFurnitureSet.Blocks.PAINTING_SMALL);
        facingBlock(DunmerFurnitureSet.Blocks.PAINTING_WIDE);
        ovenBlock(DunmerFurnitureSet.Blocks.OVEN);
        doorBlock(DunmerFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlock(DunmerFurnitureSet.Blocks.DOOR_SINGLE);
        facingBlock(DunmerFurnitureSet.Blocks.BED_SINGLE);
        facingBlock(DunmerFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlock(DunmerFurnitureSet.Blocks.SHELF);
        sofaBlock(DunmerFurnitureSet.Blocks.SOFA);
        counterBlock(DunmerFurnitureSet.Blocks.COUNTER);
    }
}

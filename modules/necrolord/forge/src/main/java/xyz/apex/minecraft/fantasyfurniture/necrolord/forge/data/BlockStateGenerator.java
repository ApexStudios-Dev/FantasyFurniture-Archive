package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(NecrolordFurnitureSet.Blocks.WOOL);
        carpet(NecrolordFurnitureSet.Blocks.CARPET, NecrolordFurnitureSet.Blocks.WOOL);
        wallLight(NecrolordFurnitureSet.Blocks.WALL_LIGHT);
        simpleExistingBlock(NecrolordFurnitureSet.Blocks.FLOOR_LIGHT);
        facingBlock(NecrolordFurnitureSet.Blocks.TABLE_LARGE);
        facingBlock(NecrolordFurnitureSet.Blocks.TABLE_SMALL);
        facingBlock(NecrolordFurnitureSet.Blocks.TABLE_WIDE);
        facingBlock(NecrolordFurnitureSet.Blocks.BENCH);
        facingBlock(NecrolordFurnitureSet.Blocks.CHAIR);
        simpleExistingBlock(NecrolordFurnitureSet.Blocks.CEILING_LIGHT);
        facingBlock(NecrolordFurnitureSet.Blocks.CUSHION);
        facingBlock(NecrolordFurnitureSet.Blocks.STOOL);
        facingBlock(NecrolordFurnitureSet.Blocks.CHEST);
        facingBlock(NecrolordFurnitureSet.Blocks.BOOKSHELF);
        facingBlock(NecrolordFurnitureSet.Blocks.DESK_LEFT);
        facingBlock(NecrolordFurnitureSet.Blocks.DESK_RIGHT);
        facingBlock(NecrolordFurnitureSet.Blocks.DRAWER);
        facingBlock(NecrolordFurnitureSet.Blocks.DRESSER);
        facingBlock(NecrolordFurnitureSet.Blocks.LOCKBOX);
        facingBlock(NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM);
        facingBlock(NecrolordFurnitureSet.Blocks.WARDROBE_TOP);
        facingBlock(NecrolordFurnitureSet.Blocks.PAINTING_SMALL);
        facingBlock(NecrolordFurnitureSet.Blocks.PAINTING_WIDE);
        ovenBlock(NecrolordFurnitureSet.Blocks.OVEN);
        doorBlock(NecrolordFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlock(NecrolordFurnitureSet.Blocks.DOOR_SINGLE);
        facingBlock(NecrolordFurnitureSet.Blocks.BED_SINGLE);
        facingBlock(NecrolordFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlock(NecrolordFurnitureSet.Blocks.SHELF);
        sofaBlock(NecrolordFurnitureSet.Blocks.SOFA);
        counterBlock(NecrolordFurnitureSet.Blocks.COUNTER);
    }
}

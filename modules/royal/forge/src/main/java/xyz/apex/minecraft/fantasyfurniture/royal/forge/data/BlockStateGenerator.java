package xyz.apex.minecraft.fantasyfurniture.royal.forge.data;

import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.BlockStateProvider;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

final class BlockStateGenerator extends BlockStateProvider
{
    BlockStateGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleTintedBlock(RoyalFurnitureSet.Blocks.WOOL);
        carpetTinted(RoyalFurnitureSet.Blocks.CARPET, RoyalFurnitureSet.Blocks.WOOL);
        wallLight(RoyalFurnitureSet.Blocks.WALL_LIGHT);
        simpleExistingBlock(RoyalFurnitureSet.Blocks.FLOOR_LIGHT);
        facingBlock(RoyalFurnitureSet.Blocks.TABLE_LARGE);
        facingBlock(RoyalFurnitureSet.Blocks.TABLE_SMALL);
        facingBlock(RoyalFurnitureSet.Blocks.TABLE_WIDE);
        facingBlock(RoyalFurnitureSet.Blocks.BENCH);
        facingBlock(RoyalFurnitureSet.Blocks.CHAIR);
        simpleExistingBlock(RoyalFurnitureSet.Blocks.CEILING_LIGHT);
        facingBlock(RoyalFurnitureSet.Blocks.CUSHION);
        facingBlock(RoyalFurnitureSet.Blocks.STOOL);
        facingBlock(RoyalFurnitureSet.Blocks.CHEST);
        facingBlock(RoyalFurnitureSet.Blocks.BOOKSHELF);
        facingBlock(RoyalFurnitureSet.Blocks.DESK_LEFT);
        facingBlock(RoyalFurnitureSet.Blocks.DESK_RIGHT);
        facingBlock(RoyalFurnitureSet.Blocks.DRAWER);
        facingBlock(RoyalFurnitureSet.Blocks.DRESSER);
        facingBlock(RoyalFurnitureSet.Blocks.LOCKBOX);
        facingBlock(RoyalFurnitureSet.Blocks.WARDROBE_BOTTOM);
        facingBlock(RoyalFurnitureSet.Blocks.WARDROBE_TOP);
        facingBlock(RoyalFurnitureSet.Blocks.PAINTING_SMALL);
        facingBlock(RoyalFurnitureSet.Blocks.PAINTING_WIDE);
        ovenBlock(RoyalFurnitureSet.Blocks.OVEN);
        doorBlock(RoyalFurnitureSet.Blocks.DOOR_DOUBLE);
        doorBlock(RoyalFurnitureSet.Blocks.DOOR_SINGLE);
        facingBlock(RoyalFurnitureSet.Blocks.BED_SINGLE);
        facingBlock(RoyalFurnitureSet.Blocks.BED_DOUBLE);
        shelfBlock(RoyalFurnitureSet.Blocks.SHELF);
        sofaBlock(RoyalFurnitureSet.Blocks.SOFA);
        counterBlock(RoyalFurnitureSet.Blocks.COUNTER);
    }
}

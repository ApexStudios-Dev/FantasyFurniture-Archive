package xyz.apex.minecraft.fantasyfurniture.nordic.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

import java.util.function.Consumer;
import java.util.function.Supplier;

final class RecipeGenerator extends RecipeProvider
{
    RecipeGenerator(GatherDataEvent event)
    {
        super(event);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        nordicStation(consumer, NordicFurnitureSet.Blocks.WOOL);
        nordicStation(consumer, NordicFurnitureSet.Blocks.CARPET);
        nordicStation(consumer, NordicFurnitureSet.Blocks.WALL_LIGHT);
        nordicStation(consumer, NordicFurnitureSet.Blocks.FLOOR_LIGHT);
        nordicStation(consumer, NordicFurnitureSet.Blocks.TABLE_LARGE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.TABLE_SMALL);
        nordicStation(consumer, NordicFurnitureSet.Blocks.TABLE_WIDE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.BENCH);
        nordicStation(consumer, NordicFurnitureSet.Blocks.CHAIR);
        nordicStation(consumer, NordicFurnitureSet.Blocks.CEILING_LIGHT);
        nordicStation(consumer, NordicFurnitureSet.Blocks.CUSHION);
        nordicStation(consumer, NordicFurnitureSet.Blocks.STOOL);
        nordicStation(consumer, NordicFurnitureSet.Blocks.CHEST);
        nordicStation(consumer, NordicFurnitureSet.Blocks.BOOKSHELF);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DESK_LEFT);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DESK_RIGHT);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DRAWER);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DRESSER);
        nordicStation(consumer, NordicFurnitureSet.Blocks.LOCKBOX);
        nordicStation(consumer, NordicFurnitureSet.Blocks.WARDROBE_BOTTOM);
        nordicStation(consumer, NordicFurnitureSet.Blocks.WARDROBE_TOP);
        nordicStation(consumer, NordicFurnitureSet.Blocks.PAINTING_SMALL);
        nordicStation(consumer, NordicFurnitureSet.Blocks.PAINTING_WIDE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.OVEN);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DOOR_DOUBLE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.DOOR_SINGLE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.BED_SINGLE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.BED_DOUBLE);
        nordicStation(consumer, NordicFurnitureSet.Blocks.SHELF);
        nordicStation(consumer, NordicFurnitureSet.Blocks.SOFA);
        nordicStation(consumer, NordicFurnitureSet.Blocks.COUNTER);
    }

    private void nordicStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, null, () -> Blocks.OAK_PLANKS, () -> Blocks.BROWN_WOOL, block);
    }
}

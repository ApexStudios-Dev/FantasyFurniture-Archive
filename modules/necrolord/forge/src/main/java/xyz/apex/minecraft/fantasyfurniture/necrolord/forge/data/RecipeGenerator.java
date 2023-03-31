package xyz.apex.minecraft.fantasyfurniture.necrolord.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

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
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.WOOL);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.CARPET);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.WALL_LIGHT);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.FLOOR_LIGHT);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.TABLE_LARGE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.TABLE_SMALL);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.TABLE_WIDE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.BENCH);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.CHAIR);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.CEILING_LIGHT);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.CUSHION);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.STOOL);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.CHEST);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.BOOKSHELF);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DESK_LEFT);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DESK_RIGHT);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DRAWER);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DRESSER);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.LOCKBOX);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.WARDROBE_BOTTOM);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.WARDROBE_TOP);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.PAINTING_SMALL);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.PAINTING_WIDE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.OVEN);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DOOR_DOUBLE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.DOOR_SINGLE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.BED_SINGLE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.BED_DOUBLE);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.SHELF);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.SOFA);
        necrolordStation(consumer, NecrolordFurnitureSet.Blocks.COUNTER);
    }

    private void necrolordStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, null, () -> Blocks.DARK_OAK_PLANKS, () -> Blocks.PURPLE_WOOL, block);
    }
}

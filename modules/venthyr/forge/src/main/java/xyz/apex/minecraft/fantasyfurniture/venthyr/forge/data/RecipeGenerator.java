package xyz.apex.minecraft.fantasyfurniture.venthyr.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

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
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.WOOL);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.CARPET);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.WALL_LIGHT);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.FLOOR_LIGHT);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_LARGE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_LARGE_FANCY);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_SMALL);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_SMALL_FANCY);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_WIDE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.TABLE_WIDE_FANCY);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.BENCH);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.CHAIR);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.CEILING_LIGHT);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.CUSHION);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.STOOL);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.CHEST);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.BOOKSHELF);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DESK_LEFT);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DESK_RIGHT);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DRAWER);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DRESSER);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.LOCKBOX);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.WARDROBE_BOTTOM);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.WARDROBE_TOP);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.PAINTING_SMALL);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.PAINTING_WIDE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.OVEN);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DOOR_DOUBLE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.DOOR_SINGLE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.BED_SINGLE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.BED_DOUBLE);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.SHELF);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.SOFA);
        venthyrStation(consumer, VenthyrFurnitureSet.Blocks.COUNTER);
    }

    private void venthyrStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, null, () -> Blocks.SPRUCE_PLANKS, () -> Blocks.RED_WOOL, block);
    }
}

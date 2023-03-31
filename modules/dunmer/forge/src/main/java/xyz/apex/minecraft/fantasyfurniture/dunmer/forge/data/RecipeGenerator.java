package xyz.apex.minecraft.fantasyfurniture.dunmer.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.forge.data.providers.RecipeProvider;

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
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.WOOL);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.CARPET);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.WALL_LIGHT);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.FLOOR_LIGHT);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.TABLE_LARGE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.TABLE_SMALL);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.TABLE_WIDE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.BENCH);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.CHAIR);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.CEILING_LIGHT);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.CUSHION);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.STOOL);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.CHEST);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.BOOKSHELF);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DESK_LEFT);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DESK_RIGHT);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DRAWER);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DRESSER);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.LOCKBOX);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.WARDROBE_BOTTOM);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.WARDROBE_TOP);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.PAINTING_SMALL);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.PAINTING_WIDE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.OVEN);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DOOR_DOUBLE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.DOOR_SINGLE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.BED_SINGLE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.BED_DOUBLE);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.SHELF);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.SOFA);
        dunmerStation(consumer, DunmerFurnitureSet.Blocks.COUNTER);
    }

    private void dunmerStation(Consumer<FinishedRecipe> consumer, Supplier<? extends ItemLike> block)
    {
        furnitureStation(consumer, null, () -> Blocks.SPRUCE_PLANKS, () -> Blocks.GREEN_WOOL, block);
    }
}

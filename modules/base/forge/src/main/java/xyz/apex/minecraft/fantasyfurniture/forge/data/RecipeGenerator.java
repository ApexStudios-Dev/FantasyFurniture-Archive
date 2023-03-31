package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

final class RecipeGenerator extends RecipeProvider
{
    RecipeGenerator(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.EMPTY, Ingredient.of(Items.CRAFTING_TABLE), Ingredient.of(Items.LEATHER), RecipeCategory.MISC, FantasyFurniture.FURNITURE_STATION_BLOCK.get().asItem())
                .unlocks("has_crafting_table", RecipeProvider.has(Items.CRAFTING_TABLE))
                .unlocks("has_leather", RecipeProvider.has(Items.LEATHER))
                .save(consumer, RecipeBuilder.getDefaultRecipeId(FantasyFurniture.FURNITURE_STATION_BLOCK.get()))
        ;
    }
}

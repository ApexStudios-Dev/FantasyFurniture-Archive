/*
package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import net.minecraft.resources.ResourceLocation;

import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

final class ReiFurnitureStationRecipeDisplay extends BasicDisplay
{
    ReiFurnitureStationRecipeDisplay(FurnitureStationRecipe recipe)
    {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.getResultItem())), Optional.of(recipe.getId()));
    }

    ReiFurnitureStationRecipeDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location)
    {
        super(inputs, outputs, location);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier()
    {
        return ReiFantasyFurnitureServerPlugin.FURNITURE_STATION_RECIPE_ID;
    }
}
*/

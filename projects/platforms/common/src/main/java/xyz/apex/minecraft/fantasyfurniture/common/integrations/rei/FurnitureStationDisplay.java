package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.List;
import java.util.Optional;

public final class FurnitureStationDisplay extends BasicDisplay
{
    FurnitureStationDisplay(RecipeHolder<FurnitureStationRecipe> recipe)
    {
        this(recipe.value(), recipe.id(), ingredients(recipe));
    }

    FurnitureStationDisplay(FurnitureStationRecipe recipe, @Nullable ResourceLocation recipeId, List<EntryIngredient> inputs)
    {
        this(inputs, List.of(EntryIngredients.of(recipe.getResultItem(registryAccess()))), Optional.ofNullable(recipeId));
    }

    FurnitureStationDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> recipeId)
    {
        super(inputs, outputs, recipeId);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier()
    {
        return FurnitureStationReiServerPlugin.DISPLAY;
    }

    private static List<EntryIngredient> ingredients(RecipeHolder<FurnitureStationRecipe> recipe)
    {
        var inputs = recipe.value().getIngredients();

        return List.of(
                EntryIngredients.ofIngredient(inputs.get(FurnitureStationMenu.SLOT_INGREDIENT_A)),
                EntryIngredients.ofIngredient(inputs.get(FurnitureStationMenu.SLOT_INGREDIENT_B))
        );
    }
}

package xyz.apex.minecraft.fantasyfurniture.fabric.integrations.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;

import net.minecraft.resources.ResourceLocation;

import xyz.apex.minecraft.fantasyfurniture.shared.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.FurnitureStationRecipe;

import java.util.List;

final class EmiFurnitureStationRecipe implements EmiRecipe
{
    private final ResourceLocation recipeId;
    private final EmiIngredient ingredientLeft;
    private final EmiIngredient ingredientRight;
    private final EmiIngredient bindingAgent;
    private final EmiStack result;

    EmiFurnitureStationRecipe(FurnitureStationRecipe recipe)
    {
        recipeId = recipe.getId();

        var ingredients = recipe.getIngredients();
        ingredientLeft = EmiIngredient.of(ingredients.get(FurnitureStationMenu.SLOT_INGREDIENT_LEFT));
        ingredientRight = EmiIngredient.of(ingredients.get(FurnitureStationMenu.SLOT_INGREDIENT_RIGHT));
        bindingAgent = EmiIngredient.of(ingredients.get(FurnitureStationMenu.SLOT_BINDING_AGENT));

        result = EmiStack.of(recipe.getResultItem());
    }

    @Override
    public EmiRecipeCategory getCategory()
    {
        return EmiFantasyFurnitureFabricPlugin.FURNITURE_STATION_CATEGORY;
    }

    @Override
    public ResourceLocation getId()
    {
        return recipeId;
    }

    @Override
    public List<EmiIngredient> getInputs()
    {
        return List.of(ingredientLeft, ingredientRight, bindingAgent);
    }

    @Override
    public List<EmiStack> getOutputs()
    {
        return List.of(result);
    }

    @Override
    public int getDisplayWidth()
    {
        return 112;
    }

    @Override
    public int getDisplayHeight()
    {
        return 26;
    }

    @Override
    public void addWidgets(WidgetHolder widgets)
    {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 58, 5);
        widgets.addSlot(ingredientLeft, 0, 4);
        widgets.addSlot(ingredientRight, 18, 4);
        widgets.addSlot(bindingAgent, 36, 4);
        widgets.addSlot(result, 86, 0).output(true).recipeContext(this);
    }
}

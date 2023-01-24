package xyz.apex.minecraft.fantasyfurniture.common.integrations.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;

import net.minecraft.network.chat.Component;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

public final class JeiFurnitureStationRecipeCategory implements IRecipeCategory<FurnitureStationRecipe>
{
    public static final RecipeType<FurnitureStationRecipe> RECIPE_TYPE = RecipeType.create(FantasyFurniture.ID, "furniture_station", FurnitureStationRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slot;

    JeiFurnitureStationRecipeCategory(IJeiHelpers jei)
    {
        var gui = jei.getGuiHelper();

        background = gui.createBlankDrawable(114, 16);
        icon = gui.createDrawableItemStack(FantasyFurniture.FURNITURE_STATION_BLOCK.asStack());
        slot = gui.getSlotDrawable();
    }

    @Override
    public RecipeType<FurnitureStationRecipe> getRecipeType()
    {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return FantasyFurniture.FURNITURE_STATION_BLOCK.get().getName();
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FurnitureStationRecipe recipe, IFocusGroup focuses)
    {
        var ingredients = recipe.getIngredients();
        var result = recipe.getResultItem();

        var x = 0;
        var y = 0;

        for(var i = 0; i < ingredients.size(); i++)
        {
            builder.addSlot(RecipeIngredientRole.INPUT, x + (18 * i), y)
                   .setBackground(slot, -1, -1)
                   .addIngredients(ingredients.get(i))
            ;
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, x + 98, y)
               .setBackground(slot, -1, -1)
               .addItemStack(result)
        ;
    }
}

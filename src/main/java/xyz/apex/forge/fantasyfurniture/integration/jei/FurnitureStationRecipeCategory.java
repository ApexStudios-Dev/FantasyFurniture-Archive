package xyz.apex.forge.fantasyfurniture.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

public final class FurnitureStationRecipeCategory implements IRecipeCategory<FurnitureStationRecipes>
{
	private final IDrawable background;
	private final IDrawable icon;

	FurnitureStationRecipeCategory(IJeiHelpers jei)
	{
		IGuiHelper gui = jei.getGuiHelper();

		background = gui.createBlankDrawable(123, 175);
		icon = gui.createDrawableIngredient(FurnitureStation.BLOCK.asStack());
	}

	@Override
	public ResourceLocation getUid()
	{
		return JeiIntegration.FURNITURE_STATION_RECIPES;
	}

	@Override
	public Class<? extends FurnitureStationRecipes> getRecipeClass()
	{
		return FurnitureStationRecipes.class;
	}

	@Override
	public Component getTitle()
	{
		return FurnitureStation.BLOCK.get().getName();
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
	public void setIngredients(FurnitureStationRecipes recipe, IIngredients ingredients)
	{
		recipe.setIngredients(ingredients);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FurnitureStationRecipes recipe, IIngredients ingredients)
	{
		recipe.setRecipe(recipeLayout);
	}

	@Override
	public void draw(FurnitureStationRecipes recipe, PoseStack pose, double mouseX, double mouseY)
	{
		recipe.draw(pose, background);
	}
}

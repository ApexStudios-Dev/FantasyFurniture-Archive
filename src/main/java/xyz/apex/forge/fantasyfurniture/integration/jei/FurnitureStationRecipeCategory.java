/*
package xyz.apex.forge.fantasyfurniture.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
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
		icon = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK, FurnitureStation.BLOCK_ITEM.asStack());
	}

	@Override
	public ResourceLocation getUid()
	{
		return FurnitureStationRecipes.RECIPE_TYPE.getUid();
	}

	@Override
	public RecipeType<FurnitureStationRecipes> getRecipeType()
	{
		return FurnitureStationRecipes.RECIPE_TYPE;
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
	public void setRecipe(IRecipeLayoutBuilder builder, FurnitureStationRecipes recipe, IFocusGroup focuses)
	{
		IRecipeCategory.super.setRecipe(builder, recipe, focuses);
		recipe.setRecipe(builder);
	}

	@Override
	public void draw(FurnitureStationRecipes recipe, IRecipeSlotsView recipeSlotsView, PoseStack pose, double mouseX, double mouseY)
	{
		IRecipeCategory.super.draw(recipe, recipeSlotsView, pose, mouseX, mouseY);
		recipe.draw(pose, background);
	}
}*/
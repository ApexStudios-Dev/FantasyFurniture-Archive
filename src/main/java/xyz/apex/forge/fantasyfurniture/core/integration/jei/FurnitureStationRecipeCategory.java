package xyz.apex.forge.fantasyfurniture.core.integration.jei;

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

import xyz.apex.forge.fantasyfurniture.AllBlocks;

public final class FurnitureStationRecipeCategory implements IRecipeCategory<FurnitureStationRecipes>
{
	private final IDrawable background;
	private final IDrawable icon;

	FurnitureStationRecipeCategory(IJeiHelpers jei)
	{
		IGuiHelper gui = jei.getGuiHelper();

		background = gui.createBlankDrawable(123, 175);
		icon = gui.createDrawableIngredient(VanillaTypes.ITEM_STACK, AllBlocks.FURNITURE_STATION.asStack());
	}

	@Override
	public RecipeType<FurnitureStationRecipes> getRecipeType()
	{
		return FurnitureStationRecipes.RECIPE_TYPE;
	}

	@Override
	public Component getTitle()
	{
		return AllBlocks.FURNITURE_STATION.get().getName();
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
		recipe.setRecipe(builder);
	}

	@Override
	public void draw(FurnitureStationRecipes recipe, IRecipeSlotsView recipeSlotsView, PoseStack pose, double mouseX, double mouseY)
	{
		IRecipeCategory.super.draw(recipe, recipeSlotsView, pose, mouseX, mouseY);
		recipe.draw(pose, background);
	}

	@Override
	public ResourceLocation getUid()
	{
		return FurnitureStationRecipes.RECIPE_TYPE.getUid();
	}

	@Override
	public Class<? extends FurnitureStationRecipes> getRecipeClass()
	{
		return FurnitureStationRecipes.class;
	}
}

package xyz.apex.forge.fantasyfurniture.integration.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.Tags;

import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import java.util.Arrays;

public final class FurnitureStationRecipeCategory implements IRecipeCategory<Item>
{
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawable inputSlot;
	private final IDrawable resultSlot;

	FurnitureStationRecipeCategory(IJeiHelpers jei)
	{
		IGuiHelper gui = jei.getGuiHelper();

		background = gui.createBlankDrawable(120, 36);
		icon = gui.createDrawableIngredient(FurnitureStation.BLOCK.asItemStack());

		ResourceLocation texture = new ResourceLocation("jei", "textures/gui/gui_vanilla.png");
		inputSlot = gui.createDrawable(texture, 0, 228, 18, 18);
		resultSlot = gui.createDrawable(texture, 56, 224, 26, 26);
	}

	@Override
	public ResourceLocation getUid()
	{
		return JeiIntegration.FURNITURE_STATION_RECIPES;
	}

	@Override
	public Class<? extends Item> getRecipeClass()
	{
		return Item.class;
	}

	@Override
	public String getTitle()
	{
		return getTitleAsTextComponent().getString();
	}

	@Override
	public ITextComponent getTitleAsTextComponent()
	{
		return FurnitureStation.BLOCK.asBlock().getName();
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
	public void setIngredients(Item recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(Arrays.asList(
				Ingredient.of(Items.CLAY_BALL),
				Ingredient.of(Tags.Items.DYES_RED),
				Ingredient.of(Tags.Items.DYES_YELLOW),
				Ingredient.of(Tags.Items.DYES_BLUE)
		));

		ingredients.setOutput(VanillaTypes.ITEM, recipe.getDefaultInstance());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, Item recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup groups = recipeLayout.getItemStacks();

		groups.init(FurnitureStation.CLAY_SLOT, true, 20, 2);
		groups.init(FurnitureStation.RED_DYE_SLOT, true, 2, 20);
		groups.init(FurnitureStation.YELLOW_DYE_SLOT, true, 20, 20);
		groups.init(FurnitureStation.BLUE_DYE_SLOT, true, 38, 20);
		groups.init(FurnitureStation.RESULT_SLOT, false, 96, 12);

		groups.set(ingredients);
	}

	@Override
	public void draw(Item recipe, MatrixStack pose, double mouseX, double mouseY)
	{
		inputSlot.draw(pose, 20, 2);
		inputSlot.draw(pose, 2, 20);
		inputSlot.draw(pose, 20, 20);
		inputSlot.draw(pose, 38, 20);

		resultSlot.draw(pose, 92, 8);
	}
}

package xyz.apex.forge.fantasyfurniture.core.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.forge.apexcore.registrate.entry.ItemEntry;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.AllItems;
import xyz.apex.forge.fantasyfurniture.AllMenus;
import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationMenuScreen;
import xyz.apex.forge.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.forge.fantasyfurniture.core.FurnitureStation;

import java.util.Arrays;
import java.util.Collections;

@mezz.jei.api.JeiPlugin
public final class JeiPlugin implements IModPlugin
{
	private static final ResourceLocation PLUGIN_ID = new ResourceLocation(Mods.FANTASY_FURNITURE, "jei_integration");

	@Override
	public ResourceLocation getPluginUid()
	{
		return PLUGIN_ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration)
	{
		var jei = registration.getJeiHelpers();
		registration.addRecipeCategories(new FurnitureStationRecipeCategory(jei));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		var jei = registration.getJeiHelpers();
		var results = FurnitureStation.buildResultsList();
		var recipes = Collections.singletonList(new FurnitureStationRecipes(results, jei));
		registration.addRecipes(FurnitureStationRecipes.RECIPE_TYPE, recipes);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		registration.addRecipeTransferHandler(FurnitureStationMenu.class, AllMenus.FURNITURE_STATION.get(), FurnitureStationRecipes.RECIPE_TYPE, FurnitureStation.CLAY_SLOT, 3, FurnitureStation.RESULT_SLOT + 1, (9 * 3) + 9);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		var font = Minecraft.getInstance().font;
		// x, y should match title location in screen class
		var imageWidth = 176;
		var titleWidth = font.width(AllBlocks.FURNITURE_STATION.get().getName());
		var titleLabelX = (imageWidth - titleWidth) / 2;
		var titleLabelY = 6;

		registration.addRecipeClickArea(FurnitureStationMenuScreen.class, titleLabelX, titleLabelY, titleWidth, font.lineHeight, FurnitureStationRecipes.RECIPE_TYPE);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(AllBlocks.FURNITURE_STATION.asStack(), FurnitureStationRecipes.RECIPE_TYPE);

		// NOTE: Need to be updated with each new oven
		Arrays.stream(new ItemEntry[] {
				      AllItems.NORDIC_OVEN, AllItems.DUNMER_OVEN,
				      AllItems.VENTHYR_OVEN, AllItems.BONE_SKELETON_OVEN,
				      AllItems.BONE_WITHER_OVEN, AllItems.ROYAL_OVEN,
				      AllItems.NECROLORD_OVEN
		})
		      .map(ItemEntry::asStack)
		      .forEach(stack -> registration.addRecipeCatalyst(stack, RecipeTypes.SMOKING));
	}
}

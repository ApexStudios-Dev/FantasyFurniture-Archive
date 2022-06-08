package xyz.apex.forge.fantasyfurniture.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import java.util.Collections;

@JeiPlugin
public final class JeiIntegration implements IModPlugin
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();
	private static final ResourceLocation PLUGIN_ID = REGISTRY.id("jei_integration");
	public static final ResourceLocation FURNITURE_STATION_RECIPES = REGISTRY.id("recipes/furniture_station");

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
		var results = FurnitureStation.getCraftingResults();
		var recipes = Collections.singleton(new FurnitureStationRecipes(results, jei));
		registration.addRecipes(recipes, FURNITURE_STATION_RECIPES);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		registration.addRecipeTransferHandler(
				FurnitureStationContainer.class,
				FURNITURE_STATION_RECIPES,
				FurnitureStation.CLAY_SLOT,
				3,
				FurnitureStation.RESULT_SLOT + 1,
				(9 * 3) + 9
		);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		var font = Minecraft.getInstance().font;
		// x, y should match title location in screen class
		var x = 6;
		var y = 8;
		var w = font.width(FurnitureStation.BLOCK.asBlock().getName());
		var h = font.lineHeight;
		var padding = 2;
		registration.addRecipeClickArea(FurnitureStationContainerScreen.class, x - padding, y - padding, w + (padding * 2), h + (padding * 2), FURNITURE_STATION_RECIPES);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(FurnitureStation.BLOCK.asItemStack(), FURNITURE_STATION_RECIPES);
	}
}

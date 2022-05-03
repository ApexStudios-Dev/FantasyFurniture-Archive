package xyz.apex.forge.fantasyfurniture.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import xyz.apex.forge.fantasyfurniture.client.screen.FurnitureStationContainerScreen;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

@JeiPlugin
public final class JeiIntegration implements IModPlugin
{
	public static final Logger LOGGER = LogManager.getLogger();

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
		IJeiHelpers jei = registration.getJeiHelpers();
		registration.addRecipeCategories(new FurnitureStationRecipeCategory(jei));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		registration.addRecipes(FurnitureStation.CRAFTABLE.getValues(), FURNITURE_STATION_RECIPES);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		registration.addRecipeTransferHandler(new FurnitureStationRecipeTransferHandler(registration), FURNITURE_STATION_RECIPES);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		FontRenderer font = Minecraft.getInstance().font;
		// x, y should match title location in screen class
		int x = 6;
		int y = 8;
		int w = font.width(FurnitureStation.BLOCK.asBlock().getName());
		int h = font.lineHeight;
		int padding = 2;
		registration.addRecipeClickArea(FurnitureStationContainerScreen.class, x - padding, y - padding, w + (padding * 2), h + (padding * 2), FURNITURE_STATION_RECIPES);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(FurnitureStation.BLOCK.asItemStack(), FURNITURE_STATION_RECIPES);
	}
}

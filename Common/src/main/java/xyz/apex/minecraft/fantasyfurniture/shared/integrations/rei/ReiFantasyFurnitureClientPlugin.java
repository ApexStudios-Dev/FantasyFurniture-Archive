package xyz.apex.minecraft.fantasyfurniture.shared.integrations.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.BuiltinClientPlugin;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.FurnitureStationRecipe;

import java.util.List;

public interface ReiFantasyFurnitureClientPlugin extends REIClientPlugin
{
    @Override
    default void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(FurnitureStationRecipe.class, FantasyFurniture.FURNITURE_STATION_RECIPE::is, ReiFurnitureStationRecipeDisplay::new);
    }

    @Override
    default void registerCategories(CategoryRegistry registry)
    {
        registry.add(new ReiFurnitureStationRecipeDisplayCategory());
        registry.addWorkstations(ReiFantasyFurnitureServerPlugin.FURNITURE_STATION_RECIPE_ID, EntryStacks.of(FantasyFurniture.FURNITURE_STATION_BLOCK));

        registry.addWorkstations(BuiltinClientPlugin.SMOKING, EntryIngredients.ofItems(List.of(NordicSet.OVEN)));
    }

    @Override
    default void registerScreens(ScreenRegistry registry)
    {
        registry.registerContainerClickArea(new Rectangle(71, 16, 63, 16), FurnitureStationMenuScreen.class, ReiFantasyFurnitureServerPlugin.FURNITURE_STATION_RECIPE_ID);
    }
}

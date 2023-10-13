package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

public class FurnitureStationReiClientPlugin implements REIClientPlugin
{
    @Override
    public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new FurnitureStationCategory());
        registry.addWorkstations(FurnitureStationReiServerPlugin.DISPLAY, EntryStacks.of(FantasyFurniture.FURNITURE_STATION_BLOCK));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(FurnitureStationRecipe.class, FantasyFurniture.FURNITURE_STATION_RECIPE, FurnitureStationDisplay::new);
    }
}

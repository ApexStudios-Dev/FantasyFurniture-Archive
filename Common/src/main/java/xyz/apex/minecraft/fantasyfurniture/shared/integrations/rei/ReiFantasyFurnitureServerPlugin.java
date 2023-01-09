package xyz.apex.minecraft.fantasyfurniture.shared.integrations.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.display.SimpleDisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public interface ReiFantasyFurnitureServerPlugin extends REIServerPlugin
{
    CategoryIdentifier<ReiFurnitureStationRecipeDisplay> FURNITURE_STATION_RECIPE_ID = CategoryIdentifier.of(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName());
    SimpleDisplaySerializer<ReiFurnitureStationRecipeDisplay> FURNITURE_STATION_RECIPE_SERIALIZER = BasicDisplay.Serializer.ofSimple(ReiFurnitureStationRecipeDisplay::new);

    @Override
    default void registerDisplaySerializer(DisplaySerializerRegistry registry)
    {
        registry.register(FURNITURE_STATION_RECIPE_ID, FURNITURE_STATION_RECIPE_SERIALIZER);
    }
}

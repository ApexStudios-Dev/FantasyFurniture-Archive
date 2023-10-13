package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public class FurnitureStationReiServerPlugin implements REIServerPlugin
{
    public static final CategoryIdentifier<FurnitureStationDisplay> DISPLAY = CategoryIdentifier.of(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().withPrefix("rei/"));

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry)
    {
        registry.register(DISPLAY, BasicDisplay.Serializer.ofSimple(FurnitureStationDisplay::new));
    }
}

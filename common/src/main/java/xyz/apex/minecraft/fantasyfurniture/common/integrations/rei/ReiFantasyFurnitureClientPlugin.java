package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.BuiltinClientPlugin;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.List;

public interface ReiFantasyFurnitureClientPlugin extends REIClientPlugin
{
    @Override
    default void registerCollapsibleEntries(CollapsibleEntryRegistry registry)
    {
        registry.group(
                new ResourceLocation(FantasyFurniture.ID, NordicSet.NAME),
                Component.translatable("itemGroup.%s.%s".formatted(FantasyFurniture.ID, NordicSet.NAME)),
                EntryIngredients.ofItemTag(NordicSet.ITEM_TAG)
        );
    }

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
        registry.addWorkstations(BuiltinClientPlugin.FUEL, EntryIngredients.ofItems(List.of(NordicSet.OVEN)));
    }

    @Override
    default void registerScreens(ScreenRegistry registry)
    {
        registry.registerContainerClickArea(new Rectangle(71, 16, 63, 16), FurnitureStationMenuScreen.class, ReiFantasyFurnitureServerPlugin.FURNITURE_STATION_RECIPE_ID);
    }
}

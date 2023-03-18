/*
package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

import net.minecraft.network.chat.Component;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

import java.util.List;

final class ReiFurnitureStationRecipeDisplayCategory implements DisplayCategory<ReiFurnitureStationRecipeDisplay>
{
    @Override
    public CategoryIdentifier<? extends ReiFurnitureStationRecipeDisplay> getCategoryIdentifier()
    {
        return ReiFantasyFurnitureServerPlugin.FURNITURE_STATION_RECIPE_ID;
    }

    @Override
    public Component getTitle()
    {
        // category.rei.fantasy_furniture.furniture_station
        return Component.translatable(FantasyFurniture.FURNITURE_STATION_RECIPE.getRegistryName().toLanguageKey("category.rei"));
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(FantasyFurniture.FURNITURE_STATION_BLOCK);
    }

    @Override
    public int getDisplayWidth(ReiFurnitureStationRecipeDisplay display)
    {
        return 119;
    }

    @Override
    public int getDisplayHeight()
    {
        return 36;
    }

    @Override
    public List<Widget> setupDisplay(ReiFurnitureStationRecipeDisplay display, Rectangle bounds)
    {
        List<Widget> widgets = Lists.newArrayList();

        var x = bounds.getX() + 6;
        var y = bounds.getY() + 6;

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(x + 56, y + 4)));
        widgets.add(Widgets.createResultSlotBackground(new Point(x + 86, y + 4)));
        widgets.add(Widgets.createSlot(new Point(x + 86, y + 4)).entries(display.getOutputEntries().get(FurnitureStationMenu.SLOT_RESULT)).disableBackground().markOutput());

        var inputEntries = display.getInputEntries();
        widgets.add(Widgets.createSlot(new Point(x, y + 4)).entries(inputEntries.get(FurnitureStationMenu.SLOT_INGREDIENT_LEFT)).markInput());
        widgets.add(Widgets.createSlot(new Point(x + 18, y + 4)).entries(inputEntries.get(FurnitureStationMenu.SLOT_INGREDIENT_RIGHT)).markInput());
        widgets.add(Widgets.createSlot(new Point(x + 36, y + 4)).entries(inputEntries.get(FurnitureStationMenu.SLOT_BINDING_AGENT)).markInput());

        return widgets;
    }
}
*/

package xyz.apex.minecraft.fantasyfurniture.common.integrations.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

import java.util.List;

public final class FurnitureStationCategory implements DisplayCategory<FurnitureStationDisplay>
{
    @Override
    public CategoryIdentifier<? extends FurnitureStationDisplay> getCategoryIdentifier()
    {
        return FurnitureStationReiServerPlugin.DISPLAY;
    }

    @Override
    public Component getTitle()
    {
        return FantasyFurniture.FURNITURE_STATION_JEI_NAME;
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(FantasyFurniture.FURNITURE_STATION_BLOCK);
    }

    @Override
    public List<Widget> setupDisplay(FurnitureStationDisplay display, Rectangle bounds)
    {
        var font = Minecraft.getInstance().font;
        var x = bounds.getMinX() + 5;
        var y = bounds.getMinY() + 6;

        var bindingAgentX = x + 1;
        var plusX = bindingAgentX + 18 + 3;
        var plusText = Component.literal("+");
        var plusWidth = font.width(plusText);
        var ingredientAX = plusX + plusWidth;
        var ingredientBX = ingredientAX + 18;
        var arrowX = ingredientBX + 18;
        var resultX = arrowX + 1 + 24; // 24 = arrow width

        var inputEntries = display.getInputEntries();

        return List.of(
                Widgets.createRecipeBase(bounds),

                Widgets.createSlot(new Point(bindingAgentX, y))
                        .entries(EntryIngredients.ofItemTag(FantasyFurniture.FURNITURE_STATION_BINDING_AGENT))
                        .markInput(),

                Widgets.createLabel(new Point(plusX, y + (font.lineHeight / 2)), plusText),

                Widgets.createSlot(new Point(ingredientAX, y))
                        .entries(inputEntries.get(FurnitureStationMenu.SLOT_INGREDIENT_A))
                        .markInput(),

                Widgets.createSlot(new Point(ingredientBX, y))
                        .entries(inputEntries.get(FurnitureStationMenu.SLOT_INGREDIENT_B))
                        .markInput(),

                Widgets.createArrow(new Point(arrowX, y)),

                Widgets.createSlot(new Point(resultX, y))
                        .entries(display.getOutputEntries().get(0))
                        .markOutput()
        );
    }

    @Override
    public int getDisplayHeight() {
        return 5 + 18 + 5;
    }

    @Override
    public int getDisplayWidth(FurnitureStationDisplay display)
    {
        var font = Minecraft.getInstance().font;
        var x = 5;

        var bindingAgentX = x + 1;
        var plusX = bindingAgentX + 18 + 3;
        var plusText = Component.literal("+");
        var plusWidth = font.width(plusText);
        var ingredientAX = plusX + plusWidth;
        var ingredientBX = ingredientAX + 18;
        var arrowX = ingredientBX + 18;
        var resultX = arrowX + 1 + 24; // 24 = arrow width

        return resultX + 18 + 5;
    }
}

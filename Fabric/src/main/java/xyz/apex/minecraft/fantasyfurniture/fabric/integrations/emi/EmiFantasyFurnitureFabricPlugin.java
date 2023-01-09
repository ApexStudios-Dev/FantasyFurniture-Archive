package xyz.apex.minecraft.fantasyfurniture.fabric.integrations.emi;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.emi.emi.EmiRenderHelper;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.stack.EmiIngredient;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.crafting.Ingredient;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

public final class EmiFantasyFurnitureFabricPlugin implements EmiPlugin
{
    public static final EmiRecipeCategory FURNITURE_STATION_CATEGORY = new EmiRecipeCategory(FantasyFurniture.FURNITURE_STATION_BLOCK.getRegistryName(), simple(160, 240));

    @Override
    public void register(EmiRegistry registry)
    {
        registry.addCategory(FURNITURE_STATION_CATEGORY);
        registry.addWorkstation(FURNITURE_STATION_CATEGORY, EmiIngredient.of(Ingredient.of(FantasyFurniture.FURNITURE_STATION_BLOCK)));
        registry.getRecipeManager().getAllRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE.asRecipeType()).stream().map(EmiFurnitureStationRecipe::new).forEach(registry::addRecipe);
    }

    private static EmiRenderable simple(int u, int v)
    {
        return (pose, x, y, delta) -> {
            RenderSystem.setShaderTexture(0, EmiRenderHelper.WIDGETS);
            GuiComponent.blit(pose, x, y, u, v, 16, 16, 256, 256);
        };
    }
}

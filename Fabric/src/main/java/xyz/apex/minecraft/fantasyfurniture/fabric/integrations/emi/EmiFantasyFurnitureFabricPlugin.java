package xyz.apex.minecraft.fantasyfurniture.fabric.integrations.emi;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import xyz.apex.minecraft.apexcore.shared.util.function.Lazy;
import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;

import java.util.function.Supplier;

public final class EmiFantasyFurnitureFabricPlugin implements EmiPlugin
{
    public static final Supplier<EmiRecipeCategory> FURNITURE_STATION_CATEGORY = Lazy.of(() -> new EmiRecipeCategory(FantasyFurniture.FURNITURE_STATION_BLOCK.getRegistryName(), EmiStack.of(FantasyFurniture.FURNITURE_STATION_BLOCK), texture(new ResourceLocation(FantasyFurniture.ID, "textures/gui/emi_simplified.png"))));

    @Override
    public void register(EmiRegistry registry)
    {
        registry.addCategory(FURNITURE_STATION_CATEGORY.get());
        registry.addWorkstation(FURNITURE_STATION_CATEGORY.get(), EmiIngredient.of(Ingredient.of(FantasyFurniture.FURNITURE_STATION_BLOCK)));
        registry.getRecipeManager().getAllRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE.asRecipeType()).stream().map(EmiFurnitureStationRecipe::new).forEach(registry::addRecipe);
    }

    private static EmiRenderable texture(ResourceLocation texture)
    {
        return (pose, x, y, delta) -> {
            RenderSystem.setShaderTexture(0, texture);
            GuiComponent.blit(pose, x, y, 0, 0, 16, 16, 16, 16);
        };
    }
}

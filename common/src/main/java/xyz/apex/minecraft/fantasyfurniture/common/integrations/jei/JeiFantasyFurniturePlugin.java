package xyz.apex.minecraft.fantasyfurniture.common.integrations.jei;

import dev.architectury.utils.GameInstance;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;

import net.minecraft.resources.ResourceLocation;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

public interface JeiFantasyFurniturePlugin extends IModPlugin
{
    ResourceLocation PLUGIN_ID = new ResourceLocation(FantasyFurniture.ID, "jei");

    @Override
    default ResourceLocation getPluginUid()
    {
        return PLUGIN_ID;
    }

    @Override
    default void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new JeiFurnitureStationRecipeCategory(registration.getJeiHelpers()));
    }

    @Override
    default void registerRecipes(IRecipeRegistration registration)
    {
        var recipeManager = GameInstance.getClient().level.getRecipeManager();
        registration.addRecipes(JeiFurnitureStationRecipeCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE.asRecipeType()));
    }

    @Override
    default void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
    {
        registration.addRecipeTransferHandler(
                FurnitureStationMenu.class,
                AllMenuTypes.FURNITURE_STATION.get(),
                JeiFurnitureStationRecipeCategory.RECIPE_TYPE,
                1,
                FurnitureStationMenu.SLOT_INGREDIENT_COUNT,
                FurnitureStationMenu.SLOT_INGREDIENT_COUNT + 1,
                9 * 4
        );
    }

    @Override
    default void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(FantasyFurniture.FURNITURE_STATION_BLOCK.asStack(), JeiFurnitureStationRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(NordicSet.OVEN.asStack(), RecipeTypes.SMOKING, RecipeTypes.FUELING);
        registration.addRecipeCatalyst(VenthyrSet.OVEN.asStack(), RecipeTypes.SMOKING, RecipeTypes.FUELING);
        registration.addRecipeCatalyst(DunmerSet.OVEN.asStack(), RecipeTypes.SMOKING, RecipeTypes.FUELING);
        registration.addRecipeCatalyst(BoneSet.Wither.OVEN.asStack(), RecipeTypes.SMOKING, RecipeTypes.FUELING);
        registration.addRecipeCatalyst(BoneSet.Skeleton.OVEN.asStack(), RecipeTypes.SMOKING, RecipeTypes.FUELING);
    }

    @Override
    default void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(
                FurnitureStationMenuScreen.class,
                71, 16,
                63, 16,
                JeiFurnitureStationRecipeCategory.RECIPE_TYPE
        );
    }
}

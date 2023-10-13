package xyz.apex.minecraft.fantasyfurniture.common.integrations.jei;

import com.google.common.base.Suppliers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.client.screen.FurnitureStationMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.function.Supplier;

@JeiPlugin
public final class FurnitureStationJeiPlugin implements IModPlugin
{
    public static final ResourceLocation PLUGIN_UID = new ResourceLocation(FantasyFurniture.ID, "jei/furniture_station");
    public static final Supplier<RecipeType<RecipeHolder<FurnitureStationRecipe>>> RECIPE_TYPE = Suppliers.memoize(() -> RecipeType.createFromVanilla(FantasyFurniture.FURNITURE_STATION_RECIPE));

    @Override
    public ResourceLocation getPluginUid()
    {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        registration.addRecipeCategories(new FurnitureStationRecipeCategory(registration.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addRecipes(
                RECIPE_TYPE.get(),
                Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(FantasyFurniture.FURNITURE_STATION_RECIPE)
        );
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
    {
        registration.addRecipeTransferHandler(
                FurnitureStationMenu.class,
                FantasyFurniture.FURNITURE_STATION_MENU.value(),
                RECIPE_TYPE.get(),
                FurnitureStationMenu.SLOT_BINDING_AGENT,
                FurnitureStationMenu.SLOT_COUNT,
                FurnitureStationMenu.SLOT_COUNT + 1,
                (9 * 3) + 9
        );
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        var font = Minecraft.getInstance().font;

        registration.addRecipeClickArea(
                FurnitureStationMenuScreen.class,
                SimpleContainerMenu.SLOT_BORDER_OFFSET,
                6,
                font.width(FantasyFurniture.FURNITURE_STATION_BLOCK.value().getName()),
                font.lineHeight,
                RECIPE_TYPE.get()
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(FantasyFurniture.FURNITURE_STATION_BLOCK.asStack(), RECIPE_TYPE.get());
    }
}

package xyz.apex.minecraft.fantasyfurniture.shared.recipe;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;

import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;

import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class CustomRecipeBookCategories
{
    static
    {
        RecipeBookCategories.values();
    }

    public static RecipeBookCategories FURNITURE_STATION_NORDIC;

    @ApiStatus.Internal
    public static void bootstrap() {}

    @ApiStatus.Internal
    @Nullable
    public static List<ItemStack> getIcons(RecipeBookCategories recipeBookCategory)
    {
        if(recipeBookCategory == FURNITURE_STATION_NORDIC) return List.of(NordicSet.BED_SINGLE.asStack(), NordicSet.WOOL.asStack());
        return null;
    }

    @ApiStatus.Internal
    @Nullable
    public static List<RecipeBookCategories> getCategories(RecipeBookType recipeBookType)
    {
        if(recipeBookType == CustomRecipeBookTypes.FURNITURE_STATION) return List.of(FURNITURE_STATION_NORDIC);
        return null;
    }
}

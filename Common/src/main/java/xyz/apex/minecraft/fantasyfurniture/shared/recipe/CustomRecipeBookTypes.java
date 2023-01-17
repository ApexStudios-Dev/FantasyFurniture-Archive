package xyz.apex.minecraft.fantasyfurniture.shared.recipe;

import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.world.inventory.RecipeBookType;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class CustomRecipeBookTypes
{
    static
    {
        // this is here to ensure class is loaded
        // before our custom type
        RecipeBookType.values();
    }

    public static RecipeBookType FURNITURE_STATION;

    @ApiStatus.Internal
    public static void bootstrap()
    {
        EnvExecutor.runInEnv(Env.CLIENT, () -> CustomRecipeBookCategories::bootstrap);
    }
}

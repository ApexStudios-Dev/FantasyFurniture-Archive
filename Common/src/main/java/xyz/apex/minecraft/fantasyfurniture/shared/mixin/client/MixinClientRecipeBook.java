package xyz.apex.minecraft.fantasyfurniture.shared.mixin.client;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;

import xyz.apex.minecraft.fantasyfurniture.shared.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.CustomRecipeBookCategories;
import xyz.apex.minecraft.fantasyfurniture.shared.recipe.FurnitureStationRecipe;

import java.util.Locale;

@Mixin(ClientRecipeBook.class)
public abstract class MixinClientRecipeBook
{
    @Shadow @Final private static Logger LOGGER;

    @Inject(
            method = "getCategory",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void FantasyFurniture$getCategory(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> cir)
    {
        if(recipe instanceof FurnitureStationRecipe fRecipe)
        {
            var furnitureSet = fRecipe.getFurnitureSet().toLowerCase(Locale.ROOT);

            cir.setReturnValue(switch(furnitureSet) {
                case NordicSet.NAME -> CustomRecipeBookCategories.FURNITURE_STATION_NORDIC;
                default -> {
                    LOGGER.warn("Unknown FurnitureStationRecipe FurnitureSet: {}, in Recipe: {}", furnitureSet, recipe.getId());
                    yield RecipeBookCategories.UNKNOWN;
                }
            });
        }
    }
}

package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public final class RecipeGenerator extends RecipeProvider
{
    RecipeGenerator(GatherDataEvent event)
    {
        super(event.getGenerator());
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
    }
}

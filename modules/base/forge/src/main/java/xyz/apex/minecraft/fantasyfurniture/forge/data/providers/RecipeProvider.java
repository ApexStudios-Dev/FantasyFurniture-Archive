package xyz.apex.minecraft.fantasyfurniture.forge.data.providers;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider
{
    protected RecipeProvider(GatherDataEvent event)
    {
        super(event.getGenerator().getPackOutput());
    }

    protected void furnitureStation(Consumer<FinishedRecipe> exporter, @Nullable String subSetType, Supplier<? extends ItemLike> left, Supplier<? extends ItemLike> right, Supplier<? extends ItemLike> item)
    {
        FurnitureStationRecipe.clayBound(RecipeCategory.MISC, Ingredient.of(left.get()), Ingredient.of(right.get()), item.get())
                .unlockedBy("has_left_ingredient", has(left.get()))
                .unlockedBy("has_right_ingredient", has(right.get()))
                .save(exporter, RecipeBuilder.getDefaultRecipeId(item.get()).withPrefix(subSetType == null ? "furniture_station/" : "furniture_station/%s/".formatted(subSetType)))
        ;
    }
}

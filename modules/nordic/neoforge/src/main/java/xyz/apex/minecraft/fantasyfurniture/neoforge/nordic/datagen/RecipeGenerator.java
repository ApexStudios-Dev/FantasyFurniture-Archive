package xyz.apex.minecraft.fantasyfurniture.neoforge.nordic.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.fantasyfurniture.common.nordic.NordicBlocks;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipeBuilder;

import java.util.function.Consumer;

@ApiStatus.Internal
final class RecipeGenerator extends RecipeProvider
{
    RecipeGenerator(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer)
    {
        nordicStation(writer, NordicBlocks.WOOL);
    }

    private void nordicStation(Consumer<FinishedRecipe> writer, ItemLike result)
    {
        FurnitureStationRecipeBuilder.builder(RecipeCategory.DECORATIONS, Ingredient.of(Items.OAK_PLANKS), Ingredient.of(Items.BROWN_WOOL), result)
                .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS))
                .unlockedBy("has_brown_wool", has(Items.BROWN_WOOL))
                .save(writer, RecipeBuilder.getDefaultRecipeId(result).withPrefix("furniture_station/"));
    }
}

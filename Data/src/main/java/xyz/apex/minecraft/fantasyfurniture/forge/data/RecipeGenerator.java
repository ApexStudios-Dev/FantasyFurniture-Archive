package xyz.apex.minecraft.fantasyfurniture.forge.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import xyz.apex.minecraft.fantasyfurniture.forge.FurnitureStationRecipe;
import xyz.apex.minecraft.fantasyfurniture.forge.Nordic;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class RecipeGenerator extends RecipeProvider
{
    public RecipeGenerator(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        nordicStation(consumer, Nordic.WOOL);
        nordicStation(consumer, Nordic.CARPET);
        nordicStation(consumer, Nordic.WALL_LIGHT);
        nordicStation(consumer, Nordic.FLOOR_LIGHT);
        nordicStation(consumer, Nordic.TABLE_LARGE);
        nordicStation(consumer, Nordic.TABLE_WIDE);
        nordicStation(consumer, Nordic.TABLE_SMALL);
        nordicStation(consumer, Nordic.BENCH);
        nordicStation(consumer, Nordic.CHAIR);
        nordicStation(consumer, Nordic.CHANDELIER);
        nordicStation(consumer, Nordic.CUSHION);
        nordicStation(consumer, Nordic.STOOL);
        nordicStation(consumer, Nordic.CHEST);
        nordicStation(consumer, Nordic.BOOKSHELF);
        nordicStation(consumer, Nordic.DESK_LEFT);
        nordicStation(consumer, Nordic.DESK_RIGHT);
        nordicStation(consumer, Nordic.DRAWER);
        nordicStation(consumer, Nordic.DRESSER);
        nordicStation(consumer, Nordic.LOCKBOX);
        nordicStation(consumer, Nordic.WARDROBE_BOTTOM);
        nordicStation(consumer, Nordic.WARDROBE_TOP);
    }

    private void nordicStation(Consumer<FinishedRecipe> exporter, Supplier<? extends ItemLike> item)
    {
        furnitureStationGeneric(exporter, Nordic.NAME, () -> Blocks.OAK_PLANKS, () -> Blocks.BROWN_WOOL, item);
    }

    private void furnitureStationGeneric(Consumer<FinishedRecipe> exporter, String furnitureSet, Supplier<? extends ItemLike> left, Supplier<? extends ItemLike> right, Supplier<? extends ItemLike> item)
    {
        FurnitureStationRecipe.clayBound(RecipeCategory.MISC, Ingredient.of(left.get()), Ingredient.of(right.get()), item.get())
                              .unlockedBy("has_left_ingredient", RecipeProvider.has(left.get()))
                              .unlockedBy("has_right_ingredient", RecipeProvider.has(right.get()))
                              .group(furnitureSet)
                              .save(exporter, RecipeBuilder.getDefaultRecipeId(item.get()).withPrefix("furniture_station/%s/".formatted(furnitureSet)))
        ;
    }
}

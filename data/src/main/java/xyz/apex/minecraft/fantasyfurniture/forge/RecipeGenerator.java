package xyz.apex.minecraft.fantasyfurniture.forge;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

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
        furnitureStation(consumer);

        nordic(consumer);
        venthyr(consumer);
        dunmer(consumer);
    }

    private void nordic(Consumer<FinishedRecipe> consumer)
    {
        nordicStation(consumer, NordicSet.WOOL);
        nordicStation(consumer, NordicSet.CARPET);
        nordicStation(consumer, NordicSet.WALL_LIGHT);
        nordicStation(consumer, NordicSet.FLOOR_LIGHT);
        nordicStation(consumer, NordicSet.TABLE_LARGE);
        nordicStation(consumer, NordicSet.TABLE_WIDE);
        nordicStation(consumer, NordicSet.TABLE_SMALL);
        nordicStation(consumer, NordicSet.BENCH);
        nordicStation(consumer, NordicSet.CHAIR);
        nordicStation(consumer, NordicSet.CHANDELIER);
        nordicStation(consumer, NordicSet.CUSHION);
        nordicStation(consumer, NordicSet.STOOL);
        nordicStation(consumer, NordicSet.CHEST);
        nordicStation(consumer, NordicSet.BOOKSHELF);
        nordicStation(consumer, NordicSet.DESK_LEFT);
        nordicStation(consumer, NordicSet.DESK_RIGHT);
        nordicStation(consumer, NordicSet.DRAWER);
        nordicStation(consumer, NordicSet.DRESSER);
        nordicStation(consumer, NordicSet.LOCKBOX);
        nordicStation(consumer, NordicSet.WARDROBE_BOTTOM);
        nordicStation(consumer, NordicSet.WARDROBE_TOP);
        nordicStation(consumer, NordicSet.PAINTING_WIDE);
        nordicStation(consumer, NordicSet.PAINTING_SMALL);
        nordicStation(consumer, NordicSet.OVEN);
        nordicStation(consumer, NordicSet.DOOR_DOUBLE);
        nordicStation(consumer, NordicSet.DOOR_SINGLE);
        nordicStation(consumer, NordicSet.BED_SINGLE);
        nordicStation(consumer, NordicSet.BED_DOUBLE);
        nordicStation(consumer, NordicSet.SHELF);
        nordicStation(consumer, NordicSet.SOFA);
        nordicStation(consumer, NordicSet.COUNTER);
    }

    private void venthyr(Consumer<FinishedRecipe> consumer)
    {
        venthyrStation(consumer, VenthyrSet.WOOL);
        venthyrStation(consumer, VenthyrSet.CARPET);
        venthyrStation(consumer, VenthyrSet.WALL_LIGHT);
        venthyrStation(consumer, VenthyrSet.FLOOR_LIGHT);
        venthyrStation(consumer, VenthyrSet.TABLE_LARGE);
        venthyrStation(consumer, VenthyrSet.TABLE_LARGE_FANCY);
        venthyrStation(consumer, VenthyrSet.TABLE_WIDE);
        venthyrStation(consumer, VenthyrSet.TABLE_WIDE_FANCY);
        venthyrStation(consumer, VenthyrSet.TABLE_SMALL);
        venthyrStation(consumer, VenthyrSet.TABLE_SMALL_FANCY);
        venthyrStation(consumer, VenthyrSet.BENCH);
        venthyrStation(consumer, VenthyrSet.CHAIR);
        venthyrStation(consumer, VenthyrSet.CHANDELIER);
        venthyrStation(consumer, VenthyrSet.CUSHION);
        venthyrStation(consumer, VenthyrSet.STOOL);
        venthyrStation(consumer, VenthyrSet.CHEST);
        venthyrStation(consumer, VenthyrSet.BOOKSHELF);
        venthyrStation(consumer, VenthyrSet.DESK_LEFT);
        venthyrStation(consumer, VenthyrSet.DESK_RIGHT);
        venthyrStation(consumer, VenthyrSet.DRAWER);
        venthyrStation(consumer, VenthyrSet.DRESSER);
        venthyrStation(consumer, VenthyrSet.LOCKBOX);
        venthyrStation(consumer, VenthyrSet.WARDROBE_BOTTOM);
        venthyrStation(consumer, VenthyrSet.WARDROBE_TOP);
        venthyrStation(consumer, VenthyrSet.PAINTING_WIDE);
        venthyrStation(consumer, VenthyrSet.PAINTING_SMALL);
        venthyrStation(consumer, VenthyrSet.OVEN);
        venthyrStation(consumer, VenthyrSet.DOOR_DOUBLE);
        venthyrStation(consumer, VenthyrSet.DOOR_SINGLE);
        venthyrStation(consumer, VenthyrSet.BED_SINGLE);
        venthyrStation(consumer, VenthyrSet.BED_DOUBLE);
        venthyrStation(consumer, VenthyrSet.SHELF);
        venthyrStation(consumer, VenthyrSet.SOFA);
        venthyrStation(consumer, VenthyrSet.COUNTER);
    }

    private void dunmer(Consumer<FinishedRecipe> consumer)
    {
        dunmerStation(consumer, DunmerSet.WOOL);
        dunmerStation(consumer, DunmerSet.CARPET);
        dunmerStation(consumer, DunmerSet.WALL_LIGHT);
        dunmerStation(consumer, DunmerSet.FLOOR_LIGHT);
        dunmerStation(consumer, DunmerSet.TABLE_LARGE);
        dunmerStation(consumer, DunmerSet.TABLE_WIDE);
        dunmerStation(consumer, DunmerSet.TABLE_SMALL);
        dunmerStation(consumer, DunmerSet.BENCH);
        dunmerStation(consumer, DunmerSet.CHAIR);
        dunmerStation(consumer, DunmerSet.CHANDELIER);
        dunmerStation(consumer, DunmerSet.CUSHION);
        dunmerStation(consumer, DunmerSet.STOOL);
        dunmerStation(consumer, DunmerSet.CHEST);
        dunmerStation(consumer, DunmerSet.BOOKSHELF);
        dunmerStation(consumer, DunmerSet.DESK_LEFT);
        dunmerStation(consumer, DunmerSet.DESK_RIGHT);
        dunmerStation(consumer, DunmerSet.DRAWER);
        dunmerStation(consumer, DunmerSet.DRESSER);
        dunmerStation(consumer, DunmerSet.LOCKBOX);
        dunmerStation(consumer, DunmerSet.WARDROBE_BOTTOM);
        dunmerStation(consumer, DunmerSet.WARDROBE_TOP);
        dunmerStation(consumer, DunmerSet.PAINTING_WIDE);
        dunmerStation(consumer, DunmerSet.PAINTING_SMALL);
        dunmerStation(consumer, DunmerSet.OVEN);
        dunmerStation(consumer, DunmerSet.DOOR_DOUBLE);
        dunmerStation(consumer, DunmerSet.DOOR_SINGLE);
        dunmerStation(consumer, DunmerSet.BED_SINGLE);
        dunmerStation(consumer, DunmerSet.BED_DOUBLE);
        dunmerStation(consumer, DunmerSet.SHELF);
        dunmerStation(consumer, DunmerSet.SOFA);
        dunmerStation(consumer, DunmerSet.COUNTER);
    }

    private void furnitureStation(Consumer<FinishedRecipe> exporter)
    {
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.CRAFTING_TABLE), Ingredient.of(Items.LEATHER), RecipeCategory.MISC, FantasyFurniture.FURNITURE_STATION_BLOCK.get().asItem())
                            .unlocks("has_crafting_table", RecipeProvider.has(Items.CRAFTING_TABLE))
                            .unlocks("has_leather", RecipeProvider.has(Items.LEATHER))
                            .save(exporter, RecipeBuilder.getDefaultRecipeId(FantasyFurniture.FURNITURE_STATION_BLOCK.get()))
        ;
    }

    private void nordicStation(Consumer<FinishedRecipe> exporter, Supplier<? extends ItemLike> item)
    {
        furnitureStationGeneric(exporter, NordicSet.NAME, () -> Blocks.OAK_PLANKS, () -> Blocks.BROWN_WOOL, item);
    }

    private void venthyrStation(Consumer<FinishedRecipe> exporter, Supplier<? extends ItemLike> item)
    {
        furnitureStationGeneric(exporter, VenthyrSet.NAME, () -> Blocks.SPRUCE_PLANKS, () -> Blocks.RED_WOOL, item);
    }

    private void dunmerStation(Consumer<FinishedRecipe> exporter, Supplier<? extends ItemLike> item)
    {
        furnitureStationGeneric(exporter, DunmerSet.NAME, () -> Blocks.DARK_OAK_PLANKS, () -> Blocks.GREEN_WOOL, item);
    }

    private void furnitureStationGeneric(Consumer<FinishedRecipe> exporter, String furnitureSet, Supplier<? extends ItemLike> left, Supplier<? extends ItemLike> right, Supplier<? extends ItemLike> item)
    {
        FurnitureStationRecipe.clayBound(RecipeCategory.MISC, Ingredient.of(left.get()), Ingredient.of(right.get()), item.get())
                              .unlockedBy("has_left_ingredient", RecipeProvider.has(left.get()))
                              .unlockedBy("has_right_ingredient", RecipeProvider.has(right.get()))
                              .save(exporter, RecipeBuilder.getDefaultRecipeId(item.get()).withPath(path -> "furniture_station/%s".formatted(StringUtils.prependIfMissingIgnoreCase(path, furnitureSet))))
        ;
    }
}

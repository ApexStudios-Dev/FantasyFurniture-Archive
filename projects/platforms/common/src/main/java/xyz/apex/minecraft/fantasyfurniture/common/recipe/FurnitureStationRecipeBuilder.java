package xyz.apex.minecraft.fantasyfurniture.common.recipe;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Map;

public final class FurnitureStationRecipeBuilder implements RecipeBuilder
{
    private final RecipeCategory category;
    @Nullable private String group = null;
    private final Ingredient ingredientA;
    private final Ingredient ingredientB;
    private final ItemLike result;
    private final int count;
    private final Map<String, Criterion<?>> criteriaMap = Maps.newLinkedHashMap();

    FurnitureStationRecipeBuilder(RecipeCategory category, Ingredient ingredientA, Ingredient ingredientB, ItemLike result, int count)
    {
        this.category = category;
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.result = result;
        this.count = count;
    }

    @Override
    public FurnitureStationRecipeBuilder unlockedBy(String criterionName, Criterion<?> criterionTrigger)
    {
        criteriaMap.put(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public FurnitureStationRecipeBuilder group(@Nullable String group)
    {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult()
    {
        return result.asItem();
    }

    @Override
    public void save(RecipeOutput writer, ResourceLocation recipeId)
    {
        var advancement = writer
                .advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);

        criteriaMap.forEach(advancement::addCriterion);

        writer.accept(new FinishedRecipeImpl(
                recipeId,
                new FurnitureStationRecipe(group, ingredientA, ingredientB, result, count),
                advancement.build(recipeId.withPrefix("recipes/%s/".formatted(category.getFolderName())))
        ));
    }

    private record FinishedRecipeImpl(ResourceLocation recipeId, FurnitureStationRecipe recipe, AdvancementHolder advancement) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json)
        {
            FurnitureStationRecipe.toJson(json, recipe);
        }

        @Override
        public ResourceLocation id()
        {
            return recipeId;
        }

        @Override
        public RecipeSerializer<?> type()
        {
            return FantasyFurniture.FURNITURE_STATION_RECIPE.value();
        }
    }
}

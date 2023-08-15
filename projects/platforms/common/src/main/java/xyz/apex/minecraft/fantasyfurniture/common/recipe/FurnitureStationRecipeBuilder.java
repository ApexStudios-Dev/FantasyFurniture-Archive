package xyz.apex.minecraft.fantasyfurniture.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

public final class FurnitureStationRecipeBuilder implements RecipeBuilder
{
    private final RecipeCategory category;
    @Nullable private String group = null;
    private final Ingredient ingredientA;
    private final Ingredient ingredientB;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    FurnitureStationRecipeBuilder(RecipeCategory category, Ingredient ingredientA, Ingredient ingredientB, ItemLike result, int count)
    {
        this.category = category;
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.result = result;
        this.count = count;
    }

    @Override
    public FurnitureStationRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger)
    {
        advancement.addCriterion(criterionName, criterionTrigger);
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
    public void save(Consumer<FinishedRecipe> writer, ResourceLocation recipeId)
    {
        advancement.parent(ROOT_RECIPE_ADVANCEMENT)
                   .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                   .rewards(AdvancementRewards.Builder.recipe(recipeId))
                   .requirements(RequirementsStrategy.OR);

        var advancementId = recipeId.withPrefix("recipes/%s/".formatted(category.getFolderName()));
        var recipe = new FurnitureStationRecipe(recipeId, group, ingredientA, ingredientB, result, count);
        writer.accept(new FinishedRecipeImpl(recipe, advancementId, advancement));
    }

    private record FinishedRecipeImpl(FurnitureStationRecipe recipe, ResourceLocation advancementId, Advancement.Builder advancement) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json)
        {
            FurnitureStationRecipe.toJson(json, recipe);
        }

        @Override
        public ResourceLocation getId()
        {
            return recipe.getId();
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return FantasyFurniture.FURNITURE_STATION_RECIPE.value();
        }

        @Override
        public JsonObject serializeAdvancement()
        {
            return advancement.serializeToJson();
        }

        @Override
        public ResourceLocation getAdvancementId()
        {
            return advancementId;
        }
    }
}

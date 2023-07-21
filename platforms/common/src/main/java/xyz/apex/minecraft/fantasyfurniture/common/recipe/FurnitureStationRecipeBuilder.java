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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class FurnitureStationRecipeBuilder implements RecipeBuilder
{
    private final RecipeCategory recipeCategory;
    private final Ingredient inputLeft;
    private final Ingredient inputRight;
    @Nullable private Ingredient bindingAgent = null;
    private final ItemLike result;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable private String group = null;

    private FurnitureStationRecipeBuilder(RecipeCategory recipeCategory, Ingredient inputLeft, Ingredient inputRight, ItemLike result, int count)
    {
        this.recipeCategory = recipeCategory;
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
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

    public FurnitureStationRecipeBuilder bindingAgent(Ingredient bindingAgent)
    {
        this.bindingAgent = bindingAgent;
        return this;
    }

    public FurnitureStationRecipeBuilder bindingAgent(TagKey<Item> bindingAgentTag)
    {
        return bindingAgent(Ingredient.of(bindingAgentTag));
    }

    public FurnitureStationRecipeBuilder bindingAgent(ItemLike... bindingAgents)
    {
        return bindingAgent(Ingredient.of(bindingAgents));
    }

    public FurnitureStationRecipeBuilder bindingAgent(ItemStack... bindingAgents)
    {
        return bindingAgent(Ingredient.of(bindingAgents));
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

        var advancementId = recipeId.withPrefix("recipes/%s/".formatted(recipeCategory.getFolderName()));
        var recipe = new FurnitureStationRecipe(recipeId, inputLeft, inputRight, bindingAgent, result, count, group);
        writer.accept(new FinishedRecipeImpl(advancementId, recipe, advancement));
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory recipeCategory, Ingredient inputLeft, Ingredient inputRight, ItemLike result, int count)
    {
        return new FurnitureStationRecipeBuilder(recipeCategory, inputLeft, inputRight, result, count);
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory recipeCategory, Ingredient inputLeft, Ingredient inputRight, ItemLike result)
    {
        return builder(recipeCategory, inputLeft, inputRight, result, 1);
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory recipeCategory, Ingredient inputLeft, Ingredient inputRight, ItemStack result)
    {
        return builder(recipeCategory, inputLeft, inputRight, result.getItem(), result.getCount());
    }

    private record FinishedRecipeImpl(ResourceLocation advancementId, FurnitureStationRecipe recipe, Advancement.Builder advancement) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json)
        {
            FurnitureStationRecipe.Serializer.getInstance().toJson(json, recipe);
        }

        @Override
        public ResourceLocation getId()
        {
            return recipe.getId();
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return recipe.getSerializer();
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

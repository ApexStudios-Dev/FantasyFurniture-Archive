package xyz.apex.minecraft.fantasyfurniture.shared.recipe;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.FurnitureStationMenu;

import java.util.function.Consumer;

public final class FurnitureStationRecipe implements Recipe<Container>
{
    private final ResourceLocation recipeId;
    private final String group;
    private final Ingredient ingredientLeft;
    private final Ingredient ingredientRight;
    private final Ingredient bindingAgent;
    private final ItemLike result;
    private final ItemStack cachedResult;
    private final String furnitureSet;

    private FurnitureStationRecipe(ResourceLocation recipeId, String group, String furnitureSet, Ingredient ingredientLeft, Ingredient ingredientRight, Ingredient bindingAgent, ItemLike result)
    {
        this.recipeId = recipeId;
        this.group = group;
        this.furnitureSet = furnitureSet;
        this.ingredientLeft = ingredientLeft;
        this.ingredientRight = ingredientRight;
        this.bindingAgent = bindingAgent;
        this.result = result;

        cachedResult = result.asItem().getDefaultInstance();
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if(container.getContainerSize() >= FurnitureStationMenu.SLOT_INGREDIENT_COUNT)
        {
            var inputLeft = container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_LEFT);
            var inputRight = container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_RIGHT);
            var inputBinding = container.getItem(FurnitureStationMenu.SLOT_BINDING_AGENT);

            return ingredientLeft.test(inputLeft) && ingredientRight.test(inputRight) && bindingAgent.test(inputBinding);
        }

        return false;
    }

    @Override
    public ItemStack assemble(Container container)
    {
        if(container.getContainerSize() >= FurnitureStationMenu.SLOT_INGREDIENT_COUNT)
        {
            if(!ingredientLeft.test(container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_LEFT))) return ItemStack.EMPTY;
            if(!ingredientRight.test(container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_RIGHT))) return ItemStack.EMPTY;
            if(!bindingAgent.test(container.getItem(FurnitureStationMenu.SLOT_BINDING_AGENT))) return ItemStack.EMPTY;

            return new ItemStack(result);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width * height >= FurnitureStationMenu.SLOT_INGREDIENT_COUNT;
    }

    @Override
    public ItemStack getResultItem()
    {
        return cachedResult;
    }

    @Override
    public ResourceLocation getId()
    {
        return recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return FantasyFurniture.FURNITURE_STATION_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return FantasyFurniture.FURNITURE_STATION_RECIPE.asRecipeType();
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.of(Ingredient.EMPTY, ingredientLeft, ingredientRight, bindingAgent);
    }

    @Override
    public String getGroup()
    {
        return group;
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return FantasyFurniture.FURNITURE_STATION_BLOCK.asStack();
    }

    public String getFurnitureSet()
    {
        return furnitureSet;
    }

    public static Builder builder(RecipeCategory category, String furnitureSet, Ingredient left, Ingredient right, Ingredient bindingAgent, ItemLike result)
    {
        return new Builder(category, furnitureSet, left, right, bindingAgent, result);
    }

    public static Builder clayBound(RecipeCategory category, String furnitureSet, Ingredient left, Ingredient right, ItemLike result)
    {
        return builder(category, furnitureSet, left, right, Ingredient.of(Items.CLAY_BALL), result);
    }

    public static final class Serializer implements RecipeSerializer<FurnitureStationRecipe>
    {
        @Override
        public FurnitureStationRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            var group = GsonHelper.getAsString(json, "group", "");
            var ingredientLeft = getIngredient(json, "left");
            var ingredientRight = getIngredient(json, "right");
            var bindingAgent = getIngredient(json, "binding_agent");
            var result = BuiltInRegistries.ITEM.get(new ResourceLocation(GsonHelper.getAsString(json, "result")));
            var furnitureSet = GsonHelper.getAsString(json, "furniture_set");
            return new FurnitureStationRecipe(recipeId, group, furnitureSet, ingredientLeft, ingredientRight, bindingAgent, result);
        }

        @Override
        public FurnitureStationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf data)
        {
            var group = data.readUtf();
            var ingredientLeft = Ingredient.fromNetwork(data);
            var ingredientRight = Ingredient.fromNetwork(data);
            var bindingAgent = Ingredient.fromNetwork(data);
            var result = BuiltInRegistries.ITEM.get(data.readResourceLocation());
            var furnitureSet = data.readUtf();
            return new FurnitureStationRecipe(recipeId, group, furnitureSet, ingredientLeft, ingredientRight, bindingAgent, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf data, FurnitureStationRecipe recipe)
        {
            data.writeUtf(recipe.group);
            recipe.ingredientLeft.toNetwork(data);
            recipe.ingredientRight.toNetwork(data);
            recipe.bindingAgent.toNetwork(data);
            data.writeResourceLocation(BuiltInRegistries.ITEM.getKey(recipe.result.asItem()));
            data.writeUtf(recipe.furnitureSet);
        }

        private Ingredient getIngredient(JsonObject root, String key)
        {
            return Ingredient.fromJson(GsonHelper.isArrayNode(root, key) ? GsonHelper.getAsJsonArray(root, key) : GsonHelper.getAsJsonObject(root, key));
        }
    }

    public static final class Builder implements RecipeBuilder
    {
        private final RecipeCategory category;
        private final Ingredient ingredientLeft;
        private final Ingredient ingredientRight;
        private final Ingredient bindingAgent;
        private final ItemLike result;
        private final Advancement.Builder advancement = Advancement.Builder.advancement();
        @Nullable private String group;
        private final String furnitureSet;

        private Builder(RecipeCategory category, String furnitureSet, Ingredient ingredientLeft, Ingredient ingredientRight, Ingredient bindingAgent, ItemLike result)
        {
            this.category = category;
            this.ingredientLeft = ingredientLeft;
            this.ingredientRight = ingredientRight;
            this.bindingAgent = bindingAgent;
            this.result = result;
            this.furnitureSet = furnitureSet;
        }

        @Override
        public Builder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger)
        {
            advancement.addCriterion(criterionName, criterionTrigger);
            return this;
        }

        @Override
        public Builder group(@Nullable String group)
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
        public void save(Consumer<FinishedRecipe> exporter, ResourceLocation recipeId)
        {
            advancement.parent(ROOT_RECIPE_ADVANCEMENT)
                       .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                       .rewards(AdvancementRewards.Builder.recipe(recipeId))
                       .requirements(RequirementsStrategy.OR)
            ;

            exporter.accept(new CompiledRecipe(category, recipeId, advancement, furnitureSet, group, ingredientLeft, ingredientRight, bindingAgent, result));
        }
    }

    private static final class CompiledRecipe implements FinishedRecipe
    {
        private final ResourceLocation recipeId;
        private final ResourceLocation advancementId;
        private final Ingredient ingredientLeft;
        private final Ingredient ingredientRight;
        private final Ingredient bindingAgent;
        private final ItemLike result;
        private final Advancement.Builder advancement;
        @Nullable private final String group;
        private final String furnitureSet;

        private CompiledRecipe(RecipeCategory category, ResourceLocation recipeId, Advancement.Builder advancement, String furnitureSet, @Nullable String group, Ingredient ingredientLeft, Ingredient ingredientRight, Ingredient bindingAgent, ItemLike result)
        {
            this.recipeId = recipeId;
            this.advancement = advancement;
            this.group = group;
            this.ingredientLeft = ingredientLeft;
            this.ingredientRight = ingredientRight;
            this.bindingAgent = bindingAgent;
            this.result = result;
            this.furnitureSet = furnitureSet;

            advancementId = recipeId.withPrefix("recipes/%s/".formatted(category.getFolderName()));
        }

        @Override
        public void serializeRecipeData(JsonObject json)
        {
            if(group != null) json.addProperty("group", group);
            json.add("left", ingredientLeft.toJson());
            json.add("right", ingredientRight.toJson());
            json.add("binding_agent", bindingAgent.toJson());
            json.addProperty("result", BuiltInRegistries.ITEM.getKey(result.asItem()).toString());
            json.addProperty("furniture_set", furnitureSet);
        }

        @Override
        public ResourceLocation getId()
        {
            return recipeId;
        }

        @Override
        public RecipeSerializer<?> getType()
        {
            return FantasyFurniture.FURNITURE_STATION_RECIPE.get();
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

package xyz.apex.minecraft.fantasyfurniture.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

import java.util.Objects;

public final class FurnitureStationRecipe implements Recipe<Container>
{
    private static final String JSON_GROUP = "group";
    private static final String JSON_INGREDIENT_A = "ingredient_a";
    private static final String JSON_INGREDIENT_B = "ingredient_b";
    private static final String JSON_RESULT = "result";
    private static final String JSON_ITEM = "item";
    private static final String JSON_COUNT = "count";

    private final ResourceLocation recipeId;
    @Nullable private final String group;
    private final Ingredient ingredientA;
    private final Ingredient ingredientB;
    private final ItemLike result;
    private final int count;

    FurnitureStationRecipe(ResourceLocation recipeId, @Nullable String group, Ingredient ingredientA, Ingredient ingredientB, ItemLike result, int count)
    {
        this.recipeId = recipeId;
        this.group = group;
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.result = result;
        this.count = count;
    }

    private boolean matches(Container container)
    {
        var inputA = container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_A);
        var inputB = container.getItem(FurnitureStationMenu.SLOT_INGREDIENT_B);
        var bindingAgent = container.getItem(FurnitureStationMenu.SLOT_BINDING_AGENT);

        return ingredientA.test(inputA) && ingredientB.test(inputB) && bindingAgent.is(FantasyFurniture.FURNITURE_STATION_BINDING_AGENT);
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return FantasyFurniture.FURNITURE_STATION_BLOCK.asStack();
    }

    @Override
    public String getGroup()
    {
        return Objects.requireNonNullElse(group, "");
    }

    @Override
    public boolean isIncomplete()
    {
        return ingredientA.isEmpty() || ingredientB.isEmpty();
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        var ingredients = NonNullList.<Ingredient>create();
        ingredients.add(ingredientA);
        ingredients.add(ingredientB);
        return ingredients;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        return matches(container);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess)
    {
        return matches(container) ? getResultItem(registryAccess) : ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width * height >= 3;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess)
    {
        var result = this.result.asItem().getDefaultInstance();
        result.setCount(count);
        return result;
    }

    @Override
    public ResourceLocation getId()
    {
        return recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return FantasyFurniture.FURNITURE_STATION_RECIPE.value();
    }

    @Override
    public RecipeType<?> getType()
    {
        return FantasyFurniture.FURNITURE_STATION_RECIPE;
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient ingredientA, Ingredient ingredientB, ItemLike result, int count)
    {
        return new FurnitureStationRecipeBuilder(category, ingredientA, ingredientB, result, count);
    }

    public static FurnitureStationRecipeBuilder builder(RecipeCategory category, Ingredient ingredientA, Ingredient ingredientB, ItemLike result)
    {
        return builder(category, ingredientA, ingredientB, result, 1);
    }

    public static FurnitureStationRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        var group = GsonHelper.isStringValue(json, JSON_GROUP) ? GsonHelper.getAsString(json, JSON_GROUP) : null;
        var ingredientA = ingredientFromJson(json, JSON_INGREDIENT_A);
        var ingredientB = ingredientFromJson(json, JSON_INGREDIENT_B);

        ResourceLocation itemName;
        int itemCount = 1;

        if(GsonHelper.isStringValue(json, JSON_RESULT))
            itemName = new ResourceLocation(GsonHelper.getAsString(json, JSON_RESULT));
        else
        {
            var resultJson = GsonHelper.getAsJsonObject(json, JSON_RESULT);
            itemName = new ResourceLocation(GsonHelper.getAsString(resultJson, JSON_ITEM));
            itemCount = GsonHelper.getAsInt(resultJson, JSON_COUNT, 1);
        }

        var result = BuiltInRegistries.ITEM.get(itemName);

        return new FurnitureStationRecipe(recipeId, group, ingredientA, ingredientB, result, itemCount);
    }

    public static void toJson(JsonObject json, FurnitureStationRecipe recipe)
    {
        if(recipe.group != null)
            json.addProperty(JSON_GROUP, recipe.group);

        json.add(JSON_INGREDIENT_A, recipe.ingredientA.toJson());
        json.add(JSON_INGREDIENT_B, recipe.ingredientB.toJson());

        var itemName = BuiltInRegistries.ITEM.getKey(recipe.result.asItem()).toString();

        if(recipe.count > 1)
        {
            var resultJson = new JsonObject();
            json.addProperty(JSON_ITEM, itemName);
            json.addProperty(JSON_COUNT, recipe.count);
            json.add(JSON_RESULT, resultJson);
        }
        else
            json.addProperty(JSON_RESULT, itemName);
    }

    public static FurnitureStationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
    {
        var group = buffer.readBoolean() ? buffer.readUtf() : null;
        var ingredientA = Ingredient.fromNetwork(buffer);
        var ingredientB = Ingredient.fromNetwork(buffer);
        var result = BuiltInRegistries.ITEM.get(buffer.readResourceLocation());
        var count = buffer.readBoolean() ? buffer.readVarInt() : 1;

        return new FurnitureStationRecipe(recipeId, group, ingredientA, ingredientB, result, count);
    }

    public static void toNetwork(FriendlyByteBuf buffer, FurnitureStationRecipe recipe)
    {
        buffer.writeBoolean(recipe.group != null);

        if(recipe.group != null)
            buffer.writeUtf(recipe.group);

        recipe.ingredientA.toNetwork(buffer);
        recipe.ingredientB.toNetwork(buffer);
        buffer.writeResourceLocation(BuiltInRegistries.ITEM.getKey(recipe.result.asItem()));

        buffer.writeBoolean(recipe.count > 1);

        if(recipe.count > 1)
            buffer.writeVarInt(recipe.count);
    }

    private static Ingredient ingredientFromJson(JsonObject json, String key)
    {
        var ingredientJson = GsonHelper.isArrayNode(json, key) ? GsonHelper.getAsJsonArray(json, key) : GsonHelper.getAsJsonObject(json, key);
        return Ingredient.fromJson(ingredientJson, false);
    }
}

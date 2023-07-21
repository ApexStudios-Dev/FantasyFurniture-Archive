package xyz.apex.minecraft.fantasyfurniture.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureStation;

public final class FurnitureStationRecipe implements Recipe<Container>
{
    private static final String KEY_GROUP = "group";
    private static final String KEY_INPUT_LEFT = "input_left";
    private static final String KEY_INPUT_RIGHT = "input_right";
    private static final String KEY_BINDING_AGENT = "binding_agent";
    private static final String KEY_RESULT = "result";
    private static final String KEY_ITEM = "item";
    private static final String KEY_COUNT = "count";

    private final ResourceLocation recipeId;
    private final Ingredient inputLeft;
    private final Ingredient inputRight;
    private final Ingredient bindingAgent;
    private final ItemLike result;
    private final int count;
    private final String group;

    FurnitureStationRecipe(ResourceLocation recipeId, Ingredient inputLeft, Ingredient inputRight, @Nullable Ingredient bindingAgent, ItemLike result, int count, @Nullable String group)
    {
        this.recipeId = recipeId;
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
        this.bindingAgent = bindingAgent == null ? Ingredient.EMPTY : bindingAgent;
        this.result = result;
        this.count = count;
        this.group = group == null ? "" : group;
    }

    private boolean matches(Container container)
    {
        if(container.getContainerSize() < FurnitureStation.INPUT_SLOTS)
            return false;

        var left = container.getItem(FurnitureStation.INPUT_SLOT_LEFT);
        var right = container.getItem(FurnitureStation.INPUT_SLOT_RIGHT);
        var bindingAgent = container.getItem(FurnitureStation.INPUT_SLOT_BINDING_AGENT);

        // this makes left & right slots "shapeless"
        // meaning that you can put "left" items in "right" slot and vice versa
        // but if "left" or "right" input item is in both slots it fails
        var hasLeftL = inputLeft.test(left);
        var hasLeftR = inputLeft.test(right);
        var hasRightL = inputRight.test(left);
        var hasRightR = inputRight.test(right);
        // "left" input item in either left or right slot (not both at same time)
        var hasLeft = (hasLeftL && !hasRightL) || (hasRightL && !hasLeftL);
        // "right" input item in either left or right slot (not both at same time)
        var hasRight = (hasRightR && !hasLeftR) || (hasLeftR && !hasRightR);

        return hasLeft && hasRight && FurnitureStation.isValidBindingAgent(this, bindingAgent);
    }

    public Ingredient getBindingAgent()
    {
        return bindingAgent;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        if(!matches(container))
            return false;

        return result.asItem().isEnabled(level.enabledFeatures());
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess)
    {
        if(!matches(container))
            return ItemStack.EMPTY;

        return getResultItem(registryAccess);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width * height >= FurnitureStation.INPUT_SLOTS;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess)
    {
        return new ItemStack(result, count);
    }

    @Override
    public ResourceLocation getId()
    {
        return recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return FurnitureStation.RECIPE.getRecipeSerializer();
    }

    @Override
    public RecipeType<?> getType()
    {
        return FurnitureStation.RECIPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.of(Ingredient.EMPTY, inputLeft, inputRight, bindingAgent);
    }

    @Override
    public String getGroup()
    {
        return group;
    }

    public static final class Serializer implements RecipeSerializer<FurnitureStationRecipe>
    {
        private static final Serializer INSTANCE = new Serializer();

        private Serializer()
        {
        }

        @Override
        public FurnitureStationRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            var group = GsonHelper.getAsString(json, KEY_GROUP, "");
            var inputLeft = ingredientFromJson(json, KEY_INPUT_LEFT);
            var inputRight = ingredientFromJson(json, KEY_INPUT_RIGHT);
            var bindingAgent = json.has(KEY_BINDING_AGENT) ? ingredientFromJson(json, KEY_BINDING_AGENT) : Ingredient.EMPTY;

            ResourceLocation resultName = null;
            int resultCount = 1;

            if(GsonHelper.isStringValue(json, KEY_RESULT))
                resultName = new ResourceLocation(GsonHelper.getAsString(json, KEY_RESULT));
            else if(GsonHelper.isObjectNode(json, KEY_RESULT))
            {
                var resultJson = GsonHelper.getAsJsonObject(json, KEY_RESULT);
                resultName = new ResourceLocation(GsonHelper.getAsString(json, KEY_ITEM));
                resultCount = Math.max(resultCount, GsonHelper.getAsInt(resultJson, KEY_COUNT, resultCount));
            }

            var result = resultName == null ? Items.AIR : BuiltInRegistries.ITEM.get(resultName);
            return new FurnitureStationRecipe(recipeId, inputLeft, inputRight, bindingAgent, result, resultCount, group);
        }

        public void toJson(JsonObject json, FurnitureStationRecipe recipe)
        {
            json.add(KEY_INPUT_LEFT, recipe.inputLeft.toJson());
            json.add(KEY_INPUT_RIGHT, recipe.inputRight.toJson());

            if(!recipe.group.isBlank())
                json.addProperty(KEY_GROUP, recipe.group);
            if(!recipe.bindingAgent.isEmpty())
                json.add(KEY_BINDING_AGENT, recipe.bindingAgent.toJson());

            var registryName = BuiltInRegistries.ITEM.getKey(recipe.result.asItem()).toString();

            if(recipe.count > 1)
            {
                var resultJson = new JsonObject();
                resultJson.addProperty(KEY_ITEM, registryName);
                resultJson.addProperty(KEY_COUNT, recipe.count);
                json.add(KEY_RESULT, resultJson);
            }
            else
                json.addProperty(KEY_RESULT, registryName);
        }

        @Override
        public FurnitureStationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
        {
            var group = buffer.readBoolean() ? buffer.readUtf() : "";
            var inputLeft = Ingredient.fromNetwork(buffer);
            var inputRight = Ingredient.fromNetwork(buffer);
            var bindingAgent = buffer.readBoolean() ? Ingredient.fromNetwork(buffer) : Ingredient.EMPTY;
            var count = buffer.readBoolean() ? buffer.readVarInt() : 1;
            var result = BuiltInRegistries.ITEM.get(buffer.readResourceLocation());
            return new FurnitureStationRecipe(recipeId, inputLeft, inputRight, bindingAgent, result, count, group);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FurnitureStationRecipe recipe)
        {
            var flag = !recipe.group.isBlank();
            buffer.writeBoolean(flag);

            if(flag)
                buffer.writeUtf(recipe.group);

            recipe.inputLeft.toNetwork(buffer);
            recipe.inputRight.toNetwork(buffer);

            flag = !recipe.bindingAgent.isEmpty();
            buffer.writeBoolean(flag);

            if(flag)
                recipe.bindingAgent.toNetwork(buffer);

            flag = recipe.count > 1;
            buffer.writeBoolean(flag);

            if(flag)
                buffer.writeVarInt(recipe.count);

            buffer.writeResourceLocation(BuiltInRegistries.ITEM.getKey(recipe.result.asItem()));
        }

        public static Serializer getInstance()
        {
            return INSTANCE;
        }

        private static Ingredient ingredientFromJson(JsonObject root, String key)
        {
            return Ingredient.fromJson(GsonHelper.isArrayNode(root, key) ? GsonHelper.getAsJsonArray(root, key) : GsonHelper.getAsJsonObject(root, key));
        }
    }
}

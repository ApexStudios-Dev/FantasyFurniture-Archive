package xyz.apex.minecraft.fantasyfurniture.common.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
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

    public static final Codec<FurnitureStationRecipe> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    ExtraCodecs.strictOptionalField(Codec.STRING, JSON_GROUP, "").forGetter(FurnitureStationRecipe::getGroup),
                    Ingredient.CODEC_NONEMPTY.fieldOf(JSON_INGREDIENT_A).forGetter(recipe -> recipe.ingredientA),
                    Ingredient.CODEC_NONEMPTY.fieldOf(JSON_INGREDIENT_B).forGetter(recipe -> recipe.ingredientB),
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf(JSON_RESULT).forGetter(recipe -> recipe.result.asItem()),
                    Codec.INT.fieldOf(JSON_COUNT).forGetter(recipe -> recipe.count)
            ).apply(instance, FurnitureStationRecipe::new)
    );

    @Nullable private final String group;
    private final Ingredient ingredientA;
    private final Ingredient ingredientB;
    private final ItemLike result;
    private final int count;

    FurnitureStationRecipe(@Nullable String group, Ingredient ingredientA, Ingredient ingredientB, ItemLike result, int count)
    {
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

    public static void toJson(JsonObject json, FurnitureStationRecipe recipe)
    {
        // why do we now use codecs but FinishedRecipe still wants this legacy method?
        if(recipe.group != null && !recipe.group.isEmpty())
            json.addProperty("group", recipe.group);

        json.add("ingredient_a", recipe.ingredientA.toJson(true));
        json.add("ingredient_b", recipe.ingredientB.toJson(true));
        json.addProperty("result", BuiltInRegistries.ITEM.getKey(recipe.result.asItem()).toString());
        json.addProperty("count", recipe.count);
    }

    public static FurnitureStationRecipe fromNetwork(FriendlyByteBuf buffer)
    {
        var group = buffer.readBoolean() ? buffer.readUtf() : null;
        var ingredientA = Ingredient.fromNetwork(buffer);
        var ingredientB = Ingredient.fromNetwork(buffer);
        var result = BuiltInRegistries.ITEM.get(buffer.readResourceLocation());
        var count = buffer.readBoolean() ? buffer.readVarInt() : 1;

        return new FurnitureStationRecipe(group, ingredientA, ingredientB, result, count);
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
}

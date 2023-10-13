package xyz.apex.minecraft.fantasyfurniture.common.integrations.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.common.recipe.FurnitureStationRecipe;

public final class FurnitureStationRecipeCategory implements IRecipeCategory<RecipeHolder<FurnitureStationRecipe>>
{
    private static final ResourceLocation SPRITE_ARROW_JEI = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/arrow_jei");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slot;

    FurnitureStationRecipeCategory(IJeiHelpers jei)
    {
        var gui = jei.getGuiHelper();

        background = gui.createBlankDrawable(113, 16);
        icon = gui.createDrawableItemStack(FantasyFurniture.FURNITURE_STATION_BLOCK.asStack());
        slot = gui.getSlotDrawable();
    }

    @Override
    public RecipeType<RecipeHolder<FurnitureStationRecipe>> getRecipeType()
    {
        return FurnitureStationJeiPlugin.RECIPE_TYPE.get();
    }

    @Override
    public Component getTitle()
    {
        return FantasyFurniture.FURNITURE_STATION_JEI_NAME;
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<FurnitureStationRecipe> holder, IFocusGroup focuses)
    {
        var recipe = holder.value();
        var ingredients = recipe.getIngredients();
        var client = Minecraft.getInstance();

        builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
                .setBackground(slot, -1, -1)
                .addIngredients(Ingredient.of(FantasyFurniture.FURNITURE_STATION_BINDING_AGENT));

        builder.addSlot(RecipeIngredientRole.INPUT, 26, 0)
                .setBackground(slot, -1, -1)
                .addIngredients(ingredients.get(FurnitureStationMenu.SLOT_INGREDIENT_A));

        builder.addSlot(RecipeIngredientRole.INPUT, 44, 0)
                .setBackground(slot, -1, -1)
                .addIngredients(ingredients.get(FurnitureStationMenu.SLOT_INGREDIENT_B));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 0)
                .setBackground(slot, -1, -1)
                .addItemStack(recipe.getResultItem(client.level.registryAccess()));
    }

    @Override
    public void draw(RecipeHolder<FurnitureStationRecipe> holder, IRecipeSlotsView slotsView, GuiGraphics graphics, double mouseX, double mouseY)
    {
        var client = Minecraft.getInstance();

        graphics.drawString(client.font, "+", 18, 4, 0xFFFFFF);
        graphics.blitSprite(SPRITE_ARROW_JEI, 61, 1, 33, 16);
    }
}

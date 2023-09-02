package xyz.apex.minecraft.fantasyfurniture.common.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.menu.FurnitureStationMenu;

@SideOnly(PhysicalSide.CLIENT)
public final class FurnitureStationMenuScreen extends SimpleContainerMenuScreen<FurnitureStationMenu>
{
    public static final ResourceLocation SPRITE_RECIPE = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/recipe");
    public static final ResourceLocation SPRITE_RECIPE_BACKGROUND = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/recipe_background");
    public static final ResourceLocation SPRITE_RECIPE_HIGHLIGHTED = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/recipe_highlighted");
    public static final ResourceLocation SPRITE_RECIPE_SELECTED = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/recipe_selected");
    public static final ResourceLocation SPRITE_SCROLLER = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/scroller");
    public static final ResourceLocation SPRITE_SCROLLER_BACKGROUND = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/scroller_background");
    public static final ResourceLocation SPRITE_SCROLLER_DISABLED = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/scroller_disabled");
    public static final ResourceLocation SPRITE_ARROW = new ResourceLocation(FantasyFurniture.ID, "container/furniture_station/arrow");

    private float scrollOffs = 0F;
    private boolean scrolling = false;
    private int startIndex = 0;
    private boolean displayRecipes = false;

    private int recipeBackgroundX;
    private int recipeBackgroundY;
    private int recipeBackgroundWidth;
    private int recipeBackgroundHeight;
    private int recipeX;
    private int recipeY;
    private int recipeColumns;
    private int recipeRows;
    private int recipeSlotSize;
    private int scrollBarX;
    private int scrollBarY;
    private int scrollBarWidth;
    private int scrollBarHeight;
    private int scrollBarBackgroundWidth;
    private int scrollBarBackgroundHeight;
    private int scrollBarFullHeight;

    public FurnitureStationMenuScreen(FurnitureStationMenu menu, Inventory inventory, Component title)
    {
        super(menu, inventory, title);

        menu.registerUpdateListener(this::containerChanged);
    }

    @Override
    protected void init()
    {
        super.init();

        updatePositions();
    }

    private void updatePositions()
    {
        recipeSlotSize = 18;
        recipeColumns = 8;
        recipeRows = 4;

        recipeBackgroundWidth = recipeSlotSize * recipeColumns + 1;
        recipeBackgroundHeight = recipeSlotSize * recipeRows + 2;

        scrollBarBackgroundWidth = 14;
        scrollBarBackgroundHeight = recipeBackgroundHeight;
        scrollBarFullHeight = scrollBarBackgroundHeight - 2;

        scrollBarWidth = scrollBarBackgroundWidth - 2;
        scrollBarHeight = 15;

        recipeBackgroundX = leftPos - 3 + ((imageWidth / 2) - (recipeBackgroundWidth / 2) - (scrollBarWidth / 2));
        recipeBackgroundY = topPos + 44;

        recipeX = recipeBackgroundX + 1;
        recipeY = recipeBackgroundY + 1;

        scrollBarX = recipeBackgroundX + recipeBackgroundWidth + 1;
        scrollBarY = recipeBackgroundY;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY)
    {
        super.renderBg(graphics, partialTick, mouseX, mouseY);

        graphics.blitSprite(SPRITE_ARROW, recipeX + (18 * 3) + 15, recipeY - 18 - 7, 60, 16);
        graphics.blitSprite(SPRITE_RECIPE_BACKGROUND, recipeBackgroundX, recipeBackgroundY, recipeBackgroundWidth, recipeBackgroundHeight);
        graphics.blitSprite(SPRITE_SCROLLER_BACKGROUND, scrollBarX, scrollBarY, scrollBarBackgroundWidth, scrollBarBackgroundHeight);

        renderButtons(graphics, mouseX, mouseY, false);

        var k = (int) (57F * scrollOffs);
        var scrollerSprite = isScrollBarActive() ? SPRITE_SCROLLER : SPRITE_SCROLLER_DISABLED;
        graphics.blitSprite(scrollerSprite, scrollBarX + 1, scrollBarY + 1 + k, scrollBarWidth, scrollBarHeight);

        renderButtons(graphics, mouseX, mouseY, true);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if(menu.getCarried().isEmpty() && hoveredSlot != null && hoveredSlot.hasItem())
            return;

        if(displayRecipes)
        {
            var maxSlots = recipeColumns * recipeRows;
            var lastIndex = startIndex + maxSlots;
            var recipes = menu.getRecipes();

            for(var index = startIndex; index < lastIndex && index < recipes.size(); index++)
            {
                var slotIndex = index - startIndex;
                var slotX = recipeX + slotIndex % recipeColumns * recipeSlotSize;
                var slotY = recipeY + slotIndex / recipeColumns * recipeSlotSize;

                if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + recipeSlotSize && mouseY < slotY + recipeSlotSize)
                {
                    guiGraphics.renderTooltip(font, recipes.get(index).value().getResultItem(minecraft.level.registryAccess()), mouseX, mouseY);
                    break;
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        scrolling = false;

        if(displayRecipes)
        {
            for(var index = startIndex; index < menu.getNumRecipes(); index++)
            {
                var slotIndex = index - startIndex;
                var slotX = (double) (recipeX + slotIndex % recipeColumns * recipeSlotSize);
                var slotY = (double) (recipeY + slotIndex / recipeColumns * recipeSlotSize);

                if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + recipeSlotSize && mouseY < slotY + recipeSlotSize)
                {
                    if(!menu.clickMenuButton(minecraft.player, index))
                        continue;

                    minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1F));
                    minecraft.gameMode.handleInventoryButtonClick(menu.containerId, index);
                    return true;
                }
            }

            if(mouseX >= scrollBarX && mouseY >= scrollBarY && mouseX < scrollBarX + scrollBarWidth && mouseY < scrollBarY + scrollBarFullHeight)
                scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY)
    {
        if(scrolling && isScrollBarActive())
        {
            var scrollEndY = recipeY + scrollBarFullHeight;
            scrollOffs = (float) (mouseY - recipeY - (scrollBarHeight / 2F)) / ((scrollEndY - recipeY) - scrollBarHeight);
            scrollOffs = Mth.clamp(scrollOffs, 0F, 1F);
            startIndex = (int) ((scrollOffs * getOffscreenRows()) + .5D) * recipeColumns;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY)
    {
        if(super.mouseScrolled(mouseX, mouseY, scrollX, scrollY))
            return true;

        if(isScrollBarActive())
        {
            var offscreenRows = getOffscreenRows();
            var amount = (float) scrollY / offscreenRows;
            scrollOffs = Mth.clamp(scrollOffs - amount, 0F, 1F);
            startIndex = (int) ((scrollOffs * offscreenRows) + .5D) * recipeColumns;
        }

        return true;
    }

    private void renderButtons(GuiGraphics graphics, int mouseX, int mouseY, boolean drawItem)
    {
        var maxSlots = recipeColumns * recipeRows;
        var lastIndex = startIndex + maxSlots;
        var recipes = menu.getRecipes();

        for(var index = startIndex; index < lastIndex && index < recipes.size(); index++)
        {
            var slotIndex = index - startIndex;
            var slotX = recipeX + slotIndex % recipeColumns * recipeSlotSize;
            var slotY = recipeY + slotIndex / recipeColumns * recipeSlotSize;

            if(drawItem)
                graphics.renderItem(recipes.get(index).value().getResultItem(minecraft.level.registryAccess()), slotX + 1, slotY + 1);
            else
            {
                var slotSprite = SPRITE_RECIPE;

                if(index == menu.getSelectedRecipeIndex())
                    slotSprite = SPRITE_RECIPE_SELECTED;
                else if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + recipeSlotSize && mouseY < slotY + recipeSlotSize)
                    slotSprite = SPRITE_RECIPE_HIGHLIGHTED;

                graphics.blitSprite(slotSprite, slotX, slotY, recipeSlotSize, recipeSlotSize);
            }
        }
    }

    private boolean isScrollBarActive()
    {
        var maxSlots = recipeColumns * recipeRows;
        return displayRecipes && menu.getNumRecipes() > maxSlots;
    }

    private int getOffscreenRows()
    {
        return (menu.getNumRecipes() + recipeColumns - 1) / recipeColumns - recipeRows;
    }

    private void containerChanged()
    {
        displayRecipes = menu.hasInputItem();

        if(!displayRecipes)
        {
            scrollOffs = 0F;
            startIndex = 0;
        }

        updatePositions();
    }
}

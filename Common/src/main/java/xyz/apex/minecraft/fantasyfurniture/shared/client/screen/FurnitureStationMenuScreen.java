package xyz.apex.minecraft.fantasyfurniture.shared.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import xyz.apex.minecraft.fantasyfurniture.shared.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.FurnitureStationMenu;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public final class FurnitureStationMenuScreen extends AbstractContainerScreen<FurnitureStationMenu> implements RecipeUpdateListener
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(FantasyFurniture.ID, "textures/gui/container/furniture_station.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("minecraft", "textures/gui/recipe_button.png");
    private static final int SLOT_SIZE = 18;
    private static final int SCROLL_BAR_WIDTH = 12;
    private static final int SCROLL_BAR_HEIGHT = 15;

    private int resultX;
    private int resultY;
    private int horizontalSlots;
    private int verticalSlots;

    private int scrollBarX;
    private int scrollBarY;
    private int scrollBarYMax;
    private int scrollBarYMin;
    private float scrollBarYOffset;
    private boolean scrollBarActive = false;
    private boolean scrolling = false;
    private int startIndex = 0;

    private final RecipeBookComponent recipeBook = new RecipeBookComponent();
    @Nullable private ImageButton recipeBookButton;
    private boolean widthTooNarrow;
    private boolean buttonClicked;
    private boolean recipeBookInitialized;

    public FurnitureStationMenuScreen(FurnitureStationMenu menu, Inventory inventory, Component displayName)
    {
        super(menu, inventory, displayName);

        menu.setScreenListener(this::refreshScreenData);
    }

    @Override
    protected void init()
    {
        imageWidth = 176;
        imageHeight = 222;

        super.init();

        titleLabelX = (imageWidth - font.width(title)) / 2;
        inventoryLabelY = imageHeight - 94;

        refreshScreenData();

        widthTooNarrow = width < 379;
        recipeBook.init(width, height, minecraft, widthTooNarrow, menu);
        leftPos = recipeBook.updateScreenPosition(width, imageWidth);
        recipeBookButton = addRenderableWidget(new ImageButton(leftPos + 140, topPos + 120, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, btn -> {
            recipeBook.toggleVisibility();
            refreshScreenData();
            buttonClicked = true;
        }));
        addWidget(recipeBook);
        setInitialFocus(recipeBook);
        recipeBookInitialized = true;
    }

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partialTick)
    {
        renderBackground(pose);

        if(recipeBook.isVisible() && widthTooNarrow)
        {
            renderBg(pose, partialTick, mouseX, mouseY);
            recipeBook.render(pose, mouseX, mouseY, partialTick);
        }
        else
        {
            recipeBook.render(pose, mouseX, mouseY, partialTick);
            super.render(pose, mouseX, mouseY, partialTick);
            recipeBook.renderGhostRecipe(pose, leftPos, topPos, false, partialTick);
        }

        renderScrollBar(pose);
        renderResults(pose, mouseX, mouseY);
        renderTooltip(pose, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack pose, float partialTick, int mouseX, int mouseY)
    {
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(pose, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderTooltip(PoseStack pose, int mouseX, int mouseY)
    {
        super.renderTooltip(pose, mouseX, mouseY);
        recipeBook.renderTooltip(pose, leftPos, topPos, mouseX, mouseY);
    }

    @Override
    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY)
    {
        return (!widthTooNarrow || !recipeBook.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
    {
        scrolling = false;

        if(recipeBook.mouseClicked(mouseX, mouseY, mouseButton))
        {
            setFocused(recipeBook);
            return true;
        }

        if(widthTooNarrow && recipeBook.isVisible()) return false;

        if(mouseButton == GLFW_MOUSE_BUTTON_LEFT)
        {
            if(scrollBarActive && mouseX >= scrollBarX && mouseY >= scrollBarY && mouseX < scrollBarX + SCROLL_BAR_WIDTH && mouseY < scrollBarY + SCROLL_BAR_WIDTH) scrolling = true;
            if(handleResultClick(mouseX, mouseY)) return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int mouseButton)
    {
        if(buttonClicked)
        {
            buttonClicked = false;
            return true;
        }

        return super.mouseReleased(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double dragX, double dragY)
    {
        if(mouseButton == GLFW_MOUSE_BUTTON_LEFT && handleScrollBarDrag(mouseY)) return true;
        return super.mouseDragged(mouseX, mouseY, mouseButton, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        if(scrollBarActive)
        {
            var rows = getOffscreenRows();
            var f = (float) delta / rows;
            scrollBarYOffset = Mth.clamp(scrollBarYOffset - f, 0F, 1F);
            // 59 = scroll bar box top - (scroll bar height - 1)
            scrollBarY = scrollBarYMin + (int) (scrollBarYOffset * 57);

            var newIndex = (int) ((scrollBarYOffset * rows) + .5D) * horizontalSlots;
            startIndex = Mth.clamp(newIndex, 0, BuiltInRegistries.ITEM.size() - 1);

            return true;
        }

        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton)
    {
        var bl = mouseX < (double) guiLeft || mouseY < (double) guiTop || mouseX >= (double) (guiLeft + imageWidth) || mouseY >= (double) (guiTop + imageHeight);
        return recipeBook.hasClickedOutside(mouseX, mouseY, leftPos, topPos, imageWidth, imageHeight, mouseButton) && bl;
    }

    @Override
    protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type)
    {
        super.slotClicked(slot, slotId, mouseButton, type);
        recipeBook.slotClicked(slot);
    }

    @Override
    protected void containerTick()
    {
        recipeBook.tick();
    }

    @Override
    public void recipesUpdated()
    {
        recipeBook.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent()
    {
        return recipeBook;
    }

    private void refreshScreenData()
    {
        // called too early
        if(minecraft == null) return;
        if(recipeBookInitialized) leftPos = recipeBook.updateScreenPosition(width, imageWidth);

        resultX = leftPos + 17;
        resultY = topPos + 45;

        // 144 = brown box width
        horizontalSlots = 126 / SLOT_SIZE;
        // 72 = brown box height
        verticalSlots = 72 / SLOT_SIZE;

        scrollBarYMin = topPos + 45; // 45 <- scroll bar box, top left
        scrollBarYMax = scrollBarYMin + 72; // 72 <- scroll bar box height

        scrollBarX = leftPos + 146; // 146 <- scroll bar box, top left
        scrollBarY = scrollBarYMin;

        startIndex = 0;
        scrollBarYOffset = 0F;
        scrolling = false;
        scrollBarActive = menu.getRecipes().size() > (horizontalSlots * verticalSlots);

        if(recipeBookInitialized && recipeBookButton != null) recipeBookButton.setPosition(leftPos + 140, topPos + 120);
    }

    private void renderScrollBar(PoseStack pose)
    {
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderTexture(0, TEXTURE);

        blit(
                pose,
                scrollBarX, scrollBarY,
                SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT,
                194F + (scrollBarActive ? 0F : SCROLL_BAR_WIDTH), 0F,
                SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT,
                256, 256
        );
    }

    private void renderResults(PoseStack pose, int mouseX, int mouseY)
    {
        var recipes = menu.getRecipes();
        var result = menu.getResult();

        var hovered = -1;

        var xCounter = 0;
        var yCounter = 0;

        var carriedItem = menu.getCarried();

        for(var i = startIndex; i < recipes.size(); i++)
        {
            var slotX = resultX + (xCounter * SLOT_SIZE);
            var slotY = resultY + (yCounter * SLOT_SIZE);
            var slotV = 0;

            var recipeResult = recipes.get(i).getResultItem();
            var isSelected = result.sameItem(recipeResult);

            if(isSelected) slotV = SLOT_SIZE;

            // no tooltips/hover states if carrying item under mouse
            if(carriedItem.isEmpty() && hovered == -1)
            {
                if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + SLOT_SIZE && mouseY < slotY + SLOT_SIZE)
                {
                    hovered = i;
                    if(!isSelected) slotV = SLOT_SIZE * 2;
                }
            }

            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            RenderSystem.setShader(GameRenderer::getPositionShader);
            RenderSystem.setShaderTexture(0, TEXTURE);

            blit(
                    pose,
                    slotX, slotY,
                    SLOT_SIZE, SLOT_SIZE,
                    176F, slotV,
                    SLOT_SIZE, SLOT_SIZE,
                    256, 256
            );

            itemRenderer.renderGuiItem(recipeResult, slotX + 1, slotY + 1);

            if(xCounter + 1 < horizontalSlots)
            {
                xCounter++;
            }
            else
            {
                if(yCounter + 1 >= verticalSlots) break;
                xCounter = 0;
                yCounter++;
            }
        }

        if(hovered != -1) renderTooltip(pose, recipes.get(hovered).getResultItem(), mouseX, mouseY);
    }

    private boolean handleResultClick(double mouseX, double mouseY)
    {
        var recipes = menu.getRecipes();
        var result = menu.getResult();

        var xCounter = 0;
        var yCounter = 0;

        var carriedItem = menu.getCarried();

        for(var i = startIndex; i < recipes.size(); i++)
        {
            var slotX = resultX + (xCounter * SLOT_SIZE);
            var slotY = resultY + (yCounter * SLOT_SIZE);

            var recipeResult = recipes.get(i).getResultItem();
            var isSelected = result.sameItem(recipeResult);

            // no tooltips/hover states if carrying item under mouse
            if(carriedItem.isEmpty() && !isSelected)
            {
                if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + SLOT_SIZE && mouseY < slotY + SLOT_SIZE)
                {
                    if(menu.clickMenuButton(minecraft.player, i))
                    {
                        minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1F));
                        minecraft.gameMode.handleInventoryButtonClick(menu.containerId, i);
                        return true;
                    }
                }
            }

            if(xCounter + 1 < horizontalSlots) xCounter++;
            else
            {
                if(yCounter + 1 >= verticalSlots) return false;

                xCounter = 0;
                yCounter++;
            }
        }

        return false;
    }

    private boolean handleScrollBarDrag(double mouseY)
    {
        if(!scrollBarActive || !scrolling) return false;

        scrollBarYOffset = ((float) mouseY - scrollBarYMin - (SCROLL_BAR_HEIGHT / 2F)) / ((float) (scrollBarYMax - scrollBarYMin) - SCROLL_BAR_HEIGHT);
        scrollBarYOffset = Mth.clamp(scrollBarYOffset, 0F, 1F);
        // 59 = scroll bar box top - (scroll bar height - 1)
        scrollBarY = scrollBarYMin + (int) (scrollBarYOffset * 57);
        var newIndex = (int) ((scrollBarYOffset * getOffscreenRows()) + .5D) * horizontalSlots;
        startIndex = Mth.clamp(newIndex, 0, BuiltInRegistries.ITEM.size() - 1);
        return true;
    }

    private int getOffscreenRows()
    {
        return (menu.getRecipes().size() + horizontalSlots - 1) / horizontalSlots - verticalSlots;
    }
}

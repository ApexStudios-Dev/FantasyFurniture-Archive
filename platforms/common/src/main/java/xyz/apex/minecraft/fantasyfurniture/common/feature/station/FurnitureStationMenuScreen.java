package xyz.apex.minecraft.fantasyfurniture.common.feature.station;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import org.joml.Math;
import xyz.apex.minecraft.apexcore.common.lib.PhysicalSide;
import xyz.apex.minecraft.apexcore.common.lib.SideOnly;
import xyz.apex.minecraft.apexcore.common.lib.menu.SimpleContainerMenuScreen;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

@SideOnly(PhysicalSide.CLIENT)
public final class FurnitureStationMenuScreen extends SimpleContainerMenuScreen<FurnitureStationMenu> implements ContainerListener
{
    private static final ResourceLocation BACKGROUND = new ResourceLocation(FantasyFurniture.ID, "textures/gui/container/furniture_station.png");
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

    FurnitureStationMenuScreen(FurnitureStationMenu menu, Inventory playerInventory, Component displayName)
    {
        super(menu, playerInventory, displayName, BACKGROUND);

        menu.addSlotListener(this);
    }

    private void refreshScreenData()
    {
        if(minecraft == null)
            return;

        resultX = leftPos + 17;
        resultY = topPos + 45;

        horizontalSlots = 126 / SLOT_SIZE;
        verticalSlots = 72 / SLOT_SIZE;

        scrollBarYMin = topPos + 45;
        scrollBarYMax = scrollBarYMax + 72;

        scrollBarX = leftPos + 146;
        scrollBarY = scrollBarYMin;

        startIndex = 0;
        scrollBarYOffset = 0;
        scrolling = false;
        scrollBarActive = menu.getRecipes().size() > (horizontalSlots * verticalSlots);
    }

    private void renderScrollBar(GuiGraphics graphics)
    {
        graphics.blit(
                BACKGROUND,
                scrollBarX, scrollBarY,
                SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT,
                194F + (scrollBarActive ? 0F : SCROLL_BAR_WIDTH), 0F,
                SCROLL_BAR_WIDTH, SCROLL_BAR_HEIGHT,
                256, 256
        );
    }

    private void renderResults(GuiGraphics graphics, int mouseX, int mouseY)
    {
        var recipes = menu.getRecipes();
        var result = menu.getResult();
        var hovered = ItemStack.EMPTY;
        var xCounter = 0;
        var yCounter = 0;
        var carriedItem = menu.getCarried();
        var registryAccess = minecraft.level.registryAccess();

        for(var i = startIndex; i < recipes.size(); i++)
        {
            var slotX = resultX + (xCounter * SLOT_SIZE);
            var slotY = resultY + (yCounter * SLOT_SIZE);
            var slotV = 0;
            var slotResult = recipes.get(i).getResultItem(registryAccess);
            var isSelected = ItemStack.matches(result, slotResult);

            if(isSelected)
                slotV = SLOT_SIZE;

            if(carriedItem.isEmpty() && slotResult.isEmpty())
            {
                if(mouseX >= slotX && mouseY >= slotY && mouseX < slotX + SLOT_SIZE && mouseY < slotY + SLOT_SIZE)
                {
                    hovered = slotResult;

                    if(!isSelected)
                        slotV = SLOT_SIZE * 2;
                }
            }

            graphics.blit(
                    BACKGROUND,
                    slotX, slotY,
                    SLOT_SIZE, SLOT_SIZE,
                    176F, slotV,
                    SLOT_SIZE, SLOT_SIZE,
                    256, 256
            );

            graphics.renderFakeItem(slotResult, slotX + 1, slotY + 1);

            if(xCounter + 1 < horizontalSlots)
                xCounter++;
            else
            {
                if(yCounter + 1 >= verticalSlots)
                    break;

                yCounter++;
                xCounter = 0;
            }
        }

        if(!hovered.isEmpty())
            graphics.renderTooltip(font, hovered, mouseX, mouseY);
    }

    private boolean handleResultClick(double mouseX, double mouseY)
    {
        var recipes = menu.getRecipes();
        var result = menu.getResult();
        var xCounter = 0;
        var yCounter = 0;
        var carriedItem = menu.getCarried();
        var registryAccess = minecraft.level.registryAccess();

        for(var i = startIndex; i < recipes.size(); i++)
        {
            var slotX = resultX + (xCounter * SLOT_SIZE);
            var slotY = resultY + (yCounter * SLOT_SIZE);
            var slotResult = recipes.get(i).getResultItem(registryAccess);
            var isSelected = ItemStack.matches(result, slotResult);

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

            if(xCounter + 1 < horizontalSlots)
                xCounter++;
            else
            {
                if(yCounter + 1 >= verticalSlots)
                    break;

                yCounter++;
                xCounter = 0;
            }
        }

        return false;
    }

    private boolean handleScrollBarDrag(double mouseY)
    {
        if(!scrollBarActive || !scrolling)
            return false;

        scrollBarYOffset = ((float) mouseY - scrollBarYMin - (SCROLL_BAR_HEIGHT / 2F)) / ((float) (scrollBarYMax - scrollBarYMin) - SCROLL_BAR_HEIGHT);
        scrollBarYOffset = Math.clamp(0F, 1F, scrollBarYOffset);
        scrollBarY = scrollBarYMin + (int) (scrollBarYOffset * 57);

        var newIndex = (int) ((scrollBarYOffset * getOffscreenRows()) + .5D) * horizontalSlots;
        startIndex = Math.clamp(0, BuiltInRegistries.ITEM.size() - 1, newIndex);

        return true;
    }

    private int getOffscreenRows()
    {
        return (menu.getRecipes().size() + horizontalSlots - 1) / horizontalSlots - verticalSlots;
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
    }

    @Override
    protected void renderFg(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        renderScrollBar(graphics);
        renderResults(graphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        scrolling = false;

        if(button == InputConstants.MOUSE_BUTTON_LEFT)
        {
            if(scrollBarActive && mouseX >= scrollBarX && mouseY >= scrollBarY && mouseX < scrollBarX + SCROLL_BAR_WIDTH && mouseY < scrollBarY + SCROLL_BAR_HEIGHT)
                scrolling = true;
            else if(handleResultClick(mouseX, mouseY))
                return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY)
    {
        if(button == InputConstants.MOUSE_BUTTON_LEFT && handleScrollBarDrag(mouseY))
            return true;

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        if(scrollBarActive)
        {
            var rows = getOffscreenRows();
            var f = (float) delta / rows;
            scrollBarYOffset = Math.clamp(0F, 1F, scrollBarYOffset - f);
            scrollBarY = scrollBarYMin + (int) (scrollBarYOffset * 57);

            var newIndex = (int) ((scrollBarYOffset * rows) + .5D) * horizontalSlots;
            startIndex = Math.clamp(0, BuiltInRegistries.ITEM.size() - 1, newIndex);

            return true;
        }

        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public void slotChanged(AbstractContainerMenu menu, int slotIndex, ItemStack stack)
    {
        refreshScreenData();
    }

    @Override
    public void dataChanged(AbstractContainerMenu menu, int slotIndex, int value)
    {
        refreshScreenData();
    }
}

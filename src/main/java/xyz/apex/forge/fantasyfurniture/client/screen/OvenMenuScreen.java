package xyz.apex.forge.fantasyfurniture.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import xyz.apex.forge.apexcore.lib.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.init.ModElements;
import xyz.apex.forge.fantasyfurniture.menu.OvenMenu;

public final class OvenMenuScreen extends BaseMenuScreen<OvenMenu> implements RecipeUpdateListener
{
	public static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation(Mods.MINECRAFT, "textures/gui/recipe_button.png");

	public final RecipeBookComponent recipeBookComponent;
	private boolean widthTooNarrow;

	public OvenMenuScreen(OvenMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, ModElements.OVEN_TEXTURE);

		recipeBookComponent = new SmokingRecipeBookComponent();
	}

	@Override
	protected void init()
	{
		super.init();

		widthTooNarrow = width < 379;
		recipeBookComponent.init(width, height, minecraft, widthTooNarrow, menu.recipeBookMenu);
		leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);

		addRenderableWidget(new ImageButton(leftPos + 20, height / 2 - 49, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, btn -> {
			recipeBookComponent.toggleVisibility();
			leftPos = recipeBookComponent.updateScreenPosition(width, imageWidth);

			btn.x = leftPos + 20;
			btn.y = height / 2 - 49;
		}));

		titleLabelX = (imageWidth - font.width(title)) / 2;
	}

	@Override
	protected void containerTick()
	{
		super.containerTick();
		recipeBookComponent.tick();
	}

	@Override
	public void render(PoseStack pose, int mouseX, int mouseY, float partialTicks)
	{
		renderBackground(pose);

		if(recipeBookComponent.isVisible() && widthTooNarrow)
		{
			renderBg(pose, partialTicks, mouseX, mouseY);
			recipeBookComponent.render(pose, mouseX, mouseY, partialTicks);
		}
		else
		{
			recipeBookComponent.render(pose, mouseX, mouseY, partialTicks);
			super.render(pose, mouseX, mouseY, partialTicks);
			recipeBookComponent.renderGhostRecipe(pose, leftPos, topPos, true, partialTicks);
		}

		recipeBookComponent.renderTooltip(pose, leftPos, topPos, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack pose, float partialTicks, int mouseX, int mouseY)
	{
		super.renderBg(pose, partialTicks, mouseX, mouseY);

		if(menu.isLit())
		{
			var litProgress = menu.getLitProgress();
			blit(pose, leftPos + 56, topPos + 36 + 12 - litProgress, 176, 12 - litProgress, 14, litProgress + 1);
		}

		var burnProgress = menu.getBurnProgress();
		blit(pose, leftPos + 79, topPos + 34, 176, 14, burnProgress + 1, 16);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		if(recipeBookComponent.mouseClicked(mouseX, mouseY, mouseButton))
			return true;

		return widthTooNarrow && recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void slotClicked(Slot slot, int slotIndex, int mouseButton, ClickType clickType)
	{
		super.slotClicked(slot, slotIndex, mouseButton, clickType);
		recipeBookComponent.slotClicked(slot);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		return !recipeBookComponent.keyPressed(keyCode, scanCode, modifiers) && super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int x, int y, int mouseButton)
	{
		boolean flag = mouseX < (double) x || mouseY < (double) y || mouseX >= (double) (x + imageWidth) || mouseY >= (double) (y + imageHeight);
		return recipeBookComponent.hasClickedOutside(mouseX, mouseY, leftPos, topPos, imageWidth, imageHeight, mouseButton) && flag;
	}

	@Override
	public boolean charTyped(char typedChar, int modifier)
	{
		return this.recipeBookComponent.charTyped(typedChar, modifier) || super.charTyped(typedChar, modifier);
	}

	@Override
	public void recipesUpdated()
	{
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent()
	{
		return recipeBookComponent;
	}

	@Override
	public void removed()
	{
		recipeBookComponent.removed();
		super.removed();
	}
}
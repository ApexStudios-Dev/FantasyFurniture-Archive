package xyz.apex.forge.fantasyfurniture.client.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public final class FurnitureStationContainerScreen extends ContainerScreen<FurnitureStationContainer>
{
	public static final ResourceLocation TEXTURE = FFRegistry.getInstance().id("textures/gui/container/furniture_station.png");

	private int clayIndex = 0;
	private int woodIndex = 0;
	private int stoneIndex = 0;
	private int cycleCounter = 0;

	private float scrollOffset = 0F;
	private int startIndex = 0;
	private boolean scrolling = false;

	@Nullable private TextFieldWidget searchBox;
	private boolean focusSearchBoxNextTick = false;

	public FurnitureStationContainerScreen(FurnitureStationContainer container, PlayerInventory playerInventory, ITextComponent title)
	{
		super(container, playerInventory, title);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 222;

		minecraft.keyboardHandler.setSendRepeatsToGui(true);

		super.init();

		inventoryLabelY = imageHeight - 94;

		scrolling = false;
		startIndex = 0;
		scrollOffset = 0F;

		String text = searchBox == null ? "" : searchBox.getValue();

		int s = 15 + (16 * 3) + 10;
		int e = imageWidth - 12;

		int w = e - s;
		int h = 16;
		int x = leftPos + s;
		int y = topPos + titleLabelY + font.lineHeight + 6;

		searchBox = addButton(new TextFieldWidget(font, x, y, w, h, new TranslationTextComponent("gui.recipebook.search_hint")));
		searchBox.setMaxLength(50);
		searchBox.setBordered(true);
		searchBox.setVisible(true);
		searchBox.setTextColor(16777215);
		searchBox.setValue(text);
		searchBox.setCanLoseFocus(true);
	}

	@Override
	public void tick()
	{
		if(searchBox != null)
		{
			Slot claySlot = menu.getClaySlot();
			Slot woodSlot = menu.getWoodSlot();
			Slot stoneSlot = menu.getStoneSlot();

			if(!claySlot.hasItem() || !woodSlot.hasItem() || !stoneSlot.hasItem())
			{
				searchBox.active = false;
				searchBox.setValue("");
				focusSearchBoxNextTick =  false;
				searchBox.setFocus(false);
				setFocused(null);
			}
			else
				searchBox.active = true;

			searchBox.tick();

			if(focusSearchBoxNextTick)
			{
				if(!searchBox.isFocused())
				{
					setFocused(searchBox);
					searchBox.setFocus(true);
				}

				focusSearchBoxNextTick = false;
			}
		}

		super.tick();
	}

	@Override
	public void removed()
	{
		searchBox = null;
		minecraft.keyboardHandler.setSendRepeatsToGui(false);
		super.removed();
	}

	@Override
	public void render(MatrixStack pose, int mouseX, int mouseY, float partialTicks)
	{
		renderBackground(pose);
		super.render(pose, mouseX, mouseY, partialTicks);

		if(searchBox != null && searchBox.isVisible() && !searchBox.isFocused() && searchBox.getValue().isEmpty())
			drawString(pose, font, searchBox.getMessage().copy().withStyle(TextFormatting.GRAY, TextFormatting.ITALIC), searchBox.x + 2, searchBox.y + (font.lineHeight / 2), searchBox.getFGColor());

		renderResults(pose, mouseX, mouseY);
		renderSlotBackgrounds(pose, mouseX, mouseY);
		renderTooltip(pose, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack pose, float partialTicks, int mouseX, int mouseY)
	{
		RenderSystem.color4f(1F, 1F, 1F, 1F);
		getMinecraft().getTextureManager().bind(TEXTURE);
		int i = (width - imageWidth) / 2;
		int j = (height - imageHeight) / 2;
		blit(pose, i, j, 0, 0, imageWidth, imageHeight);

		int k = (int) (57F * scrollOffset);
		blit(pose, i + 127, j + 45 + k, 194 + (scrollbarActive() ? 0 : 12), 0, 12, 15);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		scrolling = false;

		if(searchBox != null && searchBox.active && searchBox.isVisible())
		{
			if(searchBox.mouseClicked(mouseX, mouseY, mouseButton))
				return true;

			if(mouseButton == GLFW_MOUSE_BUTTON_RIGHT && searchBox.isMouseOver(mouseX, mouseY))
			{
				searchBox.setValue("");
				return true;
			}
		}

		if(clickedResult(mouseX, mouseY, mouseButton))
			return true;

		if(!menu.getResults().isEmpty())
		{
			int x = leftPos + 128;
			int y = topPos + 46;
			int w = 12;
			int h = 71;

			if(mouseX >= x && mouseY >= y && mouseX < x + w && mouseY < y + h)
			{
				scrolling = true;
				return true;
			}
		}

		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double dragX, double dragY)
	{
		List<ItemStack> results = menu.getResults();
		int size = results.size();

		if(scrolling && scrollbarActive())
		{
			int i = topPos + 44;
			int j = i + 73;

			int offscreenRows = (size + 6 - 1) / 4 - 4;

			scrollOffset = ((float) mouseY - (float) i - 7.5F) / ((float) (j - i) - 15F);
			scrollOffset = MathHelper.clamp(scrollOffset, 0F, 1F);
			startIndex = (int) ((double) (scrollOffset * (float) offscreenRows) + .5D) * 6;

			return true;
		}

		return super.mouseDragged(mouseX, mouseY, mouseButton, dragX, dragY);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollDelta)
	{
		if(scrollbarActive())
		{
			List<ItemStack> results = menu.getResults();
			int size = results.size();

			int offscreenRows = (size + 6 - 1) / 4 - 4;

			scrollOffset = (float) ((double) scrollOffset - scrollDelta / (double) offscreenRows);
			scrollOffset = MathHelper.clamp(scrollOffset, 0F, 1F);
			startIndex = (int) ((double) (scrollOffset * (float) offscreenRows) + .5D) * 6;

			return true;
		}

		return super.mouseScrolled(mouseX, mouseY, scrollDelta);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers)
	{
		if(searchBox != null && searchBox.active && searchBox.isVisible())
		{
			if(searchBox.isFocused())
			{
				if(searchBox.keyPressed(keyCode, scanCode, modifiers))
					return true;

				if(minecraft.options.keyInventory.matches(keyCode, scanCode))
					return false;

				if(keyCode == GLFW_KEY_ESCAPE)
				{
					focusSearchBoxNextTick = false;
					searchBox.setFocus(false);
					return true;
				}
			}
			else
			{
				if(minecraft.options.keyChat.matches(keyCode, scanCode))
				{
					focusSearchBoxNextTick = true;
					return true;
				}
			}
		}

		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char typedChar, int modifiers)
	{
		if(searchBox != null && searchBox.active && searchBox.isFocused() && searchBox.isVisible() && searchBox.charTyped(typedChar, modifiers))
			return true;

		return super.charTyped(typedChar, modifiers);
	}

	@Override
	public List<? extends IGuiEventListener> children()
	{
		if(searchBox != null && !searchBox.active)
		{
			ArrayList<IGuiEventListener> kids = Lists.newArrayList(children);
			kids.remove(searchBox);
			return kids;
		}

		return children;
	}

	private void renderSlotBackgrounds(MatrixStack pose, int mouseX, int mouseY)
	{
		Slot claySlot = menu.getClaySlot();
		Slot woodSlot = menu.getWoodSlot();
		Slot stoneSlot = menu.getStoneSlot();

		clayIndex = renderSlotBackground(claySlot, FurnitureStation.CLAY, pose, mouseX, mouseY, clayIndex);
		woodIndex = renderSlotBackground(woodSlot, FurnitureStation.WOOD, pose, mouseX, mouseY, woodIndex);
		stoneIndex = renderSlotBackground(stoneSlot, FurnitureStation.STONE, pose, mouseX, mouseY, stoneIndex);

		cycleCounter++;

		if(cycleCounter > 125)
			cycleCounter = 0;
	}

	private int renderSlotBackground(Slot slot, ITag.INamedTag<Item> backgroundTag, MatrixStack pose, int mouseX, int mouseY, int counter)
	{
		int index  = counter;

		if(!slot.hasItem())
		{
			List<Item> values = backgroundTag.getValues();

			if(cycleCounter == 125)
			{
				index++;

				if(index >= values.size())
					index = 0;
			}

			int x = leftPos + slot.x;
			int y = topPos + slot.y;

			Item item = values.get(index);
			ItemStack stack = item.getDefaultInstance();

			FontRenderer stackFont = item.getFontRenderer(stack);
			stackFont = stackFont == null ? font : stackFont;

			renderTranslucentItem(stack, x, y);
			itemRenderer.renderGuiItemDecorations(stackFont, stack, x, y);

			// TODO: Maybe this should be a config?
			//  Show tooltip theres more than 1 valid input item
			if(/*backgroundTag.getValues().size() > 1 &&*/ minecraft.player.inventory.getCarried().isEmpty())
			{
				if(mouseX >= x && mouseY >= y && mouseX < x + 16 && mouseY < y + 16)
				{
					ITextComponent name = stack.getHoverName();
					ITextComponent accepts = FurnitureStation.buildAcceptsAnyComponent(backgroundTag);
					renderWrappedToolTip(pose, Lists.newArrayList(name, accepts), mouseX, mouseY, stackFont);
				}
			}
		}

		return index;
	}

	private boolean isItemValid(ItemStack stack)
	{
		if(searchBox != null && searchBox.isVisible())
		{
			String value = searchBox.getValue();

			if(!value.isEmpty())
			{
				ITextComponent displayName = stack.getHoverName();
				return StringUtils.containsIgnoreCase(displayName.getString(), value);
			}
		}

		return true;
	}

	private void renderResults(MatrixStack pose, int mouseX, int mouseY)
	{
		List<ItemStack> results = menu.getResults();

		if(!results.isEmpty())
		{
			Minecraft mc = getMinecraft();

			int centerX = (width - imageWidth) / 2;
			int centerY = (height - imageHeight) / 2;

			int yOffset = 0;
			boolean canHover = true;
			int maxY = centerY + 46 + (18 * 4);

			ItemStack selectedResult = menu.getResultSlot().getItem();
			int visibleItemIndex = 0;

			for(int j = startIndex; j < results.size(); j++)
			{
				ItemStack resultItem = results.get(j);

				if(!isItemValid(resultItem))
					continue;

				int resultItemX = centerX + 17 + 18 * (visibleItemIndex % 6);
				int resultItemY = centerY + 46 + yOffset;

				if(visibleItemIndex % 6 == 5)
					yOffset += 18;
				if(resultItemY >= maxY)
					break;

				boolean isHovered = false;
				float vOffset = 0F;

				if(canHover)
					isHovered = mouseX >= resultItemX && mouseY >= resultItemY && mouseX < resultItemX + 18 && mouseY < resultItemY + 18;

				if(isHovered)
				{
					canHover = false;
					vOffset = 18F * 2F;
				}

				if(!selectedResult.isEmpty() && ItemStack.isSame(selectedResult, resultItem))
					vOffset = 18F;

				mc.textureManager.bind(TEXTURE);
				blit(pose, resultItemX - 1, resultItemY - 1, 176F, vOffset, 18, 18, 256, 256);

				FontRenderer stackFont = resultItem.getItem().getFontRenderer(resultItem);
				stackFont = stackFont == null ? font : stackFont;

				itemRenderer.renderGuiItem(resultItem, resultItemX, resultItemY);
				itemRenderer.renderGuiItemDecorations(stackFont, resultItem, resultItemX, resultItemY);

				if(isHovered)
					renderTooltip(pose, resultItem, mouseX, mouseY);

				visibleItemIndex++;
			}
		}
	}

	private boolean clickedResult(double mouseX, double mouseY, int mouseButton)
	{
		if(mouseButton != GLFW_MOUSE_BUTTON_LEFT)
			return false;

		List<ItemStack> results = menu.getResults();

		if(!results.isEmpty())
		{
			ItemStack selectedItem = menu.getResultSlot().getItem();

			int centerX = (width - imageWidth) / 2;
			int centerY = (height - imageHeight) / 2;

			int yOffset = 0;
			int maxY = centerY + 46 + (18 * 4);
			int visibleItemIndex = 0;

			for(int j = startIndex; j < results.size(); j++)
			{
				ItemStack resultItem = results.get(j);

				if(!isItemValid(resultItem))
					continue;
				if(!selectedItem.isEmpty() && ItemStack.isSame(selectedItem, resultItem))
					continue;

				int resultItemX = centerX + 17 + 18 * (visibleItemIndex % 6);
				int resultItemY = centerY + 46 + yOffset;

				if(visibleItemIndex % 6 == 5)
					yOffset += 18;
				if(resultItemY >= maxY)
					break;

				if(mouseX >= resultItemX && mouseY >= resultItemY && mouseX < resultItemX + 18 && mouseY < resultItemY + 18)
				{
					Minecraft mc = getMinecraft();
					mc.getSoundManager().play(SimpleSound.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1F));
					mc.gameMode.handleInventoryButtonClick(menu.containerId, j);
					return true;
				}

				visibleItemIndex++;
			}
		}

		return false;
	}

	private void renderTranslucentItem(ItemStack stack, int x, int y)
	{
		if(stack.isEmpty())
			return;

		Minecraft mc = getMinecraft();
		IBakedModel model = itemRenderer.getModel(stack, null, null);

		RenderSystem.pushMatrix();

		mc.textureManager.bind(AtlasTexture.LOCATION_BLOCKS);
		mc.textureManager.getTexture(AtlasTexture.LOCATION_BLOCKS).setFilter(false, false);

		RenderSystem.enableRescaleNormal();
		RenderSystem.enableAlphaTest();

		RenderSystem.defaultAlphaFunc();
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

		RenderSystem.color4f(1F, 1F, 1F, 1F);
		RenderSystem.translatef((float) x, (float) y, 100F + getBlitOffset());
		RenderSystem.translatef(8F, 8F, 0F);
		RenderSystem.scalef(1F, -1F, 1F);
		RenderSystem.scalef(16F, 16F, 16F);

		MatrixStack pose = new MatrixStack();
		IRenderTypeBuffer.Impl buffer = mc.renderBuffers().bufferSource();
		boolean flag = !model.usesBlockLight();

		if(flag)
			RenderHelper.setupForFlatItems();

		int color = 15728880;
		int overlay = OverlayTexture.NO_OVERLAY;

		if(flag)
			color = (191 << 24) | 0xf000f0;
		else
			overlay = OverlayTexture.pack(.45F, false);

		itemRenderer.render(stack, ItemCameraTransforms.TransformType.GUI, false, pose, buffer, color, overlay, model);
		buffer.endBatch();

		RenderSystem.enableDepthTest();

		if(flag)
			RenderHelper.setupFor3DItems();

		RenderSystem.disableAlphaTest();
		RenderSystem.disableRescaleNormal();
		RenderSystem.popMatrix();
	}

	private boolean scrollbarActive()
	{
		return menu.getResults().size() > 6 * 4;
	}
}

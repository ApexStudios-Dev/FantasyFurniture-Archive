package xyz.apex.forge.fantasyfurniture.client.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
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

import xyz.apex.forge.fantasyfurniture.container.FurnitureStationContainer;
import xyz.apex.forge.fantasyfurniture.init.FFRegistry;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;

import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

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

	public FurnitureStationContainerScreen(FurnitureStationContainer container, PlayerInventory playerInventory, ITextComponent title)
	{
		super(container, playerInventory, title);
	}

	@Override
	protected void init()
	{
		imageWidth = 176;
		imageHeight = 222;

		inventoryLabelY = imageHeight - 94;

		scrolling = false;
		startIndex = 0;
		scrollOffset = 0F;

		super.init();
	}

	@Override
	public void render(MatrixStack pose, int mouseX, int mouseY, float partialTicks)
	{
		renderBackground(pose);
		super.render(pose, mouseX, mouseY, partialTicks);
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

			for(int i = startIndex; i < results.size(); i++)
			{
				ItemStack resultItem = results.get(i);

				int resultItemX = centerX + 17 + 18 * (i % 6);
				int resultItemY = centerY + 46 + yOffset;

				if(i % 6 == 5)
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
			int centerX = (width - imageWidth) / 2;
			int centerY = (height - imageHeight) / 2;

			int yOffset = 0;
			int maxY = centerY + 46 + (18 * 4);

			for(int i = startIndex; i < results.size(); i++)
			{
				int resultItemX = centerX + 17 + 18 * (i % 6);
				int resultItemY = centerY + 46 + yOffset;

				if(i % 6 == 5)
					yOffset += 18;
				if(resultItemY >= maxY)
					break;

				if(mouseX >= resultItemX && mouseY >= resultItemY && mouseX < resultItemX + 18 && mouseY < resultItemY + 18)
				{
					Minecraft mc = getMinecraft();
					mc.getSoundManager().play(SimpleSound.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1F));
					mc.gameMode.handleInventoryButtonClick(menu.containerId, i);
					return true;
				}
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

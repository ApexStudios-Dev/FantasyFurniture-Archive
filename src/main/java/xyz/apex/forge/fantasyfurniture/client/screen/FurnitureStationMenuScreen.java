package xyz.apex.forge.fantasyfurniture.client.screen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;

import xyz.apex.forge.apexcore.revamp.client.screen.BaseMenuScreen;
import xyz.apex.forge.commonality.Mods;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.init.FurnitureStation;
import xyz.apex.forge.fantasyfurniture.menu.FurnitureStationMenu;
import xyz.apex.forge.fantasyfurniture.net.C2SSyncSelectedResultPacket;

import java.util.*;
import java.util.stream.Collectors;

import static org.lwjgl.glfw.GLFW.*;

public class FurnitureStationMenuScreen extends BaseMenuScreen<FurnitureStationMenu>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(Mods.FANTASY_FURNITURE, "textures/gui/container/furniture_station.png");

	private int clayIndex = 0;
	private int woodIndex = 0;
	private int stoneIndex = 0;
	private int cycleCounter = 0;

	private float scrollOffset = 0F;
	private int startIndex = 0;
	private boolean scrolling = false;

	@Nullable private EditBox searchBox;
	private boolean focusSearchBoxNextTick = false;
	private int currentSearchSyntaxColor = 0xffffff;
	private boolean changeSearchSyntaxColor = true;

	private final Map<TagKey<Item>, List<ItemStack>> tagValues = Maps.newHashMap();
	private final Map<Item, List<TagKey<Item>>> itemTags = Maps.newHashMap();

	public FurnitureStationMenuScreen(FurnitureStationMenu menu, Inventory playerInventory, Component title)
	{
		super(menu, playerInventory, title, TEXTURE);
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

		var text = searchBox == null ? "" : searchBox.getValue();

		var s = 15 + (16 * 3) + 10;
		var e = imageWidth - 12;

		var w = e - s;
		var h = 16;
		var x = leftPos + s;
		var y = topPos + titleLabelY + font.lineHeight + 6;

		searchBox = addRenderableWidget(new EditBox(font, x, y, w, h, Component.translatable("gui.recipebook.search_hint")));
		searchBox.setMaxLength(50);
		searchBox.setBordered(true);
		searchBox.setVisible(true);
		searchBox.setTextColor(currentSearchSyntaxColor);
		searchBox.setValue(text);
		searchBox.setCanLoseFocus(true);
	}

	@Override
	public void containerTick()
	{
		if(searchBox != null)
		{
			var claySlot = menu.getClaySlot();
			var woodSlot = menu.getWoodSlot();
			var stoneSlot = menu.getStoneSlot();

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

		updateSearchBoxSyntaxHighlighting();
		super.containerTick();
	}

	@Override
	public void removed()
	{
		searchBox = null;
		minecraft.keyboardHandler.setSendRepeatsToGui(false);
		super.removed();
	}

	@Override
	public void render(PoseStack pose, int mouseX, int mouseY, float partialTicks)
	{
		super.render(pose, mouseX, mouseY, partialTicks);

		if(searchBox != null && searchBox.isVisible() && !searchBox.isFocused() && searchBox.getValue().isEmpty())
			drawString(pose, font, searchBox.getMessage().copy().withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC), searchBox.x + 2, searchBox.y + (font.lineHeight / 2), searchBox.getFGColor());

		renderResults(pose, mouseX, mouseY);
		renderSlotBackgrounds(pose, mouseX, mouseY);

		{
			if(searchBox != null && searchBox.isVisible())
			{
				if(searchBox.isMouseOver(mouseX, mouseY))
				{
					renderComponentTooltip(pose, Arrays.asList(
							Component.literal("'")
									.append(Component.literal("@")
											.withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC))
									.append("' -> Search by ")
									.append(Component.literal("Mod ID")
											.withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC)),

							Component.literal("'")
									.append(Component.literal("#")
											.withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC))
									.append("' -> Search by ")
									.append(Component.literal("ItemTag")
											.withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC))

							/*StringTextComponent.EMPTY,
							new StringTextComponent("Examples:")
									.withStyle(TextFormatting.DARK_GRAY, TextFormatting.ITALIC),
							new StringTextComponent("'")
									.withStyle(TextFormatting.DARK_GRAY, TextFormatting.ITALIC)
									.append(new StringTextComponent("@" + FantasyFurniture.ID)
											.withStyle(TextFormatting.GRAY))
									.append("' - Search for items from the mod '")
									.append(new StringTextComponent(ModList.get().getModContainerById(FantasyFurniture.ID).map(ModContainer::getModInfo).map(IModInfo::getDisplayName).orElse("Fantasy's Furniture"))
											.withStyle(TextFormatting.GRAY))
									.append("'"),
							new StringTextComponent("'")
									.withStyle(TextFormatting.DARK_GRAY, TextFormatting.ITALIC)
									.append(new StringTextComponent("#" + FurnitureSet.NORDIC.serializedName)
											.withStyle(TextFormatting.GRAY))
									.append("' - Search for all '")
									.append(new StringTextComponent(FurnitureSet.NORDIC.englishName)
											.withStyle(TextFormatting.GRAY))
									.append("' Items")*/
					), mouseX, mouseY, font);
				}
			}
		}
	}

	@Override
	protected void renderBg(PoseStack pose, float partialTicks, int mouseX, int mouseY)
	{
		super.renderBg(pose, partialTicks, mouseX, mouseY);
		var i = (width - imageWidth) / 2;
		var j = (height - imageHeight) / 2;
		var k = (int) (57F * scrollOffset);
		blit(pose, i + 127, j + 45 + k, 194 + (scrollbarActive() ? 0 : 12), 0, 12, 15);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
	{
		scrolling = false;

		if(searchBox != null && searchBox.active && searchBox.isVisible())
		{
			if(searchBox.mouseClicked(mouseX, mouseY, mouseButton))
			{
				changeSearchSyntaxColor = true;
				return true;
			}

			if(mouseButton == GLFW_MOUSE_BUTTON_RIGHT && searchBox.isMouseOver(mouseX, mouseY))
			{
				changeSearchSyntaxColor = true;
				searchBox.setValue("");
				return true;
			}
		}

		if(clickedResult(mouseX, mouseY, mouseButton))
			return true;

		if(!menu.getResults().isEmpty())
		{
			var x = leftPos + 128;
			var y = topPos + 46;
			var w = 12;
			var h = 71;

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
		var results = menu.getResults();
		var size = results.size();

		if(scrolling && scrollbarActive())
		{
			var i = topPos + 44;
			var j = i + 73;

			var offscreenRows = (size + 6 - 1) / 4 - 4;

			scrollOffset = ((float) mouseY - (float) i - 7.5F) / ((float) (j - i) - 15F);
			scrollOffset = Mth.clamp(scrollOffset, 0F, 1F);
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
			var results = menu.getResults();
			var size = results.size();

			var offscreenRows = (size + 6 - 1) / 4 - 4;

			scrollOffset = (float) ((double) scrollOffset - scrollDelta / (double) offscreenRows);
			scrollOffset = Mth.clamp(scrollOffset, 0F, 1F);
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
				{
					changeSearchSyntaxColor = true;
					return true;
				}

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
		{
			changeSearchSyntaxColor = true;
			return true;
		}

		return super.charTyped(typedChar, modifiers);
	}

	@Override
	public List<? extends GuiEventListener> children()
	{
		if(searchBox != null && !searchBox.active)
		{
			var kids = Lists.newArrayList(super.children());
			kids.remove(searchBox);
			return kids;
		}

		return super.children();
	}

	private void updateSearchBoxSyntaxHighlighting()
	{
		if(searchBox == null || !changeSearchSyntaxColor)
			return;

		var value = searchBox.getValue();

		if(StringUtils.isBlank(value))
		{
			var syntaxColor = 0xffffff;
			searchBox.setTextColor(syntaxColor);
			currentSearchSyntaxColor = syntaxColor;
			return;
		}

		var results = menu.getResults();

		if(!results.isEmpty())
		{
			for(var j = startIndex; j < results.size(); j++)
			{
				var resultItem = results.get(j);

				if(isItemValid(resultItem))
				{
					changeSearchSyntaxColor = false;
					var syntaxColor = DyeColor.GREEN.getTextColor();
					searchBox.setTextColor(syntaxColor);
					currentSearchSyntaxColor = syntaxColor;
					return;
				}
			}
		}

		var syntaxColor = DyeColor.RED.getTextColor();
		searchBox.setTextColor(syntaxColor);
		currentSearchSyntaxColor = syntaxColor;
		changeSearchSyntaxColor = false;
	}

	private void renderSlotBackgrounds(PoseStack pose, int mouseX, int mouseY)
	{
		var claySlot = menu.getClaySlot();
		var woodSlot = menu.getWoodSlot();
		var stoneSlot = menu.getStoneSlot();

		clayIndex = renderSlotBackground(claySlot, FurnitureStation.CLAY, pose, mouseX, mouseY, clayIndex);
		woodIndex = renderSlotBackground(woodSlot, FurnitureStation.WOOD, pose, mouseX, mouseY, woodIndex);
		stoneIndex = renderSlotBackground(stoneSlot, FurnitureStation.STONE, pose, mouseX, mouseY, stoneIndex);

		cycleCounter++;

		if(cycleCounter > 125)
			cycleCounter = 0;
	}

	private int renderSlotBackground(Slot slot, TagKey<Item> backgroundTag, PoseStack pose, int mouseX, int mouseY, int counter)
	{
		var index  = counter;

		if(!slot.hasItem())
		{
			var values = tagValues.computeIfAbsent(backgroundTag, $ -> ForgeRegistries.ITEMS.tags().getTag(backgroundTag).stream().map(Item::getDefaultInstance).collect(Collectors.toList()));

			if(cycleCounter == 125)
			{
				index++;

				if(index >= values.size())
					index = 0;
			}

			var x = leftPos + slot.x;
			var y = topPos + slot.y;

			var stack = values.get(index);
			var stackFont = IClientItemExtensions.of(stack).getFont(stack, IClientItemExtensions.FontContext.TOOLTIP);
			stackFont = stackFont == null ? font : stackFont;

			renderTranslucentItem(pose, stack, x, y);
			itemRenderer.renderGuiItemDecorations(stackFont, stack, x, y);

			// TODO: Maybe this should be a config?
			//  Show tooltip theres more than 1 valid input item
			if(/*backgroundTag.getValues().size() > 1 &&*/ menu.getCarried().isEmpty())
			{
				if(mouseX >= x && mouseY >= y && mouseX < x + 16 && mouseY < y + 16)
				{
					var name = stack.getHoverName();
					var accepts = FurnitureStation.buildAcceptsAnyComponent(backgroundTag);
					renderTooltip(pose, Lists.newArrayList(name, accepts), Optional.empty(), mouseX, mouseY, stackFont);
				}
			}
		}

		return index;
	}

	private boolean isItemValid(ItemStack stack)
	{
		if(stack.isEmpty())
			return false;
		if(searchBox == null)
			return true;

		if(!searchBox.isVisible() || !searchBox.active)
			return true;

		var value = searchBox.getValue();

		if(StringUtils.isBlank(value))
			return true;

		var values = value.split("\\s+");
		var item = stack.getItem();
		var tags = itemTags.computeIfAbsent(item, $ -> ForgeRegistries.ITEMS.tags().getReverseTag(item).map(t -> t.getTagKeys().collect(Collectors.toList())).orElse(Collections.emptyList()));
		var displayName = stack.getHoverName().getString();

		for(var filter : values)
		{
			if(filter.startsWith("#"))
			{
				var rawTagName = filter.substring(1);

				for(var tag : tags)
				{
					var tagName = tag.location();
					var namespace = tagName.getNamespace();
					var path = tagName.getPath();

					if(StringUtils.containsIgnoreCase(namespace, rawTagName) || StringUtils.containsIgnoreCase(path, rawTagName))
						return true;
				}
			}
			else if(filter.startsWith("@"))
			{
				var raw = filter.substring(1);
				var modId = item.getCreatorModId(stack);

				if(StringUtils.containsIgnoreCase(modId, raw))
					return true;
			}
			else
			{
				if(StringUtils.containsIgnoreCase(displayName, filter))
					return true;
			}
		}

		return false;
	}

	private void renderResults(PoseStack pose, int mouseX, int mouseY)
	{
		var results = menu.getResults();

		if(!results.isEmpty())
		{
			var mc = getMinecraft();

			var centerX = (width - imageWidth) / 2;
			var centerY = (height - imageHeight) / 2;

			var yOffset = 0;
			var canHover = true;
			var maxY = centerY + 46 + (18 * 4);

			var selectedResult = menu.getResultSlot().getItem();
			var visibleItemIndex = 0;

			var hoveredStack = ItemStack.EMPTY;

			for(var j = startIndex; j < results.size(); j++)
			{
				var resultItem = results.get(j);

				if(!isItemValid(resultItem))
					continue;

				var resultItemX = centerX + 17 + 18 * (visibleItemIndex % 6);
				var resultItemY = centerY + 46 + yOffset;

				if(visibleItemIndex % 6 == 5)
					yOffset += 18;
				if(resultItemY >= maxY)
					break;

				var isHovered = false;
				var vOffset = 0F;

				if(canHover)
					isHovered = mouseX >= resultItemX && mouseY >= resultItemY && mouseX < resultItemX + 18 && mouseY < resultItemY + 18;

				if(isHovered)
				{
					canHover = false;
					vOffset = 18F * 2F;
				}

				if(!selectedResult.isEmpty() && ItemStack.matches(selectedResult, resultItem))
					vOffset = 18F;

				RenderSystem.setShaderTexture(0, TEXTURE);
				blit(pose, resultItemX - 1, resultItemY - 1, 176F, vOffset, 18, 18, 256, 256);

				var stackFont = IClientItemExtensions.of(resultItem).getFont(resultItem, IClientItemExtensions.FontContext.TOOLTIP);
				stackFont = stackFont == null ? font : stackFont;

				itemRenderer.renderGuiItem(resultItem, resultItemX, resultItemY);
				itemRenderer.renderGuiItemDecorations(stackFont, resultItem, resultItemX, resultItemY);

				if(isHovered && hoveredStack.isEmpty())
					hoveredStack = resultItem;

				visibleItemIndex++;
			}

			if(!hoveredStack.isEmpty())
				renderTooltip(pose, hoveredStack, mouseX, mouseY);
		}
	}

	private boolean clickedResult(double mouseX, double mouseY, int mouseButton)
	{
		if(mouseButton != GLFW_MOUSE_BUTTON_LEFT)
			return false;

		var results = menu.getResults();

		if(!results.isEmpty())
		{
			var selectedItem = menu.getResultSlot().getItem();

			var centerX = (width - imageWidth) / 2;
			var centerY = (height - imageHeight) / 2;

			var yOffset = 0;
			var maxY = centerY + 46 + (18 * 4);
			var visibleItemIndex = 0;

			for(var j = startIndex; j < results.size(); j++)
			{
				var resultItem = results.get(j);

				if(!isItemValid(resultItem))
					continue;

				var isSelected = false;

				if(!selectedItem.isEmpty() && ItemStack.matches(selectedItem, resultItem))
					isSelected = true;

				var resultItemX = centerX + 17 + 18 * (visibleItemIndex % 6);
				var resultItemY = centerY + 46 + yOffset;

				if(visibleItemIndex % 6 == 5)
					yOffset += 18;
				if(resultItemY >= maxY)
					break;

				if(!isSelected)
				{
					if(mouseX >= resultItemX && mouseY >= resultItemY && mouseX < resultItemX + 18 && mouseY < resultItemY + 18)
					{
						Minecraft mc = getMinecraft();
						mc.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1F));
						// mc.gameMode.handleInventoryButtonClick(menu.containerId, j);
						menu.setSelectedResult(j);
						FantasyFurniture.NETWORK.sendToServer(new C2SSyncSelectedResultPacket(j));
						return true;
					}
				}

				visibleItemIndex++;
			}
		}

		return false;
	}

	private void renderTranslucentItem(PoseStack pose, ItemStack stack, int x, int y)
	{
		var modelViewStack = RenderSystem.getModelViewStack();
		modelViewStack.pushPose();

		modelViewStack.mulPoseMatrix(pose.last().pose());

		var stackFont = IClientItemExtensions.of(stack).getFont(stack, IClientItemExtensions.FontContext.TOOLTIP);
		stackFont = stackFont == null ? font : stackFont;

		itemRenderer.renderAndDecorateFakeItem(stack, x, y);

		RenderSystem.enableDepthTest();
		RenderSystem.depthFunc(516);
		GuiComponent.fill(pose, x, y, x + 16, y + 16, 822083583);
		RenderSystem.depthFunc(515);
		RenderSystem.disableDepthTest();

		itemRenderer.renderGuiItemDecorations(stackFont, stack, x, y, null);

		modelViewStack.popPose();
	}

	private boolean scrollbarActive()
	{
		return menu.getResults().size() > 6 * 4;
	}
}
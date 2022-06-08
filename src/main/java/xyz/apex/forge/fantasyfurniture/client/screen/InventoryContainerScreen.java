package xyz.apex.forge.fantasyfurniture.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.container.InventoryContainer;

@OnlyIn(Dist.CLIENT)
public class InventoryContainerScreen<CONTAINER extends InventoryContainer> extends AbstractContainerScreen<CONTAINER>
{
	protected final ResourceLocation texture;

	public InventoryContainerScreen(CONTAINER menu, Inventory playerInventory, Component titleComponent, ResourceLocation texture)
	{
		super(menu, playerInventory, titleComponent);

		this.texture = texture;
	}

	@Override
	protected void init()
	{
		super.init();
		titleLabelX = (imageWidth - font.width(title)) / 2;
	}

	@Override
	public void render(PoseStack pose, int mouseX, int mouseY, float partialTick)
	{
		renderBackground(pose);
		super.render(pose, mouseX, mouseY, partialTick);
		renderTooltip(pose, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack pose, float partialTick, int mouseX, int mouseY)
	{
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		RenderSystem.setShaderTexture(0, texture);
		var i = (width - imageWidth) / 2;
		var j = (height - imageHeight) / 2;
		blit(pose, i, j, 0, 0, imageWidth, imageHeight);
	}
}

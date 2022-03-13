package xyz.apex.forge.fantasyfurniture.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.container.InventoryContainer;

@OnlyIn(Dist.CLIENT)
public class InventoryContainerScreen<CONTAINER extends InventoryContainer> extends ContainerScreen<CONTAINER>
{
	protected final ResourceLocation texture;

	public InventoryContainerScreen(CONTAINER menu, PlayerInventory playerInventory, ITextComponent titleComponent, ResourceLocation texture)
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
	public void render(MatrixStack pose, int mouseX, int mouseY, float partialTick)
	{
		renderBackground(pose);
		super.render(pose, mouseX, mouseY, partialTick);
		renderTooltip(pose, mouseX, mouseY);
	}

	@Override
	protected void renderBg(MatrixStack pose, float partialTick, int mouseX, int mouseY)
	{
		RenderSystem.color4f(1F, 1F, 1F, 1F);
		minecraft.getTextureManager().bind(texture);
		int i = (width - imageWidth) / 2;
		int j = (height - imageHeight) / 2;
		blit(pose, i, j, 0, 0, imageWidth, imageHeight);
	}
}

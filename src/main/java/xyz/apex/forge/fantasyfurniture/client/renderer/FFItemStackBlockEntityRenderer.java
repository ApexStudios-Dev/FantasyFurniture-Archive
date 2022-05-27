package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.renderer.entity.WidowBloomBlockEntityRenderer;
import xyz.apex.forge.fantasyfurniture.init.Decorations;
import xyz.apex.java.utility.Lazy;

public final class FFItemStackBlockEntityRenderer extends ItemStackTileEntityRenderer
{
	private final WidowBloomBlockEntityRenderer widowBloomBlockEntityRenderer;
	private final Lazy<WidowBloomBlockEntity> widowBloomBlockEntity = Lazy.of(Decorations.VENTHYR_WIDOW_BLOOM_BLOCK_ENTITY::createBlockEntity);

	@Override
	public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack pose, IRenderTypeBuffer buffer, int light, int overlay)
	{
		float partialTick = Minecraft.getInstance().getDeltaFrameTime();

		if(Decorations.VENTHYR_WIDOW_BLOOM_BLOCK_ITEM.isInStack(stack))
		{
			WidowBloomBlockEntity blockEntity = this.widowBloomBlockEntity.get();
			widowBloomBlockEntityRenderer.render(blockEntity, partialTick, pose, buffer, light, overlay);
		}
		else
			super.renderByItem(stack, transformType, pose, buffer, light, overlay);
	}

	public FFItemStackBlockEntityRenderer()
	{
		super();

		widowBloomBlockEntityRenderer = new WidowBloomBlockEntityRenderer(TileEntityRendererDispatcher.instance);
	}
}

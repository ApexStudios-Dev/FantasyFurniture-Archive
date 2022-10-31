package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;
import xyz.apex.forge.fantasyfurniture.common.block.entity.WidowBloomBlockEntity;

public final class WidowBloomBlockEntityRenderer implements BlockEntityRenderer<WidowBloomBlockEntity>
{
	private final WidowBloomModel model;

	public WidowBloomBlockEntityRenderer(BlockEntityRendererProvider.Context ctx)
	{
		model = new WidowBloomModel(ctx.bakeLayer(WidowBloomModel.LAYER_LOCATION));
	}

	@Override
	public void render(WidowBloomBlockEntity blockEntity, float partialTicks, PoseStack pose, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
	{
		var renderType = model.renderType(WidowBloomModel.TEXTURE);
		var modelBuffer = buffer.getBuffer(renderType);

		pose.pushPose();

		if(blockEntity.hasLevel())
		{
			var blockState = blockEntity.getBlockState();
			var facing = BaseBlock.getFacing(blockState);

			pose.translate(.5D, .5D, .5D);
			pose.mulPose(Vector3f.YP.rotationDegrees(-facing.toYRot()));
			pose.mulPose(Vector3f.XP.rotationDegrees(180F));
			pose.translate(0D, -1D, 0D);
		}
		else
		{
			pose.translate(.5D, .5D, .5D);
			pose.mulPose(Vector3f.ZP.rotationDegrees(180F));
		}

		model.renderToBuffer(pose, modelBuffer, combinedLight, combinedOverlay, 1F, 1F, 1F, 1F);

		pose.popPose();
	}
}

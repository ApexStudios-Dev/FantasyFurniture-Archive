package xyz.apex.forge.fantasyfurniture.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;

import xyz.apex.forge.fantasyfurniture.block.decorations.WidowBloomBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.WidowBloomModel;

public final class WidowBloomBlockEntityRenderer extends TileEntityRenderer<WidowBloomBlockEntity>
{
	private final WidowBloomModel model;

	public WidowBloomBlockEntityRenderer(TileEntityRendererDispatcher rendererDispatcher)
	{
		super(rendererDispatcher);

		model = new WidowBloomModel();
	}

	@Override
	public void render(WidowBloomBlockEntity blockEntity, float partialTicks, MatrixStack pose, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		RenderType renderType = RenderType.entityTranslucentCull(WidowBloomModel.TEXTURE);
		IVertexBuilder modelBuffer = buffer.getBuffer(renderType);

		pose.pushPose();

		if(blockEntity.hasLevel())
		{
			BlockState blockState = blockEntity.getBlockState();
			Direction facing = blockState.getValue(WidowBloomBlock.FACING);

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

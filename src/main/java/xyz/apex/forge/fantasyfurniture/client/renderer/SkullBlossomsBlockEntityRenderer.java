package xyz.apex.forge.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.commonality.SideOnly;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.forge.fantasyfurniture.common.block.entity.SkullBlossomsBlockEntity;


@SideOnly(SideOnly.Side.CLIENT)
public final class SkullBlossomsBlockEntityRenderer implements BlockEntityRenderer<SkullBlossomsBlockEntity>
{
	private final SkullBlossomsModel model;

	public SkullBlossomsBlockEntityRenderer(BlockEntityRendererProvider.Context ctx)
	{
		model = new SkullBlossomsModel(ctx.bakeLayer(SkullBlossomsModel.LAYER_LOCATION));
	}

	@Override
	public void render(SkullBlossomsBlockEntity blockEntity, float partialTicks, PoseStack pose, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
	{
		var renderType = AllBlocks.BONE_SKELETON_SKULL_BLOSSOMS.isIn(blockEntity.getBlockState()) ? model.renderType(SkullBlossomsModel.TEXTURE_SKELETON) : model.renderType(SkullBlossomsModel.TEXTURE_WITHER);
		var modelBuffer = buffer.getBuffer(renderType);

		pose.pushPose();

		if(blockEntity.hasLevel())
		{
			var blockState = blockEntity.getBlockState();
			var facing = BaseBlock.getFacing(blockState);

			pose.translate(.5D, .5D, .5D);
			pose.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
			pose.mulPose(Axis.XP.rotationDegrees(180F));
			pose.translate(0D, -1D, 0D);
		}
		else
		{
			pose.translate(.5D, .5D, .5D);
			pose.mulPose(Axis.ZP.rotationDegrees(180F));
		}

		model.renderToBuffer(pose, modelBuffer, combinedLight, combinedOverlay, 1F, 1F, 1F, 1F);

		pose.popPose();
	}
}

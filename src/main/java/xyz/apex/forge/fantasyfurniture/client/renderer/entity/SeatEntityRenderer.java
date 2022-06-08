package xyz.apex.forge.fantasyfurniture.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

@OnlyIn(Dist.CLIENT)
public final class SeatEntityRenderer extends EntityRenderer<SeatEntity>
{
	public SeatEntityRenderer(EntityRendererProvider.Context ctx)
	{
		super(ctx);
	}

	@Override
	public ResourceLocation getTextureLocation(SeatEntity seat)
	{
		return null;
	}

	@Override
	protected void renderNameTag(SeatEntity seat, Component nameTag, PoseStack pose, MultiBufferSource buffer, int packedLight)
	{
	}
}

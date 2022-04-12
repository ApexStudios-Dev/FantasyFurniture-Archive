package xyz.apex.forge.fantasyfurniture.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.entity.SeatEntity;

@OnlyIn(Dist.CLIENT)
public final class SeatEntityRenderer extends EntityRenderer<SeatEntity>
{
	public SeatEntityRenderer(EntityRendererManager manager)
	{
		super(manager);
	}

	@Override
	public ResourceLocation getTextureLocation(SeatEntity seat)
	{
		return null;
	}

	@Override
	protected void renderNameTag(SeatEntity seat, ITextComponent nameTag, MatrixStack pose, IRenderTypeBuffer buffer, int packedLight)
	{
	}
}

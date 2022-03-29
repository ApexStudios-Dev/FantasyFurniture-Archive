package xyz.apex.forge.fantasyfurniture.mixin.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.hotswaps.ClientMixinHotSwaps;

@Mixin(LivingRenderer.class)
@OnlyIn(Dist.CLIENT)
public class LivingRendererMixin
{
	@Inject(method = "setupRotations", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/matrix/MatrixStack;mulPose(Lnet/minecraft/util/math/vector/Quaternion;)V", ordinal = 6, shift = At.Shift.AFTER))
	private void setupRotations(LivingEntity entity, MatrixStack pose, float ageInTicks, float rotationYaw, float partialTick, CallbackInfo ci)
	{
		ClientMixinHotSwaps.setupLivingRendererRotations(entity, pose);
	}
}

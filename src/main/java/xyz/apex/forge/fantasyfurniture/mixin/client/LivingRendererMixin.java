package xyz.apex.forge.fantasyfurniture.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

import xyz.apex.forge.fantasyfurniture.common.block.furniture.BedBlock;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
{
	@Inject(method = "setupRotations", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;mulPose(Lorg/joml/Quaternionf;)V", ordinal = 6, shift = At.Shift.AFTER))
	private void setupRotations(T entity, PoseStack pose, float ageInTicks, float rotationYaw, float partialTick, CallbackInfo ci)
	{
		var pos = entity.blockPosition();
		var level = entity.level;
		var blockState = level.getBlockState(pos);
		var block = blockState.getBlock();

		if(block instanceof BedBlock bed)
			bed.onFixBedRotations(entity, pose);
	}
}

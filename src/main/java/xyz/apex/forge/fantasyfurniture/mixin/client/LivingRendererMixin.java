package xyz.apex.forge.fantasyfurniture.mixin.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.block.base.BedBlock;

@Mixin(LivingRenderer.class)
public class LivingRendererMixin
{
	@Inject(method = "setupRotations", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/matrix/MatrixStack;mulPose(Lnet/minecraft/util/math/vector/Quaternion;)V", ordinal = 4, shift = At.Shift.AFTER))
	private void setupRotations(LivingEntity entity, MatrixStack pose, float ageInTicks, float rotationYaw, float partialTick, CallbackInfo ci)
	{
		BlockPos pos = entity.blockPosition();
		World level = entity.level;
		BlockState blockState = level.getBlockState(pos);
		Block block = blockState.getBlock();

		if(block instanceof BedBlock)
			((BedBlock) block).onFixBedRotations(entity, pose);
	}
}

package xyz.apex.forge.fantasyfurniture.hotswaps;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class ClientMixinHotSwaps
{
	public static void setupLivingRendererRotations(LivingEntity entity, MatrixStack pose)
	{
		BlockPos pos = entity.blockPosition();
		World level = entity.level;
		BlockState blockState = level.getBlockState(pos);

		if(blockState.isBed(level, pos, entity))
		{
			pose.translate(0D, .09D, .1D);
			pose.scale(.9F, .9F, .9F);
		}
	}
}

package xyz.apex.forge.fantasyfurniture.core;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.init.FFBlocks;

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
			if(FFBlocks.NORDIC_BED.hasBlockState(blockState))
				pose.translate(0, .09, .1);
		}
	}
}

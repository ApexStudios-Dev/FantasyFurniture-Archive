package xyz.apex.forge.fantasyfurniture.block.base.set;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3f;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;

public class SetBedSingleBlock extends BedBlock
{
	public SetBedSingleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected int getBedHeadMultiBlockIndex(BlockState blockState)
	{
		return 1;
	}

	@Override
	public void onFixBedRotations(LivingEntity entity, MatrixStack pose)
	{
		pose.scale(.9F, .9F, .9F);
		pose.mulPose(Vector3f.XP.rotationDegrees(180));
		pose.mulPose(Vector3f.ZP.rotationDegrees(180));
		pose.translate(3.25D, 0D, 0D);
	}
}

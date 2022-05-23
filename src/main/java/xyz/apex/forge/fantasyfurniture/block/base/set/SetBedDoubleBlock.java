package xyz.apex.forge.fantasyfurniture.block.base.set;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;

public class SetBedDoubleBlock extends BedBlock
{
	public SetBedDoubleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected int getBedFootMultiBlockIndex(BlockState blockState)
	{
		int index = pattern.getIndex(blockState);
		return index % 2 == 0 ? 2 : 3;
	}

	@Override
	public void onFixBedRotations(LivingEntity entity, MatrixStack pose)
	{
		pose.scale(.9F, .9F, .9F);
		pose.translate(0D, 0D, .025D);
	}
}

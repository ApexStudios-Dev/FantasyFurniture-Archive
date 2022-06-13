package xyz.apex.forge.fantasyfurniture.block.base.set;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.player.RemotePlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;

public class SetBedDoubleBlock extends BedBlock
{
	public SetBedDoubleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected int getBedFootMultiBlockIndex(BlockState blockState)
	{
		int index = getMultiBlockIndex(blockState);
		return index % 2 == 0 ? 2 : 3;
	}

	@Override
	public void onFixBedRotations(LivingEntity entity, PoseStack pose)
	{
		if(entity instanceof RemotePlayer)
		{
			pose.scale(.85F, .85F, .85F);
			pose.translate(0D, .2D, .15D);
		}
		else
		{
			pose.scale(.9F, .9F, .9F);
			pose.translate(0D, .2D, -.05D);
		}
	}

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_2x2x1;
	}
}

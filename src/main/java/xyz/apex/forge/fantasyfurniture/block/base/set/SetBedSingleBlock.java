package xyz.apex.forge.fantasyfurniture.block.base.set;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.player.RemotePlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;

public class SetBedSingleBlock extends BedBlock
{
	public SetBedSingleBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	protected int getBedFootMultiBlockIndex(BlockState blockState)
	{
		return 1;
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
}

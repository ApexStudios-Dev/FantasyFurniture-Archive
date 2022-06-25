package xyz.apex.forge.fantasyfurniture.block.base.set;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.core.BedBlock;
import xyz.apex.forge.fantasyfurniture.init.FFPatterns;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

public class SetBedSingleBlock extends BedBlock implements IFurnitureSetBlock
{
	protected final ModBlocks furnitureSet;

	public SetBedSingleBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(properties);

		this.furnitureSet = furnitureSet;
	}

	@Override
	public final ModBlocks getFurnitureSet()
	{
		return furnitureSet;
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

	@Override
	public MultiBlockPattern getMultiBlockPattern()
	{
		return FFPatterns.PATTERN_2x1x1;
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.bedSingle(this, blockState);
	}
}

package xyz.apex.forge.fantasyfurniture.block.furniture;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

public class BedSingleBlock extends BedBlock
{
	public BedSingleBlock(Properties properties)
	{
		super(properties);
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
		return ModPatterns.PATTERN_2x1x1;
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		if(ModBlocks.DUNMER_BED_SINGLE.has(blockState))
			return RenderShape.MODEL;

		return super.getRenderShape(blockState);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_BED_SINGLE.has(blockState))
			return HitBoxes.NORDIC.bedSingle(this, blockState);
		else if(ModBlocks.DUNMER_BED_SINGLE.has(blockState))
			return HitBoxes.DUNMER.bedSingle(this, blockState);
		else if(ModBlocks.VENTHYR_BED_SINGLE.has(blockState))
			return HitBoxes.VENTHYR.bedSingle(this, blockState);
		else if(ModBlocks.BONE_SKELETON_BED_SINGLE.has(blockState) || ModBlocks.BONE_WITHER_BED_SINGLE.has(blockState))
			return HitBoxes.BONE.bedSingle(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
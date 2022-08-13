package xyz.apex.forge.fantasyfurniture.block.furniture;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;
import xyz.apex.forge.fantasyfurniture.init.ModPatterns;

import java.util.function.Consumer;

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
		if(ModBlocks.DUNMER_BED_SINGLE.isIn(blockState))
			return RenderShape.MODEL;

		return super.getRenderShape(blockState);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_BED_SINGLE.isIn(blockState))
			return HitBoxes.NORDIC.bedSingle(this, blockState);
		else if(ModBlocks.DUNMER_BED_SINGLE.isIn(blockState))
			return HitBoxes.DUNMER.bedSingle(this, blockState);
		else if(ModBlocks.VENTHYR_BED_SINGLE.isIn(blockState))
			return HitBoxes.VENTHYR.bedSingle(this, blockState);
		else if(ModBlocks.BONE_SKELETON_BED_SINGLE.isIn(blockState) || ModBlocks.BONE_WITHER_BED_SINGLE.isIn(blockState))
			return HitBoxes.BONE.bedSingle(this, blockState);
		else if(ModBlocks.ROYAL_BED_SINGLE.isIn(blockState))
			return HitBoxes.ROYAL.bedSingle(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends BedSingleBlock
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(DyeableBlock.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return DyeableBlock.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			DyeableBlock.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
			return DyeableBlock.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = DyeableBlock.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return DyeableBlock.getCloneItemStack(blockState, level, pos, stack);
		}
	}
}
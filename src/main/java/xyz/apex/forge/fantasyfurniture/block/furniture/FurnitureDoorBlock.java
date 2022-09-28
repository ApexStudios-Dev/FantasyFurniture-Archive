package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.List;

public class FurnitureDoorBlock extends DoorBlock
{
	public FurnitureDoorBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		var half = blockState.getOptionalValue(HALF).orElse(DoubleBlockHalf.LOWER);
		return half == DoubleBlockHalf.LOWER ? RenderShape.MODEL : RenderShape.INVISIBLE;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.NORDIC.doorDouble(this, blockState);
		else if(ModBlocks.NORDIC_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.NORDIC.doorSingle(this, blockState);
		else if(ModBlocks.DUNMER_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.DUNMER.doorDouble(this, blockState);
		else if(ModBlocks.DUNMER_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.DUNMER.doorSingle(this, blockState);
		else if(ModBlocks.VENTHYR_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.VENTHYR.doorDouble(this, blockState);
		else if(ModBlocks.VENTHYR_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.VENTHYR.doorSingle(this, blockState);
		else if(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.isIn(blockState) || ModBlocks.BONE_WITHER_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.BONE.doorDouble(this, blockState);
		else if(ModBlocks.BONE_SKELETON_DOOR_SINGLE.isIn(blockState) || ModBlocks.BONE_WITHER_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.BONE.doorSingle(this, blockState);
		else if(ModBlocks.ROYAL_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.ROYAL.doorDouble(this, blockState);
		else if(ModBlocks.ROYAL_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.ROYAL.doorSingle(this, blockState);
		/*else if(ModBlocks.NECROLORD_DOOR_DOUBLE.isIn(blockState))
			return HitBoxes.NECROLORD.doorDouble(this, blockState);
		else if(ModBlocks.NECROLORD_DOOR_SINGLE.isIn(blockState))
			return HitBoxes.NECROLORD.doorSingle(this, blockState);*/

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends FurnitureDoorBlock implements IDyeable
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(IDyeable.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var color = super.getMapColor(blockState, level, pos, defaultColor);
			return IDyeable.getDyedMapColor(blockState, level, pos, color);
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
		{
			super.createBlockStateDefinition(builder);
			IDyeable.registerProperties(builder::add);
		}

		@Nullable
		@Override
		public BlockState getStateForPlacement(BlockPlaceContext ctx)
		{
			var placementBlockState = super.getStateForPlacement(ctx);
			return IDyeable.getStateForPlacement(ctx, placementBlockState);
		}

		@Override
		public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
		{
			var interactionResult = IDyeable.use(blockState, level, pos, player, hand);

			if(interactionResult.consumesAction())
				return interactionResult;

			return super.use(blockState, level, pos, player, hand, result);
		}

		@Override
		public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
		{
			var stack = super.getCloneItemStack(blockState, target, level, pos, player);
			return IDyeable.getCloneItemStack(blockState, level, pos, stack);
		}

		@Override
		public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
		{
			super.appendHoverText(stack, level, tooltip, flag);
			IDyeable.appendHoverText(this, tooltip);
		}
	}
}
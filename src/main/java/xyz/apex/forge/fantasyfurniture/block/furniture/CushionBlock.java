package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.SeatBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.List;
import java.util.function.Consumer;

public class CushionBlock extends SeatBlock
{
	public static final VoxelShape ROYAL_FLOOR_CUSHION_SHAPE = box(2, 0, 2, 14, 3, 14);
	public static final VoxelShaper ROYAL_FLOOR_CUSHION_SHAPER = VoxelShaper.forHorizontal(ROYAL_FLOOR_CUSHION_SHAPE, Direction.NORTH);

	public CushionBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		if(ModBlocks.ROYAL_FLOOR_CUSHION.isIn(blockState))
			return .0D;
		else if(ModBlocks.ROYAL_CUSHION.isIn(blockState))
			return .3D;
		return .2D;
	}

	@Override
	public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float distance)
	{
		super.fallOn(level, blockState, pos, entity, distance * .5F);
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
	{
		if(entity.isSuppressingBounce())
			super.updateEntityAfterFallOn(level, entity);
		else
			bounceUp(entity);
	}

	protected void bounceUp(Entity entity)
	{
		var deltaMovement = entity.getDeltaMovement();

		if(deltaMovement.y < 0D)
		{
			var d0 = entity instanceof LivingEntity ? 1D : .8D;
			entity.setDeltaMovement(deltaMovement.x, -deltaMovement.y * (double) .66F * d0, deltaMovement.z);
		}
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_CUSHION.isIn(blockState))
			return HitBoxes.NORDIC.cushion(this, blockState);
		else if(ModBlocks.DUNMER_CUSHION.isIn(blockState))
			return HitBoxes.DUNMER.cushion(this, blockState);
		else if(ModBlocks.VENTHYR_CUSHION.isIn(blockState))
			return HitBoxes.VENTHYR.cushion(this, blockState);
		else if(ModBlocks.BONE_SKELETON_SKULL.isIn(blockState) || ModBlocks.BONE_WITHER_SKULL.isIn(blockState))
			return HitBoxes.BONE.cushion(this, blockState);
		else if(ModBlocks.ROYAL_CUSHION.isIn(blockState))
			return HitBoxes.ROYAL.cushion(this, blockState);
		else if(ModBlocks.ROYAL_FLOOR_CUSHION.isIn(blockState))
			return ROYAL_FLOOR_CUSHION_SHAPER.get(BaseBlock.getFacing(blockState));

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends CushionBlock implements IDyeable
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
		protected void registerProperties(Consumer<Property<?>> consumer)
		{
			super.registerProperties(consumer);
			IDyeable.registerProperties(consumer);
		}

		@Override
		protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
		{
			placementBlockState = super.modifyPlacementState(placementBlockState, ctx);
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
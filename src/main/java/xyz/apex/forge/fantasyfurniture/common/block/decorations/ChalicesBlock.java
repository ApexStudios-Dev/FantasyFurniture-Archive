package xyz.apex.forge.fantasyfurniture.common.block.decorations;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.AllBlocks;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

import java.util.List;
import java.util.function.Consumer;

public class ChalicesBlock extends StackedBlock
{
	public static final VoxelShape VENTHYR_SHAPE_0 = box(6.5D, 0D, 6.5D, 9.5D, 8D, 9.5D);

	public static final VoxelShape VENTHYR_SHAPE_1 = VoxelShaper.or(
			box(9.5D, 0D, 5.5D, 12.5D, 8D, 8.5D),
			box(2D, 0D, 8D, 6D, 8D, 12D)
	);

	public static final VoxelShape VENTHYR_SHAPE_2 = VoxelShaper.or(
			box(10.5D, 0D, 6.5D, 13.5D, 8D, 9.5D),
			box(2D, 0D, 9D, 6D, 8D, 13D),
			box(5D, 0D, 2D, 9D, 8D, 6D)
	);

	public static final VoxelShape BONE_SHAPE_0 = VoxelShaper.or(
			box(6.5, 0, 6.5, 9.5, 1, 9.5),
			box(7.25, 1, 7.25, 8.75, 4, 8.75),
			box(6, 4, 6, 10, 8, 10)
	);

	public static final VoxelShape BONE_SHAPE_1 = VoxelShaper.or(
			box(3.5, 0, 8.5, 6.5, 1, 11.5),
			box(4.25, 1, 9.25, 5.75, 4, 10.75),
			box(3, 4, 8, 7, 8, 12),
			box(8.25, 4, 4.25, 13.75, 8, 9.75),
			box(9, 0, 5, 13, 1, 9),
			box(10, 1, 6, 12, 4, 8)
	);

	public static final VoxelShape BONE_SHAPE_2 = VoxelShaper.or(
			box(4.5, 0, 10.5, 7.5, 1, 13.5),
			box(5.25, 1, 11.25, 6.75, 4, 12.75),
			box(4, 4, 10, 8, 8, 14),
			box(9.25, 4, 5.25, 14.75, 8, 10.75),
			box(10, 0, 6, 14, 1, 10),
			box(11, 1, 7, 13, 4, 9),
			box(1.25, 4, 2.25, 6.75, 8, 7.75),
			box(2, 0, 3, 6, 1, 7),
			box(3, 1, 4, 5, 4, 6)
	);

	public static final VoxelShape ROYAL_SHAPE_0 = box(6.5, 0, 6.5, 9.5, 8, 9.5);

	public static final VoxelShape ROYAL_SHAPE_1 = VoxelShaper.or(
			box(9.5, 0, 5.5, 12.5, 8, 8.5),
			box(2.5, 0, 8.5, 5.5, 8, 11.5)
	);

	public static final VoxelShape ROYAL_SHAPE_2 = VoxelShaper.or(
			box(10.5, 0, 6.5, 13.5, 8, 9.5),
			box(2.5, 0, 9.5, 5.5, 8, 12.5),
			box(5.5, 0, 2.5, 8.5, 8, 5.5)
	);

	public static final IntegerProperty CHALICES = IntegerProperty.create("chalices", 0, 2);
	public static final VoxelShaper VENTHYR_SHAPER_0 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper VENTHYR_SHAPER_1 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper VENTHYR_SHAPER_2 = VoxelShaper.forHorizontal(VENTHYR_SHAPE_2, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_0 = VoxelShaper.forHorizontal(BONE_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_1 = VoxelShaper.forHorizontal(BONE_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper BONE_SHAPER_2 = VoxelShaper.forHorizontal(BONE_SHAPE_2, Direction.NORTH);
	public static final VoxelShaper ROYAL_SHAPER_0 = VoxelShaper.forHorizontal(ROYAL_SHAPE_0, Direction.NORTH);
	public static final VoxelShaper ROYAL_SHAPER_1 = VoxelShaper.forHorizontal(ROYAL_SHAPE_1, Direction.NORTH);
	public static final VoxelShaper ROYAL_SHAPER_2 = VoxelShaper.forHorizontal(ROYAL_SHAPE_2, Direction.NORTH);

	public ChalicesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return CHALICES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = getFacing(blockState);
		var count = blockState.getValue(CHALICES);

		if(AllBlocks.VENTHYR_CHALICES.isIn(blockState))
			return (count == 2 ? VENTHYR_SHAPER_2 : count == 1 ? VENTHYR_SHAPER_1 : VENTHYR_SHAPER_0).get(facing);
		else if(AllBlocks.BONE_SKELETON_CHALICES.isIn(blockState) || AllBlocks.BONE_WITHER_CHALICES.isIn(blockState))
			return (count == 2 ? BONE_SHAPER_2 : count == 1 ? BONE_SHAPER_1 : BONE_SHAPER_0).get(facing);
		else if(AllBlocks.ROYAL_CHALICES.isIn(blockState))
			return (count == 2 ? ROYAL_SHAPER_2 : count == 1 ? ROYAL_SHAPER_1 : ROYAL_SHAPER_0).get(facing);

		return super.getShape(blockState, level, pos, ctx);
	}

	public static class Dyeable extends ChalicesBlock implements IDyeable
	{
		public Dyeable(Properties properties)
		{
			super(properties);

			registerDefaultState(IDyeable.registerDefaultBlockState(defaultBlockState()));
		}

		@Override
		public MaterialColor getMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
		{
			var mapColor = super.getMapColor(blockState, level, pos, defaultColor);
			return IDyeable.getDyedMapColor(blockState, level, pos, mapColor);
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

		@Override
		protected ItemStack getPoppedStack(Level level, BlockPos pos, BlockState blockState, int count, Player player, InteractionHand hand)
		{
			var stack = super.getPoppedStack(level, pos, blockState, count, player, hand);
			return IDyeable.getCloneItemStack(blockState, level, pos, stack);
		}
	}
}

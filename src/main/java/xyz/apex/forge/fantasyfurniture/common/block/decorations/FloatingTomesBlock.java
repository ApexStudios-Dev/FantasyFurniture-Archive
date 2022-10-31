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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.common.block.furniture.IDyeable;

import java.util.List;
import java.util.function.Consumer;

public class FloatingTomesBlock extends StackedBlock
{
	public static final VoxelShape SHAPE_0 = Block.box(2.5, 1.5, 4, 15.5, 5, 14);

	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			Block.box(2.5, 1.5, 4, 15.5, 5, 14),
			Block.box(3, 7.5, -0.5, 12, 11, 12.5)
	);

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);

	public static final IntegerProperty TOMES = IntegerProperty.create("tomes", 0, 1);

	public FloatingTomesBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getStackSizeProperty()
	{
		return TOMES;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var count = blockState.getValue(TOMES);
		var shaper = count == 0 ? SHAPER_0 : SHAPER_1;
		return shaper.get(facing);
	}

	public static class Dyeable extends FloatingTomesBlock implements IDyeable
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

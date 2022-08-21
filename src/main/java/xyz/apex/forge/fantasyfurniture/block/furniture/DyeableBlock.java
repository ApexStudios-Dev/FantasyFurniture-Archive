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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class DyeableBlock extends Block implements IDyeable
{
	public DyeableBlock(Properties properties)
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
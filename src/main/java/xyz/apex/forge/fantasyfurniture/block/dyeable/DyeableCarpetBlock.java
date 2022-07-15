package xyz.apex.forge.fantasyfurniture.block.dyeable;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.HitResult;

import xyz.apex.forge.fantasyfurniture.item.dyeable.IDyeableItem;

public class DyeableCarpetBlock extends CarpetBlock implements IDyeableBlock
{
	public DyeableCarpetBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(IDyeableBlock.withDefaultDyeColor(defaultBlockState()));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(DYE_COLOR_PROPERTY);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var placementBlockState = super.getStateForPlacement(ctx);

		if(placementBlockState != null)
		{
			var dyeColor = IDyeableItem.getDyeColor(ctx.getItemInHand());
			placementBlockState = IDyeableBlock.withDyeColor(placementBlockState, dyeColor);
		}

		return placementBlockState;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
	{
		var stack = super.getCloneItemStack(blockState, target, level, pos, player);
		var dyeColor = IDyeableBlock.getDyeColor(blockState);
		return IDyeableItem.withDyeColor(stack, dyeColor);
	}
}
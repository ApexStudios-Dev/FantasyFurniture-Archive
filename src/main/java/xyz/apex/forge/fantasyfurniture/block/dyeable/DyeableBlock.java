package xyz.apex.forge.fantasyfurniture.block.dyeable;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.HitResult;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.item.dyeable.IDyeableItem;

import java.util.function.Consumer;

public class DyeableBlock extends BaseBlock implements IDyeableBlock
{
	public DyeableBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(IDyeableBlock.withDefaultDyeColor(defaultBlockState()));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(DYE_COLOR_PROPERTY);
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

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
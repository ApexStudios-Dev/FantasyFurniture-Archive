package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class DyeableCarpetBlock extends CarpetBlock
{
	public DyeableCarpetBlock(Properties properties)
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		DyeableBlock.registerProperties(builder::add);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var placementBlockState = super.getStateForPlacement(ctx);
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
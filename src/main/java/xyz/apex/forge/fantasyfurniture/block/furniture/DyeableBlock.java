package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import xyz.apex.forge.apexcore.lib.block.IMultiBlock;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;

import java.util.Optional;
import java.util.function.Consumer;

public class DyeableBlock extends Block
{
	public DyeableBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(registerDefaultBlockState(defaultBlockState()));
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
		registerProperties(builder::add);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var placementBlockState = super.getStateForPlacement(ctx);
		return getStateForPlacement(ctx, placementBlockState);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		var interactionResult = use(blockState, level, pos, player, hand);

		if(interactionResult.consumesAction())
			return interactionResult;

		return super.use(blockState, level, pos, player, hand, result);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState blockState, HitResult target, BlockGetter level, BlockPos pos, Player player)
	{
		var stack = super.getCloneItemStack(blockState, target, level, pos, player);
		return getCloneItemStack(blockState, level, pos, stack);
	}

	public static BlockState registerDefaultBlockState(BlockState blockState)
	{
		return FantasyFurniture.setDyeColor(blockState, DyeColor.WHITE);
	}

	public static Optional<DyeColor> getDyeColor(BlockGetter level, BlockPos pos, BlockState blockState)
	{
		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);
			var originBlockState = level.getBlockState(origin);
			return FantasyFurniture.getDyeColor(originBlockState).or(() -> FantasyFurniture.getDyeColor(blockState));
		}

		return FantasyFurniture.getDyeColor(blockState);
	}

	public static boolean setDyeColor(LevelAccessor level, BlockPos pos, BlockState blockState, DyeColor dyeColor)
	{
		var changed = false;

		if(blockState.getBlock() instanceof IMultiBlock multiBlock)
		{
			var origin = multiBlock.getMultiBlockOriginPos(blockState, pos);

			for(var localPos : multiBlock.getMultiBlockLocalPositions())
			{
				var worldPos = multiBlock.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, localPos);

				if(worldPos.equals(pos))
					continue;

				var worldBlockState = level.getBlockState(worldPos);
				var newBlockState = FantasyFurniture.setDyeColor(worldBlockState, dyeColor);

				if(newBlockState != worldBlockState)
					changed = level.setBlock(worldPos, newBlockState, Block.UPDATE_ALL) || changed;
			}
		}

		if(blockState.getBlock() instanceof FurnitureDoorBlock)
		{
			var half = blockState.getValue(FurnitureDoorBlock.HALF);
			var otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
			var otherBlockState = level.getBlockState(otherPos);
			var newOtherBlockState = FantasyFurniture.setDyeColor(otherBlockState, dyeColor);

			if(newOtherBlockState != otherBlockState)
				changed = level.setBlock(otherPos, newOtherBlockState, Block.UPDATE_ALL) || changed;
		}

		var newBlockState = FantasyFurniture.setDyeColor(blockState, dyeColor);

		if(newBlockState != blockState)
			changed = level.setBlock(pos, newBlockState, Block.UPDATE_ALL) || changed;

		return changed;
	}

	public static MaterialColor getDyedMapColor(BlockState blockState, BlockGetter level, BlockPos pos, MaterialColor defaultColor)
	{
		return getDyeColor(level, pos, blockState).map(DyeColor::getMaterialColor).orElse(defaultColor);
	}

	public static void registerProperties(Consumer<Property<?>> consumer)
	{
		consumer.accept(FantasyFurniture.DYE_COLOR_PROPERTY);
	}

	@Nullable
	public static BlockState getStateForPlacement(BlockPlaceContext ctx, @Nullable BlockState placementBlockState)
	{
		if(placementBlockState != null)
		{
			var effectivelyFinal = placementBlockState;
			placementBlockState = FantasyFurniture.getDyeColor(ctx.getItemInHand()).map(c -> FantasyFurniture.setDyeColor(effectivelyFinal, c)).orElse(placementBlockState);
		}

		return placementBlockState;
	}

	public static InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand)
	{
		var stack = player.getItemInHand(hand);
		var dyeColor = DyeColor.getColor(stack);

		if(dyeColor != null && setDyeColor(level, pos, blockState, dyeColor))
		{
			if(!player.isCreative())
				stack.shrink(1);

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	public static ItemStack getCloneItemStack(BlockState blockState, BlockGetter level, BlockPos pos, ItemStack stack)
	{
		return getDyeColor(level, pos, blockState).map(color -> FantasyFurniture.setDyeColor(stack, color)).orElse(stack);
	}
}
package xyz.apex.forge.fantasyfurniture.block.decorations;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.fantasyfurniture.block.furniture.IDyeable;

import java.util.List;
import java.util.function.Consumer;

public abstract class StackedBlock extends BaseBlock
{
	private final Lazy<Integer> minValue = Lazy.of(() -> getMinValue(getStackSizeProperty()));
	private final Lazy<Integer> maxValue = Lazy.of(() -> getMaxValue(getStackSizeProperty()));

	public StackedBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(getStackSizeProperty(), 0));
	}

	public abstract IntegerProperty getStackSizeProperty();

	protected boolean isForStack(ItemStack stack)
	{
		return stack.is(asItem());
	}

	@Override
	protected @Nullable BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState != null)
		{
			var level = ctx.getLevel();
			var pos = ctx.getClickedPos();
			var blockState = level.getBlockState(pos);
			var property = getStackSizeProperty();

			if(blockState.hasProperty(property))
			{
				var current = blockState.getValue(property);
				var amount = Math.min(maxValue.get(), current + 1);

				if(amount != current || amount != placementBlockState.getValue(property))
				{
					placementBlockState = placementBlockState.setValue(property, amount);

					for(int i = 0; i < 5; i++)
					{
						level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState), pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 0D, 0D, 0D);
					}
				}
			}
		}

		return placementBlockState;
	}

	@Override
	public boolean canBeReplaced(BlockState blockState, BlockPlaceContext ctx)
	{
		var property = getStackSizeProperty();

		if(!blockState.hasProperty(property))
			return false;

		return !ctx.isSecondaryUseActive() && isForStack(ctx.getItemInHand()) && blockState.getValue(property) < maxValue.get() || super.canBeReplaced(blockState, ctx);
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
	{
		var stack = player.getItemInHand(hand);

		if(!isForStack(stack))
		{
			var stackSizeProperty = getStackSizeProperty();
			var count = blockState.getValue(stackSizeProperty);
			var minValue = this.minValue.get();
			int newCount = count - 1;

			if(newCount < minValue)
			{
				var poppedStack = getPoppedStack(level, pos, blockState, minValue, player, hand);
				level.destroyBlock(pos, false, player);

				if(!player.isCreative())
					popResource(level, pos, poppedStack);

				return InteractionResult.sidedSuccess(level.isClientSide);
			}

			var newBlockState = blockState.setValue(stackSizeProperty, newCount);
			BlockHelper.playBreakSound(level, pos, player);

			if(!player.isCreative())
			{
				var poppedStack = getPoppedStack(level, pos, newBlockState, newCount, player, hand);
				popResource(level, pos, poppedStack);
			}

			for(int i = 0; i < 5; i++)
			{
				level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, newBlockState), pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 0D, 0D, 0D);
			}

			level.setBlockAndUpdate(pos, newBlockState);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return super.use(blockState, level, pos, player, hand, hit);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(getStackSizeProperty());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
	{
		super.appendHoverText(stack, level, tooltip, flag);
		tooltip.add(getStackableTranslation().withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
	}

	public MutableComponent getStackableTranslation()
	{
		return new TranslatableComponent(getStackableTranslationKey());
	}

	protected ItemStack getPoppedStack(Level level, BlockPos pos, BlockState blockState, int count, Player player, InteractionHand hand)
	{
		return new ItemStack(this);
	}

	public final String getStackableTranslationKey()
	{
		return "%s.stacked".formatted(getDescriptionId());
	}

	public static int getMinValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i <= 0).min().orElse(0);
	}

	public static int getMaxValue(IntegerProperty property)
	{
		return property.getPossibleValues().stream().mapToInt(i -> i).filter(i -> i >= 0).max().orElse(0);
	}

	public static abstract class Dyeable extends StackedBlock implements IDyeable
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
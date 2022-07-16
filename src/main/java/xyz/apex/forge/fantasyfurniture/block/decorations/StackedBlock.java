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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.Lazy;

import xyz.apex.forge.apexcore.lib.block.BaseBlock;
import xyz.apex.forge.apexcore.lib.block.BlockHelper;

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
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
	{
		var stack = player.getItemInHand(hand);
		var stackSizeProperty = getStackSizeProperty();
		var count = blockState.getValue(stackSizeProperty);
		int newCount;
		var minValue = this.minValue.get();
		var maxValue = this.maxValue.get();

		if(isForStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount < minValue)
		{
			level.setBlockAndUpdate(pos, blockState.setValue(stackSizeProperty, minValue));
			level.destroyBlock(pos, !player.isCreative(), player);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		if(newCount <= maxValue)
		{
			if(newCount < count)
			{
				BlockHelper.playBreakSound(level, pos, player);

				if(!player.isCreative())
					popResource(level, pos, new ItemStack(this));
			}
			else
			{
				BlockHelper.playPlaceSound(level, pos, player);

				if(!player.isCreative())
					stack.shrink(1);
			}

			for(int i = 0; i < 5; i++)
			{
				level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState), pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 0D, 0D, 0D);
			}

			level.setBlockAndUpdate(pos, blockState.setValue(stackSizeProperty, newCount));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return InteractionResult.FAIL;
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
}
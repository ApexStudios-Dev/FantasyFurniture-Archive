package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.java.utility.Lazy;

import javax.annotation.Nullable;
import java.util.List;

public abstract class SimpleFourWayWaterLoggedStackedBlock extends SimpleFourWayWaterLoggedBlock implements IStackedBlock
{
	private final Lazy<Integer> minValue = Lazy.of(() -> IStackedBlock.getMinValue(getStackSizeProperty()));
	private final Lazy<Integer> maxValue = Lazy.of(() -> IStackedBlock.getMaxValue(getStackSizeProperty()));

	public SimpleFourWayWaterLoggedStackedBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(getStackSizeProperty(), 0));
	}

	@Override
	public abstract IntegerProperty getStackSizeProperty();

	@Override
	public boolean isForStack(ItemStack stack)
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(getStackSizeProperty());
		super.createBlockStateDefinition(builder);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag)
	{
		super.appendHoverText(stack, level, tooltip, flag);
		tooltip.add(getStackableTranslation().withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
	}

	@Override
	public String getStackableTranslationKey()
	{
		return getDescriptionId() + ".stacked";
	}
}

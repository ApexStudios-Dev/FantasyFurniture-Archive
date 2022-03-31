package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.java.utility.Lazy;

public abstract class SimpleFourWayStackedBlock extends SimpleFourWayBlock
{
	private final Lazy<Integer> minValue = Lazy.of(() -> getStackSizeProperty().getPossibleValues().stream().mapToInt(i -> i).filter(i -> i <= 0).min().orElse(0));
	private final Lazy<Integer> maxValue = Lazy.of(() -> getStackSizeProperty().getPossibleValues().stream().mapToInt(i -> i).filter(i -> i >= 0).max().orElse(0));

	public SimpleFourWayStackedBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(getStackSizeProperty(), 0));
	}

	protected abstract IntegerProperty getStackSizeProperty();

	protected boolean isForStack(ItemStack stack)
	{
		return stack.getItem() == asItem();
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		ItemStack stack = player.getItemInHand(hand);
		IntegerProperty stackSizeProperty = getStackSizeProperty();
		int count = blockState.getValue(stackSizeProperty);
		int newCount;
		int minValue = this.minValue.get();
		int maxValue = this.maxValue.get();

		if(isForStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount < minValue)
		{
			level.setBlockAndUpdate(pos, blockState.setValue(stackSizeProperty, minValue));
			level.destroyBlock(pos, !player.isCreative(), player);
			return ActionResultType.sidedSuccess(level.isClientSide);
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

			level.setBlockAndUpdate(pos, blockState.setValue(stackSizeProperty, newCount));
			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return ActionResultType.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(getStackSizeProperty());
		super.createBlockStateDefinition(builder);
	}
}

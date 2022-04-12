package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.java.utility.Lazy;

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

			for(int i = 0; i < 5; i++)
			{
				level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 0D, 0D, 0D);
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

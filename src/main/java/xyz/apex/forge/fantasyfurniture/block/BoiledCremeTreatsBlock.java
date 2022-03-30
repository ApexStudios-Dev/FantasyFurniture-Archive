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

import xyz.apex.forge.fantasyfurniture.init.Decorations;

public final class BoiledCremeTreatsBlock extends SimpleFourWayBlock
{
	public static final IntegerProperty COUNT = IntegerProperty.create("treats", 0, 2);

	public BoiledCremeTreatsBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(COUNT, 0));
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		ItemStack stack = player.getItemInHand(hand);
		int count = blockState.getValue(COUNT);
		int newCount;

		if(Decorations.BOILED_CREME_TREATS.isInStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount <= 2 && newCount >= 0)
		{
			if(newCount < count)
				popResource(level, pos, Decorations.BOILED_CREME_TREATS.asItemStack());
			else
			{
				if(!player.isCreative())
					stack.shrink(1);
			}

			level.setBlockAndUpdate(pos, blockState.setValue(COUNT, newCount));
			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return ActionResultType.FAIL;
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		int count = blockState.getValue(COUNT);

		if(count > 0)
			popResource(level, pos, Decorations.BOILED_CREME_TREATS.asItemStack(count));

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(COUNT);
		super.createBlockStateDefinition(builder);
	}
}

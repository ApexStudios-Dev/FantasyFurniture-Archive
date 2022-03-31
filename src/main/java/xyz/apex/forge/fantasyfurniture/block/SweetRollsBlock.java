package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.BlockHelper;
import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.init.Decorations;

public final class SweetRollsBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 4D, 10D);
	public static final VoxelShape SHAPE_1 = box(2D, 0D, 4D, 14D, 4D, 11D);
	public static final VoxelShape SHAPE_2 = box(2D, 0D, 1D, 14D, 4D, 13D);

	public static final IntegerProperty ROLLS = IntegerProperty.create("rolls", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public SweetRollsBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(ROLLS, 0));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(ROLLS);
		VoxelShaper shaper;

		if(count == 1)
			shaper = SHAPER_1;
		else if(count < 1)
			shaper = SHAPER_0;
		else
			shaper = SHAPER_2;

		return shaper.get(facing);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		ItemStack stack = player.getItemInHand(hand);
		int count = blockState.getValue(ROLLS);
		int newCount;

		if(Decorations.SWEETROLLS.isInStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount <= 2 && newCount >= 0)
		{
			if(newCount < count)
			{
				BlockHelper.playBreakSound(level, pos, player);
				popResource(level, pos, Decorations.SWEETROLLS.asItemStack());
			}
			else
			{
				BlockHelper.playPlaceSound(level, pos, player);

				if(!player.isCreative())
					stack.shrink(1);
			}

			level.setBlockAndUpdate(pos, blockState.setValue(ROLLS, newCount));
			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return ActionResultType.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(ROLLS);
		super.createBlockStateDefinition(builder);
	}
}

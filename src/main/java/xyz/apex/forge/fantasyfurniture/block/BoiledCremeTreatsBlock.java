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

public final class BoiledCremeTreatsBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE_0 = box(6D, 0D, 6D, 10D, 2D, 10D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(
			box(9D, 0D, 8D, 13D, 2D, 12D),
			box(3D, 0D, 4D, 7D, 2D, 8D)
	);
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(
			box(8D, 0D, 10D, 12D, 2D, 14D),
			box(2D, 0D, 6D, 6D, 2D, 10D),
			box(10D, 0D, 3D, 14D, 2D, 7D)
	);

	public static final IntegerProperty COUNT = IntegerProperty.create("treats", 0, 2);
	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public BoiledCremeTreatsBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(COUNT, 0));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(COUNT);
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
		int count = blockState.getValue(COUNT);
		int newCount;

		if(Decorations.BOILED_CREME_TREATS.isInStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount <= 2 && newCount >= 0)
		{
			if(newCount < count)
			{
				BlockHelper.playBreakSound(level, pos, player);
				popResource(level, pos, Decorations.BOILED_CREME_TREATS.asItemStack());
			}
			else
			{
				BlockHelper.playPlaceSound(level, pos, player);

				if(!player.isCreative())
					stack.shrink(1);
			}

			level.setBlockAndUpdate(pos, blockState.setValue(COUNT, newCount));
			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return ActionResultType.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(COUNT);
		super.createBlockStateDefinition(builder);
	}
}

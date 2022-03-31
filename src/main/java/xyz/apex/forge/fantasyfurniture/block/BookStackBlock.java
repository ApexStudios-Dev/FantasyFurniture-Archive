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

public final class BookStackBlock extends SimpleFourWayBlock
{
	public static final VoxelShape SHAPE_0 = box(4D, 0D, 3D, 11D, 3D, 13D);
	public static final VoxelShape SHAPE_1 = VoxelShaper.or(SHAPE_0, box(4.05233282139849D, 3D, 3.263094859751D, 11.05233282139849D, 6D, 13.263094859751D));
	public static final VoxelShape SHAPE_2 = VoxelShaper.or(SHAPE_1, box(4.05233282139849D, 6D, 2.736905140249D, 11.05233282139849D, 9D, 12.736905140249D));

	public static final VoxelShaper SHAPER_0 = VoxelShaper.forHorizontal(SHAPE_0, Direction.NORTH);
	public static final VoxelShaper SHAPER_1 = VoxelShaper.forHorizontal(SHAPE_1, Direction.NORTH);
	public static final VoxelShaper SHAPER_2 = VoxelShaper.forHorizontal(SHAPE_2, Direction.NORTH);

	public static final IntegerProperty BOOKS = IntegerProperty.create("books", 0, 2);

	public BookStackBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(BOOKS, 0));
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		int count = blockState.getValue(BOOKS);
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
		int count = blockState.getValue(BOOKS);
		int newCount;

		if(Decorations.BOOK_STACK.isInStack(stack))
			newCount = count + 1;
		else
			newCount = count - 1;

		if(newCount <= 2 && newCount >= 0)
		{
			if(newCount < count)
			{
				BlockHelper.playBreakSound(level, pos, player);
				popResource(level, pos, Decorations.BOOK_STACK.asItemStack());
			}
			else
			{
				BlockHelper.playPlaceSound(level, pos, player);

				if(!player.isCreative())
					stack.shrink(1);
			}

			level.setBlockAndUpdate(pos, blockState.setValue(BOOKS, newCount));
			return ActionResultType.sidedSuccess(level.isClientSide);
		}

		return ActionResultType.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BOOKS);
		super.createBlockStateDefinition(builder);
	}
}

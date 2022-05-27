package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.Constants;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableSmallBlock;

public final class VenthyrTableSmallBlock extends SetTableSmallBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 4D, 2D, 4D),
			box(1D, 0D, 12D, 4D, 2D, 15D),
			box(12D, 0D, 12D, 15D, 2D, 15D),
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(1.5D, 2D, 1.5D, 3.5D, 13D, 3.5D),
			box(1.5D, 2D, 12.5D, 3.5D, 13, 14.5D),
			box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
			box(0D, 13D, 0D, 16D, 16D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final BooleanProperty FANCY = BooleanProperty.create("fancy");

	public VenthyrTableSmallBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(FANCY, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = super.getStateForPlacement(ctx);

		if(blockState != null)
		{
			ItemStack stack = ctx.getItemInHand();
			CompoundNBT stackTag = stack.getTag();

			if(stackTag != null)
			{
				String name = FANCY.getName();

				if(stackTag.contains(name, Constants.NBT.TAG_BYTE) && stackTag.getBoolean(name))
					blockState = blockState.setValue(FANCY, true);
			}
		}

		return blockState;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}

	@Override
	public void fillItemCategory(ItemGroup itemGroup, NonNullList<ItemStack> items)
	{
		super.fillItemCategory(itemGroup, items);

		ItemStack stack = asItem().getDefaultInstance();
		CompoundNBT stackTag = stack.getOrCreateTag();
		stackTag.putBoolean(FANCY.getName(), true);
		items.add(stack);
	}

	@Override
	public ItemStack getPickBlock(BlockState blockState, RayTraceResult result, IBlockReader level, BlockPos pos, PlayerEntity player)
	{
		ItemStack stack = super.getPickBlock(blockState, result, level, pos, player);

		if(blockState.getValue(FANCY))
		{
			CompoundNBT stackTag = stack.getOrCreateTag();
			stackTag.putBoolean(FANCY.getName(), true);
		}

		return stack;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FANCY);
		super.createBlockStateDefinition(builder);
	}
}

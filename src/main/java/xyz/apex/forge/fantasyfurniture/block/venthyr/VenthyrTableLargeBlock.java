package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.Constants;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableLargeBlock;

import javax.annotation.Nullable;

public final class VenthyrTableLargeBlock extends SetTableLargeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-15D, 0D, 1D, -12D, 2D, 4D),
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(12D, 0D, 28D, 15D, 2D, 31D),
			box(-15D, 0D, 28D, -12D, 2D, 31D),
			box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
			box(-14.5D, 2D, 28.5D, -12.5D, 13D, 30.5D),
			box(12.5D, 2D, 28.5D, 14.5D, 13D, 30.5D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(-16D, 13D, 0D, 16D, 16D, 32D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public static final BooleanProperty FANCY = VenthyrTableSmallBlock.FANCY;

	public VenthyrTableLargeBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(FANCY, false));
	}

	@Nullable
	@Override
	protected BlockState getPlacementState(BlockItemUseContext ctx, BlockState defaultBlockState)
	{
		BlockState blockState = super.getPlacementState(ctx, defaultBlockState);

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
		VoxelShape shape = SHAPER.get(facing);
		int index = pattern.getIndex(blockState);

		if(index == 1 || index == 3)
		{
			Direction other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FANCY);
		super.createBlockStateDefinition(builder);
	}
}

package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;

import javax.annotation.Nullable;

public class SimpleFourWayBlock extends HorizontalBlock
{
	public static final DirectionProperty FACING = HorizontalBlock.FACING;

	public SimpleFourWayBlock(Properties properties)
	{
		super(properties);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
		super.createBlockStateDefinition(builder);
	}
}

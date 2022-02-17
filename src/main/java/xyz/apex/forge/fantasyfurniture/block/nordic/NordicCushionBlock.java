package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.fantasyfurniture.block.BaseSeatBlock;
import xyz.apex.forge.fantasyfurniture.block.BlockHelper;
import xyz.apex.java.utility.Lazy;

public final class NordicCushionBlock extends BaseSeatBlock
{
	private static final Lazy<VoxelShape> SHAPE = Lazy.of(NordicCushionBlock::createUpperSouthShape);

	public NordicCushionBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false));
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		if(player.isShiftKeyDown())
		{
			SHAPE.invalidate();
			return ActionResultType.SUCCESS;
		}

		return super.use(blockState, level, pos, player, hand, rayTraceResult);
	}

	@Override
	protected double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE.get();
	}

	private static VoxelShape createUpperSouthShape()
	{
		return BlockHelper.makeShape(
				box(2D, 0D, 2D, 4D, 4D, 4D),
				box(2D, 0D, 12D, 4D, 4D, 14D),
				box(12D, 0D, 12D, 14D, 4D, 14D),
				box(12D, 0D, 2D, 14D, 4D, 4D),
				box(2D, 4D, 2D, 14D, 7D, 14D)
		);
	}
}

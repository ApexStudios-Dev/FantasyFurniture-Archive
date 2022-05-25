package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetDeskBlock;
import xyz.apex.forge.fantasyfurniture.init.FurnitureSet;

public final class VenthyrDeskBlock extends SetDeskBlock
{
	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(-15D, 0D, 1D, -12D, 2D, 4D),
			box(-15D, 0D, 12D, -12D, 2D, 15D),
			box(12D, 0D, 12D, 15, 2D, 15D),
			box(12D, 0D, 1D, 15, 2D, 4D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
			box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
			box(12.5D, 2D, 12.5D, 14.5D, 13, 14.5D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(5D, 9D, 2, 12D, 13D, 11D),
			box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
			box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
	);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(-15D, 0D, 1D, -12D, 2D, 4D),
			box(-15D, 0D, 12D, -12D, 2D, 15D),
			box(12D, 0D, 12D, 15D, 2D, 15D),
			box(12D, 0D, 1D, 15D, 2D, 4D),
			box(12.5D, 2D, 1.5D, 14.5D, 13D, 3.5D),
			box(-14.5D, 2D, 1.5D, -12.5D, 13D, 3.5D),
			box(-14.5D, 2D, 12.5D, -12.5D, 13D, 14.5D),
			box(12.5D, 2D, 12.5D, 14.5D, 13D, 14.5D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-12D, 9D, 2D, -5D, 13D, 11D),
			box(12.5D, 9D, 3.5D, 14.5D, 13D, 12.5D),
			box(-14.5D, 9D, 3.5D, -12.5D, 13D, 12.5D)
	);

	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);

	public VenthyrDeskBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction facing = blockState.getValue(FACING);
		VoxelShaper shaper = FurnitureSet.VENTHYR.deskLeftBlock.is(this) ? SHAPER_LEFT : SHAPER_RIGHT;
		VoxelShape shape = shaper.get(facing);

		if(!pattern.isOrigin(blockState))
		{
			Direction other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

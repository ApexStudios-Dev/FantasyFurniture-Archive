package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
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

	public VenthyrDeskBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shaper = FurnitureSet.NORDIC.deskLeftBlock.is(this) ? SHAPER_LEFT : SHAPER_RIGHT;
		var shape = shaper.get(facing);

		if(!isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

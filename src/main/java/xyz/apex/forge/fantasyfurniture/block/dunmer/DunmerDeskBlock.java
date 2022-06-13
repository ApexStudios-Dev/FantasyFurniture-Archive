package xyz.apex.forge.fantasyfurniture.block.dunmer;

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

public final class DunmerDeskBlock extends SetDeskBlock
{
	public static final VoxelShape SHAPE_LEFT = VoxelShaper.or(
			box(12, 0, 2, 14, 14, 4),
			box(-14, 0, 2, -12, 14, 4),
			box(-14, 0, 12, -12, 14, 14),
			box(12, 0, 12, 14, 14, 14),
			box(-16, 14, 0, 16, 16, 16),
			box(4, 10, 2, 11, 14, 11)
	);

	public static final VoxelShape SHAPE_RIGHT = VoxelShaper.or(
			box(12, 0, 2, 14, 14, 4),
			box(-14, 0, 2, -12, 14, 4),
			box(-14, 0, 12, -12, 14, 14),
			box(12, 0, 12, 14, 14, 14),
			box(-16, 14, 0, 16, 16, 16),
			box(-11, 10, 2, -4, 14, 11)
	);

	public static final VoxelShaper SHAPER_LEFT = VoxelShaper.forHorizontal(SHAPE_LEFT, Direction.NORTH);
	public static final VoxelShaper SHAPER_RIGHT = VoxelShaper.forHorizontal(SHAPE_RIGHT, Direction.NORTH);

	public DunmerDeskBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shaper = FurnitureSet.DUNMER.deskLeftBlock.is(this) ? SHAPER_LEFT : SHAPER_RIGHT;
		var shape = shaper.get(facing);

		if(!isMultiBlockOrigin(blockState))
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		return shape;
	}
}

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;

public final class DunmerTableWideBlock extends SetTableWideBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(12, 0, 2, 14, 14, 4),
			box(-14, 0, 2, -12, 14, 4),
			box(-14, 0, 12, -12, 14, 14),
			box(12, 0, 12, 14, 14, 14),
			box(-16, 14, 0, 16, 16, 16)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);

	public DunmerTableWideBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);

		if(!isMultiBlockOrigin(blockState))
		{
			var opposite = facing.getClockWise();
			shape = shape.move(opposite.getStepX(), 0D, opposite.getStepZ());
		}

		return shape;
	}
}

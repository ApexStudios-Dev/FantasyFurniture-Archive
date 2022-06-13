package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetTableWideBlock;

public final class NordicTableWideBlock extends SetTableWideBlock
{
	public static final VoxelShape SHAPE_A = VoxelShaper.or(
			box(13D, 0D, 0D, 15D, 9D, 2D),
			box(13D, 7D, 1D, 15D, 13D, 3D),
			box(13D, 7D, 13D, 15D, 13D, 15D),
			box(-15D, 7D, 13D, -13D, 13D, 15D),
			box(-15D, 0D, 0D, -13D, 9D, 2D),
			box(-15D, 0D, 14D, -13D, 9D, 16D),
			box(13D, 0D, 14D, 15D, 9D, 16D),
			box(-16D, 13D, 0D, 16D, 16D, 16D),
			box(-15D, 7D, 1D, -13D, 13D, 3D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE_A, Direction.NORTH);

	public NordicTableWideBlock(Properties properties)
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

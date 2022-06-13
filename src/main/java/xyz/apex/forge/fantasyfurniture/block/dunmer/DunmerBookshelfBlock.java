package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBookshelfBlock;

public final class DunmerBookshelfBlock extends SetBookshelfBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			Block.box(-14, 0, 2, -12, 30, 4),
			Block.box(-14, 0, 12, -12, 30, 14),
			Block.box(12, 0, 12, 14, 30, 14),
			Block.box(12, 0, 2, 14, 30, 4),
			Block.box(-12, 9, 4, 12, 32, 12),
			Block.box(-15, 9, 1, 15, 11, 15),
			Block.box(-15, 19, 1, 15, 21, 15),
			Block.box(-15, 30, 1, 15, 32, 15)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public DunmerBookshelfBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);
		var index = getMultiBlockIndex(blockState);

		if(index == 1 || index == 3)
		{
			var other = facing.getClockWise();
			shape = shape.move(other.getStepX(), 0D, other.getStepZ());
		}

		if(index == 2 || index == 3)
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}
}

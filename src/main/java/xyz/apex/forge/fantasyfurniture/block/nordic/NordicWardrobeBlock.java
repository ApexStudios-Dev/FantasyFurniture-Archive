package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetWardrobeBlock;

public final class NordicWardrobeBlock extends SetWardrobeBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(-14.75D, 0D, .25D, -12.25D, 31D, 2.75D),
			box(-14.75D, 0D, 13.25D, -12.25D, 31D, 15.75D),
			box(12.25D, 0D, 13.25D, 14.75D, 31D, 15.75D),
			box(12.25D, 0D, .25D, 14.75D, 31D, 2.75D),
			box(-14D, 2D, 1D, 14D, 31D, 15D),
			box(-15D, 31D, 0D, 15D, 32D, 16D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicWardrobeBlock(Properties properties)
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

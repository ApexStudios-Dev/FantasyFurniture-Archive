package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetChairBlock;

public final class VenthyrChairBlock extends SetChairBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(1D, 0D, 1D, 4D, 5D, 4D),
			box(12D, 0D, 1D, 15D, 5D, 4D),
			box(12D, 0D, 12D, 15D, 5D, 15D),
			box(1D, 0D, 12D, 4D, 5D, 15D),
			box(.5D, 5D, .5D, 15.5D, 9D, 15.5D),
			box(1D, 9D, 12D, 15D, 31D, 15D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public VenthyrChairBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);

		if(!isMultiBlockOrigin(blockState))
			shape = shape.move(0D, -1D, 0D);

		return shape;
	}

	@Override
	public boolean sitAtOriginOnly()
	{
		return true;
	}
}

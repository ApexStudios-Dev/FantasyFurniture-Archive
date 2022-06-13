package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;

public final class VenthyrBedSingleBlock extends SetBedSingleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0D, 0D, 29D, 3D, 2D, 32D),
			box(13D, 0D, 29D, 16D, 2D, 32D),
			box(13D, 0D, 0D, 16D, 2D, 3D),
			box(0D, 0D, 0D, 3D, 2D, 3D),
			box(.5D, 2D, .5D, 2.5D, 12D, 2.5D),
			box(.5D, 2D, 29.5D, 2.5D, 12D, 31.5D),
			box(13.5D, 2D, 29.5D, 15.5D, 12D, 31.5D),
			box(13.5D, 2D, .5D, 15.5D, 12D, 2.5D),
			box(13D, 12D, 0D, 16D, 14D, 3D),
			box(0D, 12D, 0D, 3D, 14D, 3D),
			box(0D, 12D, 29D, 3D, 14D, 32D),
			box(13D, 12D, 29D, 16D, 14D, 32D),
			box(.5D, 0D, 2D, 15.5D, 5D, 30D),
			box(2.5D, 0D, 29.5D, 13.5D, 11D, 31.5D),
			box(2.5D, 0D, .5D, 13.5D, 13D, 2.5D),
			box(1.5D, 5D, 2.5D, 14.5D, 8D, 29.5D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public VenthyrBedSingleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = BaseBlock.getFacing(blockState);
		var shape = SHAPER.get(facing);

		if(!isMultiBlockOrigin(blockState))
			shape = shape.move(facing.getStepX(), 0D, facing.getStepZ());

		return shape;
	}
}

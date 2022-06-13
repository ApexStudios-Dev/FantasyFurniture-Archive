package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetBedSingleBlock;

public final class DunmerBedSingleBlock extends SetBedSingleBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(0D, 0D, 0D, 2D, 13D, 2D),
			box(0D, 0D, 30D, 2D, 11D, 32D),
			box(14D, 0D, 30D, 16D, 11D, 32D),
			box(14D, 0D, 0D, 16D, 13D, 2D),
			box(2D, 3D, 0D, 14D, 14.25D, 2D),
			box(2D, 3D, 30D, 14D, 12.25D, 32D),
			box(1D, 5D, 2D, 15D, 8D, 30D),
			box(0D, 3D, 2D, 16D, 5D, 30D)
	);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);
	public DunmerBedSingleBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public RenderShape getRenderShape(BlockState blockState)
	{
		return RenderShape.MODEL;
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

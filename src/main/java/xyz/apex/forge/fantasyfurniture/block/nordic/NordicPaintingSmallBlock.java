package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetPaintingSmallBlock;

public final class NordicPaintingSmallBlock extends SetPaintingSmallBlock
{
	public static final VoxelShape SHAPE = box(0D, 0D, 14D, 16D, 16D, 16D);

	public static final VoxelShaper SHAPER = VoxelShaper.forHorizontal(SHAPE, Direction.NORTH);

	public NordicPaintingSmallBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		var facing = blockState.getValue(FACING);
		return SHAPER.get(facing);
	}
}

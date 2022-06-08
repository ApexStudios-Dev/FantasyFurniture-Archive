package xyz.apex.forge.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetChandelierBlock;

public final class NordicChandelierBlock extends SetChandelierBlock
{
	public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);

	public NordicChandelierBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}
}

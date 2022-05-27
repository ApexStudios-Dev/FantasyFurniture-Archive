package xyz.apex.forge.fantasyfurniture.block.venthyr;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import xyz.apex.forge.apexcore.lib.block.VoxelShaper;
import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;
import xyz.apex.forge.fantasyfurniture.block.base.set.SetFloorLightBlock;

import java.util.Random;

public final class VenthyrFloorLightBlock extends SetFloorLightBlock
{
	public static final VoxelShape SHAPE = VoxelShaper.or(
			box(6D, 0D, 6D, 10D, 2D, 10D),
			box(7D, 2D, 7D, 9D, 20D, 9D),
			box(4D, 17.75D, 7D, 7D, 21.75D, 9D),
			box(9D, 17.75D, 7D, 12D, 21.75D, 9D),
			box(7D, 17.75D, 4D, 9D, 21.75D, 7D),
			box(7D, 17.75D, 9D, 9D, 21.75D, 12D),
			box(2.5D, 20.75D, 2.5D, 13.5D, 24D, 13.5D),
			box(10.25D, 24D, 10.25D, 12.5D, 28.75D, 12.5D),
			box(3.5D, 24D, 10.25D, 5.75D, 28.75D, 12.5D),
			box(3.5D, 24D, 3.5D, 5.75D, 28.75D, 5.75D),
			box(10.25D, 24D, 3.5D, 12.5D, 28.75D, 5.75D)
	);

	public VenthyrFloorLightBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		if(pattern.isOrigin(blockState))
			return SHAPE;
		return SHAPE.move(0D, -1D, 0D);
	}

	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED) && blockState.getValue(SIDE) == Side.TOP)
		{
			double x = pos.getX() + .3D;
			double y = pos.getY() + .9D;
			double z = pos.getZ() + .3D;

			onLightParticle(level, pos, blockState, x, y, z, rng);

			onLightParticle(level, pos, blockState, x + .4D, y, z, rng);
			onLightParticle(level, pos, blockState, x, y, z + .4D, rng);
			onLightParticle(level, pos, blockState, x + .4D, y, z + .4D, rng);
		}
	}
}

package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import xyz.apex.forge.fantasyfurniture.block.base.set.SetChandelierBlock;

import java.util.Random;

public final class DunmerChandelierBlock extends SetChandelierBlock
{
	public static final VoxelShape SHAPE = box(1, 2, 1, 15, 16, 15);

	public DunmerChandelierBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			double x = pos.getX() + .5D;
			double y = pos.getY() + .875D;
			double z = pos.getZ() + .5D;

			onLightParticle(level, pos, blockState, x + .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x + .275D, y, z - .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z + .275D, rng);
			onLightParticle(level, pos, blockState, x - .275D, y, z - .275D, rng);
		}
	}
}

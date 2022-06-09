package xyz.apex.forge.fantasyfurniture.block.dunmer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return SHAPE;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			double x = pos.getX() + .5D;
			double y = pos.getY() + .8D;
			double z = pos.getZ() + .525D;

			onLightParticle(level, pos, blockState, x + .25D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x - .27D, y, z + .25D, rng);
			onLightParticle(level, pos, blockState, x + .27D, y, z - .25D, rng);
			onLightParticle(level, pos, blockState, x - .25D, y, z - .25D, rng);
		}
	}
}

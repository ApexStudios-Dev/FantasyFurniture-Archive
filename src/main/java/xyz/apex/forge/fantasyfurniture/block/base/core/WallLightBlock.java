package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class WallLightBlock extends SimpleFourWayWaterLoggedBlock
{
	public WallLightBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState defaultBlockState = super.getStateForPlacement(ctx);

		if(defaultBlockState != null)
		{
			World level = ctx.getLevel();
			BlockPos pos = ctx.getClickedPos();
			Direction[] nearestLookingDirections = ctx.getNearestLookingDirections();

			for(Direction direction : nearestLookingDirections)
			{
				if(direction.getAxis().isHorizontal())
				{
					Direction opposite = direction.getOpposite();
					BlockState blockState = defaultBlockState.setValue(FACING, opposite);

					if(blockState.canSurvive(level, pos))
						return blockState;
				}
			}
		}

		return null;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos pos, BlockPos facingPos)
	{
		if(facing.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(level, pos))
		{
			if(blockState.getValue(WATERLOGGED))
				level.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

			return Blocks.AIR.defaultBlockState();
		}

		return super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		Direction facing = blockState.getValue(FACING);
		BlockPos oppositePos = pos.relative(facing.getOpposite());
		BlockState oppositeBlockState = level.getBlockState(oppositePos);
		return oppositeBlockState.isFaceSturdy(level, oppositePos, facing);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			Direction facing = blockState.getValue(FACING).getOpposite();

			double hStep = .12D;
			double vStep = .24D;

			double x = pos.getX() + .5D + (hStep * facing.getStepX());
			double y = pos.getY() + .7D + vStep;
			double z = pos.getZ() + .5D + (hStep * facing.getStepZ());

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
	}

	protected void onLightParticle(World level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.FLAME, pX, pY, pZ, 0D, 0D, 0D);
	}
}

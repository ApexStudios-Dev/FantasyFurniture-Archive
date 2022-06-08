package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
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
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var defaultBlockState = super.getStateForPlacement(ctx);

		if(defaultBlockState != null)
		{
			var level = ctx.getLevel();
			var pos = ctx.getClickedPos();
			var nearestLookingDirections = ctx.getNearestLookingDirections();

			for(var direction : nearestLookingDirections)
			{
				if(direction.getAxis().isHorizontal())
				{
					var opposite = direction.getOpposite();
					var blockState = defaultBlockState.setValue(FACING, opposite);

					if(blockState.canSurvive(level, pos))
						return blockState;
				}
			}
		}

		return null;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
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
	public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
	{
		var facing = blockState.getValue(FACING);
		var oppositePos = pos.relative(facing.getOpposite());
		var oppositeBlockState = level.getBlockState(oppositePos);
		return oppositeBlockState.isFaceSturdy(level, oppositePos, facing);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, Level level, BlockPos pos, Random rng)
	{
		if(!blockState.getValue(WATERLOGGED))
		{
			var facing = blockState.getValue(FACING).getOpposite();

			var hStep = .12D;
			var vStep = .24D;

			var x = pos.getX() + .5D + (hStep * facing.getStepX());
			var y = pos.getY() + .7D + vStep;
			var z = pos.getZ() + .5D + (hStep * facing.getStepZ());

			onLightParticle(level, pos, blockState, x, y, z, rng);
		}
	}

	protected void onLightParticle(Level level, BlockPos pos, BlockState blockState, double pX, double pY, double pZ, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
		level.addParticle(ParticleTypes.FLAME, pX, pY, pZ, 0D, 0D, 0D);
	}
}

package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class BaseWallLight extends HorizontalBlock
{
	protected final IParticleData particleData;

	public BaseWallLight(Properties properties, IParticleData particleData)
	{
		super(properties);

		this.particleData = particleData;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos pos, BlockPos facingPos)
	{
		return facing.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : blockState;
	}

	@Override
	public boolean canSurvive(BlockState blockState, IWorldReader level, BlockPos pos)
	{
		Direction facing = blockState.getValue(FACING);
		BlockPos relativePos = pos.relative(facing.getOpposite());
		BlockState relativeBlockState = level.getBlockState(relativePos);
		return relativeBlockState.isFaceSturdy(level, relativePos, facing);
	}

	@SuppressWarnings("ConstantConditions") // Super is nonnull
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = super.getStateForPlacement(ctx);
		IWorldReader level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		for(Direction direction : ctx.getNearestLookingDirections())
		{
			if(direction.getAxis().isHorizontal())
			{
				blockState = blockState.setValue(FACING, direction);

				if(blockState.canSurvive(level, pos))
					return blockState;
			}
		}

		return null;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState blockState, World level, BlockPos pos, Random rng)
	{
		playParticleAt(level, pos.getX(), pos.getY(), pos.getZ(), blockState, rng);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
		super.createBlockStateDefinition(builder);
	}

	protected void playParticleAt(World level, double x, double y, double z, BlockState blockState, Random rng)
	{
		level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
		level.addParticle(particleData, x, y, z, 0D, 0D, 0D);
	}
}

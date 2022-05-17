package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CushionBlock extends SeatBlock
{
	public CushionBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public void fallOn(World level, BlockPos pos, Entity entity, float distance)
	{
		super.fallOn(level, pos, entity, distance * .5F);
	}

	@Override
	public void updateEntityAfterFallOn(IBlockReader level, Entity entity)
	{
		if(entity.isSuppressingBounce())
			super.updateEntityAfterFallOn(level, entity);
		else
			bounceUp(entity);
	}

	protected void bounceUp(Entity entity)
	{
		Vector3d deltaMovement = entity.getDeltaMovement();

		if(deltaMovement.y < 0D)
		{
			double d0 = entity instanceof LivingEntity ? 1D : .8D;
			entity.setDeltaMovement(deltaMovement.x, -deltaMovement.y * (double) .66F * d0, deltaMovement.z);
		}
	}
}

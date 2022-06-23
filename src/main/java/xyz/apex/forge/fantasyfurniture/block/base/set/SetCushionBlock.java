package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.SeatBlock;

import java.util.function.Consumer;

public class SetCushionBlock extends SeatBlock
{
	public SetCushionBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .2D;
	}

	@Override
	public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float distance)
	{
		super.fallOn(level, blockState, pos, entity, distance * .5F);
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
	{
		if(entity.isSuppressingBounce())
			super.updateEntityAfterFallOn(level, entity);
		else
			bounceUp(entity);
	}

	protected void bounceUp(Entity entity)
	{
		var deltaMovement = entity.getDeltaMovement();

		if(deltaMovement.y < 0D)
		{
			var d0 = entity instanceof LivingEntity ? 1D : .8D;
			entity.setDeltaMovement(deltaMovement.x, -deltaMovement.y * (double) .66F * d0, deltaMovement.z);
		}
	}
}

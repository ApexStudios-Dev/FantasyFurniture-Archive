package xyz.apex.forge.fantasyfurniture.block.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.lib.block.SeatBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class CushionBlock extends SeatBlock
{
	public CushionBlock(Properties properties)
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

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_CUSHION.isIn(blockState))
			return HitBoxes.NORDIC.cushion(this, blockState);
		else if(ModBlocks.DUNMER_CUSHION.isIn(blockState))
			return HitBoxes.DUNMER.cushion(this, blockState);
		else if(ModBlocks.VENTHYR_CUSHION.isIn(blockState))
			return HitBoxes.VENTHYR.cushion(this, blockState);
		else if(ModBlocks.BONE_SKELETON_SKULL.isIn(blockState) || ModBlocks.BONE_WITHER_SKULL.isIn(blockState))
			return HitBoxes.BONE.cushion(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
	}
}
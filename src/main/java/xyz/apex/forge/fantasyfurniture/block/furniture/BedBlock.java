package xyz.apex.forge.fantasyfurniture.block.furniture;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.BaseMultiBlock;

import java.util.function.Consumer;

public abstract class BedBlock extends BaseMultiBlock
{
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

	public BedBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(OCCUPIED, false));
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(OCCUPIED);
	}

	@Nullable
	@Override
	public Direction getFourWayFacing(BlockPlaceContext ctx)
	{
		return ctx.getHorizontalDirection();
	}

	protected abstract int getBedFootMultiBlockIndex(BlockState blockState);

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
	{
		if(level.isClientSide)
			return InteractionResult.CONSUME;

		var footPos = getBedFootPos(this, blockState, pos);
		var footBlockState = blockState;

		if(!footPos.equals(pos))
			footBlockState = level.getBlockState(footPos);

		if(!footBlockState.is(this))
			return InteractionResult.CONSUME;

		if(!net.minecraft.world.level.block.BedBlock.canSetSpawn(level))
			return onBadBedSetSpawn(level, footBlockState, footPos, player, hand);
		else if(footBlockState.getValue(OCCUPIED))
			return onBedOccupied(level, footBlockState, footPos, player, hand);
		else
			return onSleepInBed(level, footBlockState, footPos, player, hand);
	}

	@Override
	public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, float dist)
	{
		super.fallOn(level, blockState, pos, entity, dist * .5F);
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter level, Entity entity)
	{
		if(entity.isSuppressingBounce())
			super.updateEntityAfterFallOn(level, entity);
		else
			bounceUp(entity);
	}

	@Override
	public boolean isBed(BlockState blockState, BlockGetter level, BlockPos pos, @Nullable Entity player)
	{
		return blockState.is(this);
	}

	@Override
	public void setBedOccupied(BlockState blockState, Level level, BlockPos pos, LivingEntity sleeper, boolean occupied)
	{
		var footPos = getBedFootPos(this, blockState, pos);
		var footBlockState = level.getBlockState(footPos);

		var headPos = footPos.relative(BaseBlock.getFacing(footBlockState));
		var headBlockState = level.getBlockState(headPos);

		super.setBedOccupied(footBlockState, level, footPos, sleeper, occupied);
		super.setBedOccupied(headBlockState, level, headPos, sleeper, occupied);
	}

	@Override
	public boolean isPathfindable(BlockState blockState, BlockGetter level, BlockPos pos, PathComputationType pathType)
	{
		return false;
	}

	public void onFixBedRotations(LivingEntity entity, PoseStack pose)
	{
	}

	protected InteractionResult onBadBedSetSpawn(Level level, BlockState blockState, BlockPos pos, Player player, InteractionHand hand)
	{
		level.removeBlock(pos, false);
		level.explode(null, DamageSource.badRespawnPointExplosion(), null, pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 5F, true, Explosion.BlockInteraction.DESTROY);
		return InteractionResult.SUCCESS;
	}

	protected InteractionResult onBedOccupied(Level level, BlockState blockState, BlockPos pos, Player player, InteractionHand hand)
	{
		if(!kickVillagerOutOfBed(level, pos))
			player.displayClientMessage(getOccupiedTranslation(), true);

		return InteractionResult.SUCCESS;
	}

	protected InteractionResult onSleepInBed(Level level, BlockState blockState, BlockPos pos, Player player, InteractionHand hand)
	{
		var facing = BaseBlock.getFacing(blockState);
		var sleepPos = pos.relative(facing);

		player.startSleepInBed(sleepPos).ifLeft(result -> {
			if(result != null)
				player.displayClientMessage(result.getMessage(), true);
		});

		return InteractionResult.SUCCESS;
	}

	public String getOccupiedTranslationKey()
	{
		return "%s.occupied".formatted(getDescriptionId());
	}

	public Component getOccupiedTranslation()
	{
		return Component.translatable(getOccupiedTranslationKey());
	}

	public static boolean kickVillagerOutOfBed(Level level, BlockPos pos)
	{
		var villagersInBed = level.getEntities(EntityType.VILLAGER, new AABB(pos), LivingEntity::isSleeping);

		if(villagersInBed.isEmpty())
			return false;
		else
		{
			villagersInBed.get(0).stopSleeping();
			return true;
		}
	}

	public static void bounceUp(Entity entity)
	{
		var delta = entity.getDeltaMovement();

		if(delta.y < 0D)
		{
			var d = entity instanceof LivingEntity ? 1D : .8D;
			entity.setDeltaMovement(delta.x, -delta.y * .66F * d, delta.z);
		}
	}

	public static BlockPos getBedFootPos(BedBlock bed, BlockState blockState, BlockPos pos)
	{
		var footIndex = bed.getBedFootMultiBlockIndex(blockState);
		var currentIndex = bed.getMultiBlockIndex(blockState);

		if(currentIndex == footIndex)
			return pos;

		var origin = bed.getMultiBlockOriginPos(blockState, pos);
		var footLocalSpace = bed.getMultiBlockLocalPositions().get(footIndex);
		return bed.getMultiBlockWorldSpaceFromLocalSpace(blockState, origin, footLocalSpace);
	}
}
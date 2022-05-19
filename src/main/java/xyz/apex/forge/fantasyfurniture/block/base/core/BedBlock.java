package xyz.apex.forge.fantasyfurniture.block.base.core;

import com.mojang.blaze3d.matrix.MatrixStack;
import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;

import xyz.apex.forge.apexcore.lib.multiblock.MultiBlockPattern;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public abstract class BedBlock extends SimpleFourWayWaterLoggedMultiBlock
{
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

	public BedBlock(Properties properties, MultiBlockPattern pattern)
	{
		super(properties, pattern);

		registerDefaultState(defaultBlockState().setValue(OCCUPIED, false));
	}

	protected abstract int getBedHeadMultiBlockIndex(BlockState blockState);

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(OCCUPIED);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result)
	{
		if(level.isClientSide)
			return ActionResultType.CONSUME;

		BlockPos headPos = getBedHedPos(this, blockState, pos);
		BlockState headBlockState = level.getBlockState(headPos);

		if(!headBlockState.is(this))
			return ActionResultType.CONSUME;

		if(!canSetSpawn(level))
			return onBadBedSetSpawn(level, headBlockState, headPos, player, hand);
		else if(headBlockState.getValue(OCCUPIED))
			return onBedOccupied(level, blockState, pos, player, hand);
		else
			return onSleepInBed(level, blockState, pos, player, hand);
	}

	@Override
	public void fallOn(World level, BlockPos pos, Entity entity, float dist)
	{
		super.fallOn(level, pos, entity, dist * .5F);
	}

	@Override
	public void updateEntityAfterFallOn(IBlockReader level, Entity entity)
	{
		if(entity.isSuppressingBounce())
			super.updateEntityAfterFallOn(level, entity);
		else
			bounceUp(entity);
	}

	@Override
	public boolean isBed(BlockState blockState, IBlockReader level, BlockPos pos, @Nullable Entity player)
	{
		return blockState.is(this);
	}

	@Override
	public void setBedOccupied(BlockState blockState, World level, BlockPos pos, LivingEntity sleeper, boolean occupied)
	{
		BlockPos headPos = getBedHedPos(this, blockState, pos);
		BlockState headBlockState = level.getBlockState(headPos);
		super.setBedOccupied(headBlockState, level, headPos, sleeper, occupied);
	}

	@Override
	public Optional<Vector3d> getBedSpawnPosition(EntityType<?> entityType, BlockState blockState, IWorldReader level, BlockPos pos, float orientation, @Nullable LivingEntity sleeper)
	{
		Optional<Vector3d> standUpPosition = findStandUpPosition(entityType, level, pos, orientation);

		if(standUpPosition.isPresent())
			return standUpPosition;

		return super.getBedSpawnPosition(entityType, blockState, level, pos, orientation, sleeper);
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}

	public void onFixBedRotations(LivingEntity entity, MatrixStack pose)
	{
	}

	protected ActionResultType onBadBedSetSpawn(World level, BlockState blockState, BlockPos pos, PlayerEntity player, Hand hand)
	{
		level.removeBlock(pos, false);
		level.explode(null, DamageSource.badRespawnPointExplosion(), null, pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D, 5F, true, Explosion.Mode.DESTROY);
		return ActionResultType.SUCCESS;
	}

	protected ActionResultType onBedOccupied(World level, BlockState blockState, BlockPos pos, PlayerEntity player, Hand hand)
	{
		// TODO: Custom occupied messages like seats
		if(!kickVillagerOutOfBed(level, pos))
			player.displayClientMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);

		return ActionResultType.SUCCESS;
	}

	protected ActionResultType onSleepInBed(World level, BlockState blockState, BlockPos pos, PlayerEntity player, Hand hand)
	{
		BlockPos headPos = getBedHedPos(this, blockState, pos);

		player.startSleepInBed(headPos).ifLeft(result -> {
			if(result != null)
				player.displayClientMessage(result.getMessage(), true);
		});

		return ActionResultType.SUCCESS;
	}

	public static boolean kickVillagerOutOfBed(World level, BlockPos pos)
	{
		List<VillagerEntity> villagersInBed = level.getEntities(EntityType.VILLAGER, new AxisAlignedBB(pos), LivingEntity::isSleeping);

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
		Vector3d delta = entity.getDeltaMovement();

		if(delta.y < 0D)
		{
			double d = entity instanceof LivingEntity ? 1D : .8D;
			entity.setDeltaMovement(delta.x, -delta.y * .66F * d, delta.z);
		}
	}

	public static BlockPos getBedHedPos(BedBlock bed, BlockState blockState, BlockPos pos)
	{
		int headIndex = bed.getBedHeadMultiBlockIndex(blockState);
		MultiBlockPattern pattern = bed.getMultiBlockPattern();
		int currentIndex = pattern.getIndex(blockState);

		if(currentIndex == headIndex)
			return pos;

		List<BlockPos> localPositions = pattern.getLocalPositions();
		BlockPos localSpace = localPositions.get(currentIndex);
		BlockPos origin = pattern.getOriginFromWorldSpace(blockState, pos, localSpace);
		BlockPos headLocalSPace = localPositions.get(headIndex);
		return pattern.getWorldSpaceFromLocalSpace(blockState, origin, headLocalSPace);
	}

	public static boolean canSetSpawn(World level)
	{
		return net.minecraft.block.BedBlock.canSetSpawn(level);
	}

	public static boolean isBedBlock(BlockState blockState)
	{
		Block block = blockState.getBlock();

		if(block instanceof BedBlock || block instanceof net.minecraft.block.BedBlock)
			return true;

		return blockState.is(BlockTags.BEDS);
	}

	public static boolean isBunkBed(IBlockReader level, BlockPos pos)
	{
		return isBedBlock(level.getBlockState(pos.below())) || isBedBlock(level.getBlockState(pos.above()));
	}

	public static Optional<Vector3d> findStandUpPosition(EntityType<?> entityType, ICollisionReader level, BlockPos pos, float f)
	{
		Direction facing = level.getBlockState(pos).getValue(FACING);
		Direction facingClockWise = facing.getClockWise();
		Direction dir = facingClockWise.isFacingAngle(f) ? facingClockWise.getOpposite() : facingClockWise;

		if(isBunkBed(level, pos))
			return findBunkBedStandUpPosition(entityType, level, pos, facing, dir);
		else
		{
			int[][] aint = bedStandUpOffsets(facing, dir);
			Optional<Vector3d> optional = findStandUpPositionAtOffset(entityType, level, pos, aint, true);
			return optional.isPresent() ? optional : findStandUpPositionAtOffset(entityType, level, pos, aint, false);
		}
	}

	private static Optional<Vector3d> findBunkBedStandUpPosition(EntityType<?> entityType, ICollisionReader level, BlockPos pos, Direction dirA, Direction dirB)
	{
		int[][] aint = bedSurroundStandUpOffsets(dirA, dirB);
		Optional<Vector3d> optional = findStandUpPositionAtOffset(entityType, level, pos, aint, true);

		if(optional.isPresent())
			return optional;
		else
		{
			BlockPos below = pos.below();
			Optional<Vector3d> belowOptional = findStandUpPositionAtOffset(entityType, level, below, aint, true);

			if(belowOptional.isPresent())
				return belowOptional;
			else
			{
				int[][] aint1 = bedAboveStandUpOffsets(dirA);
				Optional<Vector3d> optA = findStandUpPositionAtOffset(entityType, level, pos, aint1, true);

				if(optA.isPresent())
					return optA;
				else
				{
					Optional<Vector3d> optB = findStandUpPositionAtOffset(entityType, level, pos, aint, false);

					if(optB.isPresent())
						return optB;
					else
					{
						Optional<Vector3d> optC = findStandUpPositionAtOffset(entityType, level, below, aint, false);
						return optC.isPresent() ? optC : findStandUpPositionAtOffset(entityType, level, pos, aint1, false);
					}
				}
			}
		}
	}

	private static Optional<Vector3d> findStandUpPositionAtOffset(EntityType<?> entityType, ICollisionReader level, BlockPos pos, int[][] ints, boolean flag)
	{
		BlockPos.Mutable posMutable = new BlockPos.Mutable();

		for(int[] aint : ints)
		{
			posMutable.set(pos.getX() + aint[0], pos.getY(), pos.getZ() + aint[1]);
			Vector3d vector3d = TransportationHelper.findSafeDismountLocation(entityType, level, posMutable, flag);

			if(vector3d != null)
				return Optional.of(vector3d);
		}

		return Optional.empty();
	}

	private static int[][] bedStandUpOffsets(Direction dirA, Direction dirB)
	{
		return ArrayUtils.addAll(bedSurroundStandUpOffsets(dirA, dirB), bedAboveStandUpOffsets(dirA));
	}

	private static int[][] bedSurroundStandUpOffsets(Direction dirA, Direction dirB)
	{
		int dirAStepX = dirA.getStepX();
		int dirAStepZ = dirA.getStepZ();

		int dirBStepX = dirB.getStepX();
		int dirBStepZ = dirB.getStepZ();

		return new int[][] {
				{ dirBStepX, dirBStepZ },
				{ dirBStepX - dirAStepX, dirBStepZ - dirAStepZ },
				{ dirBStepX - dirAStepX * 2, dirBStepZ - dirAStepZ * 2 },
				{ -dirAStepX * 2, -dirAStepZ * 2 },
				{ -dirBStepX - dirAStepX * 2, -dirBStepZ - dirAStepZ * 2 },
				{ -dirBStepX - dirAStepX, -dirBStepZ - dirAStepZ },
				{ -dirBStepX, -dirBStepZ },
				{ -dirBStepX + dirAStepX, -dirBStepZ + dirAStepZ },
				{ dirAStepX, dirAStepZ },
				{ dirBStepX + dirAStepX, dirBStepZ + dirAStepZ }
		};
	}

	private static int[][] bedAboveStandUpOffsets(Direction dir)
	{
		return new int[][] {
				{ 0, 0 },
				{ -dir.getStepX(), -dir.getStepZ() }
		};
	}
}

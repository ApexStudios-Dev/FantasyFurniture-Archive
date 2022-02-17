package xyz.apex.forge.fantasyfurniture.block;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

// TODO: Should maybe be moved into ApexCore
// Copy of BedBlock but not using BlockEntities
public class BaseBedBlock extends HorizontalBlock
{
	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
	public static final VoxelShape BASE = box(0D, 3D, 0D, 16D, 9D, 16D);
	public static final VoxelShape LEG_NORTH_WEST = box(0D, 0D, 0D, 3D, 3D, 3D);
	public static final VoxelShape LEG_SOUTH_WEST = box(0D, 0D, 13D, 3D, 3D, 16D);
	public static final VoxelShape LEG_NORTH_EAST = box(13D, 0D, 0D, 16D, 3D, 3D);
	public static final VoxelShape LEG_SOUTH_EAST = box(13D, 0D, 13D, 16D, 3D, 16D);
	public static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_WEST, LEG_NORTH_EAST);
	public static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BASE, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
	public static final VoxelShape WEST_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_WEST, LEG_SOUTH_WEST);
	public static final VoxelShape EAST_SHAPE = VoxelShapes.or(BASE, LEG_NORTH_EAST, LEG_SOUTH_EAST);

	public BaseBedBlock(Properties properties)
	{
		super(properties);
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	public static Direction getBedOrientation(IBlockReader level, BlockPos pos)
	{
		BlockState blockState = level.getBlockState(pos);
		return isBedBlockState(blockState) ? blockState.getValue(FACING) : null;
	}

	@Override
	public ActionResultType use(BlockState blockState, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		if(level.isClientSide)
			return ActionResultType.CONSUME;
		else
		{
			if(blockState.getValue(PART) != BedPart.HEAD)
			{
				pos = pos.relative(blockState.getValue(FACING));
				blockState = level.getBlockState(pos);

				if(!blockState.is(this))
					return ActionResultType.CONSUME;
			}

			if(!canSetSpawn(level))
			{
				level.removeBlock(pos, false);
				BlockPos blockPos = pos.relative(blockState.getValue(FACING).getOpposite());

				if(level.getBlockState(blockPos).is(this))
					level.removeBlock(blockPos, false);

				level.explode(null, DamageSource.badRespawnPointExplosion(), null, (double) pos.getX() + .5D, (double) pos.getY() + .5D, (double) pos.getZ() + .5D, 5F, true, Explosion.Mode.DESTROY);
				return ActionResultType.SUCCESS;
			}
			else if(blockState.getValue(OCCUPIED))
			{
				if(!kickVillagerOutOfBed(level, pos))
					player.displayClientMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);

				return ActionResultType.SUCCESS;
			}
			else
			{
				player.startSleepInBed(pos).ifLeft((sleepResult) -> {
					if(sleepResult != null)
						player.displayClientMessage(sleepResult.getMessage(), true);
				});

				return ActionResultType.SUCCESS;
			}
		}
	}

	// TODO: Remove
	public static boolean canSetSpawn(World level)
	{
		return BedBlock.canSetSpawn(level);
	}

	private boolean kickVillagerOutOfBed(World level, BlockPos pos)
	{
		List<VillagerEntity> villagers = level.getEntitiesOfClass(VillagerEntity.class, new AxisAlignedBB(pos), LivingEntity::isSleeping);

		if(villagers.isEmpty())
			return false;
		else
		{
			villagers.get(0).stopSleeping();
			return true;
		}
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
		{
			Vector3d deltaMovement = entity.getDeltaMovement();

			if(deltaMovement.y < 0D)
			{
				double d0 = entity instanceof LivingEntity ? 1D : .8D;
				entity.setDeltaMovement(deltaMovement.x, -deltaMovement.y * (double) .66F * d0, deltaMovement.z);
			}
		}
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos currentPos, BlockPos facingPos)
	{
		if(facing == getNeighbourDirection(blockState.getValue(PART), blockState.getValue(FACING)))
			return facingBlockState.is(this) && facingBlockState.getValue(PART) != blockState.getValue(PART) ? blockState.setValue(OCCUPIED, facingBlockState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
		else
			return super.updateShape(blockState, facing, facingBlockState, level, currentPos, facingPos);
	}

	private static Direction getNeighbourDirection(BedPart bedPart, Direction direction)
	{
		return bedPart == BedPart.FOOT ? direction : direction.getOpposite();
	}

	@Override
	public void playerWillDestroy(World level, BlockPos pos, BlockState blockState, PlayerEntity player)
	{
		if(!level.isClientSide && player.isCreative())
		{
			BedPart bedPart = blockState.getValue(PART);

			if(bedPart == BedPart.FOOT)
			{
				BlockPos blockPos = pos.relative(getNeighbourDirection(bedPart, blockState.getValue(FACING)));
				BlockState state = level.getBlockState(blockPos);

				if(state.getBlock() == this && state.getValue(PART) == BedPart.HEAD)
				{
					level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 35);
					level.levelEvent(player, 2001, blockPos, Block.getId(state));
				}
			}
		}

		super.playerWillDestroy(level, pos, blockState, player);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		Direction direction = ctx.getHorizontalDirection();
		BlockPos clickedPos = ctx.getClickedPos();
		BlockPos blockPos = clickedPos.relative(direction);
		return ctx.getLevel().getBlockState(blockPos).canBeReplaced(ctx) ? defaultBlockState().setValue(FACING, direction) : null;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader level, BlockPos pos, ISelectionContext ctx)
	{
		Direction direction = getConnectedDirection(blockState).getOpposite();

		switch(direction)
		{
			case NORTH: return NORTH_SHAPE;
			case SOUTH: return SOUTH_SHAPE;
			case WEST: return WEST_SHAPE;
			default: return EAST_SHAPE;
		}
	}

	// TODO: Remove
	public static Direction getConnectedDirection(BlockState blockState)
	{
		return BedBlock.getConnectedDirection(blockState);
	}

	// TODO: Remove
	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.Type getBlockType(BlockState blockState)
	{
		return BedBlock.getBlockType(blockState);
	}

	private static boolean isBunkBed(IBlockReader level, BlockPos pos)
	{
		return isBedBlockState(level.getBlockState(pos.below()));
	}

	public static Optional<Vector3d> findStandUpPosition(EntityType<?> entityType, ICollisionReader level, BlockPos pos, float angle)
	{
		Direction facing = level.getBlockState(pos).getValue(FACING);
		Direction facingClockWise = facing.getClockWise();
		Direction dir = facingClockWise.isFacingAngle(angle) ? facingClockWise.getOpposite() : facingClockWise;

		if(isBunkBed(level, pos))
			return findBunkBedStandUpPosition(entityType, level, pos, facing, dir);
		else
		{
			int[][] standUpOffsets = bedStandUpOffsets(facing, dir);
			Optional<Vector3d> upPositionAtOffset = findStandUpPositionAtOffset(entityType, level, pos, standUpOffsets, true);
			return upPositionAtOffset.isPresent() ? upPositionAtOffset : findStandUpPositionAtOffset(entityType, level, pos, standUpOffsets, false);
		}
	}

	private static Optional<Vector3d> findBunkBedStandUpPosition(EntityType<?> entityType, ICollisionReader level, BlockPos pos, Direction direction, Direction dir)
	{
		int[][] bedSurroundStandUpOffsets = bedSurroundStandUpOffsets(direction, dir);
		Optional<Vector3d> upPositionAtOffset = findStandUpPositionAtOffset(entityType, level, pos, bedSurroundStandUpOffsets, true);

		if(upPositionAtOffset.isPresent())
			return upPositionAtOffset;
		else
		{
			BlockPos belowPos = pos.below();
			Optional<Vector3d> standUpPositionAtOffset = findStandUpPositionAtOffset(entityType, level, belowPos, bedSurroundStandUpOffsets, true);

			if(standUpPositionAtOffset.isPresent())
				return standUpPositionAtOffset;
			else
			{
				int[][] bedAboveStandUpOffsets = bedAboveStandUpOffsets(direction);
				Optional<Vector3d> standUpPositionAtOffset1 = findStandUpPositionAtOffset(entityType, level, pos, bedAboveStandUpOffsets, true);

				if(standUpPositionAtOffset1.isPresent())
					return standUpPositionAtOffset1;
				else
				{
					Optional<Vector3d> positionAtOffset = findStandUpPositionAtOffset(entityType, level, pos, bedSurroundStandUpOffsets, false);

					if(positionAtOffset.isPresent())
						return positionAtOffset;
					else
					{
						Optional<Vector3d> atOffset = findStandUpPositionAtOffset(entityType, level, belowPos, bedSurroundStandUpOffsets, false);
						return atOffset.isPresent() ? atOffset : findStandUpPositionAtOffset(entityType, level, pos, bedAboveStandUpOffsets, false);
					}
				}
			}
		}
	}

	private static Optional<Vector3d> findStandUpPositionAtOffset(EntityType<?> p_242654_0_, ICollisionReader p_242654_1_, BlockPos p_242654_2_, int[][] p_242654_3_, boolean p_242654_4_)
	{
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for(int[] aint : p_242654_3_)
		{
			blockpos$mutable.set(p_242654_2_.getX() + aint[0], p_242654_2_.getY(), p_242654_2_.getZ() + aint[1]);
			Vector3d vector3d = TransportationHelper.findSafeDismountLocation(p_242654_0_, p_242654_1_, blockpos$mutable, p_242654_4_);
			if(vector3d != null)
			{
				return Optional.of(vector3d);
			}
		}

		return Optional.empty();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState pState)
	{
		return pState.getValue(PART) == BedPart.HEAD ? BlockRenderType.INVISIBLE : BlockRenderType.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, PART, OCCUPIED);
	}

	@Override
	public void setPlacedBy(World level, BlockPos pos, BlockState blockState, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.setPlacedBy(level, pos, blockState, placer, stack);

		if(!level.isClientSide)
		{
			BlockPos relativePos = pos.relative(blockState.getValue(FACING));
			level.setBlock(relativePos, blockState.setValue(PART, BedPart.HEAD), 3);
			level.blockUpdated(pos, Blocks.AIR);
			blockState.updateNeighbourShapes(level, pos, 3);
		}

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public long getSeed(BlockState blockState, BlockPos pos)
	{
		BlockPos relativePos = pos.relative(blockState.getValue(FACING), blockState.getValue(PART) == BedPart.HEAD ? 0 : 1);
		return MathHelper.getSeed(relativePos.getX(), pos.getY(), relativePos.getZ());
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}

	@Override
	public boolean isBed(BlockState blockState, IBlockReader level, BlockPos pos, @Nullable Entity player)
	{
		return isBedBlockState(blockState);
	}

	private static int[][] bedStandUpOffsets(Direction direction, Direction dir)
	{
		return ArrayUtils.addAll(bedSurroundStandUpOffsets(direction, dir), bedAboveStandUpOffsets(direction));
	}

	private static int[][] bedSurroundStandUpOffsets(Direction direction, Direction dir)
	{
		int dirStepX = dir.getStepX();
		int dirStepZ = dir.getStepZ();

		int directionStepX = direction.getStepX();
		int directionStepZ = direction.getStepZ();

		return new int[][] {
				{ dirStepX, dirStepZ },
				{ dirStepX - directionStepX, dirStepZ - directionStepZ },
				{ dirStepX - directionStepX * 2, dirStepZ - directionStepZ * 2 },
				{ -directionStepX * 2, -directionStepZ * 2 },
				{ -dirStepX - directionStepX * 2, -dirStepZ - directionStepZ * 2 },
				{ -dirStepX - directionStepX, -dirStepZ - directionStepZ },
				{ -dirStepX, -dirStepZ },
				{ -dirStepX + directionStepX, -dirStepZ + directionStepZ },
				{ directionStepX, directionStepZ },
				{ dirStepX + directionStepX, dirStepZ + directionStepZ }
		};
	}

	private static int[][] bedAboveStandUpOffsets(Direction direction)
	{
		return new int[][] {
				{ 0, 0 },
				{ -direction.getStepX(), -direction.getStepZ() }
		};
	}

	public static boolean isBedBlock(Block block)
	{
		if(block instanceof BedBlock)
			return true;
		if(block instanceof BaseBedBlock)
			return true;
		return block.is(BlockTags.BEDS);
	}

	public static boolean isBedBlockState(BlockState blockState)
	{
		if(isBedBlock(blockState.getBlock()))
			return true;
		return blockState.is(BlockTags.BEDS);
	}
}

package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.apexcore.revamp.block.SeatBlock;
import xyz.apex.java.utility.nullness.NonnullConsumer;

import javax.annotation.Nullable;

public class SofaBlock extends SeatBlock
{
	public static final EnumProperty<ConnectionType> CONNECTION_TYPE = EnumProperty.create("connection_type", ConnectionType.class);

	public SofaBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(CONNECTION_TYPE, ConnectionType.NONE));
	}

	@Override
	protected void registerProperties(NonnullConsumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(CONNECTION_TYPE);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
	{
		blockState = super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
		return getBlockState(level, pos, blockState, this);
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState != null)
			placementBlockState = getBlockState(ctx.getLevel(), ctx.getClickedPos(), placementBlockState, this);

		return placementBlockState;
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
	public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
		updateConnectionBlockState(level, pos, blockState, this);
	}

	public static void updateConnectionBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		var newBlockState = getBlockState(level, pos, blockState, baseSofaBlock);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		var connectionType = getConnectionState(level, pos, blockState, baseSofaBlock);
		return blockState.setValue(CONNECTION_TYPE, connectionType);
	}

	public static ConnectionType getConnectionState(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		var originFacing = BaseBlock.getFacing(blockState);
		var originConnectionType = blockState.getValue(CONNECTION_TYPE);

		var leftPos = pos.relative(originFacing.getCounterClockWise());
		var rightPos = pos.relative(originFacing.getClockWise());
		var inFrontPos = pos.relative(originFacing);

		var leftBlockState = level.getBlockState(leftPos);
		var rightBlockState = level.getBlockState(rightPos);
		var inFrontBlockState = level.getBlockState(inFrontPos);

		if(isCornerConnection(leftBlockState, rightBlockState, inFrontBlockState, originFacing, baseSofaBlock))
			return ConnectionType.CORNER;

		var hasLeft = isLeftConnection(leftBlockState, originFacing, originConnectionType, baseSofaBlock);
		var hasRight = isRightConnection(rightBlockState, originFacing, originConnectionType, baseSofaBlock);

		if(hasLeft && !hasRight)
			return ConnectionType.LEFT;
		if(hasRight && !hasLeft)
			return ConnectionType.RIGHT;
		if(hasLeft && hasRight)
			return ConnectionType.CENTER;

		return ConnectionType.NONE;
	}

	public static boolean isCornerConnection(BlockState leftBlockState, BlockState rightBlockState, BlockState inFrontBlockState, Direction originFacing, SofaBlock baseSofaBlock)
	{
		if(!inFrontBlockState.is(baseSofaBlock))
			return false;

		var inFrontFacing = BaseBlock.getFacing(inFrontBlockState);

		if(leftBlockState.is(baseSofaBlock))
		{
			var leftFacing = BaseBlock.getFacing(leftBlockState);
			return isCornerFacing(originFacing, leftFacing, inFrontFacing);
		}
		else if(rightBlockState.is(baseSofaBlock))
		{
			var rightFacing = BaseBlock.getFacing(rightBlockState);
			return isCornerFacing(originFacing, rightFacing, inFrontFacing);
		}

		return false;
	}

	public static boolean isCornerFacing(Direction originFacing, Direction sidedFacing, Direction inFrontFacing)
	{
		if(originFacing == sidedFacing)
			return sidedFacing.getCounterClockWise() == inFrontFacing || sidedFacing == inFrontFacing.getClockWise();
		else
			return sidedFacing.getOpposite() == inFrontFacing || sidedFacing == inFrontFacing.getOpposite();
	}

	public static boolean isLeftConnection(BlockState leftBlockState, Direction originFacing, ConnectionType originConnectionType, SofaBlock baseSofaBlock)
	{
		if(!leftBlockState.is(baseSofaBlock))
			return false;
		if(BaseBlock.getFacing(leftBlockState) == originFacing)
			return true;

		var leftConnectionType = leftBlockState.getValue(CONNECTION_TYPE);

		if(leftConnectionType == ConnectionType.CORNER || leftConnectionType == ConnectionType.CENTER)
			return true;
		return false;
	}

	public static boolean isRightConnection(BlockState rightBlockState, Direction originFacing, ConnectionType originConnectionType, SofaBlock baseSofaBlock)
	{
		if(!rightBlockState.is(baseSofaBlock))
			return false;
		if(BaseBlock.getFacing(rightBlockState) == originFacing)
			return true;

		var rightConnectionType = rightBlockState.getValue(CONNECTION_TYPE);

		if(rightConnectionType == ConnectionType.CORNER || rightConnectionType == ConnectionType.CENTER)
			return true;
		return false;
	}

	public enum ConnectionType implements StringRepresentable
	{
		LEFT("left"),
		RIGHT("right"),
		CENTER("center"),
		CORNER("corner"),
		NONE("single");

		private final String serializedName;

		ConnectionType(String serializedName)
		{
			this.serializedName = serializedName;
		}

		@Override
		public String getSerializedName()
		{
			return serializedName;
		}
	}
}

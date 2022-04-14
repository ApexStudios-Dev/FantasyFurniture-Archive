package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(CONNECTION_TYPE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos pos, BlockPos facingPos)
	{
		return getBlockState(level, pos, blockState, this);
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = super.getStateForPlacement(ctx);
		return getBlockState(ctx.getLevel(), ctx.getClickedPos(), blockState, this);
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

	@Override
	public void neighborChanged(BlockState blockState, World level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
		updateConnectionBlockState(level, pos, blockState, this);
	}

	public static void updateConnectionBlockState(IWorld level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		BlockState newBlockState = getBlockState(level, pos, blockState, baseSofaBlock);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(IWorld level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		ConnectionType connectionType = getConnectionState(level, pos, blockState, baseSofaBlock);
		return blockState.setValue(CONNECTION_TYPE, connectionType);
	}

	public static ConnectionType getConnectionState(IWorld level, BlockPos pos, BlockState blockState, SofaBlock baseSofaBlock)
	{
		Direction originFacing = blockState.getValue(FACING);
		ConnectionType originConnectionType = blockState.getValue(CONNECTION_TYPE);

		BlockPos leftPos = pos.relative(originFacing.getCounterClockWise());
		BlockPos rightPos = pos.relative(originFacing.getClockWise());
		BlockPos inFrontPos = pos.relative(originFacing);

		BlockState leftBlockState = level.getBlockState(leftPos);
		BlockState rightBlockState = level.getBlockState(rightPos);
		BlockState inFrontBlockState = level.getBlockState(inFrontPos);

		if(isCornerConnection(leftBlockState, rightBlockState, inFrontBlockState, originFacing, baseSofaBlock))
			return ConnectionType.CORNER;

		boolean hasLeft = isLeftConnection(leftBlockState, originFacing, originConnectionType, baseSofaBlock);
		boolean hasRight = isRightConnection(rightBlockState, originFacing, originConnectionType, baseSofaBlock);

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

		Direction inFrontFacing = inFrontBlockState.getValue(FACING);

		if(leftBlockState.is(baseSofaBlock))
		{
			Direction leftFacing = leftBlockState.getValue(FACING);
			return isCornerFacing(originFacing, leftFacing, inFrontFacing);
		}
		else if(rightBlockState.is(baseSofaBlock))
		{
			Direction rightFacing = rightBlockState.getValue(FACING);
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
		if(leftBlockState.getValue(FACING) == originFacing)
			return true;

		ConnectionType leftConnectionType = leftBlockState.getValue(CONNECTION_TYPE);

		if(leftConnectionType == ConnectionType.CORNER || leftConnectionType == ConnectionType.CENTER)
			return true;
		return false;
	}

	public static boolean isRightConnection(BlockState rightBlockState, Direction originFacing, ConnectionType originConnectionType, SofaBlock baseSofaBlock)
	{
		if(!rightBlockState.is(baseSofaBlock))
			return false;
		if(rightBlockState.getValue(FACING) == originFacing)
			return true;

		ConnectionType rightConnectionType = rightBlockState.getValue(CONNECTION_TYPE);

		if(rightConnectionType == ConnectionType.CORNER || rightConnectionType == ConnectionType.CENTER)
			return true;
		return false;
	}

	public enum ConnectionType implements IStringSerializable
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

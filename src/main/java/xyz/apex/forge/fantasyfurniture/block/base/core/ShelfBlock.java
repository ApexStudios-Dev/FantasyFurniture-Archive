package xyz.apex.forge.fantasyfurniture.block.base.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;

public class ShelfBlock extends SimpleFourWayWaterLoggedBlock
{
	public static final EnumProperty<ConnectionType> CONNECTION_TYPE = EnumProperty.create("connection_type", ConnectionType.class);

	public ShelfBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(CONNECTION_TYPE, ConnectionType.NONE));
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		var connectionState = getConnectionState(level, currentPos, blockState, this);
		return blockState.setValue(CONNECTION_TYPE, connectionState);
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		var blockState = super.getStateForPlacement(ctx);
		var connectionState = getConnectionState(ctx.getLevel(), ctx.getClickedPos(), blockState, this);
		return blockState.setValue(CONNECTION_TYPE, connectionState);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(CONNECTION_TYPE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);

		var connectionState = getConnectionState(level, pos, blockState, this);
		level.setBlockAndUpdate(pos, blockState.setValue(CONNECTION_TYPE, connectionState));
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public boolean isPathfindable(BlockState blockState, BlockGetter level, BlockPos pos, PathComputationType pathType)
	{
		return false;
	}

	private static ConnectionType getConnectionState(LevelAccessor level, BlockPos pos, BlockState blockState, ShelfBlock baseShelfBlock)
	{
		var facing = blockState.getValue(FACING);

		var leftPos = pos.relative(facing.getCounterClockWise());
		var rightPos = pos.relative(facing.getClockWise());

		var hasLeft = hasConnection(level, leftPos, baseShelfBlock);
		var hasRight = hasConnection(level, rightPos, baseShelfBlock);

		if(hasLeft && hasRight)
			return ConnectionType.BOTH;
		if(hasLeft)
			return ConnectionType.LEFT;
		if(hasRight)
			return ConnectionType.RIGHT;
		return ConnectionType.NONE;
	}

	private static boolean hasConnection(LevelAccessor level, BlockPos pos, ShelfBlock baseShelfBlock)
	{
		var blockState = level.getBlockState(pos);
		return blockState.is(baseShelfBlock);
	}

	public enum ConnectionType implements StringRepresentable
	{
		NONE("none"),
		LEFT("left"),
		RIGHT("right"),
		BOTH("boh");

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

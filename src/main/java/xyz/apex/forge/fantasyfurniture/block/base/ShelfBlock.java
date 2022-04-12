package xyz.apex.forge.fantasyfurniture.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

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
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, IWorld level, BlockPos currentPos, BlockPos facingPos)
	{
		ConnectionType connectionState = getConnectionState(level, currentPos, blockState, this);
		return blockState.setValue(CONNECTION_TYPE, connectionState);
	}

	@SuppressWarnings("ConstantConditions") // super is non null
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = super.getStateForPlacement(ctx);
		ConnectionType connectionState = getConnectionState(ctx.getLevel(), ctx.getClickedPos(), blockState, this);
		return blockState.setValue(CONNECTION_TYPE, connectionState);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(CONNECTION_TYPE);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public void neighborChanged(BlockState blockState, World level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);

		ConnectionType connectionState = getConnectionState(level, pos, blockState, this);
		level.setBlockAndUpdate(pos, blockState.setValue(CONNECTION_TYPE, connectionState));
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState blockState)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader level, BlockPos pos, PathType pathType)
	{
		return false;
	}

	private static ConnectionType getConnectionState(IWorld level, BlockPos pos, BlockState blockState, ShelfBlock baseShelfBlock)
	{
		Direction facing = blockState.getValue(FACING);

		BlockPos leftPos = pos.relative(facing.getCounterClockWise());
		BlockPos rightPos = pos.relative(facing.getClockWise());

		boolean hasLeft = hasConnection(level, leftPos, baseShelfBlock);
		boolean hasRight = hasConnection(level, rightPos, baseShelfBlock);

		if(hasLeft && hasRight)
			return ConnectionType.BOTH;
		if(hasLeft)
			return ConnectionType.LEFT;
		if(hasRight)
			return ConnectionType.RIGHT;
		return ConnectionType.NONE;
	}

	private static boolean hasConnection(IWorld level, BlockPos pos, ShelfBlock baseShelfBlock)
	{
		BlockState blockState = level.getBlockState(pos);
		return blockState.is(baseShelfBlock);
	}

	public enum ConnectionType implements IStringSerializable
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

package xyz.apex.forge.fantasyfurniture.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BaseSofaBlock extends BaseSeatBlock
{
	public static final EnumProperty<ConnectionType> CONNECTION_TYPE = EnumProperty.create("connection_type", ConnectionType.class);

	public BaseSofaBlock(Properties properties)
	{
		super(properties);
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
		BlockState blockState = super.getStateForPlacement(ctx).setValue(FACING, ctx.getHorizontalDirection().getOpposite());
		return getBlockState(ctx.getLevel(), ctx.getClickedPos(), blockState, this);
	}

	@Override
	public void neighborChanged(BlockState blockState, World level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
		updateConnectionBlockState(level, pos, blockState, this);
	}

	public static void updateConnectionBlockState(IWorld level, BlockPos pos, BlockState blockState, BaseSofaBlock baseSofaBlock)
	{
		BlockState newBlockState = getBlockState(level, pos, blockState, baseSofaBlock);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(IWorld level, BlockPos pos, BlockState blockState, BaseSofaBlock baseSofaBlock)
	{
		ConnectionType connectionType = getConnectionState(level, pos, blockState, baseSofaBlock);
		return blockState.setValue(CONNECTION_TYPE, connectionType);
	}

	public static ConnectionType getConnectionState(IWorld level, BlockPos pos, BlockState blockState, BaseSofaBlock baseSofaBlock)
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

	public static boolean isCornerConnection(BlockState leftBlockState, BlockState rightBlockState, BlockState inFrontBlockState, Direction originFacing, BaseSofaBlock baseSofaBlock)
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

	public static boolean isLeftConnection(BlockState leftBlockState, Direction originFacing, ConnectionType originConnectionType, BaseSofaBlock baseSofaBlock)
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

	public static boolean isRightConnection(BlockState rightBlockState, Direction originFacing, ConnectionType originConnectionType, BaseSofaBlock baseSofaBlock)
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
		CORNER("turn"),
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

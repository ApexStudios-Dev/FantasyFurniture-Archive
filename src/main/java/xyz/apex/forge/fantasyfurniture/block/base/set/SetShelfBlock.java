package xyz.apex.forge.fantasyfurniture.block.base.set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SetShelfBlock extends BaseBlock implements IFurnitureSetBlock
{
	public static final EnumProperty<ConnectionType> CONNECTION_TYPE = EnumProperty.create("connection_type", ConnectionType.class);

	protected final ModBlocks furnitureSet;

	public SetShelfBlock(ModBlocks furnitureSet, Properties properties)
	{
		super(properties);

		this.furnitureSet = furnitureSet;

		registerDefaultState(defaultBlockState().setValue(CONNECTION_TYPE, ConnectionType.NONE));
	}

	@Override
	public final ModBlocks getFurnitureSet()
	{
		return furnitureSet;
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		var connectionState = getConnectionState(level, currentPos, blockState, this);
		return blockState.setValue(CONNECTION_TYPE, connectionState);
	}

	@Nullable
	@Override
	protected BlockState modifyPlacementState(BlockState placementBlockState, BlockPlaceContext ctx)
	{
		placementBlockState = super.modifyPlacementState(placementBlockState, ctx);

		if(placementBlockState != null)
		{
			var connection = getConnectionState(ctx.getLevel(), ctx.getClickedPos(), placementBlockState, this);
			placementBlockState = placementBlockState.setValue(CONNECTION_TYPE, connection);
		}

		return placementBlockState;
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(CONNECTION_TYPE);
	}

	@Override
	public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);

		var connectionState = getConnectionState(level, pos, blockState, this);
		level.setBlockAndUpdate(pos, blockState.setValue(CONNECTION_TYPE, connectionState));
	}

	@Override
	public boolean isPathfindable(BlockState blockState, BlockGetter level, BlockPos pos, PathComputationType pathType)
	{
		return false;
	}

	@Override
	public final VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		return furnitureSet.hitBoxes.shelf(this, blockState);
	}

	private static ConnectionType getConnectionState(LevelAccessor level, BlockPos pos, BlockState blockState, Block baseShelfBlock)
	{
		var facing = BaseBlock.getFacing(blockState);

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

	private static boolean hasConnection(LevelAccessor level, BlockPos pos, Block baseShelfBlock)
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

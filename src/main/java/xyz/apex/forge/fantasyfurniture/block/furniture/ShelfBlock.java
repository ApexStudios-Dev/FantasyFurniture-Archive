package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.BaseBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class ShelfBlock extends BaseBlock
{
	public static final EnumProperty<Connection> CONNECTION = EnumProperty.create("connection", Connection.class);

	public ShelfBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected void registerProperties(Consumer<Property<?>> consumer)
	{
		super.registerProperties(consumer);
		consumer.accept(FACING_4_WAY);
		consumer.accept(WATERLOGGED);
		consumer.accept(CONNECTION);
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_SHELF.has(blockState))
			return HitBoxes.NORDIC.shelf(this, blockState);

		return super.getShape(blockState, level, pos, ctx);
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
	public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
	{
		super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
		updateConnectionBlockState(level, pos, blockState, this);
	}

	public static void updateConnectionBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, ShelfBlock shelf)
	{
		var newBlockState = getBlockState(level, pos, blockState, shelf);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, ShelfBlock shelf)
	{
		if(blockState.hasProperty(CONNECTION))
		{
			var connection = getConnection(level, pos, blockState, shelf);
			return blockState.setValue(CONNECTION, connection);
		}

		return blockState;
	}

	public static Connection getConnection(LevelAccessor level, BlockPos pos, BlockState blockState, ShelfBlock shelf)
	{
		var facing = getFacing(blockState);
		var connection = blockState.getOptionalValue(CONNECTION).orElse(Connection.SINGLE);

		var leftPos = pos.relative(facing.getCounterClockWise());
		var rightPos = pos.relative(facing.getClockWise());

		var leftBlockState = level.getBlockState(leftPos);
		var rightBlockState = level.getBlockState(rightPos);

		var isLeft = isSideConnection(leftBlockState, facing, connection, shelf);
		var isRight = isSideConnection(rightBlockState, facing, connection, shelf);

		if(isLeft && isRight)
			return Connection.CENTER;
		else if(isLeft)
			return Connection.LEFT;
		else if(isRight)
			return Connection.RIGHT;

		return Connection.SINGLE;
	}

	public static boolean isSideConnection(BlockState side, Direction facing, Connection connection, ShelfBlock shelf)
	{
		if(!side.is(shelf))
			return false;
		if(getFacing(side) == facing)
			return true;

		var sideConnection = side.getOptionalValue(CONNECTION).orElse(Connection.SINGLE);
		return sideConnection == Connection.CENTER;
	}

	public enum Connection implements StringRepresentable
	{
		LEFT("left"),
		RIGHT("right"),

		CENTER("center"),

		SINGLE("single");

		public final String serializedName;

		Connection(String serializedName)
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
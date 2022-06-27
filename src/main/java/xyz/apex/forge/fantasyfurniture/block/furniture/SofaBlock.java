package xyz.apex.forge.fantasyfurniture.block.furniture;

import org.jetbrains.annotations.Nullable;

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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import xyz.apex.forge.apexcore.revamp.block.SeatBlock;
import xyz.apex.forge.fantasyfurniture.init.HitBoxes;
import xyz.apex.forge.fantasyfurniture.init.ModBlocks;

import java.util.function.Consumer;

public class SofaBlock extends SeatBlock
{
	public static final EnumProperty<Connection> CONNECTION = EnumProperty.create("connection", Connection.class);

	public SofaBlock(Properties properties)
	{
		super(properties);

		registerDefaultState(defaultBlockState().setValue(CONNECTION, Connection.SINGLE));
	}

	@Override
	public double getSeatYOffset(BlockState blockState)
	{
		return .1D;
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
	public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext ctx)
	{
		if(ModBlocks.NORDIC_SOFA.has(blockState))
			return HitBoxes.NORDIC.sofa(this, blockState);

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

	public static void updateConnectionBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock sofa)
	{
		var newBlockState = getBlockState(level, pos, blockState, sofa);

		if(newBlockState != blockState)
			level.setBlock(pos, newBlockState, 3);
	}

	public static BlockState getBlockState(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock sofa)
	{
		if(blockState.hasProperty(CONNECTION))
		{
			var connection = getConnection(level, pos, blockState, sofa);
			return blockState.setValue(CONNECTION, connection);
		}

		return blockState;
	}

	public static Connection getConnection(LevelAccessor level, BlockPos pos, BlockState blockState, SofaBlock sofa)
	{
		var facing = getFacing(blockState);
		var connection = blockState.getOptionalValue(CONNECTION).orElse(Connection.SINGLE);

		var leftPos = pos.relative(facing.getCounterClockWise());
		var rightPos = pos.relative(facing.getClockWise());
		var frontPos = pos.relative(facing);

		var leftBlockState = level.getBlockState(leftPos);
		var rightBlockState = level.getBlockState(rightPos);
		var frontBlockState = level.getBlockState(frontPos);

		if(isCornerConnection(leftBlockState, rightBlockState, frontBlockState, facing, sofa))
			return Connection.CORNER;

		var isLeft = isSideConnection(leftBlockState, facing, connection, sofa);
		var isRight = isSideConnection(rightBlockState, facing, connection, sofa);

		if(isLeft && isRight)
			return Connection.CENTER;
		else if(isLeft)
			return Connection.LEFT;
		else if(isRight)
			return Connection.RIGHT;

		return Connection.SINGLE;
	}

	public static boolean isCornerConnection(BlockState left, BlockState right, BlockState front, Direction facing, SofaBlock sofa)
	{
		if(!front.is(sofa))
			return false;

		var frontFacing = getFacing(front);

		if(left.is(sofa))
		{
			var leftFacing = getFacing(left);
			return isCornerFacing(facing, leftFacing, frontFacing);
		}
		else if(right.is(sofa))
		{
			var rightFacing = getFacing(right);
			return isCornerFacing(facing, rightFacing, frontFacing);
		}

		return false;
	}

	public static boolean isCornerFacing(Direction facing, Direction sideFacing, Direction frontFacing)
	{
		if(facing == sideFacing)
			return sideFacing.getCounterClockWise() == frontFacing || sideFacing == frontFacing.getClockWise();
		else
			return sideFacing.getOpposite() == frontFacing || sideFacing == frontFacing.getOpposite();
	}

	public static boolean isSideConnection(BlockState side, Direction facing, Connection connection, SofaBlock sofa)
	{
		if(!side.is(sofa))
			return false;
		if(getFacing(side) == facing)
			return true;

		var sideConnection = side.getOptionalValue(CONNECTION).orElse(Connection.SINGLE);
		return sideConnection == Connection.CORNER || sideConnection == Connection.CENTER;
	}

	public enum Connection implements StringRepresentable
	{
		LEFT("left"),
		RIGHT("right"),

		CENTER("center"),
		CORNER("corner"),

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
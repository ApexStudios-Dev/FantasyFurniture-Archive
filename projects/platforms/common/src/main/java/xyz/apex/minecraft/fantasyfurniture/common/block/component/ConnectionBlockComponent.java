package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.property.ConnectionType;

public final class ConnectionBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<ConnectionBlockComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "connection_type", ConnectionBlockComponent::new);
    public static final EnumProperty<ConnectionType> PROPERTY = EnumProperty.create("connection_type", ConnectionType.class);

    private ConnectionBlockComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState.setValue(PROPERTY, ConnectionType.SINGLE);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(PROPERTY);
    }

    @Override
    public BlockState getStateForPlacement(BlockState placementBlockState, BlockPlaceContext context)
    {
        return getBlockState(context.getLevel(), context.getClickedPos(), placementBlockState);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborBlockState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(direction.getAxis().isHorizontal())
            return getBlockState(level, currentPos, blockState);

        return blockState;
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);

        /*var newBlockState = getBlockState(level, pos, blockState);

        if(newBlockState != blockState)
            level.setBlock(pos, newBlockState, Block.UPDATE_ALL);*/
    }

    public static BlockState getBlockState(BlockGetter level, BlockPos pos, BlockState blockState)
    {
        return blockState.setValue(PROPERTY, getConnectionType(level, pos, blockState));
    }

    public static ConnectionType getConnectionType(BlockGetter level, BlockPos pos, BlockState blockState)
    {
        if(!isConnectable(blockState))
            return ConnectionType.SINGLE;

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        var connection = blockState.getValue(PROPERTY);

        var leftPos = pos.relative(facing.getCounterClockWise());
        var rightPos = pos.relative(facing.getClockWise());
        var frontPos = pos.relative(facing);

        var leftBlockState = level.getBlockState(leftPos);
        var rightBlockState = level.getBlockState(rightPos);
        var frontBlockState = level.getBlockState(frontPos);

        if(isCornerConnection(leftBlockState, rightBlockState, frontBlockState, facing))
            return ConnectionType.CORNER;

        var isLeft = isSideConnection(leftBlockState, facing, connection);
        var isRight = isSideConnection(rightBlockState, facing, connection);

        if(isLeft && isRight)
            return ConnectionType.CENTER;
        else if(isLeft)
            return ConnectionType.LEFT;
        else if(isRight)
            return ConnectionType.RIGHT;

        return ConnectionType.SINGLE;
    }

    public static boolean isCornerConnection(BlockState left, BlockState right, BlockState front, Direction facing)
    {
        if(!isConnectable(front))
            return false;

        var frontFacing = front.getValue(HorizontalFacingBlockComponent.FACING);

        if(isConnectable(left))
        {
            var leftFacing = left.getValue(HorizontalFacingBlockComponent.FACING);
            return isCornerFacing(facing, leftFacing, frontFacing);
        }
        else if(isConnectable(right))
        {
            var rightFacing = right.getValue(HorizontalFacingBlockComponent.FACING);
            return isCornerFacing(facing, rightFacing, frontFacing);
        }

        return false;
    }

    public static boolean isCornerFacing(Direction facing, Direction sideFacing, Direction frontFacing)
    {
        if(facing == sideFacing)
            return sideFacing.getCounterClockWise() == frontFacing || sideFacing == frontFacing.getClockWise();

        return sideFacing.getOpposite() == frontFacing || sideFacing == frontFacing.getOpposite();
    }

    public static boolean isSideConnection(BlockState side, Direction facing, ConnectionType connectionType)
    {
        if(!isConnectable(side))
            return false;
        if(side.getValue(HorizontalFacingBlockComponent.FACING) == facing)
            return true;

        var sideConnection = side.getValue(PROPERTY);
        return sideConnection == ConnectionType.CORNER || sideConnection == ConnectionType.CENTER;
    }

    public static boolean isConnectable(BlockState blockState, BlockState other)
    {
        if(!isConnectable(other))
            return false;

        var otherType = other.getValue(PROPERTY);

        return switch(blockState.getValue(PROPERTY)) {
            case SINGLE -> true;
            case LEFT -> otherType != ConnectionType.LEFT;
            case RIGHT -> otherType != ConnectionType.RIGHT;
            case CENTER -> true;
            case CORNER -> true;
        };
    }

    public static boolean isConnectable(BlockState blockState)
    {
        return blockState.getBlock() instanceof BlockComponentHolder holder && holder.hasComponent(COMPONENT_TYPE);
    }
}

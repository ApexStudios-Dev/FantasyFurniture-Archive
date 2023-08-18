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

import java.util.EnumSet;

public final class ConnectionBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<ConnectionBlockComponent> SOFA_COMPONENT_TYPE = register(FantasyFurniture.ID, "connection_type/sofa", ConnectionType.SINGLE, ConnectionType.LEFT, ConnectionType.RIGHT, ConnectionType.CENTER, ConnectionType.CORNER);
    public static final BlockComponentType<ConnectionBlockComponent> SHELF_COMPONENT_TYPE = register(FantasyFurniture.ID, "connection_type/shelf", ConnectionType.SINGLE, ConnectionType.LEFT, ConnectionType.RIGHT, ConnectionType.CENTER);

    private final EnumProperty<ConnectionType> property;

    private ConnectionBlockComponent(BlockComponentHolder componentHolder, ConnectionType... connectionTypes)
    {
        super(componentHolder);

        property = EnumProperty.create("connection_type", ConnectionType.class, EnumSet.of(ConnectionType.SINGLE, connectionTypes));
    }

    public EnumProperty<ConnectionType> getProperty()
    {
        return property;
    }

    public boolean isValidConnection(ConnectionType connectionType)
    {
        return property.getPossibleValues().contains(connectionType);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState.setValue(property, ConnectionType.SINGLE);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(property);
    }

    @Override
    public BlockState getStateForPlacement(BlockState placementBlockState, BlockPlaceContext context)
    {
        return getBlockState(this, context.getLevel(), context.getClickedPos(), placementBlockState);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborBlockState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(direction.getAxis().isHorizontal())
            return getBlockState(this, level, currentPos, blockState);

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

    public static BlockComponentType<ConnectionBlockComponent> register(String ownerId, String componentName, ConnectionType... connectionTypes)
    {
        return BlockComponentType.register(ownerId, componentName, componentHolder -> new ConnectionBlockComponent(componentHolder, connectionTypes));
    }

    public static BlockState getBlockState(ConnectionBlockComponent component, BlockGetter level, BlockPos pos, BlockState blockState)
    {
        return blockState.setValue(component.property, getConnectionType(component, level, pos, blockState));
    }

    public static ConnectionType getConnectionType(ConnectionBlockComponent component, BlockGetter level, BlockPos pos, BlockState blockState)
    {
        if(!isConnectable(component, blockState))
            return ConnectionType.SINGLE;

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

        var leftPos = pos.relative(facing.getCounterClockWise());
        var rightPos = pos.relative(facing.getClockWise());
        var frontPos = pos.relative(facing);

        var leftBlockState = level.getBlockState(leftPos);
        var rightBlockState = level.getBlockState(rightPos);
        var frontBlockState = level.getBlockState(frontPos);

        if(component.isValidConnection(ConnectionType.CORNER) && isCornerConnection(component, leftBlockState, rightBlockState, frontBlockState, facing))
            return ConnectionType.CORNER;

        var isLeft = isSideConnection(component, leftBlockState, facing);
        var isRight = isSideConnection(component, rightBlockState, facing);

        if(component.isValidConnection(ConnectionType.CENTER) && isLeft && isRight)
            return ConnectionType.CENTER;
        else if(component.isValidConnection(ConnectionType.LEFT) && isLeft)
            return ConnectionType.LEFT;
        else if(component.isValidConnection(ConnectionType.RIGHT) && isRight)
            return ConnectionType.RIGHT;

        return ConnectionType.SINGLE;
    }

    public static boolean isCornerConnection(ConnectionBlockComponent component, BlockState left, BlockState right, BlockState front, Direction facing)
    {
        if(!isConnectable(component, front))
            return false;

        var frontFacing = front.getValue(HorizontalFacingBlockComponent.FACING);

        if(isConnectable(component, left))
        {
            var leftFacing = left.getValue(HorizontalFacingBlockComponent.FACING);
            return isCornerFacing(facing, leftFacing, frontFacing);
        }
        else if(isConnectable(component, right))
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

    public static boolean isSideConnection(ConnectionBlockComponent component, BlockState side, Direction facing)
    {
        if(!isConnectable(component, side))
            return false;
        if(side.getValue(HorizontalFacingBlockComponent.FACING) == facing)
            return true;

        var sideConnection = side.getValue(component.property);
        return sideConnection == ConnectionType.CORNER || sideConnection == ConnectionType.CENTER;
    }

    public static boolean isConnectable(ConnectionBlockComponent component, BlockState blockState)
    {
        return blockState.hasProperty(component.getProperty());
    }
}

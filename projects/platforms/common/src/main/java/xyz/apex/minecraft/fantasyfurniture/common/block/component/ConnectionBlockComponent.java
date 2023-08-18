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
    public static final BlockComponentType<ConnectionBlockComponent> SOFA_COMPONENT_TYPE = register(FantasyFurniture.ID, "connection_type/sofa", ConnectionType.SINGLE, ConnectionType.INNER_LEFT, ConnectionType.INNER_RIGHT, ConnectionType.LEFT, ConnectionType.RIGHT, ConnectionType.CENTER);
    public static final BlockComponentType<ConnectionBlockComponent> SHELF_COMPONENT_TYPE = register(FantasyFurniture.ID, "connection_type/shelf", ConnectionType.SINGLE, ConnectionType.LEFT, ConnectionType.RIGHT, ConnectionType.CENTER);
    public static final BlockComponentType<ConnectionBlockComponent> COUNTER_COMPONENT_TYPE = register(FantasyFurniture.ID, "connection_type/counter", ConnectionType.SINGLE, ConnectionType.INNER_LEFT, ConnectionType.INNER_RIGHT, ConnectionType.LEFT, ConnectionType.RIGHT);

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
        placementBlockState = placementBlockState.setValue(HorizontalFacingBlockComponent.FACING, context.getHorizontalDirection().getOpposite());
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
        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

        if(component.isValidConnection(ConnectionType.INNER_LEFT) || component.isValidConnection(ConnectionType.INNER_RIGHT))
        {
            Direction dir1;
            var blockState1 = level.getBlockState(pos.relative(facing));

            if(isConnectable(component, blockState1) && (dir1 = blockState1.getValue(HorizontalFacingBlockComponent.FACING)).getAxis() != facing.getAxis() && isDifferentOrientation(component, level, pos, blockState, dir1.getOpposite()))
            {
                if(component.isValidConnection(ConnectionType.INNER_LEFT) && dir1 == facing.getCounterClockWise())
                    return ConnectionType.INNER_LEFT;
                else if(component.isValidConnection(ConnectionType.INNER_RIGHT))
                    return ConnectionType.INNER_RIGHT;
            }
        }

        if(component.isValidConnection(ConnectionType.OUTER_LEFT) || component.isValidConnection(ConnectionType.OUTER_RIGHT))
        {
            Direction dir2;
            var blockState2 = level.getBlockState(pos.relative(facing.getOpposite()));

            if(isConnectable(component, blockState2) && (dir2 = blockState2.getValue(HorizontalFacingBlockComponent.FACING)).getAxis() != facing.getAxis() && isDifferentOrientation(component, level, pos, blockState, dir2))
            {
                if(component.isValidConnection(ConnectionType.OUTER_LEFT) && dir2 == facing.getCounterClockWise())
                    return ConnectionType.OUTER_LEFT;
                else if(component.isValidConnection(ConnectionType.OUTER_RIGHT))
                    return ConnectionType.OUTER_RIGHT;
            }
        }

        var left = canConnect(component, level, pos, facing.getCounterClockWise());
        var right = canConnect(component, level, pos, facing.getClockWise());

        if(component.isValidConnection(ConnectionType.CENTER) && left && right)
            return ConnectionType.CENTER;
        else if(component.isValidConnection(ConnectionType.LEFT) && left)
            return ConnectionType.LEFT;
        else if(component.isValidConnection(ConnectionType.RIGHT) && right)
            return ConnectionType.RIGHT;

        return ConnectionType.SINGLE;
    }

    public static boolean canConnect(ConnectionBlockComponent component, BlockGetter level, BlockPos pos, Direction dir)
    {
        return isConnectable(component, level.getBlockState(pos.relative(dir)));
    }

    public static boolean isDifferentOrientation(ConnectionBlockComponent component, BlockGetter level, BlockPos pos, BlockState blockState, Direction dir)
    {
        var otherBlockState = level.getBlockState(pos.relative(dir));
        return !isConnectable(component, otherBlockState) || otherBlockState.getValue(HorizontalFacingBlockComponent.FACING) != blockState.getValue(HorizontalFacingBlockComponent.FACING);
    }

    public static boolean isConnectable(ConnectionBlockComponent component, BlockState blockState)
    {
        return blockState.hasProperty(component.getProperty());
    }
}

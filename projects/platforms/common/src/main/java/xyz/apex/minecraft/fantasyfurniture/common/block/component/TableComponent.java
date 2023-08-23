package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Collections;
import java.util.Map;

public final class TableComponent extends BaseBlockComponent
{
    public static final BlockComponentType<TableComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "table", TableComponent::new);

    public static final int MAX_LENGTH = 8;
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = Collections.unmodifiableMap(Util.make(Maps.newEnumMap(Direction.class), map -> {
        map.put(Direction.NORTH, NORTH);
        map.put(Direction.EAST, EAST);
        map.put(Direction.SOUTH, SOUTH);
        map.put(Direction.WEST, WEST);
    }));

    private TableComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState
                .setValue(ROTATED, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATED, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public BlockState getStateForPlacement(BlockState placementBlockState, BlockPlaceContext context)
    {
        var rotated = context.getHorizontalDirection().getAxis() == Direction.Axis.X;
        return placementBlockState.setValue(ROTATED, rotated);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborBlockState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(direction.getAxis().isVertical())
            return blockState;

        var property = PROPERTY_BY_DIRECTION.get(direction);
        var oppositeProperty = PROPERTY_BY_DIRECTION.get(direction.getOpposite());

        var connection = blockState.getValue(property);

        if(neighborBlockState.is(getGameObject()))
        {
            var neighborConnection = neighborBlockState.getValue(oppositeProperty);

            if(neighborConnection && !connection)
                return blockState.setValue(property, true);
            else if(!neighborConnection && connection)
                return blockState.setValue(property, false);
        }
        else if(connection)
            return blockState.setValue(property, false);

        if(isValidTable(neighborBlockState) && !connection)
            tryConnect(level, currentPos, direction);

        return blockState;
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState blockState, Player player)
    {
        for(var direction : Direction.Plane.HORIZONTAL)
        {
            if(blockState.getValue(PROPERTY_BY_DIRECTION.get(direction)))
            {
                trySplit(level, pos, direction);
                break;
            }
        }
    }

    private static void tryConnect(LevelAccessor level, BlockPos pos, Direction direction)
    {
        var list = Lists.<BlockPos>newArrayList();
        var i = 0;
        var tableLength = 1;

        while(tableLength < MAX_LENGTH)
        {
            var state = level.getBlockState(pos.relative(direction.getOpposite(), tableLength));

            if(isValidTable(state) && state.getValue(PROPERTY_BY_DIRECTION.get(direction)))
                tableLength++;
            else
                break;
        }

        var otherTableLength = 1;

        for(var j = 0; j < MAX_LENGTH; j++)
        {
            var state = level.getBlockState(pos.relative(direction, j));

            if(isValidTable(state) && state.getValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite())))
                otherTableLength++;
            else
                break;
        }

        if(tableLength + otherTableLength > MAX_LENGTH)
            return;

        var flag = false;
        var offsetPos = pos.relative(direction);

        if(isSameRotation(level.getBlockState(pos), level.getBlockState(offsetPos)))
            list.add(offsetPos);
        else
            return;

        if(isValidTable(level.getBlockState(offsetPos)))
            flag = level.getBlockState(offsetPos).getValue(PROPERTY_BY_DIRECTION.get(direction.getClockWise()));

        for(var j = 1; j < MAX_LENGTH; j++)
        {
            var mutable = pos.mutable().move(direction.getClockWise(), j);
            var state = level.getBlockState(mutable);
            var offsetState = level.getBlockState(mutable.move(direction));

            if(!isValidTable(state) || (isValidTable(state) && !state.getValue(PROPERTY_BY_DIRECTION.get(direction.getCounterClockWise())) || !isSameRotation(level.getBlockState(pos), state)))
            {
                if(isValidTable(offsetState) && flag)
                    return;

                break;
            }

            if(!isValidTable(offsetState) || (isValidTable(offsetState) && !offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getCounterClockWise())) || !isSameRotation(level.getBlockState(pos), offsetState)))
                return;

            if(isValidTable(offsetState))
                flag = offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getClockWise()));
            else
                flag = false;

            list.add(mutable);
            i++;
        }

        if(isValidTable(level.getBlockState(offsetPos)))
            flag = level.getBlockState(offsetPos).getValue(PROPERTY_BY_DIRECTION.get(direction.getCounterClockWise()));
        else
            flag = false;

        for(var j = 1; j < MAX_LENGTH; j++)
        {
            var mutable = pos.mutable().move(direction.getCounterClockWise(), j);
            var state = level.getBlockState(mutable);
            var offsetState = level.getBlockState(mutable.move(direction));

            if(!isValidTable(state) || (isValidTable(state) && !state.getValue(PROPERTY_BY_DIRECTION.get(direction.getClockWise()))) || !isSameRotation(level.getBlockState(pos), state))
            {
                if(isValidTable(offsetState) && flag)
                    return;

                break;
            }

            if(!isValidTable(offsetState) || isValidTable(offsetState) && !offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getClockWise())) || !isSameRotation(level.getBlockState(pos), offsetState))
                return;

            if(isValidTable(offsetState))
                flag = offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getCounterClockWise()));
            else
                flag = false;

            list.add(mutable);
            i++;
        }

        if(i >= MAX_LENGTH)
            return;

        list.forEach(tablePos -> level.setBlock(tablePos, level.getBlockState(tablePos).setValue(PROPERTY_BY_DIRECTION.get(direction.getOpposite()), true), Block.UPDATE_ALL));
    }

    private static void trySplit(LevelAccessor level, BlockPos pos, Direction direction)
    {
        var list = Lists.<BlockPos>newArrayList();

        for(var j = 1; j < MAX_LENGTH; j++)
        {
            var mutable = pos.mutable().move(direction.getClockWise(), j);
            var offsetState = level.getBlockState(mutable);

            if(isValidTable(offsetState) && offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getCounterClockWise())))
            {
                if(offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction)))
                    list.add(mutable.immutable());
            }
            else
                break;
        }

        for(int j = 1; j < MAX_LENGTH; j++)
        {
            var mutable = pos.mutable().move(direction.getCounterClockWise(), j);
            var offsetState = level.getBlockState(mutable);

            if(isValidTable(offsetState) && offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction.getClockWise())))
            {
                if(offsetState.getValue(PROPERTY_BY_DIRECTION.get(direction)))
                    list.add(mutable.immutable());
            }
            else
                break;
        }

        list.forEach(tablePos -> {
            var state = level.getBlockState(tablePos);
            var rotated = state.getValue(ROTATED);

            level.setBlock(tablePos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            level.setBlock(tablePos, state.getBlock().defaultBlockState().setValue(ROTATED, rotated), Block.UPDATE_ALL);
        });
    }

    public static boolean isValidTable(BlockState blockState)
    {
        return blockState.getBlock() instanceof BlockComponentHolder componentHolder && componentHolder.hasComponent(COMPONENT_TYPE);
    }

    public static boolean isSameRotation(BlockState origin, BlockState target)
    {
        if(!isValidTable(origin) || !isValidTable(target))
            return false;

        return origin.getValue(ROTATED) == target.getValue(ROTATED);
    }
}

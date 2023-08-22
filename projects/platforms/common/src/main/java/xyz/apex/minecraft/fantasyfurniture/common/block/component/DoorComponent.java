package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.helper.InteractionResultHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.Objects;
import java.util.function.UnaryOperator;

// TODO: Move into ApexCore
public final class DoorComponent extends BaseBlockComponent
{
    public static final BlockComponentType<DoorComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "door", DoorComponent::new);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    @Nullable private BlockSetType blockSetType;

    private DoorComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    public DoorComponent withBlockSetType(WoodType woodType)
    {
        return withBlockSetType(woodType.setType());
    }

    public DoorComponent withBlockSetType(BlockSetType blockSetType)
    {
        Validate.isTrue(!isRegistered());
        this.blockSetType = blockSetType;
        return this;
    }

    public BlockSetType getBlockSetType()
    {
        return Objects.requireNonNull(blockSetType);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState defaultBlockState)
    {
        return defaultBlockState.setValue(OPEN, false)
                                .setValue(HINGE, DoorHingeSide.LEFT)
                                .setValue(POWERED, false);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(OPEN, HINGE, POWERED);
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        var powered = isPowered(level, pos, blockState);

        if(!getGameObject().defaultBlockState().is(block) || powered != blockState.getValue(POWERED))
        {
            if(powered != blockState.getValue(OPEN))
            {
                playSound(null, level, pos, powered);
                level.gameEvent(null, powered ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            setBlockState(level, pos, blockState, state -> state.setValue(POWERED, powered).setValue(OPEN, powered), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if(!getBlockSetType().canOpenByHand())
            return InteractionResultHelper.BlockUse.noActionTaken();

        var open = blockState.getValue(OPEN);
        setOpen(player, level, pos, blockState, !open);
        return InteractionResultHelper.BlockUse.succeedAndSwingArmBothSides(level.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockState placementBlockState, BlockPlaceContext context)
    {
        var pos = context.getClickedPos();
        var level = context.getLevel();
        var isPowered = isPowered(level, pos, placementBlockState);
        return placementBlockState.setValue(POWERED, isPowered).setValue(OPEN, isPowered).setValue(HINGE, getHinge(getGameObject(), context));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborBlockState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent == null)
            return blockState;

        var multiBlockType = multiBlockComponent.getMultiBlockType();
        var index = MultiBlockComponent.getIndex(multiBlockType, blockState);

        if(index == 0)
            return blockState;

        var rootPos = MultiBlockComponent.rootPosition(multiBlockType, currentPos, blockState);
        var rootBlockState = level.getBlockState(rootPos);

        if(!rootBlockState.is(getGameObject()))
            return Blocks.AIR.defaultBlockState();

        return MultiBlockComponent.setIndex(multiBlockType, rootBlockState, index);
    }

    // TODO: Move to override in ApexCore
    public boolean isPathfindable(BlockState blockState, BlockGetter level, BlockPos pos, PathComputationType pathType)
    {
        return switch(pathType) {
            default -> false;
            case LAND, AIR -> blockState.getValue(OPEN);
        };
    }

    private DoorHingeSide getHinge(Block block, BlockPlaceContext context)
    {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var facing = context.getHorizontalDirection().getOpposite();
        var ccwPos = pos.relative(facing.getCounterClockWise());
        var ccwBlockState = level.getBlockState(ccwPos);
        var cwPos = pos.relative(facing.getClockWise());
        var cwBlockState = level.getBlockState(cwPos);
        var i = (ccwBlockState.isCollisionShapeFullBlock(level, ccwPos) ? -1 : 0) + (cwBlockState.isCollisionShapeFullBlock(level, cwPos) ? 1 : 0);
        var ccwIsDoor = ccwBlockState.is(block);
        var cwIsDoor = cwBlockState.is(block);

        if((!ccwIsDoor || cwIsDoor) && i <= 0)
        {
            if((!cwIsDoor || ccwIsDoor) && i >= 0)
            {
                var stepX = facing.getStepX();
                var stepZ = facing.getStepZ();
                var clickLocation = context.getClickLocation();
                var clickX = clickLocation.x - (double) pos.getX();
                var clickZ = clickLocation.z - (double) pos.getZ();

                return (
                        (stepX >= 0 || !(clickZ < .5D)) &&
                                (stepX <= 0D || !(clickZ > .5D)) &&
                                (stepZ >= 0D || !(clickX > .5D)) &&
                                (stepZ <= 0D || !(clickX < .5D))
                ) ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
            }
            else
                return DoorHingeSide.RIGHT;
        }
        else
            return DoorHingeSide.LEFT;
    }

    public void setOpen(@Nullable Entity source, Level level, BlockPos pos, BlockState blockState, boolean open)
    {
        if(blockState.getValue(OPEN) != open)
        {
            setBlockState(level, pos, blockState, state -> state.setValue(OPEN, open), 10);
            playSound(source, level, pos, open);
            level.gameEvent(source, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
    }

    private void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening)
    {
        var blockSetType = getBlockSetType();
        level.playSound(source, pos, isOpening ? blockSetType.doorOpen() : blockSetType.doorClose(), SoundSource.BLOCKS, 1F, level.random.nextFloat() * .1F + .9F);
    }

    private boolean isPowered(LevelReader level, BlockPos pos, BlockState blockState)
    {
        if(level.hasNeighborSignal(pos))
            return true;

        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent != null)
        {
            var multiBlockType = multiBlockComponent.getMultiBlockType();
            var rootPos = MultiBlockComponent.rootPosition(multiBlockType, pos, blockState);

            for(var i = 0; i < multiBlockType.size(); i++)
            {
                var newBlockState = MultiBlockComponent.setIndex(multiBlockType, blockState, i);
                var worldPosition = MultiBlockComponent.worldPosition(multiBlockType, rootPos, newBlockState);

                if(worldPosition.equals(pos))
                    continue;
                if(level.hasNeighborSignal(worldPosition))
                    return true;
            }
        }

        return false;
    }

    private void setBlockState(LevelWriter level, BlockPos pos, BlockState blockState, UnaryOperator<BlockState> modifier, int flags)
    {
        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent == null)
            level.setBlock(pos, modifier.apply(blockState), flags);
        else
        {
            var multiBlockType = multiBlockComponent.getMultiBlockType();
            var rootPos = MultiBlockComponent.rootPosition(multiBlockType, pos, blockState);

            for(var i = 0; i < multiBlockType.size(); i++)
            {
                var newBlockState = MultiBlockComponent.setIndex(multiBlockType, blockState, i);
                var worldPosition = MultiBlockComponent.worldPosition(multiBlockType, rootPos, newBlockState);
                level.setBlock(worldPosition, modifier.apply(newBlockState), flags);
            }
        }
    }
}

package xyz.apex.minecraft.fantasyfurniture.common.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Function;

// requires horizontal facing for wall placements
public final class FaceAttachingComponent extends BaseBlockComponent
{
    public static final BlockComponentType<FaceAttachingComponent> COMPONENT_TYPE = BlockComponentType.register(FantasyFurniture.ID, "light", FaceAttachingComponent::new);

    private final Set<PlacementType> placementTypes = EnumSet.noneOf(PlacementType.class);

    private FaceAttachingComponent(BlockComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    public FaceAttachingComponent withPlacementType(PlacementType placementType)
    {
        Validate.isTrue(!isRegistered(), "Can only set placement type during registration");
        placementTypes.add(placementType);
        return this;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockState placementBlockState, BlockPlaceContext context)
    {
        Direction direction;

        if(placementTypes.contains(PlacementType.WALL))
            direction = context.getHorizontalDirection();
        else if(placementTypes.contains(PlacementType.CEILING) || placementTypes.contains(PlacementType.FLOOR))
            direction = context.getNearestLookingVerticalDirection();
        else
            return null;

        var level = context.getLevel();
        var pos = context.getClickedPos();
        return asRoot(pos, placementBlockState, rootPos -> FaceAttachedHorizontalDirectionalBlock.canAttach(level, rootPos, direction) ? placementBlockState : null);
    }

    @Nullable
    private <T> T asRoot(BlockPos pos, BlockState blockState, Function<BlockPos, T> mapper)
    {
        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);

        if(multiBlockComponent == null)
            return mapper.apply(pos);

        var multiBlockType = multiBlockComponent.getMultiBlockType();
        var rootPos = MultiBlockComponent.rootPosition(multiBlockType, pos, blockState);
        return mapper.apply(rootPos);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborBlockState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        return MultiBlockComponent.asRoot(level, currentPos, blockState, (rootPos, rootBlockState) -> {
            for(var placementType : placementTypes)
            {
                if(placementType.getConnectedDirection(rootBlockState).getOpposite() == direction && !rootBlockState.canSurvive(level, rootPos))
                    return Blocks.AIR.defaultBlockState();
            }

            return blockState;
        });
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        return MultiBlockComponent.asRoot(level, pos, blockState, (rootPos, rootBlockState) -> {
            for(var placementType : placementTypes)
            {
                if(!FaceAttachedHorizontalDirectionalBlock.canAttach(level, rootPos, placementType.getConnectedDirection(rootBlockState).getOpposite()))
                    return false;
            }

            return true;
        });
    }

    public enum PlacementType
    {
        WALL {
            @Override
            public Direction getConnectedDirection(BlockState blockState)
            {
                return HorizontalFacingBlockComponent.getFacing(blockState);
            }
        },
        FLOOR {
            @Override
            public Direction getConnectedDirection(BlockState blockState)
            {
                return Direction.UP;
            }
        },
        CEILING {
            @Override
            public Direction getConnectedDirection(BlockState blockState)
            {
                return Direction.DOWN;
            }
        };

        public abstract Direction getConnectedDirection(BlockState blockState);
    }
}
package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponent;
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class LightComponent extends SimpleComponent
{
    public static final ComponentType<LightComponent> COMPONENT_TYPE = ComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "light"),
            LightComponent.class
    );

    private boolean placeOnWalls = false;
    private boolean placeOnFloor = true;
    private boolean placeOnCeilings = true;

    @ApiStatus.Internal // public cause reflection
    public LightComponent(ComponentBlock block)
    {
        super(block);
    }

    public LightComponent setPlaceOnWalls(boolean placeOnWalls)
    {
        this.placeOnWalls = placeOnWalls;
        return this;
    }

    public LightComponent setPlaceOnFloor(boolean placeOnFloor)
    {
        this.placeOnFloor = placeOnFloor;
        return this;
    }

    public LightComponent setPlaceOnCeilings(boolean placeOnCeilings)
    {
        this.placeOnCeilings = placeOnCeilings;
        return this;
    }

    @Override
    public void validate()
    {
        if(placeOnWalls && !hasComponent(ComponentTypes.HORIZONTAL_FACING)) throw new IllegalStateException("Missing required Component: '%s' due to wall placement being enabled!".formatted(ComponentTypes.HORIZONTAL_FACING));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx, BlockState blockState)
    {
        if(placeOnWalls)
        {
            var level = ctx.getLevel();
            var pos = ctx.getClickedPos();

            for(var direction : ctx.getNearestLookingDirections())
            {
                if(direction.getAxis().isHorizontal())
                {
                    var opposite = direction.getOpposite();
                    var modified = blockState.setValue(HorizontalFacingComponent.FACING, opposite);
                    if(modified.canSurvive(level, pos)) return modified;
                }
            }

            if(!placeOnFloor && !placeOnCeilings) return null;
        }

        return blockState;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        if(placeOnWalls)
        {
            var facing = blockState.getValue(HorizontalFacingComponent.FACING);
            var wallPos = pos.relative(facing);
            var wallBlockState = level.getBlockState(wallPos);
            if(wallBlockState.isFaceSturdy(level, wallPos, facing)) return true;
        }

        if(placeOnCeilings && Block.canSupportCenter(level, pos.above(), Direction.DOWN)) return true;
        return placeOnFloor;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(placeOnWalls && direction.getOpposite() == blockState.getValue(HorizontalFacingComponent.FACING) && !blockState.canSurvive(level, currentPos)) return Blocks.AIR.defaultBlockState();
        if(placeOnCeilings && direction == Direction.UP && !canSurvive(blockState, level, currentPos)) return Blocks.AIR.defaultBlockState();
        return blockState;
    }
}

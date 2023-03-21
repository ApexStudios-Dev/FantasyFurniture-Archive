package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

public final class LightComponent extends BaseBlockComponent
{
    public static final BlockComponentType<LightComponent> COMPONENT_TYPE = BlockComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "light"),
            LightComponent::new
    );

    public static final DirectionProperty FACING = WallTorchBlock.FACING;

    private boolean placeOnWalls = false;
    private boolean placeOnFloor = true;
    private boolean placeOnCeilings = true;

    @ApiStatus.Internal // public cause reflection
    public LightComponent(BlockComponentHolder block)
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
    public BlockState registerDefaultBlockState(BlockState blockState)
    {
        if(placeOnWalls) return blockState.setValue(FACING, Direction.NORTH);
        return blockState;
    }

    @Override
    public void createBlockStateDefinition(Consumer<Property<?>> consumer)
    {
        if(placeOnWalls) consumer.accept(FACING);
        super.createBlockStateDefinition(consumer);
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
                    var modified = blockState.setValue(FACING, direction);
                    if(modified.canSurvive(level, pos)) return modified.setValue(FACING, direction);
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
            var facing = blockState.getValue(FACING);
            var wallDirection = facing.getOpposite();
            var wallPos = pos.relative(wallDirection);
            var wallBlockState = level.getBlockState(wallPos);
            if(wallBlockState.isFaceSturdy(level, wallPos, wallDirection)) return true;
        }

        if(placeOnCeilings && Block.canSupportCenter(level, pos.above(), Direction.DOWN)) return true;
        return placeOnFloor;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        if(placeOnWalls && direction.getOpposite() == blockState.getValue(FACING) && !blockState.canSurvive(level, currentPos)) return Blocks.AIR.defaultBlockState();
        if(placeOnCeilings && direction == Direction.UP && !canSurvive(blockState, level, currentPos)) return Blocks.AIR.defaultBlockState();
        return blockState;
    }
}

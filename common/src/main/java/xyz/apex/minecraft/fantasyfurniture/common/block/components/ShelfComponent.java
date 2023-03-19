package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponent;
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;

import java.util.function.Consumer;

public final class ShelfComponent extends SimpleComponent
{
    public static final ComponentType<ShelfComponent> COMPONENT_TYPE = ComponentType
            .builder(new ResourceLocation(FantasyFurniture.ID, "shelf"), ShelfComponent.class)
                .requires(ComponentTypes.HORIZONTAL_FACING)
            .register();

    @ApiStatus.Internal // public cause reflection
    public ShelfComponent(ComponentBlock block)
    {
        super(block);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState blockState)
    {
        return blockState.setValue(ModBlockStateProperties.SHELF_TYPE, ShelfType.SINGLE);
    }

    @Override
    public void createBlockStateDefinition(Consumer<Property<?>> consumer)
    {
        consumer.accept(ModBlockStateProperties.SHELF_TYPE);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
    {
        return getBlockState(level, pos, blockState);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx, BlockState blockState)
    {
        return getBlockState(ctx.getLevel(), ctx.getClickedPos(), blockState);
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        updateConnectionBlockState(level, pos, blockState);
    }

    public static void updateConnectionBlockState(LevelAccessor level, BlockPos pos, BlockState blockState)
    {
        var newBlockState = getBlockState(level, pos, blockState);
        if(newBlockState != blockState) level.setBlock(pos, newBlockState, 3);
    }

    public static BlockState getBlockState(LevelAccessor level, BlockPos pos, BlockState blockState)
    {
        var shelfType = getShelfType(level, pos, blockState);
        return blockState.setValue(ModBlockStateProperties.SHELF_TYPE, shelfType);
    }

    public static ShelfType getShelfType(LevelAccessor level, BlockPos pos, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);

        var leftPos = pos.relative(facing.getCounterClockWise());
        var rightPos = pos.relative(facing.getClockWise());

        var leftBlockState = level.getBlockState(leftPos);
        var rightBlockState = level.getBlockState(rightPos);

        var isLeft = isSideConnection(blockState, leftBlockState);
        var isRight = isSideConnection(blockState, rightBlockState);

        if(isLeft && isRight) return ShelfType.CENTER;
        else if(isLeft) return ShelfType.LEFT;
        else if(isRight) return ShelfType.RIGHT;
        else return ShelfType.SINGLE;
    }

    public static boolean isSideConnection(BlockState blockState, BlockState neighbor)
    {
        if(!neighbor.is(blockState.getBlock())) return false;
        if(neighbor.getValue(HorizontalFacingComponent.FACING) == blockState.getValue(HorizontalFacingComponent.FACING)) return true;

        var neighborShelfType = neighbor.getValue(ModBlockStateProperties.SHELF_TYPE);
        return neighborShelfType == ShelfType.CENTER;
    }
}

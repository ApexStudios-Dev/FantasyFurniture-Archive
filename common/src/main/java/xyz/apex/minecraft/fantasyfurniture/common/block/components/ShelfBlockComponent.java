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
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponent;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentType;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;

import java.util.function.Consumer;

public final class ShelfBlockComponent extends BaseBlockComponent
{
    public static final BlockComponentType<ShelfBlockComponent> COMPONENT_TYPE = BlockComponentType.register(
            new ResourceLocation(FantasyFurniture.ID, "shelf"),
            ShelfBlockComponent::new,
            BlockComponentTypes.HORIZONTAL_FACING
    );

    private ShelfBlockComponent(BlockComponentHolder holder)
    {
        super(holder);
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
        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

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
        if(neighbor.getValue(HorizontalFacingBlockComponent.FACING) == blockState.getValue(HorizontalFacingBlockComponent.FACING)) return true;

        var neighborShelfType = neighbor.getValue(ModBlockStateProperties.SHELF_TYPE);
        return neighborShelfType == ShelfType.CENTER;
    }
}

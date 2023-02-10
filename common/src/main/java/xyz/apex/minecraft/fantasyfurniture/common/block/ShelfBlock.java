package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ShelfType;

public class ShelfBlock extends SimpleHorizontalFacingBlock
{
    public ShelfBlock(Properties properties)
    {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.SHELF_TYPE, ShelfType.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder.add(ModBlockStateProperties.SHELF_TYPE));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction facing, BlockState facingBlockState, LevelAccessor level, BlockPos pos, BlockPos facingPos)
    {
        blockState = super.updateShape(blockState, facing, facingBlockState, level, pos, facingPos);
        return getBlockState(level, pos, blockState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        var placementBlockState = super.getStateForPlacement(ctx);
        return placementBlockState == null ? null : getBlockState(ctx.getLevel(), ctx.getClickedPos(), placementBlockState);
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving)
    {
        super.neighborChanged(blockState, level, pos, block, fromPos, isMoving);
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
        var facing = blockState.getValue(FACING);

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
        if(neighbor.getValue(FACING) == blockState.getValue(FACING)) return true;

        var neighborShelfType = neighbor.getValue(ModBlockStateProperties.SHELF_TYPE);
        return neighborShelfType == ShelfType.CENTER;
    }
}

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

import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.CounterType;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.CounterMenu;

public final class CounterBlock extends HorizontalInventoryBlock<CounterBlockEntity, CounterMenu>
{
    public CounterBlock(Properties properties)
    {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.COUNTER_TYPE, CounterType.SINGLE));
    }

    @Override
    public BlockEntityEntry<CounterBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.COUNTER;
    }

    @Override
    public MenuEntry<CounterMenu> getInventoryMenuType()
    {
        return AllMenuTypes.COUNTER;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder.add(ModBlockStateProperties.COUNTER_TYPE));
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
        var shelfType = getCounterType(level, pos, blockState);
        return blockState.setValue(ModBlockStateProperties.COUNTER_TYPE, shelfType);
    }

    public static CounterType getCounterType(LevelAccessor level, BlockPos pos, BlockState blockState)
    {
        var facing = blockState.getValue(FACING);

        var leftPos = pos.relative(facing.getCounterClockWise());
        var rightPos = pos.relative(facing.getClockWise());
        var frontPos = pos.relative(facing);

        var leftBlockState = level.getBlockState(leftPos);
        var rightBlockState = level.getBlockState(rightPos);
        var frontBlockState = level.getBlockState(frontPos);

        if(isCornerConnection(blockState, leftBlockState, rightBlockState, frontBlockState, facing)) return CounterType.CORNER;
        else return CounterType.SINGLE;
    }

    public static boolean isCornerConnection(BlockState blockState, BlockState left, BlockState right, BlockState front, Direction facing)
    {
        var block = blockState.getBlock();
        if(!front.is(block)) return false;

        var frontFacing = front.getValue(FACING);

        if(left.is(block))
        {
            var leftFacing = left.getValue(FACING);
            return isCornerFacing(facing, leftFacing, frontFacing);
        }
        else if(right.is(block))
        {
            var rightFacing = right.getValue(FACING);
            return isCornerFacing(facing, rightFacing, frontFacing);
        }

        return false;
    }

    public static boolean isCornerFacing(Direction facing, Direction sideFacing, Direction frontFacing)
    {
        if(facing == sideFacing) return frontFacing.getCounterClockWise() == facing || sideFacing == frontFacing.getClockWise();
        else return sideFacing.getOpposite() == frontFacing || sideFacing == frontFacing.getOpposite();
    }
}

package xyz.apex.minecraft.fantasyfurniture.common.block.components;

import org.jetbrains.annotations.ApiStatus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import xyz.apex.minecraft.apexcore.common.component.ComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.ComponentType;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponent;
import xyz.apex.minecraft.apexcore.common.component.components.HorizontalFacingComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.ModBlockStateProperties;
import xyz.apex.minecraft.fantasyfurniture.common.block.properties.SofaType;

import java.util.function.Consumer;

public final class SofaComponent extends SimpleComponent
{
    public static final ComponentType<SofaComponent> COMPONENT_TYPE = ComponentType
            .builder(new ResourceLocation(FantasyFurniture.ID, "sofa"), SofaComponent.class)
                .requires(ComponentTypes.HORIZONTAL_FACING)
                .requires(SeatComponent.COMPONENT_TYPE)
            .register();

    @ApiStatus.Internal // public cause reflection
    public SofaComponent(ComponentBlock block)
    {
        super(block);
    }

    @Override
    public BlockState registerDefaultBlockState(BlockState blockState)
    {
        return blockState.setValue(ModBlockStateProperties.SOFA_TYPE, SofaType.SINGLE);
    }

    @Override
    public void createBlockStateDefinition(Consumer<Property<?>> consumer)
    {
        consumer.accept(ModBlockStateProperties.SOFA_TYPE);
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
        var sofaType = getSofaType(level, pos, blockState);
        return blockState.setValue(ModBlockStateProperties.SOFA_TYPE, sofaType);
    }

    public static SofaType getSofaType(LevelAccessor level, BlockPos pos, BlockState blockState)
    {
        var facing = blockState.getValue(HorizontalFacingComponent.FACING);

        var leftPos = pos.relative(facing.getCounterClockWise());
        var rightPos = pos.relative(facing.getClockWise());
        var frontPos = pos.relative(facing);

        var leftBlockState = level.getBlockState(leftPos);
        var rightBlockState = level.getBlockState(rightPos);
        var frontBlockState = level.getBlockState(frontPos);

        if(isCornerConnection(blockState, leftBlockState, rightBlockState, frontBlockState, facing)) return SofaType.CORNER;

        var isLeft = isSideConnection(blockState, leftBlockState);
        var isRight = isSideConnection(blockState, rightBlockState);

        if(isLeft && isRight) return SofaType.CENTER;
        else if(isLeft) return SofaType.LEFT;
        else if(isRight) return SofaType.RIGHT;
        else return SofaType.SINGLE;
    }

    public static boolean isCornerConnection(BlockState blockState, BlockState left, BlockState right, BlockState front, Direction facing)
    {
        var block = blockState.getBlock();
        if(!front.is(block)) return false;

        var frontFacing = front.getValue(HorizontalFacingComponent.FACING);

        if(left.is(block))
        {
            var leftFacing = left.getValue(HorizontalFacingComponent.FACING);
            return isCornerFacing(facing, leftFacing, frontFacing);
        }
        else if(right.is(block))
        {
            var rightFacing = right.getValue(HorizontalFacingComponent.FACING);
            return isCornerFacing(facing, rightFacing, frontFacing);
        }

        return false;
    }

    public static boolean isCornerFacing(Direction facing, Direction sideFacing, Direction frontFacing)
    {
        if(facing == sideFacing) return sideFacing.getCounterClockWise() == frontFacing || sideFacing == frontFacing.getClockWise();
        else return sideFacing.getOpposite() == frontFacing || sideFacing == frontFacing.getOpposite();
    }

    public static boolean isSideConnection(BlockState blockState, BlockState neighbor)
    {
        if(!neighbor.is(blockState.getBlock())) return false;
        if(neighbor.getValue(HorizontalFacingComponent.FACING) == blockState.getValue(HorizontalFacingComponent.FACING)) return true;

        var neighborSofaType = neighbor.getValue(ModBlockStateProperties.SOFA_TYPE);
        return neighborSofaType == SofaType.CENTER || neighborSofaType == SofaType.CORNER;
    }
}

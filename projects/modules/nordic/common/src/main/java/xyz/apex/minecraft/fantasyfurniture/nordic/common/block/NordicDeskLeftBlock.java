package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;

public final class NordicDeskLeftBlock extends NordicSmallContainerBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(13D, 0D, 0D, 15D, 9D, 2D),
            box(13D, 7D, 1D, 15D, 13D, 3D),
            box(13D, 7D, 13D, 15D, 13D, 15D),
            box(-15D, 7D, 13D, -13D, 13D, 15D),
            box(-15D, 0D, 0D, -13D, 9D, 2D),
            box(-15D, 0D, 14D, -13D, 9D, 16D),
            box(13D, 0D, 14D, 15D, 9D, 16D),
            box(-16D, 13D, 0D, 16D, 16D, 16D),
            box(-15D, 7D, 1D, -13D, 13D, 3D),
            box(5D, 9D, 2D, 12D, 13D, 11D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicDeskLeftBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_2x1x1));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var shape = switch(blockState.getValue(HorizontalFacingBlockComponent.FACING)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
        {
            facing = facing.getCounterClockWise();
            shape = shape.move(-facing.getStepX(), 0D, -facing.getStepZ());
        }

        return shape;
    }
}

package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.common.block.SeatBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.SeatComponent;

public final class NordicBenchBlock extends BaseBlockComponentHolder implements SeatBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(12D, 0D, 2D, 14D, 3D, 4D),
            box(-14D, 0D, 2D, -12D, 3D, 4D),
            box(-14D, 0D, 12D, -12D, 3D, 14D),
            box(12D, 0D, 12D, 14D, 3D, 14D),
            box(12D, 3D, 11.5D, 14D, 5D, 13.5D),
            box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
            box(-14D, 3D, 2.5D, -12D, 5D, 4.5D),
            box(-14D, 3D, 11.5D, -12D, 5D, 13.5D),
            box(-13.5D, 3.5D, 4.5D, -12.5D, 4.5D, 11.5D),
            box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D),
            box(-15D, 5D, 2D, 15D, 7D, 14D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicBenchBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_2x1x1));
        registrar.register(SeatComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var facing = HorizontalFacingBlockComponent.getFacing(blockState);

        var shape = switch(facing) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };

        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
        {
            facing = facing.getCounterClockWise();
            shape = shape.move(-facing.getStepX(), 0D, -facing.getStepZ());
        }

        return shape;
    }
}

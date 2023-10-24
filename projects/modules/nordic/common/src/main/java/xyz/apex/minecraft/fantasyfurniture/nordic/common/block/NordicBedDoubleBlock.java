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
import xyz.apex.minecraft.fantasyfurniture.common.block.component.BedComponent;

public final class NordicBedDoubleBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(-16D, 3D, 2D, 16D, 5D, 30D),
            box(-14D, 5D, 2D, 14D, 8D, 30D),
            box(-16D, 3D, 0D, 16D, 5D, 2D),
            box(-16D, 0D, 0D, -14D, 8D, 2D),
            box(14D, 0D, 0D, 16D, 8D, 2D),
            box(-16D, 12D, 0D, -8D, 14D, 2D),
            box(8D, 12D, 0D, 16D, 14D, 2D),
            box(-10D, 12D, 0D, 10D, 16D, 2D),
            box(-15D, 5D, 0D, 15D, 12D, 2D),
            box(-15D, 5D, 30D, 15D, 12D, 32D),
            box(-16D, 3D, 30D, 16D, 5D, 32D),
            box(-16D, 0D, 30D, -14D, 8D, 32D),
            box(14D, 0D, 30D, 16D, 8D, 32D),
            box(-16D, 12D, 30D, -8D, 14D, 32D),
            box(8D, 12D, 30D, 16D, 14D, 32D),
            box(-10D, 12D, 30D, 10D, 16D, 32D)
    );

    // TODO: hitbox needs flipping and being reexported
    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);

    public NordicBedDoubleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_2x1x2));
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BedComponent.COMPONENT_TYPE, component -> component.withHeadIndex(2, 3));
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

        var index = MultiBlockComponent.getIndex(getRequiredComponent(MultiBlockComponent.COMPONENT_TYPE).getMultiBlockType(), blockState);

        if(index == 0 || index == 1)
        {
            var otherFacing = facing.getOpposite();
            shape = shape.move(otherFacing.getStepX(), 0D, otherFacing.getStepZ());
        }

        if(index == 0 || index == 2)
        {
            var otherFacing = facing.getCounterClockWise();
            shape = shape.move(otherFacing.getStepX(), 0D, otherFacing.getStepZ());
        }

        return shape;
    }
}

package xyz.apex.minecraft.fantasyfurniture.venthyr.common.block;

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
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;

public final class VenthyrCounterBlock extends VenthyrMediumContainerBlock
{
    public static final VoxelShape SHAPE_SINGLE = VoxelShapeHelper.combine(
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D),
            box(1D, 1D, 2D, 15D, 12D, 3D)
    );

    public static final VoxelShape SHAPE_SINGLE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.NORTH);
    public static final VoxelShape SHAPE_SINGLE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.EAST);
    public static final VoxelShape SHAPE_SINGLE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.SOUTH);
    public static final VoxelShape SHAPE_SINGLE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.WEST);

    public static final VoxelShape SHAPE_CORNER = VoxelShapeHelper.combine(
            box(0D, 0D, 0D, 13D, 13D, 4D),
            box(0D, 0D, 3D, 16D, 13D, 16D),
            box(0D, 13D, 0D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE_CORNER_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.NORTH);
    public static final VoxelShape SHAPE_CORNER_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.EAST);
    public static final VoxelShape SHAPE_CORNER_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.SOUTH);
    public static final VoxelShape SHAPE_CORNER_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.WEST);

    public VenthyrCounterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(ConnectionBlockComponent.COUNTER_COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var facing = HorizontalFacingBlockComponent.getFacing(blockState);

        return switch(blockState.getValue(getRequiredComponent(ConnectionBlockComponent.COUNTER_COMPONENT_TYPE).getProperty())) {
            default -> switch(facing) {
                default -> SHAPE_SINGLE_NORTH;
                case EAST -> SHAPE_SINGLE_EAST;
                case SOUTH -> SHAPE_SINGLE_SOUTH;
                case WEST -> SHAPE_SINGLE_WEST;
            };

            case INNER_LEFT, INNER_RIGHT, OUTER_LEFT, OUTER_RIGHT -> switch(facing) {
                default -> SHAPE_CORNER_NORTH;
                case EAST -> SHAPE_CORNER_EAST;
                case SOUTH -> SHAPE_CORNER_SOUTH;
                case WEST -> SHAPE_CORNER_WEST;
            };
        };
    }
}

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
import xyz.apex.minecraft.fantasyfurniture.common.block.component.ConnectionBlockComponent;

public final class NordicSofaBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE_SINGLE = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 14D),
            box(0D, 10D, 0D, 2D, 12D, 14D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(14D, 6D, 0D, 16D, 10D, 2D)
    );

    public static final VoxelShape SHAPE_SINGLE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.NORTH);
    public static final VoxelShape SHAPE_SINGLE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.EAST);
    public static final VoxelShape SHAPE_SINGLE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.SOUTH);
    public static final VoxelShape SHAPE_SINGLE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE, Direction.WEST);

    public static final VoxelShape SHAPE_CENTER = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D)
    );

    public static final VoxelShape SHAPE_CENTER_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CENTER, Direction.NORTH);
    public static final VoxelShape SHAPE_CENTER_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_CENTER, Direction.EAST);
    public static final VoxelShape SHAPE_CENTER_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CENTER, Direction.SOUTH);
    public static final VoxelShape SHAPE_CENTER_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_CENTER, Direction.WEST);

    public static final VoxelShape SHAPE_LEFT = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(14D, 10D, 0D, 16D, 12D, 13D),
            box(14D, 6D, 0D, 16D, 10D, 2D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(13D, 0D, 13D, 15D, 3D, 15D)
    );

    public static final VoxelShape SHAPE_LEFT_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_LEFT, Direction.NORTH);
    public static final VoxelShape SHAPE_LEFT_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_LEFT, Direction.EAST);
    public static final VoxelShape SHAPE_LEFT_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_LEFT, Direction.SOUTH);
    public static final VoxelShape SHAPE_LEFT_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_LEFT, Direction.WEST);

    public static final VoxelShape SHAPE_RIGHT = VoxelShapeHelper.combine(
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(0D, 10D, 0D, 2D, 12D, 13D),
            box(0D, 6D, 0D, 2D, 10D, 2D),
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D)
    );

    public static final VoxelShape SHAPE_RIGHT_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_RIGHT, Direction.NORTH);
    public static final VoxelShape SHAPE_RIGHT_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_RIGHT, Direction.EAST);
    public static final VoxelShape SHAPE_RIGHT_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_RIGHT, Direction.SOUTH);
    public static final VoxelShape SHAPE_RIGHT_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_RIGHT, Direction.WEST);

    public static final VoxelShape SHAPE_CORNER = VoxelShapeHelper.combine(
            box(1D, 0D, 1D, 3D, 3D, 3D),
            box(1D, 0D, 13D, 3D, 3D, 15D),
            box(13D, 0D, 13D, 15D, 3D, 15D),
            box(13D, 0D, 1D, 15D, 3D, 3D),
            box(0D, 3D, 0D, 16D, 6D, 16D),
            box(0D, 6D, 13D, 16D, 16D, 16D),
            box(13D, 6D, 0D, 16D, 16D, 13D)
    );

    public static final VoxelShape SHAPE_CORNER_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.NORTH);
    public static final VoxelShape SHAPE_CORNER_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.EAST);
    public static final VoxelShape SHAPE_CORNER_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.SOUTH);
    public static final VoxelShape SHAPE_CORNER_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER, Direction.WEST);

    public NordicSofaBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(ConnectionBlockComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

        return switch(blockState.getValue(ConnectionBlockComponent.PROPERTY)) {
            default -> switch(facing) {
                default -> SHAPE_SINGLE_NORTH;
                case EAST -> SHAPE_SINGLE_EAST;
                case SOUTH -> SHAPE_SINGLE_SOUTH;
                case WEST -> SHAPE_SINGLE_WEST;
            };

            case LEFT -> switch(facing) {
                default -> SHAPE_LEFT_NORTH;
                case EAST -> SHAPE_LEFT_EAST;
                case SOUTH -> SHAPE_LEFT_SOUTH;
                case WEST -> SHAPE_LEFT_WEST;
            };
            case RIGHT -> switch(facing) {
                default -> SHAPE_RIGHT_NORTH;
                case EAST -> SHAPE_RIGHT_EAST;
                case SOUTH -> SHAPE_RIGHT_SOUTH;
                case WEST -> SHAPE_RIGHT_WEST;
            };

            case CENTER -> switch(facing) {
                default -> SHAPE_CENTER_NORTH;
                case EAST -> SHAPE_CENTER_EAST;
                case SOUTH -> SHAPE_CENTER_SOUTH;
                case WEST -> SHAPE_CENTER_WEST;
            };

            case CORNER -> switch(facing) {
                default -> SHAPE_CORNER_NORTH;
                case EAST -> SHAPE_CORNER_EAST;
                case SOUTH -> SHAPE_CORNER_SOUTH;
                case WEST -> SHAPE_CORNER_WEST;
            };
        };
    }
}

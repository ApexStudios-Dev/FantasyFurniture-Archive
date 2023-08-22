package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.DoorComponent;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

public final class NordicDoorSingleBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(0D, 0D, 0D, 13D, 2D, 3D),
            box(0D, 10D, 0D, 13D, 12D, 3D),
            box(0D, 20D, 0D, 13D, 22D, 3D),
            box(0D, 30D, 0D, 13D, 32D, 3D),
            box(13D, 0D, 0D, 16D, 32D, 3D),
            box(0D, 2D, .5D, 13D, 30D, 2.5D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public static final VoxelShape SHAPE_FLIPPED = VoxelShapeHelper.combine(
            box(3D, 0D, 0D, 16D, 2D, 3D),
            box(3D, 10D, 0D, 16D, 12D, 3D),
            box(3D, 20D, 0D, 16D, 22D, 3D),
            box(3D, 30D, 0D, 16D, 32D, 3D),
            box(0D, 0D, 0D, 3D, 32D, 3D),
            box(3D, 2D, .5D, 16D, 30, 2.5D)
    );

    public static final VoxelShape SHAPE_FLIPPED_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE_FLIPPED, Direction.NORTH);
    public static final VoxelShape SHAPE_FLIPPED_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE_FLIPPED, Direction.EAST);
    public static final VoxelShape SHAPE_FLIPPED_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE_FLIPPED, Direction.SOUTH);
    public static final VoxelShape SHAPE_FLIPPED_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE_FLIPPED, Direction.WEST);

    public NordicDoorSingleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_1x2x1));
        registrar.register(DoorComponent.COMPONENT_TYPE, component -> component.withBlockSetType(NordicFurnitureSet.WOOD_TYPE));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);
        var open = blockState.getValue(DoorComponent.OPEN);
        var hinge = blockState.getValue(DoorComponent.HINGE);
        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(open)
            facing = facing.getClockWise();
        if(hinge == DoorHingeSide.RIGHT && open)
            facing = facing.getOpposite();

        var shape = switch(hinge) {
            case LEFT -> switch(facing) {
                default -> open ? SHAPE_FLIPPED_NORTH : SHAPE_NORTH;
                case EAST -> open ? SHAPE_FLIPPED_EAST : SHAPE_EAST;
                case SOUTH -> open ? SHAPE_FLIPPED_SOUTH : SHAPE_SOUTH;
                case WEST -> open ? SHAPE_FLIPPED_WEST : SHAPE_WEST;
            };

            case RIGHT -> switch(facing) {
                default -> open ? SHAPE_NORTH : SHAPE_FLIPPED_NORTH;
                case EAST -> open ? SHAPE_EAST : SHAPE_FLIPPED_EAST;
                case SOUTH -> open ? SHAPE_SOUTH : SHAPE_FLIPPED_SOUTH;
                case WEST -> open ? SHAPE_WEST : SHAPE_FLIPPED_WEST;
            };
        };

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
            shape = shape.move(0D, -1D, 0D);

        return shape;
    }

    @Override
    public boolean isPathfindable(BlockState blockState, BlockGetter level, BlockPos pos, PathComputationType pathType)
    {
        return getRequiredComponent(DoorComponent.COMPONENT_TYPE).isPathfindable(blockState, level, pos, pathType);
    }
}

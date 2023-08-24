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
import xyz.apex.minecraft.fantasyfurniture.common.block.SeatBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.SeatComponent;

public final class NordicCushionBlock extends BaseBlockComponentHolder implements SeatBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2D, 0D, 2D, 4D, 2D, 4D),
            box(2D, 0D, 12D, 4D, 2D, 14D),
            box(12D, 0D, 12D, 14D, 2D, 14D),
            box(12D, 0D, 2D, 14D, 2D, 4D),
            box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
            box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
            box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
            box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
            box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
            box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
            box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
            box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicCushionBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(SeatComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return switch(HorizontalFacingBlockComponent.getFacing(blockState)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };
    }
}

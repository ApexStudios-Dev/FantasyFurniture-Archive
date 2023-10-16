package xyz.apex.minecraft.fantasyfurniture.venthyr.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

public final class VenthyrBookshelfBlock extends VenthyrLargeContainerBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(-15D, 0D, 1D, 15D, 30D, 15D),
            box(-16D, 30D, 0D, 16D, 32D, 16D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public VenthyrBookshelfBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType()
    {
        return VenthyrFurnitureSet.BOOKSHELF_BLOCK_ENTITY.value();
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_2x2x1));
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
        var index = MultiBlockComponent.getIndex(multiBlockType, blockState);

        if(index == 3 || index == 1)
            shape = shape.move(0D, -1D, 0D);

        if(index == 1 || index == 2)
        {
            facing = facing.getCounterClockWise();
            shape = shape.move(-facing.getStepX(), 0D, -facing.getStepZ());
        }

        return shape;
    }
}

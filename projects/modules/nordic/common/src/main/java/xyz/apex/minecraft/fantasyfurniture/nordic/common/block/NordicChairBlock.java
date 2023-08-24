package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
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

public final class NordicChairBlock extends BaseBlockComponentHolder implements SeatBlock
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2D, 0D, 2D, 4D, 4D, 4D),
            box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
            box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
            box(12D, 0D, 2D, 14D, 4D, 4D),
            box(2D, 0D, 12D, 4D, 4D, 14D),
            box(2D, 7D, 2D, 14D, 9D, 14D),
            box(2D, 9D, 13D, 14D, 25D, 14D),
            box(12D, 0D, 12D, 14D, 4D, 14D),
            box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
            box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
            box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
            box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicChairBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockPos getSittingPos(BlockGetter level, BlockState blockState, BlockPos pos)
    {
        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();
        return MultiBlockComponent.rootPosition(multiBlockType, pos, blockState);
    }

    @Override
    public float getSittingOffset(Entity entity, EntityDimensions dimensions, float f, float defaultOffset)
    {
        return defaultOffset + (entity instanceof LivingEntity living && living.isBaby() ? .15F : .05F);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.HORIZONTAL_FACING);
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_1x2x1));
        registrar.register(SeatComponent.COMPONENT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        var shape = switch(HorizontalFacingBlockComponent.getFacing(blockState)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };

        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
            shape = shape.move(0D, -1D, 0D);

        return shape;
    }
}

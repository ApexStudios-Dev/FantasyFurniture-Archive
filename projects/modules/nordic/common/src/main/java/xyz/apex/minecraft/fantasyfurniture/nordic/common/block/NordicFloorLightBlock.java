package xyz.apex.minecraft.fantasyfurniture.nordic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.BlockComponentRegistrar;
import xyz.apex.minecraft.apexcore.common.lib.component.block.types.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.lib.helper.VoxelShapeHelper;
import xyz.apex.minecraft.apexcore.common.lib.multiblock.MultiBlockComponent;
import xyz.apex.minecraft.fantasyfurniture.common.FurnitureSets;
import xyz.apex.minecraft.fantasyfurniture.common.block.component.FaceAttachingComponent;

public final class NordicFloorLightBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(6D, 0D, 6D, 10D, 2D, 10D),
            box(7D, 2D, 7D, 9D, 20D, 9D),
            box(6.5D, 20.75D, 2.5D, 9.5D, 22.75D, 5.5D),
            box(2.5D, 20.75D, 6.5D, 5.5D, 22.75D, 9.5D),
            box(7.25D, 22.75D, 3.25D, 8.75D, 26.75D, 4.75D),
            box(3.25D, 22.75D, 7.25D, 4.75D, 26.75D, 8.75D),
            box(7.25D, 22.75D, 11.25D, 8.75D, 26.75D, 12.75D),
            box(11.25D, 22.75D, 7.25D, 12.75D, 26.75D, 8.75D),
            box(10.5D, 20.75D, 6.5D, 13.5D, 22.75D, 9.5D),
            box(6.5D, 20.75D, 10.5D, 9.5D, 22.75D, 13.5D),
            box(3D, 16.75D, 7D, 7D, 20.75, 9D),
            box(9D, 16.75D, 7D, 13D, 20.75, 9D),
            box(7D, 16.75D, 3D, 9D, 20.75, 7D),
            box(7D, 16.75D, 9D, 9D, 20.75, 13D)
    );

    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicFloorLightBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.MULTI_BLOCK, component -> component.setMultiBlockType(FurnitureSets.MB_1x2x1));
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(FaceAttachingComponent.COMPONENT_TYPE, component -> component.withPlacementType(FaceAttachingComponent.PlacementType.FLOOR));
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockState, boolean isMoving)
    {
        super.onRemove(blockState, level, pos, newBlockState, isMoving);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random)
    {
        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
            return;

        var x = pos.getX() + .5D;
        var y = pos.getY() + 1.85D;
        var z = pos.getZ() + .5D;

        level.addParticle(ParticleTypes.FLAME, x + .27D, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x + .27D, y, z, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x - .27D, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x - .27D, y, z, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x, y, z + .27D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x, y, z + .27D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x, y, z - .27D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x, y, z - .27D, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        /*var shape = switch(blockState.getValue(HorizontalFacingBlockComponent.FACING)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };*/
        var shape = SHAPE;

        var multiBlockType = getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType();

        if(MultiBlockComponent.getIndex(multiBlockType, blockState) != 0)
            shape = shape.move(0D, -1D, 0D);

        return shape;
    }
}

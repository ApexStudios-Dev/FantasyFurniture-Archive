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
import xyz.apex.minecraft.fantasyfurniture.common.block.component.FaceAttachingComponent;

public final class NordicChandelierLightBlock extends BaseBlockComponentHolder
{
    public static final VoxelShape SHAPE = box(1D, 0D, 1D, 15, 16D, 15D);
    public static final VoxelShape SHAPE_NORTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.NORTH);
    public static final VoxelShape SHAPE_EAST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.EAST);
    public static final VoxelShape SHAPE_SOUTH = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.SOUTH);
    public static final VoxelShape SHAPE_WEST = VoxelShapeHelper.rotateHorizontal(SHAPE, Direction.WEST);

    public NordicChandelierLightBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar)
    {
        registrar.register(BlockComponentTypes.WATERLOGGED);
        registrar.register(FaceAttachingComponent.COMPONENT_TYPE, component -> component.withPlacementType(FaceAttachingComponent.PlacementType.CEILING));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random)
    {
        var x = pos.getX() + .5D;
        var y = pos.getY() + .65D;
        var z = pos.getZ() + .5D;

        level.addParticle(ParticleTypes.FLAME, x + .25D, y, z + .25D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x + .25D, y, z + .25D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x - .25D, y, z + .25D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x - .25D, y, z + .25D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x + .25D, y, z - .25D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x + .25D, y, z - .25D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.FLAME, x - .25D, y, z - .25D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.SMOKE, x - .25D, y, z - .25D, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
       /* return switch(blockState.getValue(HorizontalFacingBlockComponent.FACING)) {
            default -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
        };*/
    }
}

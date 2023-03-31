package xyz.apex.minecraft.fantasyfurniture.bone.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

import java.util.function.Consumer;

public class BoneFloorLightBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, BoneVoxelShapes.FLOOR_LIGHT));

    public BoneFloorLightBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) return;

        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .85D;
        var z = (double) pos.getZ() + .5D;

        var offsetH = .25D;

        var facing = blockState.getOptionalValue(HorizontalFacingBlockComponent.FACING).map(Direction::getClockWise).orElse(Direction.NORTH);
        var stepX = facing.getStepX();
        var stepZ = facing.getStepZ();

        level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y + .1D, z, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}

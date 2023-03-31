package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrVoxelShapes;

import java.util.function.Consumer;

public class VenthyrCeilingLightBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, VenthyrVoxelShapes.CEILING_LIGHT));

    public VenthyrCeilingLightBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .6D;
        var z = (double) pos.getZ() + .5D;

        x -= .3D;
        y -= .05D;
        z -= .3D;

        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .6D, y, z, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z + .6D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .6D, y, z + .6D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x, y, z + .6D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y, z + .6D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);

        level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}

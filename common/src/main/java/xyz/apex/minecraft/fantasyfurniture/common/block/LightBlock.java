package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.common.util.function.Lazy;
import xyz.apex.minecraft.fantasyfurniture.common.init.BoneSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

import java.util.function.Supplier;

public class LightBlock extends Block
{
    protected final Supplier<ParticleOptions> flameParticleOptions;

    public LightBlock(Supplier<ParticleOptions> flameParticleOptions, Properties properties)
    {
        super(properties);

        this.flameParticleOptions = Lazy.of(flameParticleOptions);
    }

    public ParticleOptions getFlameParticle()
    {
        return flameParticleOptions.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .6D;
        var z = (double) pos.getZ() + .5D;

        var flameParticle = getFlameParticle();
        var offset = .25D;

        if(NordicSet.CHANDELIER.hasBlockState(blockState) || DunmerSet.CHANDELIER.hasBlockState(blockState))
        {
            y += DunmerSet.CHANDELIER.hasBlockState(blockState) ? .25D : 0D;

            for(var i = 0; i < 4; i++)
            {
                var xOff = (i < 2) ? -offset : offset;
                var zOff = (i == 0 || i == 3) ? -offset : offset;

                level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
                level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(VenthyrSet.CHANDELIER.hasBlockState(blockState))
        {
            x -= .3D;
            y -= .05D;
            z -= .3D;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .6D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z + .6D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .6D, y, z + .6D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z + .6D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y, z + .6D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
        }
        else if(BoneSet.Wither.CHANDELIER.hasBlockState(blockState) || BoneSet.Skeleton.CHANDELIER.hasBlockState(blockState))
        {
            y += .15D;

            level.addParticle(ParticleTypes.SMOKE, x + .4D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + .4D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x - .4D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x - .4D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y, z + .4D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z - .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y, z - .4D, 0D, 0D, 0D);
        }
    }
}

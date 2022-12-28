package xyz.apex.minecraft.fantasyfurniture.shared.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.shared.util.Lazy;

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

        for(var i = 0; i < 4; i++)
        {
            var xOff = (i < 2) ? -offset : offset;
            var zOff = (i == 0 || i == 3) ? -offset : offset;

            level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
        }
    }
}

package xyz.apex.minecraft.fantasyfurniture.shared.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

// Modified version of vanilla class
// to be more modder friendly
//
// Changed to take in lazily loaded ParticleOptions
// as custom particles are loaded later than blocks are
// and would be null by time this classes constructor is called
//
// Note: `flameParticle` from super type is null and now deprecated
//  Using this field **WILL** result in NPE's you have been warned
//  Make use of #getFlameParticle() instead
public class FloorLightBlock extends TorchBlock
{
    protected final Supplier<ParticleOptions> flameParticleOptions;

    public FloorLightBlock(Properties properties, Supplier<ParticleOptions> flameParticleOptions)
    {
        super(properties, null);

        this.flameParticleOptions = flameParticleOptions;
    }

    public ParticleOptions getFlameParticle()
    {
        return flameParticleOptions.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .7D;
        var z = (double) pos.getZ() + .5D;

        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        level.addParticle(getFlameParticle(), x, y, z, 0D, 0D, 0D);
    }
}

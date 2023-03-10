package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.VenthyrSet;

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
public class WallLightBlock extends WallTorchBlock
{
    protected final Supplier<ParticleOptions> flameParticleOptions;

    public WallLightBlock(Properties properties, Supplier<ParticleOptions> flameParticleOptions)
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

        var flameParticle = getFlameParticle();
        var facing = blockState.getValue(FACING);

        var stepX = facing.getStepX();
        var stepZ = facing.getStepZ();

        if(NordicSet.WALL_LIGHT.hasBlockState(blockState))
        {
            y -= .2D;

            var hStep = .12D;
            var vStep = .24D;

            x -= hStep * stepX;
            y += .2D + vStep;
            z -= hStep * stepZ;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y, z, 0D, 0D, 0D);
        }
        else if(VenthyrSet.WALL_LIGHT.hasBlockState(blockState))
        {
            x -= .25D * stepX;
            y += .1D;
            z -= .25D * stepZ;

            var clockWise = facing.getClockWise();

            for(var i = 0; i < 2; i++)
            {
                var xOff = (i == 0 ? -.125D : .125D) * clockWise.getStepX();
                var zOff = (i == 0 ? -.125D : .125D) * clockWise.getStepZ();

                level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
                level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
    }
}

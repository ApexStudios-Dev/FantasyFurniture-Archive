package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.LightComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Supplier;

public class CeilingLightBlock extends SimpleComponentBlock
{
    private final Supplier<ParticleOptions> flameParticle;
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public CeilingLightBlock(Supplier<ParticleOptions> flameParticle, Properties properties)
    {
        super(properties);

        this.flameParticle = flameParticle;
    }

    @Override
    public void registerComponents()
    {
        registerComponent(LightComponent.COMPONENT_TYPE)
                .setPlaceOnWalls(false)
                .setPlaceOnFloor(false)
                .setPlaceOnCeilings(true)
        ;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .6D;
        var z = (double) pos.getZ() + .5D;

        var offset = .25D;

        if(NordicSet.CEILING_LIGHT.hasBlockState(blockState) || DunmerSet.CEILING_LIGHT.hasBlockState(blockState))
        {
            y += DunmerSet.CEILING_LIGHT.hasBlockState(blockState) ? .25D : 0D;

            for(var i = 0; i < 4; i++)
            {
                var xOff = (i < 2) ? -offset : offset;
                var zOff = (i == 0 || i == 3) ? -offset : offset;

                level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
                level.addParticle(flameParticle.get(), x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(VenthyrSet.CEILING_LIGHT.hasBlockState(blockState))
        {
            x -= .3D;
            y -= .05D;
            z -= .3D;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .6D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .6D, y, z + .6D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .6D, y, z + .6D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z + .6D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z + .6D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .1D, y + .2D, z + .1D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .1D + .4D, y + .2D, z + .1D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .1D + .4D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .1D, y + .2D, z + .1D + .4D, 0D, 0D, 0D);
        }
        else if(BoneSet.Wither.CEILING_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.CEILING_LIGHT.hasBlockState(blockState))
        {
            y += .15D;

            level.addParticle(ParticleTypes.SMOKE, x + .4D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + .4D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x - .4D, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x - .4D, y, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z + .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z + .4D, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x, y, z - .4D, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z - .4D, 0D, 0D, 0D);
        }
        else if(NecrolordSet.CEILING_LIGHT.hasBlockState(blockState))
        {
            y += .05D;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z, 0D, 0D, 0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return shapeCacher.getSafe(blockState);
    }

    private VoxelShape getShape(BlockState blockState)
    {
        if(NordicSet.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Nordic.CEILING_LIGHT;
        else if(VenthyrSet.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Venthyr.CEILING_LIGHT;
        else if(DunmerSet.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Dunmer.CEILING_LIGHT;
        else if(BoneSet.Wither.CEILING_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Bone.CEILING_LIGHT;
        else if(NecrolordSet.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Necrolord.CEILING_LIGHT;
        else if(RoyalSet.CEILING_LIGHT.hasBlockState(blockState)) return AllVoxelShapes.Royal.CEILING_LIGHT;
        else return Shapes.block();
    }
}

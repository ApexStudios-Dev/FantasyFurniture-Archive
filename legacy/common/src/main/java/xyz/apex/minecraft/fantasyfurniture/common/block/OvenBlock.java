package xyz.apex.minecraft.fantasyfurniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.components.HorizontalFacingComponent;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.OvenComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public class OvenBlock extends SimpleComponentBlock
{
    public OvenBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(OvenComponent.COMPONENT_TYPE);
        registerComponent(ComponentTypes.BLOCK_ENTITY, AllBlockEntityTypes.OVEN);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random)
    {
        if(!blockState.getValue(OvenComponent.LIT)) return;

        var multiBlockComponent = getComponent(ComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return;

        if(NordicSet.OVEN.hasBlockState(blockState))
        {
            var x = pos.getX() + .5D;
            var y = pos.getY();
            var z = pos.getZ() + .5D;

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            var facing = blockState.getValue(HorizontalFacingComponent.FACING);
            var axis = facing.getAxis();
            var h = random.nextDouble() * .6D - .3D;
            var xOff = axis == Direction.Axis.X ? facing.getStepX() * .52D : h;
            var yOff = random.nextDouble() * 9D / 16D;
            var zOff = axis == Direction.Axis.Z ? facing.getStepZ() * .52D : h;

            level.addParticle(ParticleTypes.SMOKE, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
        }
        else if(VenthyrSet.OVEN.hasBlockState(blockState))
        {
            var x = (double) pos.getX() + .5D;
            var y = (double) pos.getY() + .4D;
            var z = (double) pos.getZ() + .5D;

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            if(random.nextInt(3) == 0)
            {
                for(var i = 0; i < random.nextInt(1) + 1; i++)
                {
                    level.addParticle(ParticleTypes.LAVA, x, y, z, random.nextFloat() / 16F, 5F, random.nextFloat() / 16F);
                }
            }
        }
        else if(DunmerSet.OVEN.hasBlockState(blockState))
        {
            var facing = blockState.getValue(HorizontalFacingComponent.FACING);

            var x = (double) pos.getX();
            var y = (double) pos.getY() + 1D;
            var z = (double) pos.getZ();

            if(facing == Direction.EAST)
            {
                x += .5D;
            }
            else if(facing == Direction.SOUTH)
            {
                x += 1D;
                z += .5D;
            }
            else if(facing == Direction.NORTH)
            {
                z += .5D;
            }
            else
            {
                x += .5D;
                z += 1D;
            }

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
        }
        else if(BoneSet.Wither.OVEN.hasBlockState(blockState) || BoneSet.Skeleton.OVEN.hasBlockState(blockState))
        {
            var x = (double) pos.getX() + .5D;
            var y = (double) pos.getY() + .25D;
            var z = (double) pos.getZ() + .5D;

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            if(random.nextInt(5) == 0)
            {
                var xOff = random.triangle(.2D, .45D);
                var yOff = random.triangle(.2D, .45D);
                var zOff = random.triangle(.2D, .45D);

                if(random.nextBoolean()) level.addParticle(ParticleTypes.SCULK_SOUL, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
                else level.addParticle(ParticleTypes.SCULK_SOUL, x - xOff, y + yOff, z - zOff, 0D, 0D, 0D);
            }

            for(var i = 0; i < random.nextInt(4) + 1; i++)
            {
                var xOff = random.triangle(.2D, .45D);
                var yOff = random.triangle(.2D, .45D);
                var zOff = random.triangle(.2D, .45D);

                if(random.nextBoolean()) level.addParticle(ParticleTypes.MYCELIUM, x - xOff, y + yOff, z - zOff, 0D, 0D, 0D);
                else level.addParticle(ParticleTypes.MYCELIUM, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(NecrolordSet.OVEN.hasBlockState(blockState))
        {
            var x = (double) pos.getX() + .5D;
            var y = (double) pos.getY() + .4D;
            var z = (double) pos.getZ() + .5D;

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            if(random.nextInt(3) == 0)
            {
                for(var i = 0; i < random.nextInt(1) + 1; i++)
                {
                    level.addParticle(ParticleTypes.LAVA, x, y, z, random.nextFloat() / 16F, 5F, random.nextFloat() / 16F);
                }
            }
        }
    }

    public static final class WithMultiBlock extends OvenBlock
    {
        public WithMultiBlock(Properties properties)
        {
            super(properties);
        }

        @Override
        public void registerComponents()
        {
            super.registerComponents();
            registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x1x2_FACING);
        }
    }
}

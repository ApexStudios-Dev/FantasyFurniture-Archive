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
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.LightComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Supplier;

public final class WallLightBlock extends SimpleComponentBlock
{
    private final Supplier<ParticleOptions> flameParticle;
    private final VoxelShapeCacher shapeCacher = new VoxelShapeCacher(this::getShape);

    public WallLightBlock(Supplier<ParticleOptions> flameParticle, Properties properties)
    {
        super(properties);

        this.flameParticle = flameParticle;
    }

    @Override
    public void registerComponents()
    {
       // registerComponent(ComponentTypes.HORIZONTAL_FACING).setGetFacingDirectionFunc(facing -> Direction.NORTH);
        registerComponent(LightComponent.COMPONENT_TYPE)
                .setPlaceOnWalls(true)
                .setPlaceOnFloor(false)
                .setPlaceOnCeilings(false)
        ;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .7D;
        var z = (double) pos.getZ() + .5D;

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);

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
            level.addParticle(flameParticle.get(), x, y, z, 0D, 0D, 0D);
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
                level.addParticle(flameParticle.get(), x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(BoneSet.Wither.WALL_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.WALL_LIGHT.hasBlockState(blockState))
        {
            y -= .2D;

            var hStep = .12D;
            var vStep = .24D;

            x -= hStep * stepX;
            y += .2D + vStep;
            z -= hStep * stepZ;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y, z, 0D, 0D, 0D);
        }
        else if(NecrolordSet.WALL_LIGHT.hasBlockState(blockState))
        {
            y -= .1D;

            var hStep = .12D;
            var vStep = .24D;

            x -= hStep * stepX;
            y += .2D + vStep;
            z -= hStep * stepZ;

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
        VoxelShape shape;

        if(NordicSet.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.WALL_LIGHT;
        else if(VenthyrSet.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.WALL_LIGHT;
        else if(DunmerSet.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.WALL_LIGHT;
        else if(BoneSet.Wither.WALL_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.WALL_LIGHT;
        else if(NecrolordSet.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.WALL_LIGHT;
        else if(RoyalSet.WALL_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.WALL_LIGHT;
        else return Shapes.block();

        var facing = blockState.getValue(HorizontalFacingComponent.FACING);
        return VoxelShapeHelper.rotateHorizontal(shape, facing);
    }
}

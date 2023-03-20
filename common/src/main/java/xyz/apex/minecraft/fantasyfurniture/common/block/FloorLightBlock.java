package xyz.apex.minecraft.fantasyfurniture.common.block;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.ComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.SimpleComponentBlock;
import xyz.apex.minecraft.apexcore.common.component.types.HorizontalFacingComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeHelper;
import xyz.apex.minecraft.fantasyfurniture.common.block.components.LightComponent;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

import java.util.function.Supplier;

public class FloorLightBlock extends SimpleComponentBlock
{
    private final Supplier<ParticleOptions> flameParticle;

    public FloorLightBlock(Supplier<ParticleOptions> flameParticle, Properties properties)
    {
        super(properties);

        this.flameParticle = Suppliers.memoize(flameParticle::get);
    }

    @Override
    public void registerComponents()
    {
        registerComponent(ComponentTypes.MULTI_BLOCK, AllMultiBlockTypes.MB_1x2x1);
        registerComponent(ComponentTypes.HORIZONTAL_FACING);

        registerComponent(LightComponent.COMPONENT_TYPE)
                .setPlaceOnWalls(false)
                .setPlaceOnFloor(true)
                .setPlaceOnCeilings(false)
        ;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) return;

        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .85D;
        var z = (double) pos.getZ() + .5D;

        if(NordicSet.FLOOR_LIGHT.hasBlockState(blockState))
        {
            var offset = .25D;

            for(var i = 0; i < 4; i++)
            {
                // offset x for odds only, negate if index is 1
                // offset z for events only, negate if index is 2
                var xOff = (i % 2 == 1) ? (i == 1) ? -offset : offset : 0D;
                var zOff = (i % 2 == 0) ? (i == 2) ? -offset : offset : 0D;

                level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
                level.addParticle(flameParticle.get(), x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(VenthyrSet.FLOOR_LIGHT.hasBlockState(blockState))
        {
            x -= .225D;
            y += .1D;
            z -= .225D;

            for(var i = 0; i < 4; i++)
            {
                var xOff = (i == 1 || i == 3) ? .4D : 0D;
                var zOff = (i == 2 || i == 3) ? .4D : 0D;

                level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
                level.addParticle(flameParticle.get(), x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(DunmerSet.FLOOR_LIGHT.hasBlockState(blockState))
        {
            level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y + .1D, z, 0D, 0D, 0D);
        }
        else if(BoneSet.Wither.FLOOR_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.FLOOR_LIGHT.hasBlockState(blockState))
        {
            var offsetH = .25D;

            var facing = blockState.getOptionalValue(HorizontalFacingComponent.FACING).map(Direction::getClockWise).orElse(Direction.NORTH);
            var stepX = facing.getStepX();
            var stepZ = facing.getStepZ();

            level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y + .1D, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
        }
        else if(NecrolordSet.FLOOR_LIGHT.hasBlockState(blockState))
        {
            var offsetH = .3D;

            var facing = blockState.getOptionalValue(HorizontalFacingComponent.FACING).map(Direction::getClockWise).orElse(Direction.NORTH);
            var stepX = facing.getStepX();
            var stepZ = facing.getStepZ();

            level.addParticle(ParticleTypes.SMOKE, x, y + .2D, z, 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x, y + .2D, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + (stepX * offsetH), y + .1D, z + (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x + (stepX * offsetH), y + .1D, z + (stepZ * offsetH), 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x - (stepX * offsetH), y + .1D, z - (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle.get(), x - (stepX * offsetH), y + .1D, z - (stepZ * offsetH), 0D, 0D, 0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape;

        if(NordicSet.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Nordic.FLOOR_LIGHT;
        else if(VenthyrSet.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Venthyr.FLOOR_LIGHT;
        else if(DunmerSet.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Dunmer.FLOOR_LIGHT;
        else if(BoneSet.Wither.FLOOR_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Bone.FLOOR_LIGHT;
        else if(NecrolordSet.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Necrolord.FLOOR_LIGHT;
        else if(RoyalSet.FLOOR_LIGHT.hasBlockState(blockState)) shape = AllVoxelShapes.Royal.FLOOR_LIGHT;
        else return super.getShape(blockState, level, pos, context);

        if(!getRequiredComponent(ComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) shape = shape.move(0D, -1D, 0D);

        if(blockState.hasProperty(HorizontalFacingComponent.FACING))
        {
            var facing = blockState.getValue(HorizontalFacingComponent.FACING);
            return VoxelShapeHelper.rotateHorizontal(shape, facing);
        }

        return shape;
    }
}

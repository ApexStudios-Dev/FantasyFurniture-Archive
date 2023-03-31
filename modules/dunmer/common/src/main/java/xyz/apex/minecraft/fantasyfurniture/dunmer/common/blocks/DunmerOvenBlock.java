package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.component.block.types.HorizontalFacingBlockComponent;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity.DunmerOvenBlockEntity;

import java.util.function.Consumer;

public final class DunmerOvenBlock extends OvenBlock<DunmerOvenBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, DunmerVoxelShapes.OVEN));

    public DunmerOvenBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<DunmerOvenBlockEntity> getBlockEntityType()
    {
        return DunmerFurnitureSet.BlockEntityTypes.OVEN.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(!blockState.getValue(LIT)) return;

        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return;

        var facing = blockState.getValue(HorizontalFacingBlockComponent.FACING);

        var x = (double) pos.getX();
        var y = (double) pos.getY() + 1D;
        var z = (double) pos.getZ();

        if(facing == Direction.EAST) x += .5D;
        else if(facing == Direction.SOUTH)
        {
            x += 1D;
            z += .5D;
        }
        else if(facing == Direction.NORTH) z += .5D;
        else
        {
            x += .5D;
            z += 1D;
        }

        if(rng.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}

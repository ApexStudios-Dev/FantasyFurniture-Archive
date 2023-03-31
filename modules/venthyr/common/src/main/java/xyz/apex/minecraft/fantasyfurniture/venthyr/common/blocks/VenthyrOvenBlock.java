package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks;

import net.minecraft.core.BlockPos;
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
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity.VenthyrOvenBlockEntity;

import java.util.function.Consumer;

public final class VenthyrOvenBlock extends OvenBlock<VenthyrOvenBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, VenthyrVoxelShapes.OVEN));

    public VenthyrOvenBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<VenthyrOvenBlockEntity> getBlockEntityType()
    {
        return VenthyrFurnitureSet.BlockEntityTypes.OVEN.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(!blockState.getValue(LIT)) return;

        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return;

        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .4D;
        var z = (double) pos.getZ() + .5D;

        if(rng.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        if(rng.nextInt(3) == 0)
        {
            for(var i = 0; i < rng.nextInt(1) + 1; i++)
            {
                level.addParticle(ParticleTypes.LAVA, x, y, z, rng.nextFloat() / 16F, 5F, rng.nextFloat() / 16F);
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}

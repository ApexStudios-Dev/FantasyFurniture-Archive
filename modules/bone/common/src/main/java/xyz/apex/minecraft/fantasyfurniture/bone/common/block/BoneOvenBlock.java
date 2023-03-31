package xyz.apex.minecraft.fantasyfurniture.bone.common.block;

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
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneVoxelShapes;
import xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity.BoneOvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.common.block.OvenBlock;

import java.util.function.Consumer;

public final class BoneOvenBlock extends OvenBlock<BoneOvenBlockEntity>
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, BoneVoxelShapes.OVEN));

    public BoneOvenBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    protected BlockEntityType<BoneOvenBlockEntity> getBlockEntityType()
    {
        return BoneFurnitureSet.BlockEntityTypes.OVEN.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(!blockState.getValue(LIT)) return;

        var multiBlockComponent = getComponent(BlockComponentTypes.MULTI_BLOCK);
        if(multiBlockComponent != null && !multiBlockComponent.getMultiBlockType().isOrigin(blockState)) return;

        var x = pos.getX() + .5D;
        var y = pos.getY() + .25D;
        var z = pos.getZ() + .5D;

        if(rng.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

        if(rng.nextInt(5) == 0)
        {
            var xOff = rng.triangle(.2D, .45D);
            var yOff = rng.triangle(.2D, .45D);
            var zOff = rng.triangle(.2D, .45D);

            if(rng.nextBoolean()) level.addParticle(ParticleTypes.SCULK_SOUL, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
            else level.addParticle(ParticleTypes.SCULK_SOUL, x - xOff, y + yOff, z - zOff, 0D, 0D, 0D);
        }

        for(var i = 0; i < rng.nextInt(4) + 1; i++)
        {
            var xOff = rng.triangle(.2D, .45D);
            var yOff = rng.triangle(.2D, .45D);
            var zOff = rng.triangle(.2D, .45D);

            if(rng.nextBoolean()) level.addParticle(ParticleTypes.MYCELIUM, x - xOff, y + yOff, z - zOff, 0D, 0D, 0D);
            else level.addParticle(ParticleTypes.MYCELIUM, x + xOff, y + yOff, z + zOff, 0D, 0D, 0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}

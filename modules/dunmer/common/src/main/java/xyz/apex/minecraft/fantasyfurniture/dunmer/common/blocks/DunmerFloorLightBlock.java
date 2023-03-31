package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.apexcore.common.component.block.BaseBlockComponentHolder;
import xyz.apex.minecraft.apexcore.common.component.block.BlockComponentTypes;
import xyz.apex.minecraft.apexcore.common.util.VoxelShapeCacher;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerVoxelShapes;

import java.util.function.Consumer;

public class DunmerFloorLightBlock extends BaseBlockComponentHolder
{
    private static final VoxelShapeCacher SHAPE_CACHE = new VoxelShapeCacher(blockState -> FantasyFurniture.getCorrectedShape(blockState, DunmerVoxelShapes.FLOOR_LIGHT));

    public DunmerFloorLightBlock(Consumer<Registrar> registrarConsumer, Properties properties)
    {
        super(registrarConsumer, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(getRequiredComponent(BlockComponentTypes.MULTI_BLOCK).getMultiBlockType().isOrigin(blockState)) return;

        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .85D;
        var z = (double) pos.getZ() + .5D;

        level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
        level.addParticle(ParticleTypes.FLAME, x, y + .1D, z, 0D, 0D, 0D);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        return SHAPE_CACHE.getSafe(blockState);
    }
}
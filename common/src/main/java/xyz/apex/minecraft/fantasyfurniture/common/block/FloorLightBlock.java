package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;

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
public class FloorLightBlock extends TorchBlock implements MultiBlock
{
    protected final Supplier<ParticleOptions> flameParticleOptions;
    protected final MultiBlockType multiBlockType;

    public FloorLightBlock(MultiBlockType multiBlockType, Properties properties, Supplier<ParticleOptions> flameParticleOptions)
    {
        super(properties, null);

        this.flameParticleOptions = flameParticleOptions;
        this.multiBlockType = multiBlockType;
        SimpleMultiBlock.replaceBlockStateContainer(this);
    }

    public ParticleOptions getFlameParticle()
    {
        return flameParticleOptions.get();
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource rng)
    {
        if(multiBlockType.isOrigin(blockState)) return;

        var x = (double) pos.getX() + .5D;
        var y = (double) pos.getY() + .85D;
        var z = (double) pos.getZ() + .5D;

        var flameParticle = getFlameParticle();
        var offset = .25D;

        for(var i = 0; i < 4; i++)
        {
            // offset x for odds only, negate if index is 1
            // offset z for events only, negate if index is 2
            var xOff = (i % 2 == 1) ? (i == 1) ? -offset : offset : 0D;
            var zOff = (i % 2 == 0) ? (i == 2) ? -offset : offset : 0D;

            level.addParticle(ParticleTypes.SMOKE, x + xOff, y, z + zOff, 0D, 0D, 0D);
            level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
        }

        /*// back
        level.addParticle(ParticleTypes.SMOKE, x, y, z - .25D, 0D, 0D, 0D);
        level.addParticle(flameParticle, x, y, z - .25D, 0D, 0D, 0D);

        // front
        level.addParticle(ParticleTypes.SMOKE, x, y, z + .25D, 0D, 0D, 0D);
        level.addParticle(flameParticle, x, y, z + .25D, 0D, 0D, 0D);

        // left
        level.addParticle(ParticleTypes.SMOKE, x - .25D, y, z, 0D, 0D, 0D);
        level.addParticle(flameParticle, x - .25D, y, z, 0D, 0D, 0D);

        // right
        level.addParticle(ParticleTypes.SMOKE, x + .25D, y, z, 0D, 0D, 0D);
        level.addParticle(flameParticle, x + .25D, y, z, 0D, 0D, 0D);*/
    }

    @Override
    public boolean isSameBlockTypeForMultiBlock(BlockState blockState)
    {
        return blockState.is(this);
    }

    @Override
    public final MultiBlockType getMultiBlockType()
    {
        return multiBlockType;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        return multiBlockType.getStateForPlacement(this, defaultBlockState(), ctx);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
    {
        if(!multiBlockType.isOrigin(blockState)) return multiBlockType.canSurvive(this, level, pos, blockState);
        return super.canSurvive(blockState, level, pos);
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos pos, BlockState oldBlockState, boolean isMoving)
    {
        multiBlockType.onPlace(this, blockState, level, pos, oldBlockState);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos pos, BlockState newBlockstate, boolean isMoving)
    {
        multiBlockType.onRemove(this, blockState, level, pos, newBlockstate);
        super.onRemove(blockState, level, pos, newBlockstate, isMoving);
    }

    @SuppressWarnings("ConstantValue")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        if(multiBlockType != null) multiBlockType.registerBlockProperty(builder::add);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState)
    {
        return multiBlockType.isOrigin(blockState) ? RenderShape.MODEL : RenderShape.INVISIBLE;
    }
}
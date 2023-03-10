package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.common.init.BoneSet;
import xyz.apex.minecraft.fantasyfurniture.common.init.DunmerSet;
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
                level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
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
                level.addParticle(flameParticle, x + xOff, y, z + zOff, 0D, 0D, 0D);
            }
        }
        else if(DunmerSet.FLOOR_LIGHT.hasBlockState(blockState))
        {
            level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y + .1D, z, 0D, 0D, 0D);
        }
        else if(BoneSet.Wither.FLOOR_LIGHT.hasBlockState(blockState) || BoneSet.Skeleton.FLOOR_LIGHT.hasBlockState(blockState))
        {
            var offsetH = .25D;

            var facing = blockState.getOptionalValue(WithHorizontalFacing.FACING).map(Direction::getClockWise).orElse(Direction.NORTH);
            var stepX = facing.getStepX();
            var stepZ = facing.getStepZ();

            level.addParticle(ParticleTypes.SMOKE, x, y + .1D, z, 0D, 0D, 0D);
            level.addParticle(flameParticle, x, y + .1D, z, 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle, x + (stepX * offsetH), y + .05D, z + (stepZ * offsetH), 0D, 0D, 0D);

            level.addParticle(ParticleTypes.SMOKE, x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
            level.addParticle(flameParticle, x - (stepX * offsetH), y + .05D, z - (stepZ * offsetH), 0D, 0D, 0D);
        }
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

    public static class WithHorizontalFacing extends FloorLightBlock
    {
        public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

        public WithHorizontalFacing(MultiBlockType multiBlockType, Properties properties, Supplier<ParticleOptions> flameParticleOptions)
        {
            super(multiBlockType, properties, flameParticleOptions);

            registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext ctx)
        {
            var defaultBlockState = defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
            return multiBlockType.getStateForPlacement(this, defaultBlockState, ctx);
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
        {
            super.createBlockStateDefinition(builder.add(FACING));
        }
    }
}

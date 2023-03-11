package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlock;
import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.multiblock.SimpleMultiBlock;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.OvenBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.*;

public class OvenBlock extends AbstractFurnaceBlock
{
    public OvenBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player)
    {
        AllBlockEntityTypes.OVEN.getOptional(level, pos).ifPresent(player::openMenu);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
    {
        return AllBlockEntityTypes.OVEN.create(pos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType)
    {
        return createFurnaceTicker(level, blockEntityType, AllBlockEntityTypes.OVEN.get());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random)
    {
        if(!blockState.getValue(LIT)) return;
        if(this instanceof MultiBlock multiBlock && !multiBlock.getMultiBlockType().isOrigin(blockState)) return;

        if(NordicSet.OVEN.hasBlockState(blockState))
        {
            var x = pos.getX() + .5D;
            var y = pos.getY();
            var z = pos.getZ() + .5D;

            if(random.nextDouble() < .1D) level.playLocalSound(x, y, z, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1F, 1F, false);

            var facing = blockState.getValue(FACING);
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
            var facing = blockState.getValue(FACING);

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

    public static class AsMultiBlock extends OvenBlock implements MultiBlock
    {
        protected final MultiBlockType multiBlockType;

        public AsMultiBlock(MultiBlockType multiBlockType, Properties properties)
        {
            super(properties);

            this.multiBlockType = multiBlockType;
            SimpleMultiBlock.replaceBlockStateContainer(this); // must be after we set the MultiBlockType field
            registerDefaultState(multiBlockType.registerDefaultBlockState(defaultBlockState()
                    // need to re-apply the initial default values
                    // as replacing the state container wiped them out
                    .setValue(FACING, Direction.NORTH).setValue(LIT, false)
            ));
        }

        @Override
        public final MultiBlockType getMultiBlockType()
        {
            return multiBlockType;
        }

        @Override
        public boolean isSameBlockTypeForMultiBlock(BlockState blockState)
        {
            return blockState.is(this);
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext ctx)
        {
            var stateForPlacement = super.getStateForPlacement(ctx);
            if(stateForPlacement == null) return null;
            return multiBlockType.getStateForPlacement(this, stateForPlacement, ctx);
        }

        @Override
        public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos pos)
        {
            return multiBlockType.canSurvive(this, level, pos, blockState);
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
        public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
        {
            // null on first call, as it's set in constructor and this method is called from super
            // none-null on second call, as that's fired in our constructor and replaces the vanilla state definition
            if(multiBlockType != null) multiBlockType.registerBlockProperty(builder::add);
            super.createBlockStateDefinition(builder);
        }

        @Override
        public RenderShape getRenderShape(BlockState blockState)
        {
            return multiBlockType.isOrigin(blockState) ? RenderShape.MODEL : RenderShape.INVISIBLE;
        }

        @Override
        protected void openContainer(Level level, BlockPos pos, Player player)
        {
            var blockState = level.getBlockState(pos);
            if(!isSameBlockTypeForMultiBlock(blockState)) return;

            if(!multiBlockType.isOrigin(blockState))
            {
                var originPos = multiBlockType.getOriginPos(this, blockState, pos);
                openContainer(level, originPos, player);
            }
            else super.openContainer(level, pos, player);
        }

        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState)
        {
            return multiBlockType.isOrigin(blockState) ? super.newBlockEntity(pos, blockState) : new OvenBlockEntity.Delegate(AllBlockEntityTypes.OVEN.get(), pos, blockState);
        }

        @Nullable
        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType)
        {
            return multiBlockType.isOrigin(blockState) ? super.getTicker(level, blockState, blockEntityType) : null;
        }
    }
}

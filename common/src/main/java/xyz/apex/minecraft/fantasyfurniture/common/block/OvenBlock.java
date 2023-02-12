package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.NordicSet;

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
    }
}

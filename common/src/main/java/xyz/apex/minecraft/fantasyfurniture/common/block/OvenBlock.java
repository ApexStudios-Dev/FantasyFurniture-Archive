package xyz.apex.minecraft.fantasyfurniture.common.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;

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
}

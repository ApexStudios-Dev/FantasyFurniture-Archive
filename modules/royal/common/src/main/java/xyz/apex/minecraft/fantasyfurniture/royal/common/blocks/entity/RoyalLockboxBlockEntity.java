package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

import java.util.function.Consumer;

public final class RoyalLockboxBlockEntity extends LockboxBlockEntity
{
    public RoyalLockboxBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, RoyalFurnitureSet.BlockEntityTypes.LOCKBOX.get(), pos, blockState);
    }
}
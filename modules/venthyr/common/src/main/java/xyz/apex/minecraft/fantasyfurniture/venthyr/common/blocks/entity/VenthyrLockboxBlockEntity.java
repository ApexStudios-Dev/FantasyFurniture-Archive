package xyz.apex.minecraft.fantasyfurniture.venthyr.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;

import java.util.function.Consumer;

public final class VenthyrLockboxBlockEntity extends LockboxBlockEntity
{
    public VenthyrLockboxBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, VenthyrFurnitureSet.BlockEntityTypes.LOCKBOX.get(), pos, blockState);
    }
}

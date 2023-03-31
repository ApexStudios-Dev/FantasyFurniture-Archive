package xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;

import java.util.function.Consumer;

public final class BoneCounterBlockEntity extends CounterBlockEntity
{
    public BoneCounterBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, BoneFurnitureSet.BlockEntityTypes.COUNTER.get(), pos, blockState);
    }
}

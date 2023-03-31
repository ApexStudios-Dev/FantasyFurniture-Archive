package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

import java.util.function.Consumer;

public final class NordicCounterBlockEntity extends CounterBlockEntity
{
    public NordicCounterBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, NordicFurnitureSet.BlockEntityTypes.COUNTER.get(), pos, blockState);
    }
}

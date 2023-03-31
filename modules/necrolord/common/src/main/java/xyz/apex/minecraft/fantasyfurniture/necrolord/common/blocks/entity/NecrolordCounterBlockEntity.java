package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.CounterBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

import java.util.function.Consumer;

public final class NecrolordCounterBlockEntity extends CounterBlockEntity
{
    public NecrolordCounterBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, NecrolordFurnitureSet.BlockEntityTypes.COUNTER.get(), pos, blockState);
    }
}

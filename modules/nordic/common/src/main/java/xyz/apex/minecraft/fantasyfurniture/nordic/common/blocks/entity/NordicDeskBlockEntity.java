package xyz.apex.minecraft.fantasyfurniture.nordic.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DeskBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.nordic.common.NordicFurnitureSet;

import java.util.function.Consumer;

public final class NordicDeskBlockEntity extends DeskBlockEntity
{
    public NordicDeskBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, NordicFurnitureSet.BlockEntityTypes.DESK.get(), pos, blockState);
    }
}

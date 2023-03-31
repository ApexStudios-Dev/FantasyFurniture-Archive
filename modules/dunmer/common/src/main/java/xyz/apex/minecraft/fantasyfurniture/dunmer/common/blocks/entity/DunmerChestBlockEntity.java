package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.ChestBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

import java.util.function.Consumer;

public final class DunmerChestBlockEntity extends ChestBlockEntity
{
    public DunmerChestBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, DunmerFurnitureSet.BlockEntityTypes.CHEST.get(), pos, blockState);
    }
}

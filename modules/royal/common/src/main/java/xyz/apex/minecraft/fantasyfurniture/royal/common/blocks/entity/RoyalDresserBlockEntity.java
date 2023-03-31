package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

import java.util.function.Consumer;

public final class RoyalDresserBlockEntity extends DresserBlockEntity
{
    public RoyalDresserBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, RoyalFurnitureSet.BlockEntityTypes.DRESSER.get(), pos, blockState);
    }
}

package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DyeableBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

import java.util.function.Consumer;

public final class RoyalDyeableBlockEntity extends DyeableBlockEntity
{
    public RoyalDyeableBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, RoyalFurnitureSet.BlockEntityTypes.DYEABLE.get(), pos, blockState);
    }
}

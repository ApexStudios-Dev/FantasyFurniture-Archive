package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

import java.util.function.Consumer;

public final class RoyalDrawerBlockEntity extends DrawerBlockEntity
{
    public RoyalDrawerBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, RoyalFurnitureSet.BlockEntityTypes.DRAWER.get(), pos, blockState);
    }
}

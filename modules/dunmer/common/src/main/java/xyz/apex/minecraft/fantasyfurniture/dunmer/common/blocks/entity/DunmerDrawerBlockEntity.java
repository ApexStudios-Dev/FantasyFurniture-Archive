package xyz.apex.minecraft.fantasyfurniture.dunmer.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.dunmer.common.DunmerFurnitureSet;

import java.util.function.Consumer;

public final class DunmerDrawerBlockEntity extends DrawerBlockEntity
{
    public DunmerDrawerBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, DunmerFurnitureSet.BlockEntityTypes.DRAWER.get(), pos, blockState);
    }
}

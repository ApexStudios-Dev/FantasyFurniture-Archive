package xyz.apex.minecraft.fantasyfurniture.bone.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.bone.common.BoneFurnitureSet;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;

import java.util.function.Consumer;

public final class BoneDresserBlockEntity extends DresserBlockEntity
{
    public BoneDresserBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, BoneFurnitureSet.BlockEntityTypes.DRESSER.get(), pos, blockState);
    }
}

package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.WardrobeBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

import java.util.function.Consumer;

public final class NecrolordWardrobeBlockEntity extends WardrobeBlockEntity
{
    public NecrolordWardrobeBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, NecrolordFurnitureSet.BlockEntityTypes.WARDROBE.get(), pos, blockState);
    }
}

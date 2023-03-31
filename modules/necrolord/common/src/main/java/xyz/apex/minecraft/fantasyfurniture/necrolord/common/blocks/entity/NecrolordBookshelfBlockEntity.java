package xyz.apex.minecraft.fantasyfurniture.necrolord.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;

import java.util.function.Consumer;

public final class NecrolordBookshelfBlockEntity extends BookshelfBlockEntity
{
    public NecrolordBookshelfBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, NecrolordFurnitureSet.BlockEntityTypes.BOOKSHELF.get(), pos, blockState);
    }
}

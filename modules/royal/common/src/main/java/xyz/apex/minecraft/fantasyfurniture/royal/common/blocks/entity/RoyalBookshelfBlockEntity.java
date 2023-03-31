package xyz.apex.minecraft.fantasyfurniture.royal.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.royal.common.RoyalFurnitureSet;

import java.util.function.Consumer;

public final class RoyalBookshelfBlockEntity extends BookshelfBlockEntity
{
    public RoyalBookshelfBlockEntity(Consumer<Registrar> registrarConsumer, BlockPos pos, BlockState blockState)
    {
        super(registrarConsumer, RoyalFurnitureSet.BlockEntityTypes.BOOKSHELF.get(), pos, blockState);
    }
}

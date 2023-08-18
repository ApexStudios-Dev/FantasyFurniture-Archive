package xyz.apex.minecraft.fantasyfurniture.nordic.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LargeContainerBlockEntity;

public final class BookshelfBlockEntity extends LargeContainerBlockEntity
{
    public BookshelfBlockEntity(BlockEntityType<? extends BookshelfBlockEntity> blockEntityType, BlockPos pos, BlockState blockState)
    {
        super(blockEntityType, pos, blockState);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack, @Nullable Direction side)
    {
        return stack.is(ItemTags.BOOKSHELF_BOOKS);
    }
}

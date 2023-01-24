package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.BookshelfMenu;

public final class BookshelfBlock extends HorizontalInventoryBlock.AsMultiBlock<BookshelfBlockEntity, BookshelfMenu>
{
    public BookshelfBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public BlockEntityEntry<BookshelfBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.BOOKSHELF;
    }

    @Override
    public MenuEntry<BookshelfMenu> getInventoryMenuType()
    {
        return AllMenuTypes.BOOKSHELF;
    }
}

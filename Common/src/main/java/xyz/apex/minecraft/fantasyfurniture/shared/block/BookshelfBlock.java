package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.BookshelfMenu;

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

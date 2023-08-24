package xyz.apex.minecraft.fantasyfurniture.common.block.entity.component;

import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BaseContainerBlockEntityComponent;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentHolder;
import xyz.apex.minecraft.apexcore.common.lib.component.block.entity.BlockEntityComponentType;
import xyz.apex.minecraft.fantasyfurniture.common.FantasyFurniture;

public final class BookshelfInventoryBlockEntityComponent extends BaseContainerBlockEntityComponent<BookshelfInventoryBlockEntityComponent>
{
    public static final BlockEntityComponentType<BookshelfInventoryBlockEntityComponent> COMPONENT_TYPE = BlockEntityComponentType.register(FantasyFurniture.ID, "inventory/bookshelf", BookshelfInventoryBlockEntityComponent::new);

    private BookshelfInventoryBlockEntityComponent(BlockEntityComponentHolder componentHolder)
    {
        super(componentHolder);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack)
    {
        return stack.is(ItemTags.BOOKSHELF_BOOKS);
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side)
    {
        return stack.is(ItemTags.BOOKSHELF_BOOKS);
    }
}

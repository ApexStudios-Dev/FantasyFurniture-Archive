package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.LockboxMenu;

public final class LockboxBlock extends HorizontalInventoryBlock<LockboxBlockEntity, LockboxMenu>
{
    public LockboxBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockEntityEntry<LockboxBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.LOCKBOX;
    }

    @Override
    public MenuEntry<LockboxMenu> getInventoryMenuType()
    {
        return AllMenuTypes.LOCKBOX;
    }
}

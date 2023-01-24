package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.LockboxBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.LockboxMenu;

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

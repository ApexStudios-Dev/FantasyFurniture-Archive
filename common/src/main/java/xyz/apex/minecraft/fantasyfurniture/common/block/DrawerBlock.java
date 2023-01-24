package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.DrawerMenu;

public final class DrawerBlock extends HorizontalInventoryBlock<DrawerBlockEntity, DrawerMenu>
{
    public DrawerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockEntityEntry<DrawerBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.DRAWER;
    }

    @Override
    public MenuEntry<DrawerMenu> getInventoryMenuType()
    {
        return AllMenuTypes.DRAWER;
    }
}

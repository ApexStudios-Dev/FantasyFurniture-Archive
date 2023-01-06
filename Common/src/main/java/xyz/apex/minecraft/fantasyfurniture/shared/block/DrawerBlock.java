package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.DrawerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.DrawerMenu;

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

package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DeskBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.DeskMenu;

public final class DeskBlock extends HorizontalInventoryBlock.AsMultiBlock<DeskBlockEntity, DeskMenu>
{
    public DeskBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public BlockEntityEntry<DeskBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.DESK;
    }

    @Override
    public MenuEntry<DeskMenu> getInventoryMenuType()
    {
        return AllMenuTypes.DESK;
    }
}

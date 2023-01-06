package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.DeskBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.DeskMenu;

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

package xyz.apex.minecraft.fantasyfurniture.common.block;

import xyz.apex.minecraft.apexcore.common.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.common.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.common.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.common.block.entity.DresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.common.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.common.menu.DresserMenu;

public final class DresserBlock extends HorizontalInventoryBlock.AsMultiBlock<DresserBlockEntity, DresserMenu>
{
    public DresserBlock(MultiBlockType multiBlockType, Properties properties)
    {
        super(multiBlockType, properties);
    }

    @Override
    public BlockEntityEntry<DresserBlockEntity> getInventoryBlockEntityType()
    {
        return AllBlockEntityTypes.DRESSER;
    }

    @Override
    public MenuEntry<DresserMenu> getInventoryMenuType()
    {
        return AllMenuTypes.DRESSER;
    }
}

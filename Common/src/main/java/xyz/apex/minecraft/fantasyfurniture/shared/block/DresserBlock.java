package xyz.apex.minecraft.fantasyfurniture.shared.block;

import xyz.apex.minecraft.apexcore.shared.multiblock.MultiBlockType;
import xyz.apex.minecraft.apexcore.shared.registry.entry.BlockEntityEntry;
import xyz.apex.minecraft.apexcore.shared.registry.entry.MenuEntry;
import xyz.apex.minecraft.fantasyfurniture.shared.block.entity.DresserBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllBlockEntityTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.init.AllMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.shared.menu.DresserMenu;

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
